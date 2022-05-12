package com.hansol.hrm.service.staff;

import static com.hansol.hrm.global.response.BaseResponseStatus.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import com.hansol.hrm.global.exception.BaseException;
import com.hansol.hrm.global.response.BaseResponseStatus;
import com.hansol.hrm.service.staff.domain.StaffEntity;
import com.hansol.hrm.service.staff.domain.StaffMapper;
import com.hansol.hrm.service.staff.dto.StaffDto;

@SpringBootTest
// @TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Transactional
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(SpringExtension.class)
class StaffServiceImplTest {

	@Autowired
	private StaffService staffService;

	@Autowired
	private StaffMapper staffMapper;

	@Test
	@DisplayName("01. 직원 전체 조회 테스트")
	@Order(1)
	void findStaffs() {
		//given

		//when
		List<StaffEntity> staffEntities = staffMapper.findAll();
		List<StaffDto> staffDtoList = staffService.findStaffs().orElseGet(Collections::emptyList);

		//then
		assertEquals(staffEntities.size(), staffDtoList.size());

	}

	@Test
	@DisplayName("02. 직원 추가 테스트")
	@Order(2)
	void addStaff() {
		//given
		StaffDto staffDto = StaffDto.builder()
			.name("박현준")
			.type("인턴")
			.phone_number("010-1111-2222")
			.positionId(5L)
			.taskId(16L)
			.build();

		//when
		staffService.addStaff(staffDto);

		//then
		assertEquals(staffMapper.findByName("박현준").getName(), "박현준");

	}

	@Test
	@DisplayName("03. 직원 수정 테스트")
	@Order(3)
	void editStaff() {
		//given
		StaffDto staffDto = StaffDto.builder()
			.name("박현준")
			.type("인턴")
			.phone_number("010-1111-2222")
			.positionId(5L)
			.taskId(16L)
			.build();

		staffService.addStaff(staffDto);
		StaffDto findDto = staffService.findStaffByName("박현준").orElseThrow(() -> new BaseException(TEST_FAIL));

		//when
		findDto.setName("테스트유저123");
		staffService.editStaff(findDto.getId(), findDto);

		//then
		assertEquals(staffMapper.findByName("테스트유저123").getName(), "테스트유저123");

	}

	@Test
	@DisplayName("04. 직원 상태 삭제 변경 테스트")
	@Order(4)
	void statusToDeleteStaff() {
		//given
		StaffDto staffDto = StaffDto.builder()
			.name("박현준")
			.type("인턴")
			.phone_number("010-1111-2222")
			.positionId(5L)
			.taskId(16L)
			.build();

		staffService.addStaff(staffDto);
		StaffDto findDto = staffService.findStaffByName("박현준").orElseThrow(() -> new BaseException(TEST_FAIL));

		//when
		staffService.statusToDeleteStaff(findDto.getId());

		//then
		assertNull(staffMapper.findById(findDto.getId()));

	}

	// TODO: null값에 대해 테스트 진행

}
