package com.hansol.hrm.service.task;

import static com.hansol.hrm.global.response.BaseResponseStatus.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hansol.hrm.global.exception.BaseException;
import com.hansol.hrm.service.task.domain.Task;
import com.hansol.hrm.service.task.domain.TaskMapper;
import com.hansol.hrm.service.task.dto.TaskDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional(readOnly = true)
public class TaskServiceImpl implements TaskService {

	private final TaskMapper taskMapper;

	public TaskServiceImpl(TaskMapper taskMapper) {
		this.taskMapper = taskMapper;
	}

	@Override
	public List<TaskDto> findTasks() {
		Optional<List<Task>> optionalTaskList = Optional.ofNullable(taskMapper.findAll());
		List<Task> taskEntities = optionalTaskList.orElseGet(Collections::emptyList);

		return taskEntities.stream()
			.map(TaskDto::convertEntityToDto)
			.collect(Collectors.toList());
	}

	@Override
	public TaskDto findTaskBy(Long taskId) {

		Optional<Task> findEntity = Optional.ofNullable(taskMapper.findBy(taskId));
		Task taskEntity = findEntity.orElseThrow(() -> new BaseException(RESPONSE_EMPTY));

		return TaskDto.builder()
			.id(taskEntity.getId())
			.name(taskEntity.getName())
			.code(taskEntity.getCode())
			.build();
	}

	@Override
	@Transactional
	public Long addTask(TaskDto taskDto) {
		Task taskEntity = Task.convertDtoToEntity(taskDto);

		if (isDuplicatedCode(taskDto.getCode())) {
			throw new BaseException(DUPLICATED_VALUE);
		}

		Long result = taskMapper.insertOne(taskEntity);

		if (!result.equals(0L)) {
			return taskEntity.getId();
		} else {
			throw new BaseException(INSERT_ERROR);
		}

	}

	@Override
	@Transactional
	public Long editTask(Long taskId, TaskDto taskDto) {
		Long result = taskMapper.updateOne(taskId, Task.convertDtoToEntity(taskDto));

		if (!result.equals(0L)) {
			return result;
		} else {
			throw new BaseException(UPDATE_ERROR);
		}
	}

	@Override
	@Transactional
	public Long deleteTask(Long taskId) {
		Long result = taskMapper.deleteOne(taskId);

		if (!result.equals(0L)) {
			return result;
		} else {
			throw new BaseException(DELETE_ERROR);
		}
	}

	private Boolean isDuplicatedCode(String code) {
		return Optional.ofNullable(taskMapper.findByString("", code)).isPresent();
	}
}
