package com.hansol.hrm.service.company;

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

import com.hansol.hrm.company.service.CompanyService;
import com.hansol.hrm.global.exception.BaseException;
import com.hansol.hrm.company.domain.Company;
import com.hansol.hrm.company.domain.CompanyMapper;
import com.hansol.hrm.company.dto.CompanyDto;

@SpringBootTest
// @TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Transactional
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(SpringExtension.class)
class CompanyServiceImplTest {

	@Autowired
	private CompanyService companyService;

	@Autowired
	private CompanyMapper companyMapper;

	@Test
	@DisplayName("01. 회사 전체 조회 테스트")
	@Order(1)
	void findCompanies() {
		//given

		//when
		List<Company> companyList = companyMapper.findAll();
		List<CompanyDto> companyDtoList = companyService.findCompanies();

		//then
		assertEquals(companyList.size(), companyDtoList.size());
	}

	@Test
	@DisplayName("01-1. 존재하지 않는 ID로 조회")
	@Order(2)
	void findCompanyByNotExistsId() {
		//given
		CompanyDto companyDto = CompanyDto.builder()
			.name("테스트회사")
			.build();

		//when
		Long companyId = companyService.addCompany(companyDto);

		//then
		assertThrows(BaseException.class, () -> companyService.findCompanyBy(companyId + 1L));

	}

	@Test
	@DisplayName("01-2. 중복된 회사명 등록 시 예외처리")
	@Order(3)
	void insertDuplicatedName() {
		//given
		CompanyDto companyDto1 = CompanyDto.builder()
			.name("테스트회사1")
			.build();

		CompanyDto companyDto2 = CompanyDto.builder()
			.name("테스트회사1")
			.build();

		//when

		//then
		companyService.addCompany(companyDto1);
		assertThrows(BaseException.class, () -> companyService.addCompany(companyDto2));

	}

	@Test
	@DisplayName("02. 회사 추가 테스트")
	@Order(5)
	void addCompany() {
		//given
		CompanyDto companyDto = CompanyDto.builder()
			.name("테스트회사")
			.build();

		//when
		Long companyId = companyService.addCompany(companyDto);
		CompanyDto companyById = companyService.findCompanyBy(companyId);

		//then
		assertEquals(companyId, companyById.getId());
	}

	@Test
	@DisplayName("03. 회사 수정 테스트")
	@Order(10)
	void editCompany() {
		//given
		CompanyDto companyDto = CompanyDto.builder()
			.name("테스트회사")
			.build();

		Long companyId = companyService.addCompany(companyDto);
		CompanyDto findDto = companyService.findCompanyBy(companyId);

		//when
		findDto.setName("수정된 테스트회사");

		companyService.editCompany(findDto.getId(), findDto);

		//then
		Company companyEntity = companyMapper.findBy(companyId);

		assertEquals("수정된 테스트회사", companyEntity.getName());
	}

	@Test
	@DisplayName("04. 회사 삭제 테스트")
	@Order(15)
	void deleteCompany() {
		//given
		CompanyDto companyDto = CompanyDto.builder()
			.name("테스트회사")
			.build();

		Long companyId = companyService.addCompany(companyDto);
		CompanyDto findDto = companyService.findCompanyBy(companyId);

		//when
		companyService.deleteCompany(findDto.getId());

		//then
		assertNull(companyMapper.findBy(findDto.getId()));
	}
}
