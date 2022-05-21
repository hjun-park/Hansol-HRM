package com.hansol.hrm.position.domain;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

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
