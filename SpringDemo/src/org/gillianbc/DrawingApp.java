package org.gillianbc;

import org.springframework.context.support.ClassPathXmlApplicationContext;

@SuppressWarnings("deprecation")
public class DrawingApp {
	public static void main(String[] args) {
		
		//THIS DOES NOT SEEM TO WORK WITH ECLIPSE OXYGEN _ USE NEON
		
//		Triangle triangle = new Triangle();
		//BeanFactory in the tutorial is deprecated
		//ClassPathXmlApplicationContext implements close() so use it instead of ApplicationContext
		//ApplicationContext is the big brother of BeanFactory - does everything and more!
		ClassPathXmlApplicationContext context= new ClassPathXmlApplicationContext("spring.xml");
		
		Shape shape = (Shape) context.getBean("triangle");
		shape.draw();
		
		Shape shape2 = (Shape) context.getBean("circle");
		shape2.draw();
		context.close();
	}
}
