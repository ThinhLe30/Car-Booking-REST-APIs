package com.brooklyn.xeke.entity;

public enum Sheet {
FRONT
 {
	@Override
	public String defaultDescription() {
		// TODO Auto-generated method stub
		return "GHẾ ĐẦU TIÊN";
	}
},
MIDDLE_MIDDLE
 {
	@Override
	public String defaultDescription() {
		// TODO Auto-generated method stub
		return "GHẾ GIỮA Ở GIỮA";
	}
},
MIDDLE_RIGHT
 {
	@Override
	public String defaultDescription() {
		// TODO Auto-generated method stub
		return "GHẾ GIỮA BÊN PHẢI";
	}
},
MIDDLE_LEFT
 {
	@Override
	public String defaultDescription() {
		// TODO Auto-generated method stub
		return "GHẾ GIỮA BÊN TRÁI";
	}
},
BACK_MIDDLE
 {
	@Override
	public String defaultDescription() {
		// TODO Auto-generated method stub
		return "GHẾ SAU Ở GIỮA";
	}
},
BACK_RIGHT
 {
	@Override
	public String defaultDescription() {
		// TODO Auto-generated method stub
		return "GHẾ SAU BÊN PHẢI";
	}
},
BACK_LEFT
 {
	@Override
	public String defaultDescription() {
		// TODO Auto-generated method stub
		return "GHẾ SAU BÊN TRÁI";
	}
 };
	public abstract String defaultDescription();
}
