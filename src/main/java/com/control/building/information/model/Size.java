package com.control.building.information.model;

public enum Size {
	SMALL("S"), MIDDLE("M"), BIG("B");
	
	private String size;
	
	private Size(String size) {
		this.size = size;
	}
	
	public int getSize() {
		switch (size) {
			case "S": 
				return 50;
			case "M": 
				return 70;
			case "B": 
				return 120;
			default:
				throw new IllegalArgumentException();
		}
	}
}
