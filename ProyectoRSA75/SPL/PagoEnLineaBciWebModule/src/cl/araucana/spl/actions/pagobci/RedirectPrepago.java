/**
 * 
 */
package cl.araucana.spl.actions.pagobci;

import javax.servlet.http.HttpServletResponse;

/**
 * @author 11648834-5
 *
 */
public class RedirectPrepago extends Thread {
	HttpServletResponse resp;
	String url;
	public RedirectPrepago(HttpServletResponse resp, String url) {
		this.resp=resp;
		this.url=url;
	}
	
	public void run(){
        System.out.println(getName()+" iniciando.");
    try {
    	resp.sendRedirect(url);
    }catch (Exception exc){
        System.out.println(getName()+ " interrumpido.");
    }
        System.out.println(getName()+ " finalizando.");
    }
}
