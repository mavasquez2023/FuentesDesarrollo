package cl.platzi.hibernate;

import cl.platzi.hibernate.dao.Data;
import cl.platzi.hibernate.entities.Course;

public class App2 {

	public static void main(String[] args) {
		 
	 Data<Course> dao = new Data<Course>();
		
	// Course c = new Course();
	 
//	 dao.delete(Course.class, 3);
	// dao.save(c);
	 
	 for (Course courses : dao.read(Course.class)) {
		
		 System.out.println(courses.getName());
	}
	 
	}
	
	 
}