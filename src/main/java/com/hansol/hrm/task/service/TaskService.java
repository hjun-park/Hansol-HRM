package com.hansol.hrm.task.service;

import java.util.List;

import com.hansol.hrm.task.dto.TaskDto;

public interface TaskService {

	// 업무 전체 조회
	List<TaskDto> findTasks();

	// ID로 업무 조회
	TaskDto findTaskBy(Long taskId);

	// 업무 등록
	Long addTask(TaskDto taskDto);

	// 업무 이름, 코드 수정
	Long editTask(Long taskId, TaskDto taskDto);

	// 업무 삭제
	Long deleteTask(Long taskId);
}
