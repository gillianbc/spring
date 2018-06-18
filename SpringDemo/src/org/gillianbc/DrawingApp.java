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
		
		Triangle triangle = (Triangle) context.getBean("triangle");
		triangle.draw();
		
		Triangle triangle2 = (Triangle) context.getBean("triangle2");
		triangle2.draw();
		
		Triangle triangle3 = (Triangle) context.getBean("triangle3");
		triangle3.draw();
		Triangle triangle4 = (Triangle) context.getBean("triangle4");
		triangle4.draw();
		
		
		context.close();
	}
}
