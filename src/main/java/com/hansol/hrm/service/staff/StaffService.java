package com.hansol.hrm.service.staff;

import java.util.List;

import com.hansol.hrm.service.staff.dto.StaffDto;
import com.hansol.hrm.service.staff.dto.StaffRes;

public interface StaffService {

	// 직원 전체 조회
	List<StaffRes> findStaffs();

	// ID로 직원 조회
	StaffRes findStaffById(Long staffId);

	// 이름으로 직원 조회
	List<StaffRes> findStaffsByName(String name);

	// 직원 등록
	Long addStaff(StaffDto staffDto);

	// 직원 수정
	Long editStaff(Long staffId, StaffDto staffDto);

	// 직원 삭제 (상태삭제)
	Long statusToDeleteStaff(Long staffId);
}
