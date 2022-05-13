package com.hansol.hrm.service.position;

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
import com.hansol.hrm.service.position.domain.Position;
import com.hansol.hrm.service.position.dto.PositionDto;
import com.hansol.hrm.service.position.domain.PositionMapper;

@SpringBootTest
// @TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Transactional
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(SpringExtension.class)
class PositionServiceImplTest {

	@Autowired
	private PositionService positionService;

	@Autowired
	private PositionMapper positionMapper;

	@Test
	@DisplayName("01. 직급 전체 조회 테스트")
	@Order(1)
	void findPositions() {
		//given

		//when
		List<Position> positionList = positionMapper.findAll();
		List<PositionDto> positionDtoList = positionService.findPositions();

		//then
		assertEquals(positionList.size(), positionDtoList.size());
	}

	@Test
	@DisplayName("01-1. 존재하지 않는 ID로 조회")
	@Order(2)
	void findPositionByNotExistsId() {
		//given
		PositionDto positionDto = PositionDto.builder()
			.name("테스트직급")
			.build();

		//when
		Long positionId = positionService.addPosition(positionDto);

		//then
		assertThrows(BaseException.class, () -> positionService.findPositionBy(positionId + 1L));

	}

	@Test
	@DisplayName("01-2. 중복된 직급명 등록 시 예외처리")
	@Order(3)
	void insertDuplicatedName() {
		//given
		PositionDto positionDto1 = PositionDto.builder()
			.name("테스트직급1")
			.build();

		PositionDto positionDto2 = PositionDto.builder()
			.name("테스트직급1")
			.build();

		//when

		//then
		positionService.addPosition(positionDto1);
		assertThrows(BaseException.class, () -> positionService.addPosition(positionDto2));

	}

	@Test
	@DisplayName("02. 직급 추가 테스트")
	@Order(5)
	void addPosition() {
		//given
		PositionDto positionDto = PositionDto.builder()
			.name("테스트직급")
			.build();

		//when
		Long positionId = positionService.addPosition(positionDto);
		PositionDto positionById = positionService.findPositionBy(positionId);

		//then
		assertEquals(positionId, positionById.getId());
	}

	@Test
	@DisplayName("03. 직급 수정 테스트")
	@Order(10)
	void editPosition() {
		//given
		PositionDto positionDto = PositionDto.builder()
			.name("테스트직급")
			.build();

		Long positionId = positionService.addPosition(positionDto);
		PositionDto findDto = positionService.findPositionBy(positionId);

		//when
		findDto.setName("수정된 테스트직급");

		positionService.editPosition(findDto.getId(), findDto);

		//then
		Position positionEntity = positionMapper.findBy(positionId);

		assertEquals("수정된 테스트직급", positionEntity.getName());
	}

	@Test
	@DisplayName("04. 직급 삭제 테스트")
	@Order(15)
	void deletePosition() {
		//given
		PositionDto positionDto = PositionDto.builder()
			.name("테스트회사")
			.build();

		Long positionId = positionService.addPosition(positionDto);
		PositionDto findDto = positionService.findPositionBy(positionId);

		//when
		positionService.deletePosition(findDto.getId());

		//then
		assertNull(positionMapper.findBy(findDto.getId()));
	}
}
