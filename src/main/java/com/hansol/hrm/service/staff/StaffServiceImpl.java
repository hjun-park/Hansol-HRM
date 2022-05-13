package com.hansol.hrm.service.staff;

import static com.hansol.hrm.global.response.BaseResponseStatus.*;
import static com.hansol.hrm.service.staff.domain.Staff.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hansol.hrm.global.exception.BaseException;
import com.hansol.hrm.service.staff.domain.Staff;
import com.hansol.hrm.service.staff.domain.StaffMapper;
import com.hansol.hrm.service.staff.dto.StaffDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional(readOnly = true)
public class StaffServiceImpl implements StaffService {

	private final StaffMapper staffMapper;

	public StaffServiceImpl(StaffMapper staffMapper) {
		this.staffMapper = staffMapper;
	}

	@Override
	public List<StaffDto> findStaffs() {
		Optional<List<Staff>> optionalStaffEntities = Optional.ofNullable(staffMapper.findAll());
		List<Staff> staffEntities = optionalStaffEntities.orElseGet(Collections::emptyList);

		return staffEntities.stream()
			.map(StaffDto::convertEntityToDto)
			.collect(Collectors.toList());
	}

	@Override
	public StaffDto findStaffById(Long staffId) {

		Optional<Staff> findEntity = Optional.ofNullable(staffMapper.findById(staffId));
		Staff staffEntity = findEntity.orElseThrow(() -> new BaseException(STAFF_EMPTY));

		return StaffDto.builder()
			.id(staffEntity.getId())
			.name(staffEntity.getName())
			.phoneNumber(staffEntity.getPhoneNumber())
			.type(staffEntity.getType())
			.taskId(staffEntity.getTaskId())
			.positionId(staffEntity.getPositionId())
			.build();
	}

	@Override
	public List<StaffDto> findStaffsByName(String name) {

		Optional<List<Staff>> findEntities = Optional.ofNullable(staffMapper.findByName(name));
		List<Staff> staffEntities = findEntities.orElseGet(Collections::emptyList);

		return staffEntities.stream()
			.map(StaffDto::convertEntityToDto)
			.collect(Collectors.toList());
	}

	@Override
	@Transactional
	public Long addStaff(StaffDto staffDto) {
		Staff staffEntity = convertDtoToEntity(staffDto);
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
		Long result = staffMapper.updateOne(staffId, convertDtoToEntity(staffDto));

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
}
