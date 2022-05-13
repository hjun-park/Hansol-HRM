package com.hansol.hrm.service.staff;

import java.util.List;
import java.util.Optional;

import com.hansol.hrm.service.staff.dto.StaffDto;

public interface StaffService {

	// 직원 전체 조회
	List<StaffDto> findStaffs();

	// ID로 직원 조회
	StaffDto findStaffById(Long staffId);

	// 이름으로 직원 조회
	List<StaffDto> findStaffsByName(String name);

	// 직원 등록
	Long addStaff(StaffDto staffDto);

	// 직원 수정
	Long editStaff(Long staffId, StaffDto staffDto);

	// 직원 삭제 (상태삭제)
	Long statusToDeleteStaff(Long staffId);
}
