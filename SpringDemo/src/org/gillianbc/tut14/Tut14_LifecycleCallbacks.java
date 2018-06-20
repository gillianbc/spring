package org.gillianbc.tut14;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class Tut14_LifecycleCallbacks {
	public static void main(String[] args){
		
		AbstractApplicationContext context= new ClassPathXmlApplicationContext("spring14.xml");
		//see the warning if you comment out the line below - only matters in a desktop app, not web
		//This closes down the ApplicationContext
		context.registerShutdownHook();
		Triangle triangle = (Triangle) context.getBean("triangle");
		triangle.draw();
	}
}
