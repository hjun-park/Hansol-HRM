package com.hansol.hrm.position.service;

import java.util.List;

import com.hansol.hrm.position.dto.PositionDto;

public interface PositionService {

	// 직급 전체 조회
	List<PositionDto> findPositions();

	// ID로 직급 조회
	PositionDto findPositionBy(Long positionId);

	// 직급 등록
	Long addPosition(PositionDto positionDto);

	// 직급명 수정
	Long editPosition(Long positionId, PositionDto positionDto);

	// 직급 삭제
	Long deletePosition(Long positionId);
}
