package com.hansol.hrm.global.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 에러 코드 관리
 */
@Getter
@RequiredArgsConstructor
public enum BaseResponseStatus {
	/**
	 * 1000 : 요청 성공
	 */
	SUCCESS(true, 1000, "요청에 성공하였습니다."),

	/**
	 * 2000 : Request 오류
	 */
	// Common
	REQUEST_ERROR(false, 2000, "입력값을 확인해주세요."),

	// Common 형식 관련: 2100~
	INVALID_EMAIL(false, 2101, "올바른 이메일 형식을 입력해주세요."),

	// [POST] null 관련: 2200~
	POST_EMPTY(false, 2200, "필수 요소를 모두 확인해주세요."),

	/**
	 * 3000 : Response 오류
	 */
	// Common
	RESPONSE_ERROR(false, 3000, "값을 불러오는데 실패하였습니다."),
	RESPONSE_EMPTY(false, 3001, "조회할 정보가 없습니다."),
	DELETE_EMPTY(false, 3002, "삭제할 정보가 없습니다."),
	STAFF_EMPTY(false, 3003, "존재하지 않는 직원입니다."),

	/**
	 * 4000 : Database, Server 오류
	 */
	DATABASE_ERROR(false, 4000, "데이터베이스 연결에 실패하였습니다."),
	SERVER_ERROR(false, 4001, "서버와의 연결에 실패하였습니다."),
	INSERT_ERROR(false, 4002, "데이터베이스 입력에 실패하였습니다."),
	UPDATE_ERROR(false, 4003, "변경할 항목이 존재하지 않습니다."),
	DELETE_ERROR(false, 4004, "삭제할 항목이 존재하지 않습니다."),

	/**
	 * 5000 : 테스트 전용
	 */
	TEST_FAIL(false, 5000, "테스트 실패");



	private final boolean isSuccess;
	private final int code;
	private final String message;

	}
