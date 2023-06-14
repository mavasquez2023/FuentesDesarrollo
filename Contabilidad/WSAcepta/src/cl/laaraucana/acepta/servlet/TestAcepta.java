/**
 * 
 */
package cl.laaraucana.acepta.servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.axis.types.NonNegativeInteger;
import org.apache.axis.types.PositiveInteger;

import com.acepta.soap.ca4xml.BOLETADefType;
import com.acepta.soap.ca4xml.BOLETADefTypeDetalle;
import com.acepta.soap.ca4xml.BOLETADefTypeEncabezado;
import com.acepta.soap.ca4xml.BOLETADefTypeEncabezadoEmisor;
import com.acepta.soap.ca4xml.BOLETADefTypeEncabezadoIdDoc;
import com.acepta.soap.ca4xml.BOLETADefTypeEncabezadoIdDocIndServicio;
import com.acepta.soap.ca4xml.BOLETADefTypeEncabezadoReceptor;
import com.acepta.soap.ca4xml.BOLETADefTypeEncabezadoTotales;
import com.acepta.soap.ca4xml.Ca4XmlDatos;
import com.acepta.soap.ca4xml.Ca4XmlResponse;
import com.acepta.soap.ca4xml.Ca4XmlResponseRetval;
import com.acepta.soap.ca4xml.Ca4Xml_BindingStub;
import com.acepta.soap.ca4xml.Ca4Xml_Service;
import com.acepta.soap.ca4xml.DTEDefType;
import com.acepta.soap.ca4xml.DTEType;


/**
 * @author IBM Software Factory
 *
 */
public class TestAcepta extends HttpServlet{
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Ca4XmlResponseRetval resultado=null;
		try {
			Ca4Xml_BindingStub stub= new Ca4Xml_BindingStub();
			stub._setProperty(Ca4Xml_BindingStub.ENDPOINT_ADDRESS_PROPERTY, "http://172.22.6.85:5001/ca4xml");
			
			BOLETADefType boleta= new BOLETADefType();
			
			//ENCABEZADO
			BOLETADefTypeEncabezado encabezado= new BOLETADefTypeEncabezado();
			
			//IdDOC
			BOLETADefTypeEncabezadoIdDoc idDoc= new BOLETADefTypeEncabezadoIdDoc();
			idDoc.setTipoDTE(DTEType.fromString("39"));
			idDoc.setFolio(new PositiveInteger("1000000001"));
			idDoc.setFchEmis(new Date());
			idDoc.setIndServicio(BOLETADefTypeEncabezadoIdDocIndServicio.fromString("3"));
			encabezado.setIdDoc(idDoc);
			
			//EMISOR
			BOLETADefTypeEncabezadoEmisor emisor= new BOLETADefTypeEncabezadoEmisor();
			emisor.setRUTEmisor("70016160-9");
			emisor.setRznSocEmisor("CCAF La Araucana");
			emisor.setGiroEmisor("Caja de Compensacion");
			emisor.setDirOrigen("Merced 472");
			emisor.setCiudadOrigen("Santiago");
			emisor.setCmnaOrigen("Santiago");
			encabezado.setEmisor(emisor);
			
			//RECETOR
			BOLETADefTypeEncabezadoReceptor receptor= new BOLETADefTypeEncabezadoReceptor();
			receptor.setRUTRecep("11648834-5");
			receptor.setRznSocRecep("Claudio Lillo");
			encabezado.setReceptor(receptor);
			
			//Totales
			BOLETADefTypeEncabezadoTotales totales= new BOLETADefTypeEncabezadoTotales();
			totales.setMntNeto(new NonNegativeInteger("20000"));
			totales.setIVA(new PositiveInteger("3800"));
			totales.setMntTotal(new NonNegativeInteger("23800"));
			encabezado.setTotales(totales);
			
			//Se setea Encabezado
			boleta.setEncabezado(encabezado);
			
			//DETALLE
			BOLETADefTypeDetalle[] detalles= new BOLETADefTypeDetalle[1];
			BOLETADefTypeDetalle detalle= new BOLETADefTypeDetalle();
			detalle.setNroLinDet(new PositiveInteger("1"));
			detalle.setNmbItem("Teleasistencia");
			detalle.setDscItem("Cuota Servicio de Teleasistencia");
			detalle.setQtyItem(new BigDecimal(1.0));
			detalle.setUnmdItem("UNID");
			detalle.setPrcItem(new BigDecimal(20000.0));
			detalle.setMontoItem(new NonNegativeInteger("20000"));
			detalles[0]= detalle;
			
			//Se setea Detalle
			boleta.setDetalle(detalles);
			
			//FIRMA
			Calendar cal = Calendar.getInstance();
			
			boleta.setTmstFirma(cal);
			
			Ca4XmlDatos datos= new Ca4XmlDatos();
			DTEDefType type= new DTEDefType();
			type.setBoleta(boleta);
			datos.setDTE(type);
			
			 resultado=  stub.ca4Xml("50005151", "emitir", "Local,1", datos);
			
			if(resultado!=null){
				response.getOutputStream().println("Estado=" + resultado.getEstado());
				response.getOutputStream().println("Folio=" + resultado.getFolio());
				response.getOutputStream().println("Glosa=" + resultado.getGlosa());
				response.getOutputStream().println("URL DTE=" + resultado.getUrlDte());
			}else{
				response.getOutputStream().println("Sin Resultado");
			}
		} catch (Exception e) {
			response.getOutputStream().println("Error:" + e.getMessage());
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
