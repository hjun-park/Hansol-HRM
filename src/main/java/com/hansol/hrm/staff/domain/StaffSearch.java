package com.hansol.hrm.staff.domain;

import java.util.Objects;

import lombok.Builder;
import lombok.Getter;

@Getter
public class StaffSearch {

	private String staffName;
	private String phoneNumber;
	private String type;
	private String taskName;
	private String companyName;
	private String code;
	private String positionName;

	public StaffSearch() {
	}

	@Builder
	public StaffSearch(String staffName, String phoneNumber, String type, String taskName, String companyName,
		String code, String positionName) {
		this.staffName = staffName;
		this.phoneNumber = phoneNumber;
		this.type = type;
		this.taskName = taskName;
		this.companyName = companyName;
		this.code = code;
		this.positionName = positionName;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		StaffSearch that = (StaffSearch)o;
		return Objects.equals(getStaffName(), that.getStaffName()) && Objects.equals(getPhoneNumber(),
			that.getPhoneNumber()) && Objects.equals(getType(), that.getType()) && Objects.equals(
			getTaskName(), that.getTaskName()) && Objects.equals(getCompanyName(), that.getCompanyName())
			&& Objects.equals(getCode(), that.getCode()) && Objects.equals(getPositionName(),
			that.getPositionName());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getStaffName(), getPhoneNumber(), getType(), getTaskName(), getCompanyName(), getCode(),
			getPositionName());
	}
}
