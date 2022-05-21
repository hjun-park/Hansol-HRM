package com.hansol.hrm.staff.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class StaffRes {

	private Long id;
	private String name;
	private String phoneNumber;
	private String type;
	private String task;
	private String position;

	public StaffRes() {
	}

	@Builder
	public StaffRes(Long id, String name, String phoneNumber, String type, String task, String position) {
		this.id = id;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.type = type;
		this.task = task;
		this.position = position;
	}

}
