package com.hansol.hrm.service.base;

import java.util.List;

import com.hansol.hrm.service.base.dto.BaseDto;

public interface BaseService {

	List<BaseDto> findAll();
	BaseDto findOne(Long baseId);

	Long addOne(BaseDto baseDto);
	Long editOne(Long baseId, BaseDto baseDto);
	Long deleteOne(Long baseId);

}
