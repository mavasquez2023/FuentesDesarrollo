package cl.platzi.hibernate.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


@SuppressWarnings("rawtypes")
public class Data<T> {
	
	 private static Session con() {
		 
		   SessionFactory sesionfactory;
	       Configuration configuration = new Configuration();
	       configuration.configure();
	       sesionfactory = configuration.buildSessionFactory();
	       Session session = sesionfactory.openSession();
	       session.beginTransaction();
	       
	       return session;
		 
	 }
	
	public  void save(T entity) {
		
		  Session session = con();
	
		   session.save(entity);
	        
		   session.getTransaction().commit();
		 
		   session.close();
		   
	 }
	 
	@SuppressWarnings("unchecked")
	 public void delete(Class T, long id) {
		 
		 Session session = con();
 
		T entity = (T) session.get(T, id);
		 
		 session.remove(entity);
		 
		 session.getTransaction().commit();
		 
		 session.close();
	 }
	 
	 public void update(T entity) {
		 
		 Session session = con();
	
		 session.update(con().merge(entity));
		 
		 session.getTransaction().commit();
				
		 session.close();
	 }
	 
	
	@SuppressWarnings("unchecked")
	public List<T> read(Class T) {
		
		List<T> ret = new ArrayList<T>();
	
		 Session session = con();
	   //  ret =  session.getNamedQuery("Course.findAll").getResultList();
	       ret = con().createQuery("from " + T.getName()).getResultList();
		
	    session.getTransaction().commit();
	    
	    session.close();
	    
	    return ret;
		
	 }
}
