/**
 * 
 */
package cl.recursos;

/**
 * @author usist24
 *
 */

public class EjecutarBAT {
	public static void main(String[] args){
		Runtime aplicacion = Runtime.getRuntime(); 
        try{
        	System.out.println("in");
        	aplicacion.exec("cmd.exe /K start \\temp\\printfac2.bat"); 
        	System.out.println("out");
        	}
        catch(Exception e){
        	System.out.println(e);
        }

	}
}
