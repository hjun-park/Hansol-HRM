package com.hansol.hrm.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hansol.hrm.global.response.BaseResponse;
import com.hansol.hrm.service.staff.StaffService;
import com.hansol.hrm.service.staff.dto.StaffDto;
import com.hansol.hrm.service.task.TaskService;
import com.hansol.hrm.service.task.dto.TaskDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/tasks") // /swagger-ui/index.html
public class TaskController {

	private final TaskService taskService;

	public TaskController(TaskService taskService) {
		this.taskService = taskService;
	}

	@Operation(summary = "업무 전체 조회")
	@ApiResponses({
		@ApiResponse(responseCode = "1000", description = "성공")
	})
	@GetMapping
	public BaseResponse<List<TaskDto>> getTasks() {
		return new BaseResponse<>(taskService.findTasks());
	}

	@Operation(summary = "특정 ID 업무 조회")
	@ApiResponses({
		@ApiResponse(responseCode = "1000", description = "성공"),
		@ApiResponse(responseCode = "3001", description = "조회할 정보 미존재")
	})
	@GetMapping("/{taskId}")
	public BaseResponse<TaskDto> getTaskBy(@PathVariable Long taskId) {
		return new BaseResponse<>(taskService.findTaskBy(taskId));
	}


	@Operation(summary = "업무 등록")
	@ApiResponses({
		@ApiResponse(responseCode = "1000", description = "성공"),
		@ApiResponse(responseCode = "4002", description = "DB 입력 실패")
	})
	@PostMapping
	public BaseResponse<Long> createTask(@Valid @RequestBody TaskDto taskDto) {
		return new BaseResponse<>(taskService.addTask(taskDto));
	}

	@Operation(summary = "업무 수정")
	@ApiResponses({
		@ApiResponse(responseCode = "1000", description = "성공"),
		@ApiResponse(responseCode = "4003", description = "변경 대상 미존재")
	})
	@PatchMapping("/{taskId}")
	public BaseResponse<Long> modifyTask(@PathVariable Long taskId, @Valid @RequestBody TaskDto taskDto) {
		return new BaseResponse<>(taskService.editTask(taskId, taskDto));
	}

	@Operation(summary = "업무 삭제")
	@ApiResponses({
		@ApiResponse(responseCode = "1000", description = "성공"),
		@ApiResponse(responseCode = "4004", description = "삭제 대상 미존재")
	})
	@DeleteMapping("/{taskId}")
	public BaseResponse<Long> removeTask(@PathVariable Long taskId) {
		return new BaseResponse<>(taskService.deleteTask(taskId));
	}
}
