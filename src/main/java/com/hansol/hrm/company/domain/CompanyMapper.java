package com.hansol.hrm.company.domain;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface CompanyMapper {

	List<Company> findAll();

	Company findBy(Long companyId);

	Company findByName(String name);

	Long insertOne(Company companyEntity);

	Long updateOne(@Param("companyId") Long companyId, @Param("companyEntity") Company company);

	Long deleteOne(Long companyId);
}
