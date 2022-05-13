package com.hansol.hrm.service.position.domain;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.hansol.hrm.service.staff.domain.Staff;
import com.hansol.hrm.service.task.domain.Task;

@Repository
@Mapper
public interface PositionMapper {

	List<Position> findAll();

	Position findBy(Long positionId);

	Position findByName(String name);

	Long insertOne(Position positionEntity);

	Long updateOne(@Param("positionId") Long positionId, @Param("positionEntity") Position position);

	Long deleteOne(Long positionId);

}
