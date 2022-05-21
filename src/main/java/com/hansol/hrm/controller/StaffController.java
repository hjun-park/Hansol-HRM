package com.hansol.hrm.controller;

import java.util.Collections;
import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hansol.hrm.global.response.BaseResponse;
import com.hansol.hrm.service.staff.StaffService;
import com.hansol.hrm.service.staff.domain.StaffSearch;
import com.hansol.hrm.service.staff.dto.StaffDto;
import com.hansol.hrm.service.staff.dto.StaffRes;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/staffs") // /swagger-ui/index.html
public class StaffController {

	private final StaffService staffService;

	public StaffController(StaffService staffService) {
		this.staffService = staffService;
	}

	@Operation(summary = "직원 전체 조회")
	@ApiResponses({
		@ApiResponse(responseCode = "1000", description = "성공")
	})
	@GetMapping
	public BaseResponse<List<StaffRes>> getStaffs() {
		return new BaseResponse<>(staffService.findStaffs());
	}

	@Operation(summary = "특정 ID 직원 조회")
	@ApiResponses({
		@ApiResponse(responseCode = "1000", description = "성공"),
		@ApiResponse(responseCode = "3003", description = "존재하지 않는 직원 ID")
	})
	@GetMapping("/{staffId}")
	public BaseResponse<StaffRes> getStaffById(@PathVariable Long staffId) {
		return new BaseResponse<>(staffService.findStaffById(staffId));
	}


	@Operation(summary = "직원 등록")
	@ApiResponses({
		@ApiResponse(responseCode = "1000", description = "성공"),
		@ApiResponse(responseCode = "4002", description = "DB 입력 실패")
	})
	@PostMapping
	public BaseResponse<Long> createStaff(@Valid @RequestBody StaffDto staffDto) {
		return new BaseResponse<>(staffService.addStaff(staffDto));
	}

	@Operation(summary = "직원 수정")
	@ApiResponses({
		@ApiResponse(responseCode = "1000", description = "성공"),
		@ApiResponse(responseCode = "4003", description = "변경 대상 미존재")
	})
	@PatchMapping("/{staffId}")
	public BaseResponse<Long> modifyStaff(@PathVariable Long staffId, @Valid @RequestBody StaffDto staffDto) {
		return new BaseResponse<>(staffService.editStaff(staffId, staffDto));
	}

	@Operation(summary = "직원 삭제 처리(상태)")
	@ApiResponses({
		@ApiResponse(responseCode = "1000", description = "성공"),
		@ApiResponse(responseCode = "4004", description = "삭제 대상 미존재")
	})
	@PatchMapping("/{staffId}/status")
	public BaseResponse<Long> statusToDeleteStaff(@PathVariable Long staffId) {
		return new BaseResponse<>(staffService.statusToDeleteStaff(staffId));
	}

	@Operation(summary = "직원 필터 검색")
	@ApiResponses({
		@ApiResponse(responseCode = "1000", description = "성공")
	})
	@GetMapping("/search")
	public BaseResponse<List<StaffSearch>> filterStaff(@RequestParam(required = false) Long companyId,
											     @RequestParam(required = false) Long taskId,
												@RequestParam(required = false) Long positionId) {
		return new BaseResponse<>(staffService.filterStaff(companyId, taskId, positionId));
	}

	@Operation(summary = "직원 키워드 검색")
	@ApiResponses({
		@ApiResponse(responseCode = "1000", description = "성공")
	})
	@GetMapping("/keyword")
	public BaseResponse<List<StaffRes>> searchKeyword(@RequestParam String query) {
		return new BaseResponse<>(staffService.searchKeyword(query));
	}



}
