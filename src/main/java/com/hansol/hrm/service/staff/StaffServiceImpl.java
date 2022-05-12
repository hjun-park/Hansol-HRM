package com.hansol.hrm.service.staff;

import static com.hansol.hrm.global.response.BaseResponseStatus.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hansol.hrm.global.exception.BaseException;
import com.hansol.hrm.service.staff.domain.StaffEntity;
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
	public Optional<List<StaffDto>> findStaffs() {
		return Optional.of(staffMapper.findAll().stream()
			.map(staffEntity -> StaffDto.builder()
				.id(staffEntity.getId())
				.name(staffEntity.getName())
				.phone_number(staffEntity.getPhone_number())
				.type(staffEntity.getType())
				.taskId(staffEntity.getTaskId())
				.positionId(staffEntity.getPositionId())
				.build())
			.collect(Collectors.toList()));
	}

	@Override
	public Optional<StaffDto> findStaffById(Long staffId) {

		StaffEntity findEntity = staffMapper.findById(staffId);

		return Optional.of(StaffDto.builder()
			.id(findEntity.getId())
			.name(findEntity.getName())
			.phone_number(findEntity.getPhone_number())
			.type(findEntity.getType())
			.taskId(findEntity.getTaskId())
			.positionId(findEntity.getPositionId())
			.build());
	}

	@Override
	public Optional<StaffDto> findStaffByName(String name) {

		StaffEntity findEntity = staffMapper.findByName(name);

		return Optional.of(StaffDto.builder()
			.id(findEntity.getId())
			.name(findEntity.getName())
			.phone_number(findEntity.getPhone_number())
			.type(findEntity.getType())
			.taskId(findEntity.getTaskId())
			.positionId(findEntity.getPositionId())
			.build());
	}

	@Override
	@Transactional
	public Long addStaff(StaffDto staffDto) {
		Long result = staffMapper.insertOne(convertDtoToEntity(staffDto));

		if (!result.equals(0L)) {
			return result;
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

	private StaffEntity convertDtoToEntity(StaffDto staffDto) {
		return StaffEntity.builder()
			.name(staffDto.getName())
			.phone_number(staffDto.getPhone_number())
			.type(staffDto.getType())
			.taskId(staffDto.getTaskId())
			.positionId(staffDto.getPositionId())
			.build();
	}
}
