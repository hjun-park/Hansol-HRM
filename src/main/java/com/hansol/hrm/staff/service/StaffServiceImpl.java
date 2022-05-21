package com.hansol.hrm.staff.service;

import static com.hansol.hrm.global.response.BaseResponseStatus.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hansol.hrm.global.exception.BaseException;
import com.hansol.hrm.position.service.PositionService;
import com.hansol.hrm.staff.domain.Staff;
import com.hansol.hrm.staff.domain.StaffMapper;
import com.hansol.hrm.staff.domain.StaffSearch;
import com.hansol.hrm.staff.dto.StaffDto;
import com.hansol.hrm.staff.dto.StaffRes;
import com.hansol.hrm.task.service.TaskService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional(readOnly = true)
public class StaffServiceImpl implements StaffService {

	private final TaskService taskService;
	private final PositionService positionService;
	private final StaffMapper staffMapper;

	public StaffServiceImpl(TaskService taskService, PositionService positionService,
		StaffMapper staffMapper) {
		this.taskService = taskService;
		this.positionService = positionService;
		this.staffMapper = staffMapper;
	}

	@Override
	public List<StaffRes> findStaffs() {
		Optional<List<Staff>> optionalStaffEntities = Optional.ofNullable(staffMapper.findAll());
		List<Staff> staffList = optionalStaffEntities.orElseGet(Collections::emptyList);

		return staffList.stream()
			.map(this::convertEntityToDto)
			.collect(Collectors.toList());

	}

	@Override
	public StaffRes findStaffById(Long staffId) {

		Optional<Staff> findEntity = Optional.ofNullable(staffMapper.findById(staffId));
		Staff staff = findEntity.orElseThrow(() -> new BaseException(STAFF_EMPTY));

		return convertEntityToDto(staff);
	}

	@Override
	public List<StaffRes> findStaffsByName(String name) {

		Optional<List<Staff>> findEntities = Optional.ofNullable(staffMapper.findByName(name));
		List<Staff> staffList = findEntities.orElseGet(Collections::emptyList);

		return staffList.stream()
			.map(this::convertEntityToDto)
			.collect(Collectors.toList());
	}

	@Override
	@Transactional
	public Long addStaff(StaffDto staffDto) {
		Staff staffEntity = Staff.convertDtoToEntity(staffDto);
		Long result = staffMapper.insertOne(staffEntity);

		if (!result.equals(0L)) {
			return staffEntity.getId();
		} else {
			throw new BaseException(INSERT_ERROR);
		}
	}

	@Override
	@Transactional
	public Long editStaff(Long staffId, StaffDto staffDto) {
		Long result = staffMapper.updateOne(staffId, Staff.convertDtoToEntity(staffDto));

		if (!result.equals(0L)) {
			return result;
		} else {
			throw new BaseException(UPDATE_ERROR);
		}
	}

	@Override
	@Transactional
	public Long statusToDeleteStaff(Long staffId) {
		Long result = staffMapper.statusToDelete(staffId);

		if (!result.equals(0L)) {
			return result;
		} else {
			throw new BaseException(DELETE_ERROR);
		}

	}

	@Override
	public List<StaffSearch> filterStaff(Long companyId, Long taskId, Long positionId) {

		Optional<List<StaffSearch>> staffSearches = Optional.ofNullable(staffMapper.filterStaff(companyId, taskId, positionId));
		return staffSearches.orElseGet(Collections::emptyList);

	}

	@Override
	public List<StaffRes> searchKeyword(String keyword) {
		Optional<List<Staff>> optionalStaffList = Optional.ofNullable(staffMapper.searchKeyword(keyword));
		List<Staff> staffList = optionalStaffList.orElseGet(Collections::emptyList);

		return staffList.stream()
			.map(this::convertEntityToDto)
			.collect(Collectors.toList());
	}

	private StaffRes convertEntityToDto(Staff staff) {

		String task = taskService.findTaskBy(staff.getTaskId()).getName();
		String position = positionService.findPositionBy(staff.getPositionId()).getName();

		return StaffRes.builder()
			.id(staff.getId())
			.name(staff.getName())
			.phoneNumber(staff.getPhoneNumber())
			.type(staff.getType())
			.task(task)
			.position(position)
			.build();
	}
}
