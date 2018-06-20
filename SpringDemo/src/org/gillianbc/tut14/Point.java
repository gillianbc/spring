package org.gillianbc.tut14;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class Point implements InitializingBean, DisposableBean {
	private int x;
	private int y;
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	@Override
	public void afterPropertiesSet() throws Exception {
		// This will be called when all the member variables have been set by
		// Spring
		System.out.println("InitializingBean: Point: initialised");
	}

	@Override
	public void destroy() throws Exception {
		// This will be called when beans are destroyed by 
		// Spring
		System.out.println("DisposableBean: Point: destroyed");

	}
		
	
}
