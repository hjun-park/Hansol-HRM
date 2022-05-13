package com.hansol.hrm.service.position;

import static com.hansol.hrm.global.response.BaseResponseStatus.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hansol.hrm.global.exception.BaseException;
import com.hansol.hrm.service.company.domain.Company;
import com.hansol.hrm.service.company.dto.CompanyDto;
import com.hansol.hrm.service.position.domain.Position;
import com.hansol.hrm.service.position.domain.PositionMapper;
import com.hansol.hrm.service.position.dto.PositionDto;
import com.hansol.hrm.service.task.TaskService;
import com.hansol.hrm.service.task.domain.Task;
import com.hansol.hrm.service.task.domain.TaskMapper;
import com.hansol.hrm.service.task.dto.TaskDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional(readOnly = true)
public class PositionServiceImpl implements PositionService {

	private final PositionMapper positionMapper;

	public PositionServiceImpl(PositionMapper positionMapper) {
		this.positionMapper = positionMapper;
	}

	@Override
	public List<PositionDto> findPositions() {
		Optional<List<Position>> optionalPositionList = Optional.ofNullable(positionMapper.findAll());
		List<Position> positions = optionalPositionList.orElseGet(Collections::emptyList);

		return positions.stream()
			.map(PositionDto::convertEntityToDto)
			.collect(Collectors.toList());
	}

	@Override
	public PositionDto findPositionBy(Long positionId) {
		Optional<Position> findEntity = Optional.ofNullable(positionMapper.findBy(positionId));
		Position positionEntity = findEntity.orElseThrow(() -> new BaseException(RESPONSE_EMPTY));

		return PositionDto.builder()
			.id(positionEntity.getId())
			.name(positionEntity.getName())
			.build();
	}

	@Override
	@Transactional
	public Long addPosition(PositionDto positionDto) {
		Position positionEntity = Position.convertDtoToEntity(positionDto);

		if (isDuplicatedName(positionDto.getName())) {
			throw new BaseException(DUPLICATED_VALUE);
		}

		Long result = positionMapper.insertOne(positionEntity);

		if (!result.equals(0L)) {
			return positionEntity.getId();
		} else {
			throw new BaseException(INSERT_ERROR);
		}
	}

	@Override
	@Transactional
	public Long editPosition(Long positionId, PositionDto positionDto) {
		Long result = positionMapper.updateOne(positionId, Position.convertDtoToEntity(positionDto));

		if (!result.equals(0L)) {
			return result;
		} else {
			throw new BaseException(UPDATE_ERROR);
		}
	}

	@Override
	@Transactional
	public Long deletePosition(Long positionId) {
		Long result = positionMapper.deleteOne(positionId);

		if (!result.equals(0L)) {
			return result;
		} else {
			throw new BaseException(DELETE_ERROR);
		}
	}

	private Boolean isDuplicatedName(String name) {
		return Optional.ofNullable(positionMapper.findByName(name)).isPresent();
	}
}
