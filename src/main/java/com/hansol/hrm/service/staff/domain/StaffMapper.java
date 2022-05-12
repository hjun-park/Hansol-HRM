package com.hansol.hrm.service.staff.domain;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface StaffMapper {

	List<StaffEntity> findAll();

	Long insertOne(StaffEntity staffEntity);

	Long updateOne(@Param("staffId") Long staffId, @Param("staffEntity") StaffEntity staffEntity);

	Long statusToDelete(Long staffId);

	StaffEntity findById(Long staffId);

	StaffEntity findByName(String name);

}
