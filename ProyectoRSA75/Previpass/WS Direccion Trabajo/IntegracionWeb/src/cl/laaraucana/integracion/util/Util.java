package cl.laaraucana.integracion.util;

import org.apache.log4j.Logger;

public class Util {
	private static Logger log = Logger.getLogger(Util.class);
	public static String[] meses= {"01","02","03","04","05","06","07","08","09","10","11","12"};
	
	public static boolean validaAtributosDirTrabajo(String param1, String param2, String param3)
	{
		
		if(! param1.equals("rut_empleador"))
		   return false;
		
		if(! param2.equals("rut_trabajador"))
			 return false;
		
		if(! param3.equals("periodo"))
			 return false;
		
		
		return true;
	}
	
	public static boolean validaAtributosAut(String param1, String param2)
	{
		
		if(! param1.equals("usuario"))
		   return false;
		
		if(! param2.equals("password"))
			 return false;
		
		
		return true;
	}
	
	public static String[] validaUsuario(String usuario, String password, String tipoAut){
		
		String usuarioProp = Constantes.getInstancia().usuario;
		String passProp = Constantes.getInstancia().password;
		int seleccionado =0;
		
		String respuesta[] = new String[2];
		respuesta[0] = "0";
		
		StringBuffer textoArchivo = new StringBuffer();
		
		// SI el tipo no es aut entonces no se autentica, se retorna respuesta
		
		textoArchivo.append(UtilXML.abrirTag("respuestaservicio","tipo=\"AUT\""));
		//textoArchivo.append("\n");
		
		if(! tipoAut.equals("AUT"))
		{
			
			textoArchivo.append(UtilXML.generarTag2("control","codigo=\"9060\""));
			//textoArchivo.append("\n");
			
		}else{
		
		
			if(usuario.equals("") || usuario.equals(""))
			{
				textoArchivo.append(UtilXML.generarTag2("control","codigo=\"9100\""));
			    //textoArchivo.append("\n");
			}else
				if(password.equals("") || password.equals(""))
				{
					textoArchivo.append(UtilXML.generarTag2("control","codigo=\"9110\""));
				    //textoArchivo.append("\n");
					
				}else
					if(usuarioProp.equals(usuario) && passProp.equals(password))
					{
						
						
						textoArchivo.append(UtilXML.generarTag2("control","codigo=\"9050\""));
						//textoArchivo.append("\n");
						
						textoArchivo.append(UtilXML.abrirTag("respuestaaut"));
						//textoArchivo.append("\n");
						
						seleccionado = (int) Math.round((Math.random()*100000000)); 
						
						textoArchivo.append(UtilXML.generarTag("llave", "", String.valueOf(seleccionado)));
						//textoArchivo.append("\n");
						
						textoArchivo.append(UtilXML.cerrarTag("respuestaaut"));
						//textoArchivo.append("\n");
						
						

						respuesta[0] = "1";
					}
					else
					{
						textoArchivo.append(UtilXML.generarTag2("control","codigo=\"9120\""));
						//textoArchivo.append("\n");
						
						
					}
			}

		
			textoArchivo.append(UtilXML.cerrarTag("respuestaservicio"));
			//textoArchivo.append("\n");
			
			respuesta[1] = textoArchivo.toString();
			
		
		return respuesta;
		
		
		
	}
	
	public static String[] validaDirTrabajo(String rutEmpleador, String rutTrabajador, 
			String tipoDirTrabajo, String mes, String agno)
	{
		
		
		String respuesta[] = new String[2];
		respuesta[0] = "1";
		
		StringBuffer textoArchivo = new StringBuffer();
		
		// SI el tipo no es dirtrabajo entonces no se autentica, se retorna respuesta
		
		textoArchivo.append(UtilXML.abrirTag("respuestaservicio","tipo=\"SUBCONTR\""));
		//textoArchivo.append("\n");
		
		if(! tipoDirTrabajo.equals("SUBCONTR"))
		{
			log.warn("Tipo petición no válida:" + tipoDirTrabajo);
			textoArchivo.append(UtilXML.generarTag2("control","codigo=\"9060\""));
			//textoArchivo.append("\n");
			respuesta[0] = "0";
			
		}else{
		
		     // se valida el rut del empleador.
			
			if(!validaRut(rutEmpleador))
			{	
				log.warn("Rut Empleador no válido:" + rutEmpleador);
				textoArchivo.append(UtilXML.generarTag2("control","codigo=\"9710\""));
				//textoArchivo.append("\n");
				respuesta[0] = "0";
				
			}else
				//se valida rut de trabajador 
				if(!validaRut(rutTrabajador))
				{	
					log.warn("Rut Trabajador no válido:" + rutTrabajador);
					textoArchivo.append(UtilXML.generarTag2("control","codigo=\"9720\""));
					//textoArchivo.append("\n");
					respuesta[0] = "0";
					
				}else
					//se valida periodo
					if(!validaPeriodo(mes, agno))
					{	
						log.warn("Mes Año no válido:" + mes + ", " + agno);
						textoArchivo.append(UtilXML.generarTag2("control","codigo=\"9730\""));
						//textoArchivo.append("\n");
						respuesta[0] = "0";
						
					}
						
		}
		
		textoArchivo.append(UtilXML.cerrarTag("respuestaservicio"));
		//textoArchivo.append("\n");
		
		respuesta[1] = textoArchivo.toString();
		
		return respuesta;
		
	}
	
	public static boolean validaPeriodo(String mes, String agno)
	{
		
		int i=0;
		boolean mesesOk = false;
		boolean agnoOk = false;
		if(mes!= null && agno!= null){
			for(i=0;i < meses.length; i++)
			{

				if(meses[i].equals(mes)){
					mesesOk = true;
					break;
				}

			}


			if( (Integer.parseInt(agno) > 1900 ) && ( Integer.parseInt(agno) < 2200))
				agnoOk = true;

		}
		
		if(agnoOk && mesesOk)
		  return true;
		else
	      return false;
		
		
	}
	
	public static String agregarEspaciosIzquierda(String cadena, int cantCaracteres)
	{
		//cadena es string al cual se le quiere agregar los espacios en izquierda
		// cantCaracteres es la cant de caracteres a rellenar 
		int diferencia =0;
		int i=0;
				
		    if( cadena.length() < cantCaracteres)
		    {
		    	
		    	 diferencia = cantCaracteres - cadena.length();
		    	
		    	for(i=0; i < diferencia ; i++)
		    		cadena = " " + cadena;
		    
		    	
		    }
		    		
		return cadena;
		
		
		
	}
	
	
	public static boolean validaRut(String rut)
	{
		
		int i=0;
        int resultado =0;
        int factor =2;
        int resto=0;
        int dig_v=0;
        String rutStr=null;
        String dv = null;
        //rutStr= rut;
        
        String digito=null;
        
        
        	
	        // si el rut no contiene guion se devuelve false
	        if(rut.indexOf("-") == -1)
	        return false;
	        else {
	        	
	        	try{
	        	
	        	rutStr = rut.split("-")[0];
	        	dv = rut.split("-")[1];
	        
	       
	        	}catch(Exception e){
	        		
	        		return false;
	        	}
	        	
	        	
	        }

            if(dv.equals("") || rutStr.equals(""))
            return false;
       
  
              i = rutStr.length() - 1;
  
                 while (i >= 0)
                 {
                                   
                   resultado = (Integer.parseInt(rutStr.charAt(i) + "") * factor ) + resultado;
                   factor++;
                                    
                     if(factor == 8)
                        factor = 2;
  
                     i--;
  
                  }
  
                  resto = (resultado % 11);
  
             
                   if (resto==1)
                	      digito = "K";
                   else if (resto==0)
                	   digito = "0";
                	   else
                	   {
                		   digito = Integer.toString(11-resto);
                	      //dvr = dvi + "";
                	   }
                   
                   if(digito.equals(dv.toUpperCase()))
                	   return true;
                   else
                	   return false;
		
		
		
	}
	

}
