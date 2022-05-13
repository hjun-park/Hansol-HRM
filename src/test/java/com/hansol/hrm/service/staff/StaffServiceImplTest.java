package com.hansol.hrm.service.staff;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import com.hansol.hrm.global.exception.BaseException;
import com.hansol.hrm.service.staff.domain.Staff;
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
		List<Staff> staffEntities = staffMapper.findAll();
		List<StaffDto> staffDtoList = staffService.findStaffs();

		//then
		assertEquals(staffEntities.size(), staffDtoList.size());

	}

	@Test
	@DisplayName("01-1. 존재하지 않는 ID로 조회")
	@Order(2)
	void findStaffByNotExistsId() {
		//given
		StaffDto staffDto = StaffDto.builder()
			.name("박현준")
			.type("인턴")
			.phoneNumber("010-1111-2222")
			.positionId(5L)
			.taskId(16L)
			.build();

		//when
		Long staffId = staffService.addStaff(staffDto);

		//then
		assertThrows(BaseException.class, () -> staffService.findStaffById(staffId + 1L));

	}

	@Test
	@DisplayName("01-2. 특정 이름 가진 직원 조회")
	@Order(3)
	void findStaffByName() {
		//given
		StaffDto staffDto1 = StaffDto.builder()
			.name("박현준")
			.type("인턴")
			.phoneNumber("010-1111-2222")
			.positionId(5L)
			.taskId(16L)
			.build();

		StaffDto staffDto2 = StaffDto.builder()
			.name("김현준")
			.type("인턴")
			.phoneNumber("010-2222-3333")
			.positionId(5L)
			.taskId(16L)
			.build();

		staffService.addStaff(staffDto1);
		staffService.addStaff(staffDto2);

		//when

		//then
		assertEquals(staffMapper.findByName("현준").size(), staffService.findStaffsByName("현준").size());

	}

	@Test
	@DisplayName("02. 직원 추가 테스트")
	@Order(5)
	void addStaff() {
		//given
		StaffDto staffDto = StaffDto.builder()
			.name("박현준")
			.type("인턴")
			.phoneNumber("010-1111-2222")
			.positionId(5L)
			.taskId(16L)
			.build();

		//when
		Long staffId = staffService.addStaff(staffDto);
		StaffDto staffById = staffService.findStaffById(staffId);

		//then
		assertEquals(staffId, staffById.getId());

	}

	@Test
	@DisplayName("03. 직원 수정 테스트")
	@Order(10)
	void editStaff() {
		//given
		StaffDto staffDto = StaffDto.builder()
			.name("박현준")
			.type("인턴")
			.phoneNumber("010-1111-2222")
			.positionId(5L)
			.taskId(16L)
			.build();

		Long staffId = staffService.addStaff(staffDto);
		StaffDto findDto = staffService.findStaffById(staffId);

		//when
		findDto.setName("테스트유저123");
		staffService.editStaff(findDto.getId(), findDto);

		//then
		Staff staffEntity = staffMapper.findById(staffId);

		System.out.println("staffEntity = " + staffEntity.toString());
		assertEquals(staffEntity.getName(), "테스트유저123");
		assertEquals(staffEntity.getType(), "인턴");
		assertEquals(staffEntity.getPhoneNumber(), "010-1111-2222");
		assertEquals(staffEntity.getPositionId(), 5L);
		assertEquals(staffEntity.getTaskId(), 16L);

	}

	@Test
	@DisplayName("04. 직원 상태 삭제 변경 테스트")
	@Order(15)
	void statusToDeleteStaff() {
		//given
		StaffDto staffDto = StaffDto.builder()
			.name("박현준")
			.type("인턴")
			.phoneNumber("010-1111-2222")
			.positionId(5L)
			.taskId(16L)
			.build();

		Long staffId = staffService.addStaff(staffDto);
		StaffDto findDto = staffService.findStaffById(staffId);

		//when
		staffService.statusToDeleteStaff(findDto.getId());

		//then
		assertNull(staffMapper.findById(findDto.getId()));

	}
}
