package publicacion;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utilPub.UtilPub;

public class frame_izquierdo extends HttpServlet {

    public void doGet(HttpServletRequest request,HttpServletResponse response)
            throws IOException, ServletException {

       PrintWriter out = response.getWriter();
// String nombre = request.getParameter("nombre");
	   String ServletPath = this.getServletContext().getRealPath("/index.html");

       String pagina_html="";
//       String rut="",razon="";
       String frametipo_char = request.getParameter("frame_tipo");
            int frame_tipo = Integer.parseInt(frametipo_char);

            switch (frame_tipo) {
                case 1:
                    //planilla afp
                    pagina_html = "PlanillaAFP.htm";
                    break;
                case 2:
                    //planilla apv
                    pagina_html = "PlanillaAPV.htm";
                    break;
                case 3:
                    //planilla caja
                    pagina_html = "PlanillaCAJA.htm";
                    break;
                case 4:
                    //planilla INP
                    pagina_html = "PlanillaINP.htm";
                    break;
                case 5:
                    //planilla isapre
                    pagina_html = "PlanillaIsapre.htm";
                    break;
                case 6:
                    //planilla mutual
                    pagina_html = "PlanillaMutual.htm";
                    break;
                case 7:
                    //comprobante de pago
                    pagina_html = "ComprobantePago.htm";
                    break;
                case 8:
                    //certificado de cotizacion
                    pagina_html = "CertifCotiza.htm";
                    break;
                case 9:
                    //informe de cotizacion
                    pagina_html = "InformeCotiza.htm";
                    break;
                case 10:
                    //archivo empresas
                    pagina_html = "PlanillaAFPDNP.htm";
                    break;
                case 11:
                    //planilla afp trabajos pesados
                    pagina_html = "PlanillaAFPTP.htm";
                    break;
                case 12:
                    //planilla cotizacion APC
                    pagina_html = "PlanillaCotAPC.htm";
                    break;
                case 13:
                    //planilla cotizacion APV
                    pagina_html = "PlanillaCotAPV.htm";
                    break;
                case 14:
                    //planilla INP DNP
                    pagina_html = "PlanillaINPDNP.htm";
                    break;
                case 15:
                    //planilla INP Pagadas
                    pagina_html = "PlanillaINPPago.htm";
                    break;
                case 16:
                    //planilla AFP SIL
                    pagina_html = "PlanillaAFPSIL.htm";
                    break;
                case 17:
                    //Certificado anual sence
                    pagina_html = "CertificadoAnual.jsp";
                    break;   
                case 18:
                    //comprobante de pago
                    pagina_html = "ComprobantePagoActual.jsp";
                    break;  
                case 19:
                    //Archivo Certificado Cotizaciones
                    pagina_html = "ArchivoCertificadoCot.jsp";
                    break; 
                case 20:
                    //Archivo Certificado Cotizaciones
                    pagina_html = "AdminHomogacion.jsp";
                    break;
            }

//            frame_izquierdo archivo;
//            archivo = new frame_izquierdo();
//            frame_izquierdo archivo2;
//            archivo2 = new frame_izquierdo();
            String html = "";
//            html = archivo.getHTML(pagina_html, ServletPath);
            html = UtilPub.getHTML(pagina_html, ServletPath);
            response.setContentType("text/html");
            out.println(html);

    }

 }