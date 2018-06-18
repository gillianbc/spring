package org.gillianbc;

public class Triangle {
	private String triangleType;
	private String triangleName;
	private int height;
	//I'm using different member variables so I can tell when it's using the setter
	//and when it's using the constructor
	public Triangle(String triangleName) {
		this.triangleName = triangleName;
		
	}
	public Triangle(int height) {
		this.triangleName = "Just height";
		this.height = height;
	}
	public Triangle(String triangleName, String triangleType) {
		this.triangleName = triangleName;
		this.triangleType = triangleType;
	}
	public Triangle() {
		//it made me explicitly make this default constructor - dunno why
	}
	public String getTriangleType() {
		return triangleType;
	}

	public void setTriangleType(String triangleType) {
		this.triangleType = triangleType;
	}

	
	public void draw() {
		System.out.println("Triangle drawn:" + triangleType + " name is " + triangleName + " height " + height);
	}
}
