package org.gillianbc;

import org.springframework.beans.factory.annotation.Required;

public class Circle implements Shape{
	private Point centre;
	
	public Point getCentre() {
		return centre;
	}
	@Required
	public void setCentre(Point centre) {
		this.centre = centre;
	}
	
	@Override
	public void draw() {
		System.out.println("Circle Centre: (" + centre.getX() + "," + centre.getY() + ")");
		
	}

}
