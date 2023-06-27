package cl.araucana.wsatento.common.util;

public class RutUtil {
	public static boolean validaRut(String rutCompleto){
		try{
			if(rutCompleto == null || rutCompleto.trim().equals(""))
				return false;

			String rutStr = "";
			String dvStr = "";
			
			if(rutCompleto.indexOf("-") > -1){
				if(rutCompleto.split("-").length < 2 || rutCompleto.split("-").length > 2){
					return false;
				}else{
					rutStr = rutCompleto.split("-")[0].trim();
					dvStr = rutCompleto.split("-")[1].trim().toUpperCase();
				}
			}else{
				rutStr = rutCompleto.trim().substring(0, rutCompleto.length() -1);
				dvStr = rutCompleto.trim().substring(rutCompleto.length() - 1).toUpperCase();
			}
			
			if(dvStr.equals("") || dvStr.length() > 1 || Integer.parseInt(rutStr) == 0)
				return false;
				
			return validaRut(Integer.parseInt(rutStr), dvStr.charAt(0));
		}catch(Exception e){
			return false;
		}
	}
	
	public static boolean validaRut(int rut, char dv)
    {
        int m = 0, s = 1;
        for (; rut != 0; rut /= 10)
        {
            s = (s + rut % 10 * (9 - m++ % 6)) % 11;
        }
        return dv == (char) (s != 0 ? s + 47 : 75);
    }
	
	public static Integer getParteEnteraRut(String rut){
		String parteEntera = "";
		if(rut.indexOf("-") > -1){
			parteEntera = rut.split("-")[0].trim();
		}else{
			parteEntera= rut.trim().substring(0, rut.length() -1);
		}
		
		return new Integer(Integer.parseInt(parteEntera));
	}
	
	public static String getDVRut(String rut){
		String dv = rut.split("-")[1].trim();
		
		return dv;
	}
	
	public static void main(String []args){
		System.out.println(validaRut("00000000"));
	}
}
