package com.hansol.hrm.staff.domain;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface StaffMapper {

	List<Staff> findAll();

	Long insertOne(Staff staffEntity);

	Long updateOne(@Param("staffId") Long staffId, @Param("staffEntity") Staff staffEntity);

	Long statusToDelete(Long staffId);

	Staff findById(Long staffId);

	List<Staff> findByName(String name);

	List<StaffSearch> filterStaff(@Param("companyId") Long companyId, @Param("taskId") Long taskId, @Param("positionId") Long positionId);

	List<Staff> searchKeyword(String keyword);
}
