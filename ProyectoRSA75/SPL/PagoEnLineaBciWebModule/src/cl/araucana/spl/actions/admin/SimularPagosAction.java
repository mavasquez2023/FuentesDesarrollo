package cl.araucana.spl.actions.admin;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import cl.araucana.spl.base.AppAction;
import cl.araucana.spl.base.Constants;
import cl.araucana.spl.beans.ConcluirPago;
import cl.araucana.spl.dao.config.DaoConfig;
import cl.araucana.spl.mgr.PagoBitManager;
import cl.araucana.spl.util.ActionTools;

import com.bh.talon.User;

/**
 * ...
 *
 * <BR>
 *
 * <TABLE BORDER="1" WIDTH="100%" CELLPADDING="3" CELLSPACING="0" SUMMARY="">
 *    <TBODY>
 *        <TR BGCOLOR="#CCCCFF" CLASS="TableHeadingColor">
 *            <TH ALIGN="left" COLSPAN=4> <FONT SIZE="+2">
 *                 <B>Registro de Mantenciones</B></FONT>
 *            </TH>
 *        </TR>
 *
 *        <TR>
 *            <TD align="center"> <B>Fecha</B> </TD>
 *            <TD align="center"> <B>Versión</B> </TD>
 *            <TD> <B>Autor</B> </TD>
 *            <TD align="left"><B>Descripción</B></TD>
 *        </TR>
 *
 *        <TR BGCOLOR="white" CLASS="TableRowColor">
 *            <TD> 13-01-2014 </TD>
 *            <TD align="center"> 1.0 </TD>
 *            <TD> Gonzalo Mallea Lorca <BR> gmallea@schema.cl </TD>
 *            <TD> Versión inicial. </TD>
 *        </TR>
 *
 *        <TR BGCOLOR="white" CLASS="TableRowColor">
 *            <TD>   </TD>
 *            <TD align="center">  </TD>
 *            <TD>   </TD>
 *            <TD>  </TD>
 *        </TR>
 *    </TBODY>
 * </TABLE>
 *
 * <BR>
 *
 * @author Gonzalo Mallea Lorca (gmallea@schema.cl)
 *
 * @version 1.0
 */

public class SimularPagosAction extends AppAction {
	private static final Logger logger = Logger.getLogger(SimularPagosAction.class);
	private String target ="";
	protected ActionForward doTask(User user, ActionMapping mapping,
			ActionForm f, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		//ActionMessages am = new ActionMessages();
		String xml ="";
		String idPago="";
		boolean resp= false;
		try {
			idPago = request.getParameter("idPago");
			String banco = (String)session.getAttribute("bancoSeleccionado");
			
			int idPagoInt = Integer.parseInt(idPago);
	
			List listConcluirPago = (ArrayList)session.getAttribute("listConcluirPago");
			ConcluirPago concluirPago = null;
			for (Iterator iter = listConcluirPago.iterator(); iter.hasNext();) {
					concluirPago = (ConcluirPago)iter.next();
					if(concluirPago.getIdPago().intValue() == idPagoInt)
						break;
			}
			if(banco.trim().equals("1") || banco.trim().equals("2")){
				
				HashMap hasParameter =  new HashMap();
				
				target="pagobci";
				//request.setAttribute("contexto", target);
				//request.setAttribute("trx", ""+concluirPago.getIdPago());
				//request.setAttribute("estado",""+ Constants.BCI_ESTADO_CARGO_APLICADO);//
				
				hasParameter.put("trx",  ""+concluirPago.getIdPago());
				hasParameter.put("estado",  ""+ Constants.BCI_ESTADO_CARGO_APLICADO);
						
				resp = this.getContenidoHTML(request, response, hasParameter);
			}else if(banco.trim().equals("7")){
				SimpleDateFormat formatterSimple = new SimpleDateFormat("yyyyMMdd");
				HashMap hasParameter =  new HashMap();
				target="pagobbv";
				
				//request.setAttribute("contexto", target);
				//request.setAttribute("transaccion", ""+concluirPago.getIdPago());
				//request.setAttribute("fecha", new Date());
				//request.setAttribute("monto", concluirPago.getTotal());
				//request.setAttribute("estado", "000");
				//request.setAttribute("mensaje", "");
				
				hasParameter.put("transaccion",  ""+concluirPago.getCod_idtransaccion());
				hasParameter.put("fecha", formatterSimple.format(new Date()));
				hasParameter.put("monto",  concluirPago.getTotal() );
				hasParameter.put("estado", "000");
				hasParameter.put("mensaje", "");
				
				resp = this.getContenidoHTML(request, response, hasParameter);
				
			}else{
				
				HashMap hasParameter =  new HashMap();
				
				xml = this.generaXMLPago(concluirPago, banco);	
				//request.setAttribute("contexto", target);
				//request.setAttribute("xml", xml);
				
				hasParameter.put("xml", xml);
				
				resp =this.getContenidoHTML(request, response, hasParameter);
			}
			
		} finally {
			DaoConfig.endTransaction();
		}
		
		if(resp){
			ActionTools.addMessage(request, new ActionMessage("admin.concluir.pago.ok",idPago));			
		}else{
			ActionTools.addMessage(request, new ActionMessage("admin.concluir.pago.nok",idPago));
		}
		return mapping.findForward("ok");
	}
	
	private String generaXMLPago (ConcluirPago concluirPago, String banco){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		SimpleDateFormat formatterHora = new SimpleDateFormat("HHmmss");
		SimpleDateFormat formatterSimple = new SimpleDateFormat("yyyyMMdd");
		StringBuffer xml = new StringBuffer();
		
		if(banco.trim().equals("3")){
			//Banco Chile
			xml.append("<MPOUT>");
			xml.append("<CODRET>000</CODRET>");
			xml.append("<DESCRET>Notificacion OK</DESCRET>");
			xml.append("<IDCOM>"+concluirPago.getId_Contrato()+"</IDCOM>");
			xml.append("<IDTRX>"+concluirPago.getCod_idtransaccion()+"</IDTRX>");
			xml.append("<TOTAL>"+concluirPago.getTotal()+"</TOTAL>");
			xml.append("<NROPAGOS>"+concluirPago.getNro_pagos()+"</NROPAGOS>");
			xml.append("<FECHATRX>"+formatter.format(new Date())+"</FECHATRX>");
			xml.append("<FECHACONT>"+formatterSimple.format(concluirPago.getFch_contable())+"</FECHACONT>");
			xml.append("<NUMCOMP>"+concluirPago.getIdPago()+"</NUMCOMP>");
			xml.append("<IDREG>"+concluirPago.getIdPago()+"</IDREG>");
			xml.append("<INDPAGO>"+concluirPago.getIdPago()+"</INDPAGO>");			
			xml.append("</MPOUT>");
			target="pagobch";
		}else if(banco.trim().equals("4")){
			//Banco Santander Santiago
			xml.append("<MPOUT>");
			xml.append("<CODRET>000</CODRET>");
			xml.append("<DESCRET>Notificacion OK</DESCRET>");
			xml.append("<IDCOM>"+concluirPago.getId_Contrato()+"</IDCOM>");
			xml.append("<IDTRX>"+concluirPago.getCod_idtransaccion()+"</IDTRX>");
			xml.append("<TOTAL>"+concluirPago.getTotal()+"</TOTAL>");
			xml.append("<NROPAGOS>"+concluirPago.getNro_pagos()+"</NROPAGOS>");
			xml.append("<FECHATRX>"+formatter.format(new Date())+"</FECHATRX>");
			xml.append("<FECHACONT>"+formatterSimple.format(concluirPago.getFch_contable())+"</FECHACONT>");
			xml.append("<NUMCOMP>"+concluirPago.getIdPago()+"</NUMCOMP>");
			xml.append("<IDREG>"+concluirPago.getIdPago()+"</IDREG>");
			xml.append("<INDPAGO>"+concluirPago.getIdPago()+"</INDPAGO>");
			xml.append("</MPOUT>");
			target="pagobsa";
		}else if(banco.trim().equals("5")){
			//Banco Estado			
			xml.append("<INICIO>");							
			xml.append("<ENCABEZADO>");						
			xml.append("<ID_SESION>"+concluirPago.getIdPago()+"</ID_SESION>");
			xml.append("</ENCABEZADO>");						
			xml.append("<MULTIPAGO>");						
			xml.append("<GLOSA_MPAGO/>");
			xml.append("<ID_MPAGO>"+concluirPago.getCod_idtransaccion()+"</ID_MPAGO>");
			xml.append("<PAGO/>");					
			xml.append("<RESULTADO>");					
			xml.append("<RESULT_MPAGO>OK</RESULT_MPAGO>");					         
			xml.append("<GLOSA_ERR/>");
			xml.append("<TRX_MPAGO>"+concluirPago.getCod_idtransaccion()+"</TRX_MPAGO>");					         
			xml.append("<FEC_MPAGO>"+formatter.format(new Date())+"</FEC_MPAGO>");			   
			xml.append("<HORA_MPAGO>"+formatterHora.format(new Date())+"</HORA_MPAGO>");   
			xml.append("<FEC_CNTBL_MPAGO>"+formatter.format(concluirPago.getFch_contable())+"</FEC_CNTBL_MPAGO>");		    
			xml.append("</RESULTADO>");
			xml.append("</MULTIPAGO>");					
			xml.append("</INICIO>");
			
			target="pagobes";
		}else if(banco.trim().equals("6")){
			//Banco ITAU
			xml.append("<MPOUT>");
			xml.append("<CODRET>000</CODRET>");
			xml.append("<DESCRET>Transaccion OK</DESCRET>");
			xml.append("<IDCOM>"+concluirPago.getId_Contrato()+"</IDCOM>");
			xml.append("<IDTRX>"+concluirPago.getCod_idtransaccion()+"</IDTRX>");
			xml.append("<TOTAL>"+concluirPago.getTotal()+"</TOTAL>");
			xml.append("<NROPAGOS>"+concluirPago.getNro_pagos()+"</NROPAGOS>");
			xml.append("<FECHATRX>"+formatter.format(new Date())+"</FECHATRX>");
			xml.append("<FECHACONT>"+formatterSimple.format(concluirPago.getFch_contable())+"</FECHACONT>");
			xml.append("<FECHACONT>"+concluirPago.getIdPago()+"</FECHACONT>");
			xml.append("<NUMCOMP>"+concluirPago.getIdPago()+"</NUMCOMP>");
			xml.append("</MPOUT>");
			target="pagobit";
		}
		logger.debug("XML Generado : " + xml.toString());
		return xml.toString();
	}
	
	private boolean getContenidoHTML(HttpServletRequest request, HttpServletResponse response, HashMap parameter) throws IOException, ServletException {
		boolean respuesta=false;
		HttpURLConnection connection = null;
		try {
			
			PagoBitManager bitManager  = new PagoBitManager();
			String ipSPL = bitManager.getIpSPLPrivada();
			
			String postParams="";
			String urlString =ipSPL.trim();
			
			if(target.equals("pagobch")){
				postParams="TX="+parameter.get("xml");
				urlString= urlString+Constants.URL_Notificacion_Pago_BCH;
				
			}else if(target.equals("pagobsa")){
				postParams="TX="+parameter.get("xml");
				urlString= urlString+Constants.URL_Notificacion_Pago_BSA;
				
			}else if(target.equals("pagobes")){
				postParams ="xml="+parameter.get("xml");
				urlString= urlString+Constants.URL_Notificacion_Pago_BSE;
				
			}else if(target.equals("pagobit")){
				postParams=""+parameter.get("xml");
				urlString= urlString+Constants.URL_Notificacion_Pago_BIT;
				
			}else if(target.equals("pagobci")){
				postParams="trx="+parameter.get("trx")+"&estado="+parameter.get("estado");				
				urlString= urlString+Constants.URL_Notificacion_Pago_BCI;
				
			}else if(target.equals("pagobbv")){
				postParams="transaccion="+parameter.get("transaccion")+
						   "&fecha="+parameter.get("fecha")+
						   "&monto="+parameter.get("monto")+
						   "&estado="+parameter.get("estado")+
						   "&mensaje="+parameter.get("mensaje");
				urlString= urlString+Constants.URL_Notificacion_Pago_BBV;
				
			}
			logger.debug("Parametros a Enviar.. : " + postParams);
			logger.debug("URL Notificacion.. : "+urlString);
			
		    URL url = new URL(urlString);
		    	    
		    connection = (HttpURLConnection) url.openConnection();
		    connection.setRequestMethod("POST");
	        connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
	        connection.setRequestProperty("Content-Length", ""+ Integer.toString(postParams.getBytes().length));
	        connection.setRequestProperty("Content-Language", "en-US");
	        	        
	        connection.setUseCaches(false);
	        connection.setDoInput(true);
	        connection.setDoOutput(true);
	        
	        DataOutputStream wr = new DataOutputStream(connection
	                .getOutputStream());
	        wr.writeBytes(postParams);
	        wr.flush();
	        wr.close();
	
	        InputStream is = connection.getInputStream();
	        BufferedReader rd = new BufferedReader(new InputStreamReader(is));
	        
        	if(target.equals("pagobes")){
        		respuesta=true;
        	}
	        
	        String line;
	        while ((line = rd.readLine()) != null) {
	        	if(target.equals("pagobci")){
	        		if(line.toUpperCase().indexOf("ESTADO=21") > 0){
		        		respuesta=true;
		        	}
	        	}else if(line.toUpperCase().indexOf("<NOTIFICA>OK</NOTIFICA>") == 0){
	        		respuesta=true;
	        	}
	        	logger.debug("Respuesta Notificacion.. : " + line);
	        }
	        rd.close();
	        return respuesta;

    } catch (Exception e) {

        e.printStackTrace();
        return false;

    } finally {

        if (connection != null) {
            connection.disconnect();
        }
    }
	}
}
