package com.hansol.hrm.service.base.domain;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.hansol.hrm.service.base.dto.BaseDto;

@Repository
@Mapper
public interface BaseMapper {
	// @MapKey("id")
	List<BaseEntity> getAllDataList();
	BaseEntity getOne(Long id);

	Long insertOne(BaseEntity baseEntity);
	Long updateOne(BaseEntity baseEntity);

	Long deleteOne(Long baseId);


}
