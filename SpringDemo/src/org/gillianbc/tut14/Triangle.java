package org.gillianbc.tut14;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class Triangle implements Shape, InitializingBean, DisposableBean {
	private Point pointA;
	private Point pointB;
	private Point pointC;

	public Point getPointA() {
		return pointA;
	}

	public void setPointA(Point pointA) {
		this.pointA = pointA;
	}

	public Point getPointB() {
		return pointB;
	}

	public void setPointB(Point pointB) {
		this.pointB = pointB;
	}

	public Point getPointC() {
		return pointC;
	}

	public void setPointC(Point pointC) {
		this.pointC = pointC;
	}

	@Override
	public void draw() {
		System.out.println("Triangle: ");
		System.out.println("Point A: (" + pointA.getX() + "," + pointA.getY() + ")");
		System.out.println("Point B: (" + pointB.getX() + "," + pointB.getY() + ")");
		System.out.println("Point C: (" + pointC.getX() + "," + pointC.getY() + ")");
	}

	//You can get rid of these methods and don't use their interfaces
	//and just have init-method="myInitMethodName" destroy-method="cleanUpMethodName" in the spring.xml
	//then put the init method in the class of each bean
	//You can also put them globally in the beans tag to apply the same method name for every bean
	//default-init-method="XXXX" default-destroy-method="YYYYY".  
	//If you use the interfaces and the bean/beans tag then both afterPropertiesSet()/destroy()
	//and the method defined in the bean/beans will run
	//If you have both a beans and a bean method, then only the bean method will run
	
	@Override
	public void afterPropertiesSet() throws Exception {
		// This will be called when all the member variables have been set by
		// Spring
		System.out.println("InitializingBean: Triangle: initialised");
	}

	@Override
	public void destroy() throws Exception {
		// This will be called when beans are destroyed by 
		// Spring
		System.out.println("DisposableBean: Triangle: destroyed");

	}
	public void beanInit(){
		System.out.println("Bean init method");
	}
	public void beanCleanUp(){
		System.out.println("Bean cleanup method");
	}
	public void allBeansInit(){
		System.out.println("All Beans init method");
	}
	public void allBeansCleanUp(){
		System.out.println("All Beans cleanup method");
	}
	
}
