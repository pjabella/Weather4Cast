package com.example.weather4cast;

public class student {

	
	int image;
    String name, temp,coor;
	public student(int image, String name, String temp, String coor) {
		super();
		this.image = image;
		this.name = name;
		this.temp = temp;
		this.coor = coor;
	}
	public int getImage() {
		return image;
	}
	public void setImage(int image) {
		this.image = image;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTemp() {
		return temp;
	}
	public void setTemp(String temp) {
		this.temp = temp;
	}
	public String getCoor() {
		return coor;
	}
	public void setCoor(String coor) {
		this.coor = coor;
	}
    
    
}