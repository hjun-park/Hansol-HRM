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
import com.hansol.hrm.service.position.PositionService;
import com.hansol.hrm.service.position.dto.PositionDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/positions") // /swagger-ui/index.html
public class PositionController {

	private final PositionService positionService;

	public PositionController(PositionService positionService) {
		this.positionService = positionService;
	}

	@Operation(summary = "직급 전체 조회")
	@ApiResponses({
		@ApiResponse(responseCode = "1000", description = "성공")
	})
	@GetMapping
	public BaseResponse<List<PositionDto>> getPositions() {
		return new BaseResponse<>(positionService.findPositions());
	}

	@Operation(summary = "ID로 특정 직급 조회")
	@ApiResponses({
		@ApiResponse(responseCode = "1000", description = "성공"),
		@ApiResponse(responseCode = "3001", description = "조회할 정보 미존재")
	})
	@GetMapping("/{positionId}")
	public BaseResponse<PositionDto> getPositionBy(@PathVariable Long positionId) {
		return new BaseResponse<>(positionService.findPositionBy(positionId));
	}


	@Operation(summary = "직급 등록")
	@ApiResponses({
		@ApiResponse(responseCode = "1000", description = "성공"),
		@ApiResponse(responseCode = "2102", description = "중복된 값 등록불가"),
		@ApiResponse(responseCode = "4002", description = "DB 입력 실패")
	})
	@PostMapping
	public BaseResponse<Long> createPosition(@Valid @RequestBody PositionDto positionDto) {
		return new BaseResponse<>(positionService.addPosition(positionDto));
	}

	@Operation(summary = "직급 수정")
	@ApiResponses({
		@ApiResponse(responseCode = "1000", description = "성공"),
		@ApiResponse(responseCode = "4003", description = "변경 대상 미존재")
	})
	@PatchMapping("/{positionId}")
	public BaseResponse<Long> modifyPosition(@PathVariable Long positionId, @Valid @RequestBody PositionDto positionDto) {
		return new BaseResponse<>(positionService.editPosition(positionId, positionDto));
	}

	@Operation(summary = "직급 삭제")
	@ApiResponses({
		@ApiResponse(responseCode = "1000", description = "성공"),
		@ApiResponse(responseCode = "4004", description = "삭제 대상 미존재")
	})
	@DeleteMapping("/{positionId}")
	public BaseResponse<Long> removePosition(@PathVariable Long positionId) {
		return new BaseResponse<>(positionService.deletePosition(positionId));
	}
}
