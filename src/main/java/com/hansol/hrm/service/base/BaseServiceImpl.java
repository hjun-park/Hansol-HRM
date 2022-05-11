package com.hansol.hrm.service.base;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hansol.hrm.service.base.domain.BaseEntity;
import com.hansol.hrm.service.base.domain.BaseMapper;
import com.hansol.hrm.service.base.dto.BaseDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional(readOnly = true)
public class BaseServiceImpl implements BaseService {

	private final BaseMapper baseMapper;

	public BaseServiceImpl(BaseMapper baseMapper) {
		this.baseMapper = baseMapper;
	}

	@Override
	public List<BaseDto> findAll() {
		List<BaseDto> result = baseMapper.getAllDataList().stream() // .orElseGet(Collections::emptyList).stream()
			.map(base ->
				BaseDto.builder()
					.name(base.getName())
					.comment(base.getComment())
					.build())
			.collect(Collectors.toList());

		return result;

	}

	@Override
	public BaseDto findOne(Long baseId) {
		BaseEntity baseEntity = baseMapper.getOne(baseId);

		return BaseDto.builder()
			.name(baseEntity.getName())
			.comment(baseEntity.getComment())
			.build();

	}

	@Override
	@Transactional
	public Long addOne(BaseDto baseDto) {

		BaseEntity baseEntity = BaseEntity.builder()
			.name(baseDto.getName())
			.comment(baseDto.getComment())
			.build();

		// TODO: 0과 1에 따라서 insert 성공여부 결정
		return baseMapper.insertOne(baseEntity);
	}

	@Override
	@Transactional
	public Long editOne(Long baseId, BaseDto baseDto) {

		BaseEntity updateEntity = BaseEntity.builder()
			.id(baseId)
			.name(baseDto.getName())
			.comment(baseDto.getComment())
			.build();

		return baseMapper.updateOne(updateEntity);
	}

	@Override
	@Transactional
	public Long deleteOne(Long baseId) {

		return baseMapper.deleteOne(baseId);
	}

}
