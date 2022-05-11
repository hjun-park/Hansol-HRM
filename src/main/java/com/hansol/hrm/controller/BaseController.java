package com.hansol.hrm.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hansol.hrm.global.response.BaseResponse;
import com.hansol.hrm.service.base.BaseService;
import com.hansol.hrm.service.base.dto.BaseDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/base")
public class BaseController {

	private final BaseService baseService;

	public BaseController(BaseService baseService) {
		this.baseService = baseService;
	}

	@Operation(summary = "전체 조회", description = "Base 테이블 내역 조회")
	@ApiResponses({
		@ApiResponse(responseCode = "1000", description = "성공")
	})
	@GetMapping("/find")
	public BaseResponse<List<BaseDto>> findAll() {
		return new BaseResponse<>(baseService.findAll());
	}

	@Operation(summary = "특정 회원 조회", description = "Base 테이블 특정 회원 조회")
	@ApiResponses({
		@ApiResponse(responseCode = "1000", description = "성공")
	})
	@GetMapping("/find/{baseId}")
	public BaseResponse<BaseDto> findOne(@PathVariable Long baseId) {
		return new BaseResponse<>(baseService.findOne(baseId));
	}

	@Operation(summary = "회원 등록", description = "회원 등록")
	@ApiResponses({
		@ApiResponse(responseCode = "1000", description = "성공")
	})
	@PostMapping("/add")
	public BaseResponse<Long> addOne(@Valid @RequestBody BaseDto baseDto) {
		return new BaseResponse<>(baseService.addOne(baseDto));
	}

	@Operation(summary = "회원 수정", description = "특정 회원 수정")
	@ApiResponses({
		@ApiResponse(responseCode = "1000", description = "성공")
	})
	@PatchMapping("/edit/{baseId}")
	public BaseResponse<Long> editOne(@PathVariable Long baseId, @RequestBody BaseDto baseDto) {
		return new BaseResponse<>(baseService.editOne(baseId, baseDto));
	}


	@Operation(summary = "회원 삭제", description = "특정 회원 삭제")
	@ApiResponses({
		@ApiResponse(responseCode = "1000", description = "성공")
	})
	@DeleteMapping("/delete/{baseId}")
	public BaseResponse<Long> deleteOne(@Valid @PathVariable Long baseId) {
		return new BaseResponse<>(baseService.deleteOne(baseId));
	}



}
