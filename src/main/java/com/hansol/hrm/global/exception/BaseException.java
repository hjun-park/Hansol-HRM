package com.hansol.hrm.global.exception;

import com.hansol.hrm.global.response.BaseResponseStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BaseException extends RuntimeException {
	BaseResponseStatus status;
}
