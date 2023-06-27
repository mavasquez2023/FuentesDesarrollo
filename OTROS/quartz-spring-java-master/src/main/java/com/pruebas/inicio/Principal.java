package main.java.com.pruebas.inicio;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Principal {
	
	public static void main(String args[]){
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/application-context.xml");
		
	}
}
