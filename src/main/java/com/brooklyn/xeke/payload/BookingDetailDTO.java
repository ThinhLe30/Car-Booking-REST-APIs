package com.brooklyn.xeke.payload;

import com.brooklyn.xeke.entity.Sheet;
public class BookingDetailDTO {
	private Integer id;
	private String firstName;
	private String lastName;
	private String pickDestination;
	private String dropDestination;
	private String phone;
	private Sheet sheet;
	private boolean status;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPickDestination() {
		return pickDestination;
	}
	public void setPickDestination(String pickDestination) {
		this.pickDestination = pickDestination;
	}
	public String getDropDestination() {
		return dropDestination;
	}
	public void setDropDestination(String dropDestination) {
		this.dropDestination = dropDestination;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Sheet getSheet() {
		return sheet;
	}
	public void setSheet(Sheet sheet) {
		this.sheet = sheet;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public BookingDetailDTO(String firstName, String lastName, String pickDestination, String dropDestination,
			String phone, Sheet sheet, boolean status) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.pickDestination = pickDestination;
		this.dropDestination = dropDestination;
		this.phone = phone;
		this.sheet = sheet;
		this.status = status;
	}
	public BookingDetailDTO() {
		super();
	}
	
	
}
