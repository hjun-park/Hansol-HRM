package com.hansol.hrm.company.service;

import static com.hansol.hrm.global.response.BaseResponseStatus.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hansol.hrm.company.domain.Company;
import com.hansol.hrm.company.dto.CompanyDto;
import com.hansol.hrm.global.exception.BaseException;
import com.hansol.hrm.company.domain.CompanyMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional(readOnly = true)
public class CompanyServiceImpl implements CompanyService {

	private final CompanyMapper companyMapper;

	public CompanyServiceImpl(CompanyMapper companyMapper) {
		this.companyMapper = companyMapper;
	}

	@Override
	public List<CompanyDto> findCompanies() {
		Optional<List<Company>> optionalCompanyList = Optional.ofNullable(companyMapper.findAll());
		List<Company> companies = optionalCompanyList.orElseGet(Collections::emptyList);

		return companies.stream()
			.map(CompanyDto::convertEntityToDto)
			.collect(Collectors.toList());
	}

	@Override
	public CompanyDto findCompanyBy(Long companyId) {

		Optional<Company> findEntity = Optional.ofNullable(companyMapper.findBy(companyId));
		Company companyEntity = findEntity.orElseThrow(() -> new BaseException(RESPONSE_EMPTY));

		return CompanyDto.builder()
			.id(companyEntity.getId())
			.name(companyEntity.getName())
			.build();
	}

	@Override
	@Transactional
	public Long addCompany(CompanyDto companyDto) {
		Company companyEntity = Company.convertDtoToEntity(companyDto);

		if (isDuplicatedName(companyDto.getName())) {
			throw new BaseException(DUPLICATED_VALUE);
		}

		Long result = companyMapper.insertOne(companyEntity);

		if (!result.equals(0L)) {
			return companyEntity.getId();
		} else {
			throw new BaseException(INSERT_ERROR);
		}
	}

	@Override
	@Transactional
	public Long editCompany(Long companyId, CompanyDto companyDto) {
		Long result = companyMapper.updateOne(companyId, Company.convertDtoToEntity(companyDto));

		if (!result.equals(0L)) {
			return result;
		} else {
			throw new BaseException(UPDATE_ERROR);
		}
	}

	@Override
	@Transactional
	public Long deleteCompany(Long companyId) {
		Long result = companyMapper.deleteOne(companyId);

		if (!result.equals(0L)) {
			return result;
		} else {
			throw new BaseException(DELETE_ERROR);
		}
	}

	private Boolean isDuplicatedName(String name) {
		return Optional.ofNullable(companyMapper.findByName(name)).isPresent();
	}
}
