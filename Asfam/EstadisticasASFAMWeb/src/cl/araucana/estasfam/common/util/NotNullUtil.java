package cl.araucana.estasfam.common.util;

public class NotNullUtil {
	
	public static String replaceNullToBlank(String obj){
		if(obj!=null){
			return obj;
		}else{
			return "";
		}
	}
	
	public static String replaceNullToDate(String obj){
		if(obj!=null){
			return obj;
		}else{
			return "1900-01-01";
		}
	}
	
	public static Integer replaceNullToCero(Integer obj){
		if(obj!=null){
			return (Integer)obj;
		}else{
			return 0;
		}
	}
	
	public static Long replaceNullToCero(Long obj){
		if(obj!=null){
			return (Long)obj;
		}else{
			return 0L;
		}
	}
	
	public static Short replaceNullToCero(Short obj){
		if(obj!=null){
			return (Short)obj;
		}else{
			return 0;
		}
	}
	
	public static void main(String [] args){
		System.out.println("a: " + replaceNullToBlank("ad"));
	}
}

