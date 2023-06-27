package publicacion.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServlet;

import month.listMonth;
import utilPub.UtilPub;
import cl.araucana.cp.distribuidor.base.Utils;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadApvVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadCCAFVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadMutualVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadPensionVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadSaludVO;
import cl.araucana.cp.distribuidor.hibernate.beans.LectorEntidadVO;
import cl.araucana.cp.distribuidor.hibernate.beans.SucursalVO;
import cl.araucana.cp.distribuidor.hibernate.beans.permisos.PermConvenio;
import cl.araucana.cp.distribuidor.hibernate.beans.permisos.PermEmpresa;
import cl.araucana.cp.distribuidor.hibernate.beans.permisos.PermGrupoConvenio;

public class HibernateDao extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4116855331185389103L;
	
	public String setCajaMutual(){
		String cajaMutual = "";
		cajaMutual = "<select name='NombreMutual' id='NombreMutual' size='1' class='campos'><option value=''>Todas</option><option value='ACHS'>Asoc. Chilena Seguridad</option><option value='INSTITUTO'>Inst. Seguridad del Trabajo</option><option value='MUTUAL CAMARA'>Mutual Cám. Chilena Construcción</option></select>";
		return cajaMutual;
	}
	
	public String setCajaCajas(){
		String cajaCajas = "";
		cajaCajas = "<select id='NombreCaja' name='NombreCaja' size='1' class='campos'><option value=''>Todas</option><option value='LA araucana'>La araucana</option><option value='LOS ANDES'>Los Andes</option><option value='GABRIELA MISTRAL'>Gabriela Mistral</option><option value='LOS HEROES'>Los Héroes</option><option value='18'>18 de Septiembre</option></select>";
		return cajaCajas;
	}
	
	public String setCajaIsapre(){
		String cajaIsapre = "";
		cajaIsapre = "<select name='NomIsapre' id='NomIsapre' class='campos' size='1'><option value=''>Todas</option><option value='BANMEDICA'>Banmédica</option><option value='ISAPRE CHUQ'>Chuquicamata</option><option value='COLMENA'>Colmena Golden Cross</option><option value='CONSALUD'>Consalud</option><option value='CRUZ BLANCA'>Cruz Blanca</option><option value='CRUZ DEL'>Cruz del Norte</option><option value='CTC'>CTC - ISTEL</option><option value='FERRO'>Ferrosalud</option><option value='FUND. SALUD'>Fund. Salud Banco del Estado</option><option value='FUNDACION'>Fund. Salud El Teniente</option><option value='ISAPRE ING'>ING Salud S.A.</option><option value='MAS VIDA'>Mas Vida S.A.</option><option value='NORMED'>Normédica</option><option value='PROMEP'>Promepart</option><option value='ISAPRE RIO'>Río Blanco Ltda.</option><option value='SFERA'>Sfera S.A.</option><option value='ISAPRE VIDA PL'>Vida Plena S.A.</option><option value='VIDA TRES'>Vida Tres S.A.</option></select>";
		return cajaIsapre;
	}
	
	public String retornaRutEmpresaFinal(){
		String rut_empresa_final = "";
		rut_empresa_final  = "<input id='RutEmpresa' type='text' size='14' maxlength='12' class='campos' onkeypress=JValidaCaracter('Numerico',''); onchange=VeRut('dv1',this); onblur='DejaRut();'>&nbsp;-&nbsp;<input id='dv1' type='text' size='1' maxlength='1' style='font-family:Arial;font-size:9pt;' onkeypress=JValidaCaracter('Numerico',''); disabled>";
		return rut_empresa_final;
	}
	
	public String retornaRazonSocialFinal(){
		String razon_social_final = "";
		razon_social_final  = "<input id='RazonSocial' type='text' size='28' maxlength='40' class='campos' onkeypress=JValidaCaracter('Texto',''); onblur=DejaRazon();>";
		return razon_social_final;
	}
	
	public String retornaConvenioFinal(){
		String convenio_final = "";
		convenio_final     = "<input id='Convenio' type='text' size='5' maxlength='2' class='campos' onkeypress=JValidaCaracter('Numerico','');>";
		return convenio_final;
	}
	
	public String retornaSucursalFinal(){
		String sucursal_final = "";
		sucursal_final     = "<input id='Sucursal' type='text' size='6' maxlength='6' class='campos' ;>";
		return sucursal_final;
	}
	
	public String construyeCajaMutual(String optionsMutual) {
		String cajaMutual = "";
		cajaMutual  = "<select id='NombreMutual' name='NombreMutual' size='1' class='campos'>" + optionsMutual + "</select>";
		return cajaMutual;
	}
	
	public String construyeCajaCajas(String optionsCajas) {
		String cajaCajas = "";
		cajaCajas  = "<select id='NombreMutual' name='NombreMutual' size='1' class='campos'>" + optionsCajas + "</select>";
		return cajaCajas;
	}
	
	public String construyeCajaAFP(String optionsAFP) {
		String cajaAFP = "";
		cajaAFP  = "<select id='NombreMutual' name='NombreMutual' size='1' class='campos'>" + optionsAFP + "</select>";
		return cajaAFP;
	}
	
	public String construyeCajaAPV(String optionsAPV) {
		String cajaAPV = "";
		cajaAPV  = "<select id='NombreMutual' name='NombreMutual' size='1' class='campos'>" + optionsAPV + "</select>";
		return cajaAPV;
	}
	
	public String construyeCajaIsapre(String optionsIsapre) {
		String cajaIsapre = "";
		cajaIsapre  = "<select id='NombreMutual' name='NombreMutual' size='1' class='campos'>" + optionsIsapre + "</select>";
		return cajaIsapre;
	}
	
	public String componeHtml (String miMenu, String rut_empresa_final, String razon_social_final, String convenio_final, String sucursal_final,
    		String cajaMutual, String cajaCajas, String cajaIsapre, String cajaAFP, String cajaAPV,
    		String num_holding, String nom_usuario, String ini_cotiza, String servletPath) {
        String pagina_html="PaginaPresentacion.htm";
        String html = "";
        html = UtilPub.getHTML(pagina_html, servletPath);

        html = UtilPub.replaceAllInJS(html,"**script**", miMenu);
        
        html = html.replaceAll("[*]{2}nombre[*]{2}", ""); // no hay ID domino
        html = html.replaceAll("[*]{2}rut[*]{2}", rut_empresa_final);
        html = html.replaceAll("[*]{2}razon_social[*]{2}", razon_social_final);
        html = html.replaceAll("[*]{2}convenio[*]{2}", convenio_final);
        html = html.replaceAll("[*]{2}sucursal[*]{2}", sucursal_final);
        html = html.replaceAll("[*]{2}mensaje[*]{2}", "<br>");
        html = html.replaceAll("[*]{2}mutual[*]{2}", cajaMutual);
        html = html.replaceAll("[*]{2}isapre[*]{2}", cajaIsapre);
        html = html.replaceAll("[*]{2}caja[*]{2}", cajaCajas);
        html = html.replaceAll("[*]{2}AFP[*]{2}", cajaAFP);
        html = html.replaceAll("[*]{2}APV[*]{2}", cajaAPV);
		html = html.replaceAll("[*]{2}Holding[*]{2}", num_holding);//aqui se almacena el valor del holding en el HTML
        html = html.replaceAll("[*]{2}Usuario[*]{2}", nom_usuario);//aqui se almacena el nombre del usuario en el HTML

        if (ini_cotiza != null){
        	html = html.replaceAll("[*]{2}Feinicotiza[*]{2}", ini_cotiza);//aqui se almacena la fecha de inicio en el HTML
        }
        else {
            html = html.replaceAll("[*]{2}Feinicotiza[*]{2}", "200001");//aqui se almacena la fecha de inicio en el HTML
        }        
        return html;
    }
	
	 /** 
     * Compone menu extenso
     * @param miMenu
     * @param idDominoe
     * @return
     */
    public String componeMenuExtenso (String miMenu, String idDomino) {
    	//Todo GMALLEA ESTE ES EL MENU
        miMenu += "<script type='text/javascript'>\n";
        miMenu += "<!--\n";                
        miMenu += "stm_bm(['menu0f4d',860,'','Images/newMenu/blank.gif',0,'','',0,0,250,0,1000,1,0,0,'','540',0,0,1,2,'default','hand',''],this);\n";                
        miMenu += "stm_bp('p0',[0,4,0,0,0,0,0,0,100,'',-2,'',-2,50,0,0,'#799BD8','transparent','Images/newMenu/060417line.gif',3,0,0,'#666666','',0,0,0,'transparent','',3,'Images/newMenu/060417buttona2.gif',26,5,0,'transparent','',3,'',0,0,0,'transparent','',3,'Images/newMenu/060417buttona1.gif',26,5,0,'transparent','',3,'','','','',0,0,0,0,0,0,0,0]);\n";

        miMenu += "stm_ai('p0i0',[0,'Planillas\\r\\nCajas','','',-1,-1,0,'frame_izquierdo?nombre=" + idDomino + "&frame_tipo=3','HITLIST','frame_izquierdo?nombre=" + idDomino + "&frame_tipo=3','','','',0,0,0,'','',0,0,0,1,1,'#FFFFF7',1,'#B5BED6',1,'','Images/newMenu/tabbluebutton.gif',2,3,0,0,'#FFFFF7','#666666','#FFFFFF','#666666','bold 7pt Verdana','bold 7pt Verdana',0,0],90,26);\n";
        
        miMenu += "stm_aix('p0i1','p0i0',[0,'Planillas\\r\\nAFPs','','',-1,-1,0,'','_self','','','','',0,0,0,'','',0,0,0,1,1,'#FFFFF7',1,'#B5BED6',1,'','Images/newMenu/tabbluebutton.gif',3],90,26);\n";
        miMenu += "stm_bp('p1',[0,4,-90,0,0,0,0,0,100,'progid:DXImageTransform.Microsoft.Fade(overlap=.5,enabled=0,Duration=0.32)',-2,'progid:DXImageTransform.Microsoft.Fade(overlap=.5,enabled=0,Duration=0.32)',-2,78,0,0,'#799BD8','#EEEEEE','Images/newMenu/060417line1.gif',3,0,0,'#666666']);\n";
        miMenu += "stm_aix('p1i0','p0i0',[2,'','Images/newMenu/060417line1b.gif','Images/newMenu/060417line1b.gif',30,26,0,'','_self','','','','',0,0,0,'','',0,0,0,1,1,'#FFFFF7',1,'#B5BED6',1,'','',3,3,0,0,'#FFFFF7','#666666','#0000FF','#FF6600'],0,26);\n";
        miMenu += "stm_aix('p1i1','p0i0',[0,'Declaración\\r\\ny pago','','',-1,-1,0,'frame_izquierdo?nombre=" + idDomino + "&frame_tipo=1','HITLIST','frame_izquierdo?nombre=" + idDomino + "&frame_tipo=1','','','',0,0,0,'','',0,0,0,1,1,'#FFFFF7',1,'#B5BED6',1,'','',3,3,0,0,'#FFFFF7','#666666','#333399'],70,26);\n";
        miMenu += "stm_aix('p1i2','p1i1',[0,'Trabajos\\r\\nPesados','','',-1,-1,0,'frame_izquierdo?nombre=" + idDomino + "&frame_tipo=11','HITLIST','frame_izquierdo?nombre=" + idDomino + "&frame_tipo=11','','','',0,0,0,'','',0,0,0,1,1,'#FFFFF7',1,'#B5BED6',1,'','',3,3,0,0,'#FFFFF7','#666666','#333399'],70,26);\n";
        miMenu += "stm_aix('p1i3','p1i1',[0,'Afiliado\\r\\nVoluntario','','',-1,-1,0,'frame_izquierdo?nombre=" + idDomino + "&frame_tipo=13','HITLIST','frame_izquierdo?nombre=" + idDomino + "&frame_tipo=13','','','',0,0,0,'','',0,0,0,1,1,'#FFFFF7',1,'#B5BED6',1,'','',3,3,0,0,'#FFFFF7','#666666','#333399'],70,26);\n";
        miMenu += "stm_aix('p1i4','p1i3',[0,'Declaración y\\r\\nno pago','','',-1,-1,0,'frame_izquierdo?nombre=" + idDomino + "&frame_tipo=10','HITLIST','frame_izquierdo?nombre=" + idDomino + "&frame_tipo=10','','','',0,0,0,'','',0,0,0,1,1,'#FFFFF7',1,'#B5BED6',1,'','',3,3,0,0,'#FFFFF7','#666666','#333399'],70,26);\n";
        miMenu += "stm_aix('p1i4','p1i3',[0,'Subsidio\\r\\nIncap. Laboral','','',-1,-1,0,'frame_izquierdo?nombre=" + idDomino + "&frame_tipo=16','HITLIST','frame_izquierdo?nombre=" + idDomino + "&frame_tipo=16','','','',0,0,0,'','',0,0,0,1,1,'#FFFFF7',1,'#B5BED6',1,'','',3,3,0,0,'#FFFFF7','#666666','#333399'],70,26);\n";
        
        miMenu += "stm_aix('p1i5','p1i0',[2,'','Images/newMenu/060417line1a.gif','Images/newMenu/060417line1a.gif'],0,26);\n";
        miMenu += "stm_ep();\n";

        miMenu += "stm_aix('p0i2','p0i1',[0,'Planillas\\r\\nAPVs'],90,26);\n";
        miMenu += "stm_bpx('p2','p1',[0,4,-80]);\n";
        miMenu += "stm_aix('p2i0','p1i0',[],0,26);\n";
        miMenu += "stm_aix('p2i1','p1i3',[0,'Planillas\\r\\nAPV','','',-1,-1,0,'frame_izquierdo?nombre=" + idDomino + "&frame_tipo=2','HITLIST','frame_izquierdo?nombre=" + idDomino + "&frame_tipo=2','','','',0,0,0,'','',0,0,0,1,1,'#FFFFF7',1,'#B5BED6',1,'','',3,3,0,0,'#FFFFF7','#666666','#333399'],65,26);\n";
        miMenu += "stm_aix('p2i2','p1i3',[0,'Planillas APV\\r\\nColectivo','','',-1,-1,0,'frame_izquierdo?nombre=" + idDomino + "&frame_tipo=12','HITLIST','frame_izquierdo?nombre=" + idDomino + "&frame_tipo=12','','','',0,0,0,'','',0,0,0,1,1,'#FFFFF7',1,'#B5BED6',1,'','',3,3,0,0,'#FFFFF7','#666666','#333399'],65,26);\n";
        miMenu += "stm_aix('p2i3','p1i5',[],0,26);\n";
        miMenu += "stm_ep();\n";

        miMenu += "stm_aix('p0i3','p0i1',[0,'Planillas\\r\\nIsapres','','',-1,-1,0,'frame_izquierdo?nombre=" + idDomino + "&frame_tipo=5','HITLIST','frame_izquierdo?nombre=" + idDomino + "&frame_tipo=5','','','',0,0,0,'','',0,0,0,1,1,'#FFFFF7',1,'#B5BED6',1,'','Images/newMenu/tabbluebutton.gif',2,3,0,0,'#FFFFF7','#666666','#FFFFFF','#666666','bold 7pt Verdana','bold 7pt Verdana',0,0],90,26);\n";
        
        miMenu += "stm_aix('p0i4','p0i3',[0,'Planillas\\r\\nMutuales','','',-1,-1,0,'frame_izquierdo?nombre=" + idDomino + "&frame_tipo=6','HITLIST','frame_izquierdo?nombre=" + idDomino + "&frame_tipo=6','','','',0,0,0,'','',0,0,0,1,1,'#FFFFF7',1,'#B5BED6',1,'','Images/newMenu/tabbluebutton.gif',2,3,0,0,'#FFFFF7','#666666','#FFFFFF','#666666','bold 7pt Verdana','bold 7pt Verdana',0,0],90,26);\n";
        
        miMenu += "stm_aix('p0i5','p0i1',[0,'Planillas\\r\\nINP'],90,26);\n";
        miMenu += "stm_bpx('p3','p2',[]);\n";
        miMenu += "stm_aix('p3i0','p1i0',[],0,26);\n";
        miMenu += "stm_aix('p3i1','p1i1',[0,'Declaración y\\r\\npago','','',-1,-1,0,'frame_izquierdo?nombre=" + idDomino + "&frame_tipo=4','HITLIST','frame_izquierdo?nombre=" + idDomino + "&frame_tipo=4','','','',0,0,0,'','',0,0,0,1,1,'#FFFFF7',1,'#B5BED6',1,'','',3,3,0,0,'#FFFFF7','#666666','#333399'],80,26);\n";
        miMenu += "stm_aix('p3i2','p3i1',[0,'Declaración y\\r\\nno pago','','',-1,-1,0,'frame_izquierdo?nombre=" + idDomino + "&frame_tipo=14','HITLIST','frame_izquierdo?nombre=" + idDomino + "&frame_tipo=14','','','',0,0,0,'','',0,0,0,1,1,'#FFFFF7',1,'#B5BED6',1,'','',3,3,0,0,'#FFFFF7','#666666','#333399'],80,26);\n";
        miMenu += "stm_aix('p3i3','p3i1',[0,'Declaración y\\r\\npago atrasado','','',-1,-1,0,'frame_izquierdo?nombre=" + idDomino + "&frame_tipo=15','HITLIST','frame_izquierdo?nombre=" + idDomino + "&frame_tipo=15','','','',0,0,0,'','',0,0,0,1,1,'#FFFFF7',1,'#B5BED6',1,'','',3,3,0,0,'#FFFFF7','#666666','#333399'],80,26);\n";
        miMenu += "stm_aix('p3i4','p1i5',[],0,26);\n";
        miMenu += "stm_ep();\n";
        miMenu += "stm_aix('p0i6','p0i3',[0,'Comprobantes\\r\\nPago','','',-1,-1,0,'frame_izquierdo?nombre=" + idDomino + "&frame_tipo=7','HITLIST','frame_izquierdo?nombre=" + idDomino + "&frame_tipo=7','','','',0,0,0,'','',0,0,0,1,1,'#FFFFF7',1,'#B5BED6',1,'','Images/newMenu/tabbluebutton.gif',2,3,0,0,'#FFFFF7','#666666','#FFFFFF','#666666','bold 7pt Verdana','bold 7pt Verdana',0,0],90,26);\n";
        
        miMenu += "stm_aix('p0i7','p0i3',[0,'Certificados\\r\\nCotizaciones','','',-1,-1,0,'frame_izquierdo?nombre=" + idDomino + "&frame_tipo=8','HITLIST','frame_izquierdo?nombre=" + idDomino + "&frame_tipo=8','','','',0,0,0,'','',0,0,0,1,1,'#FFFFF7',1,'#B5BED6',1,'','Images/newMenu/tabbluebutton.gif',2,3,0,0,'#FFFFF7','#666666','#FFFFFF','#666666','bold 7pt Verdana','bold 7pt Verdana',0,0],90,26);\n";
        
        miMenu += "stm_aix('p0i8','p0i3',[0,'Informe\\r\\nCotizaciones','','',-1,-1,0,'frame_izquierdo?nombre=" + idDomino + "&frame_tipo=9','HITLIST','frame_izquierdo?nombre=" + idDomino + "&frame_tipo=9','','','',0,0,0,'','',0,0,0,1,1,'#FFFFF7',1,'#B5BED6',1,'','Images/newMenu/tabbluebutton.gif',2,3,0,0,'#FFFFF7','#666666','#FFFFFF','#666666','bold 7pt Verdana','bold 7pt Verdana',0,0],90,26);\n";

        miMenu += "stm_aix('p0i8','p0i3',[0,'Certificado Anual\\r\\nSence','','',-1,-1,0,'frame_izquierdo?nombre=" + idDomino + "&frame_tipo=17','HITLIST','frame_izquierdo?nombre=" + idDomino + "&frame_tipo=17','','','',0,0,0,'','',0,0,0,1,1,'#FFFFF7',1,'#B5BED6',1,'','Images/newMenu/tabbluebutton.gif',2,3,0,0,'#FFFFF7','#666666','#FFFFFF','#666666','bold 7pt Verdana','bold 7pt Verdana',0,0],90,26);\n";
        miMenu += "stm_aix('p0i8','p0i3',[0,'Comprobante de pago','','',-1,-1,0,'frame_izquierdo?nombre=" + idDomino + "&frame_tipo=18','HITLIST','frame_izquierdo?nombre=" + idDomino + "&frame_tipo=18','','','',0,0,0,'','',0,0,0,1,1,'#FFFFF7',1,'#B5BED6',1,'','Images/newMenu/tabbluebutton.gif',2,3,0,0,'#FFFFF7','#666666','#FFFFFF','#666666','bold 7pt Verdana','bold 7pt Verdana',0,0],90,26);\n";
        //miMenu += "stm_mc('p0',[8,'#666666',0,1,'',3]);\n";
        
        miMenu += "stm_ep();\n";
        miMenu += "stm_em();\n";
        miMenu += "//-->\n";
        miMenu += "</script>\n";
        
        return miMenu;
    }
    
    /**
     * Compone menu chico
     * @param miMenu
     * @param dato_completo
     * @param idDomino
     * @return
     */
    public String componeMenuChico (String miMenu, String dato_completo, String idDomino) {
        miMenu += "<script type='text/javascript'>\n";
        miMenu += "<!--\n";
        miMenu += "stm_bm(['menu0f4d',860,'','blank.gif',0,'','',0,0,250,0,1000,1,0,0,'','540',0,0,1,2,'default','hand',''],this);\n";
        miMenu += "stm_bp('p0',[0,4,0,0,0,0,0,0,100,'',-2,'',-2,50,0,0,'#799BD8','transparent','Images/newMenu/060417line.gif',3,0,0,'#000000','',0,0,0,'transparent','',3,'Images/newMenu/060417buttona2.gif',26,5,0,'transparent','',3,'',0,0,0,'transparent','',3,'Images/newMenu/060417buttona1.gif',26,5,0,'transparent','',3,'','','','',0,0,0,0,0,0,0,0]);\n";

        //opciones directas
        if (dato_completo.indexOf("CAJA") > -1){
           miMenu += "stm_ai('p0i0',[0,'Planillas\\r\\nCajas','','',-1,-1,0,'frame_izquierdo?nombre=" + idDomino + "&frame_tipo=3','HITLIST','frame_izquierdo?nombre=" + idDomino + "&frame_tipo=3','','','',0,0,0,'','',0,0,0,1,1,'#FFFFF7',1,'#B5BED6',1,'','Images/newMenu/tabbluebutton.gif',2,3,0,0,'#FFFFF7','#000000','#FFFFFF','#666666','bold 7pt Verdana','bold 7pt Verdana',0,0],90,26);\n";
        }

        if (dato_completo.indexOf("AFP") > -1){
        	miMenu += "stm_ai('p0i0',[0,'Planillas\\r\\nAFPs','','',-1,-1,0,'','_self','','','','',0,0,0,'','',0,0,0,1,1,'#FFFFF7',1,'#B5BED6',1,'','Images/newMenu/tabbluebutton.gif',2,3,0,0,'#FFFFF7','#000000','#FFFFFF','#666666','bold 7pt Verdana','bold 7pt Verdana',0,0],90,26);\n";
            miMenu += "stm_bp('p1',[0,4,-90,0,0,0,0,0,100,'progid:DXImageTransform.Microsoft.Fade(overlap=.5,enabled=0,Duration=0.32)',-2,'progid:DXImageTransform.Microsoft.Fade(overlap=.5,enabled=0,Duration=0.32)',-2,78,0,0,'#799BD8','#EEEEEE','Images/newMenu/060417line1.gif',3,0,0,'#000000']);\n";
            miMenu += "stm_aix('p1i0','p0i0',[2,'','Images/newMenu/060417line1b.gif','Images/newMenu/060417line1b.gif',30,26,0,'','_self','','','','',0,0,0,'','',0,0,0,1,1,'#FFFFF7',1,'#B5BED6',1,'','',3,3,0,0,'#FFFFF7','#000000','#0000FF','#FF6600'],0,26);\n";
            miMenu += "stm_aix('p1i1','p0i0',[0,'Declaración\\r\\ny pago','','',-1,-1,0,'frame_izquierdo?nombre=" + idDomino + "&frame_tipo=1','HITLIST','frame_izquierdo?nombre=" + idDomino + "&frame_tipo=1','','','',0,0,0,'','',0,0,0,1,1,'#FFFFF7',1,'#B5BED6',1,'','',3,3,0,0,'#FFFFF7','#000000','#333399'],70,26);\n";
            miMenu += "stm_aix('p1i2','p1i1',[0,'Trabajos\\r\\nPesados','','',-1,-1,0,'frame_izquierdo?nombre=" + idDomino + "&frame_tipo=11','HITLIST','frame_izquierdo?nombre=" + idDomino + "&frame_tipo=11','','','',0,0,0,'','',0,0,0,1,1,'#FFFFF7',1,'#B5BED6',1,'','',3,3,0,0,'#FFFFF7','#000000','#333399'],70,26);\n";
            miMenu += "stm_aix('p1i3','p1i1',[0,'Afiliado\\r\\nVoluntario','','',-1,-1,0,'frame_izquierdo?nombre=" + idDomino + "&frame_tipo=13','HITLIST','frame_izquierdo?nombre=" + idDomino + "&frame_tipo=13','','','',0,0,0,'','',0,0,0,1,1,'#FFFFF7',1,'#B5BED6',1,'','',3,3,0,0,'#FFFFF7','#000000','#333399'],70,26);\n";
            miMenu += "stm_aix('p1i4','p1i3',[0,'Declaración y\\r\\nno pago','','',-1,-1,0,'frame_izquierdo?nombre=" + idDomino + "&frame_tipo=10','HITLIST','frame_izquierdo?nombre=" + idDomino + "&frame_tipo=10','','','',0,0,0,'','',0,0,0,1,1,'#FFFFF7',1,'#B5BED6',1,'','',3,3,0,0,'#FFFFF7','#000000','#333399'],70,26);\n";
            miMenu += "stm_aix('p1i4','p1i3',[0,'Subsidio\\r\\nIncap. Laboral','','',-1,-1,0,'frame_izquierdo?nombre=" + idDomino + "&frame_tipo=16','HITLIST','frame_izquierdo?nombre=" + idDomino + "&frame_tipo=16','','','',0,0,0,'','',0,0,0,1,1,'#FFFFF7',1,'#B5BED6',1,'','',3,3,0,0,'#FFFFF7','#666666','#333399'],70,26);\n";
            
            miMenu += "stm_aix('p1i5','p1i0',[2,'','Images/newMenu/060417line1a.gif','Images/newMenu/060417line1a.gif'],0,26);\n";
            miMenu += "stm_ep();\n";
        }
        if (dato_completo.indexOf("APV") > -1){
        	
        	miMenu += "stm_ai('p0i0',[0,'Planillas\\r\\nAPVs','','',-1,-1,0,'','_self','','','','',0,0,0,'','',0,0,0,1,1,'#FFFFF7',1,'#B5BED6',1,'','Images/newMenu/tabbluebutton.gif',2,3,0,0,'#FFFFF7','#000000','#FFFFFF','#666666','bold 7pt Verdana','bold 7pt Verdana',0,0],90,26);\n";
            miMenu += "stm_bp('p1',[0,4,-90,0,0,0,0,0,100,'progid:DXImageTransform.Microsoft.Fade(overlap=.5,enabled=0,Duration=0.32)',-2,'progid:DXImageTransform.Microsoft.Fade(overlap=.5,enabled=0,Duration=0.32)',-2,78,0,0,'#799BD8','#EEEEEE','Images/newMenu/060417line1.gif',3,0,0,'#000000']);\n";
            miMenu += "stm_aix('p1i0','p0i0',[2,'','Images/newMenu/060417line1b.gif','Images/newMenu/060417line1b.gif',30,26,0,'','_self','','','','',0,0,0,'','',0,0,0,1,1,'#FFFFF7',1,'#B5BED6',1,'','',3,3,0,0,'#FFFFF7','#000000','#0000FF','#FF6600'],0,26);\n";
            miMenu += "stm_aix('p1i1','p0i0',[0,'Planillas\\r\\nAPV','','',-1,-1,0,'frame_izquierdo?nombre=" + idDomino + "&frame_tipo=2','HITLIST','frame_izquierdo?nombre=" + idDomino + "&frame_tipo=2','','','',0,0,0,'','',0,0,0,1,1,'#FFFFF7',1,'#B5BED6',1,'','',3,3,0,0,'#FFFFF7','#000000','#333399'],70,26);\n";
            miMenu += "stm_aix('p1i2','p1i1',[0,'Planillas APV\\r\\nColectivo','','',-1,-1,0,'frame_izquierdo?nombre=" + idDomino + "&frame_tipo=12','HITLIST','frame_izquierdo?nombre=" + idDomino + "&frame_tipo=12','','','',0,0,0,'','',0,0,0,1,1,'#FFFFF7',1,'#B5BED6',1,'','',3,3,0,0,'#FFFFF7','#000000','#333399'],70,26);\n";
            miMenu += "stm_aix('p1i3','p1i0',[2,'','Images/newMenu/060417line1a.gif','Images/newMenu/060417line1a.gif'],0,26);\n";
            miMenu += "stm_ep();\n";                	
        }

        if (dato_completo.indexOf("ISAPRE") > -1){
        	miMenu += "stm_ai('p0i0',[0,'Planillas\\r\\nIsapres','','',-1,-1,0,'frame_izquierdo?nombre=" + idDomino + "&frame_tipo=5','HITLIST','frame_izquierdo?nombre=" + idDomino + "&frame_tipo=5','','','',0,0,0,'','',0,0,0,1,1,'#FFFFF7',1,'#B5BED6',1,'','Images/newMenu/tabbluebutton.gif',2,3,0,0,'#FFFFF7','#000000','#FFFFFF','#666666','bold 7pt Verdana','bold 7pt Verdana',0,0],90,26);\n";
        }

        if (dato_completo.indexOf("MUTUAL") > -1){
        	miMenu += "stm_ai('p0i0',[0,'Planillas\\r\\nMutuales','','',-1,-1,0,'frame_izquierdo?nombre=" + idDomino + "&frame_tipo=6','HITLIST','frame_izquierdo?nombre=" + idDomino + "&frame_tipo=6','','','',0,0,0,'','',0,0,0,1,1,'#FFFFF7',1,'#B5BED6',1,'','Images/newMenu/tabbluebutton.gif',2,3,0,0,'#FFFFF7','#000000','#FFFFFF','#666666','bold 7pt Verdana','bold 7pt Verdana',0,0],90,26);\n";                	
        }

        if (dato_completo.indexOf("INP") > -1){
        	miMenu += "stm_ai('p0i0',[0,'Planillas\\r\\nINP','','',-1,-1,0,'','_self','','','','',0,0,0,'','',0,0,0,1,1,'#FFFFF7',1,'#B5BED6',1,'','Images/newMenu/tabbluebutton.gif',2,3,0,0,'#FFFFF7','#000000','#FFFFFF','#666666','bold 7pt Verdana','bold 7pt Verdana',0,0],90,26);\n";
            miMenu += "stm_bp('p1',[0,4,-90,0,0,0,0,0,100,'progid:DXImageTransform.Microsoft.Fade(overlap=.5,enabled=0,Duration=0.32)',-2,'progid:DXImageTransform.Microsoft.Fade(overlap=.5,enabled=0,Duration=0.32)',-2,78,0,0,'#799BD8','#EEEEEE','Images/newMenu/060417line1.gif',3,0,0,'#000000']);\n";
            miMenu += "stm_aix('p1i0','p0i0',[2,'','Images/newMenu/060417line1b.gif','Images/newMenu/060417line1b.gif',30,26,0,'','_self','','','','',0,0,0,'','',0,0,0,1,1,'#FFFFF7',1,'#B5BED6',1,'','',3,3,0,0,'#FFFFF7','#000000','#0000FF','#FF6600'],0,26);\n";
            miMenu += "stm_aix('p1i1','p0i0',[0,'Declaración y\\r\\npago','','',-1,-1,0,'frame_izquierdo?nombre=" + idDomino + "&frame_tipo=4','HITLIST','frame_izquierdo?nombre=" + idDomino + "&frame_tipo=4','','','',0,0,0,'','',0,0,0,1,1,'#FFFFF7',1,'#B5BED6',1,'','',3,3,0,0,'#FFFFF7','#000000','#333399'],70,26);\n";
            miMenu += "stm_aix('p1i2','p1i1',[0,'Declaración y\\r\\nno pago','','',-1,-1,0,'frame_izquierdo?nombre=" + idDomino + "&frame_tipo=14','HITLIST','frame_izquierdo?nombre=" + idDomino + "&frame_tipo=14','','','',0,0,0,'','',0,0,0,1,1,'#FFFFF7',1,'#B5BED6',1,'','',3,3,0,0,'#FFFFF7','#000000','#333399'],70,26);\n";
            miMenu += "stm_aix('p1i3','p1i1',[0,'Declaración y\\r\\npago atrasado','','',-1,-1,0,'frame_izquierdo?nombre=" + idDomino + "&frame_tipo=15','HITLIST','frame_izquierdo?nombre=" + idDomino + "&frame_tipo=15','','','',0,0,0,'','',0,0,0,1,1,'#FFFFF7',1,'#B5BED6',1,'','',3,3,0,0,'#FFFFF7','#000000','#333399'],70,26);\n";
            miMenu += "stm_aix('p1i4','p1i0',[2,'','Images/newMenu/060417line1a.gif','Images/newMenu/060417line1a.gif'],0,26);\n";
            miMenu += "stm_ep();\n";
        }

        if (dato_completo.indexOf("COMPROBANTE") > -1){
        	miMenu += "stm_ai('p0i0',[0,'Comprobantes\\r\\nPago','','',-1,-1,0,'frame_izquierdo?nombre=" + idDomino + "&frame_tipo=7','HITLIST','frame_izquierdo?nombre=" + idDomino + "&frame_tipo=7','','','',0,0,0,'','',0,0,0,1,1,'#FFFFF7',1,'#B5BED6',1,'','Images/newMenu/tabbluebutton.gif',2,3,0,0,'#FFFFF7','#000000','#FFFFFF','#666666','bold 7pt Verdana','bold 7pt Verdana',0,0],90,26);\n";
        }

        if (dato_completo.indexOf("CERTIFICADO") > -1){
        	miMenu += "stm_ai('p0i0',[0,'Certificados\\r\\nCotizaciones','','',-1,-1,0,'frame_izquierdo?nombre=" + idDomino + "&frame_tipo=8','HITLIST','frame_izquierdo?nombre=" + idDomino + "&frame_tipo=8','','','',0,0,0,'','',0,0,0,1,1,'#FFFFF7',1,'#B5BED6',1,'','Images/newMenu/tabbluebutton.gif',2,3,0,0,'#FFFFF7','#000000','#FFFFFF','#666666','bold 7pt Verdana','bold 7pt Verdana',0,0],90,26);\n";
        }

        if (dato_completo.indexOf("INFORME") > -1){
        	miMenu += "stm_ai('p0i0',[0,'Informe\\r\\nCotizaciones','','',-1,-1,0,'frame_izquierdo?nombre=" + idDomino + "&frame_tipo=9','HITLIST','frame_izquierdo?nombre=" + idDomino + "&frame_tipo=9','','','',0,0,0,'','',0,0,0,1,1,'#FFFFF7',1,'#B5BED6',1,'','Images/newMenu/tabbluebutton.gif',2,3,0,0,'#FFFFF7','#000000','#FFFFFF','#666666','bold 7pt Verdana','bold 7pt Verdana',0,0],90,26);\n";
        }
        if (dato_completo.indexOf("ARCHIVO") > -1){
        	miMenu += "stm_ai('p0i0',[0,'Archivo<br>Empresas','','',-1,-1,0,'frame_izquierdo?nombre=" + idDomino + "&frame_tipo=10','HITLIST','frame_izquierdo?nombre=" + idDomino + "&frame_tipo=10','','','',0,0,0,'','',0,0,0,1,1,'#FFFFF7',1,'#B5BED6',1,'','Images/newMenu/tabbluebutton.gif',2,3,0,0,'#FFFFF7','#000000','#FFFFFF','#666666','bold 7pt Verdana','bold 7pt Verdana',0,0],90,26);\n";
        }                
        
        miMenu += "stm_mc('p0',[8,'#000000',0,1,'',3]);\n";
        
        miMenu += "stm_ep();\n";
        miMenu += "stm_em();\n";
        miMenu += "//-->\n";
        miMenu += "</script>\n"; 
        
        return miMenu;
    }
    
    /**
     * Prepara el parametro de holding teniendo en cuenta el rango que va a 999
     * @param permisosRolLectorEmpresa
     * @return string de holding
     */
    public String cargaParametrosHoldings (HashMap permisosRolLectorEmpresa ) {
    	String holdings = "";
    	String espacio = "";
    	for (Iterator it = permisosRolLectorEmpresa.values().iterator(); it.hasNext();) {
    		PermGrupoConvenio pg = (PermGrupoConvenio) it.next();
    		String h = pg.getIdGrupoConvenio() >= 2000 && pg.getIdGrupoConvenio() <= 2318 ? "999." + String.valueOf(pg.getIdGrupoConvenio()) : String.valueOf(pg.getIdGrupoConvenio()); 
    		holdings+= (espacio + h);
    		espacio = ".";
    	}
    	return holdings;
    }
    
    public String cargaComboRut (HashMap permisosRolLectorEmpresa) {
    	String comboRutEmpresa = "";
    	
    	for (Iterator it = permisosRolLectorEmpresa.values().iterator(); it.hasNext();) {
    		PermGrupoConvenio pg = (PermGrupoConvenio) it.next();
    		
	    	// Get Result set metadata
			Iterator i = pg.getPermEmpresas().values().iterator();
			while (i.hasNext()) {
				PermEmpresa permEmpresa = (PermEmpresa) i.next();
				//comboRutEmpresa += "<option value='" + permEmpresa.getEmpresa().getIdEmpresa() + "'>" + Utils.formatRut(permEmpresa.getEmpresa().getIdEmpresa()) + "</option>";
				comboRutEmpresa += permEmpresa.getEmpresa().getIdEmpresa() + " ";
			}
    	}
    	
    	//ordenamiento desendente de los ruts
    	comboRutEmpresa = comboRutEmpresa.trim();
    	String[] array= Utils.OrdenarBurbuja(comboRutEmpresa.split(" ")); 
    	
    	comboRutEmpresa="";
    	for(int i=0;i<array.length;i++){
    		comboRutEmpresa += "<option value='" + array[i] + "'>" + Utils.formatRut(Integer.parseInt(array[i])) + "</option>";
    	}
    	
		return comboRutEmpresa;
    }
    
    /**
     * 
     * @param permisosRolLectorEmpresa
     * @return
     */
    public String cargaComboRazonSocial (HashMap permisosRolLectorEmpresa) {
    	String comboRazonSocialEmpresa = "";
    	for (Iterator it = permisosRolLectorEmpresa.values().iterator(); it.hasNext();) {
    		PermGrupoConvenio pg = (PermGrupoConvenio) it.next();    	
    	
	    	// Get Result set metadata
    		Iterator i = pg.getPermEmpresas().values().iterator();
			while (i.hasNext()) {
				PermEmpresa permEmpresa = (PermEmpresa) i.next();
				//comboRazonSocialEmpresa += "<option value='" + permEmpresa.getEmpresa().getIdEmpresa() + "'>" + permEmpresa.getEmpresa().getRazonSocial() + "</option>";
				comboRazonSocialEmpresa += permEmpresa.getEmpresa().getRazonSocial() + ":" + permEmpresa.getEmpresa().getIdEmpresa() + "\n";
			}
    	}
    	
    	//ordenamiento alfabético de las razones sociales 
        String[] razon_social_array = comboRazonSocialEmpresa.split("\n");

		for(int j=0;j<razon_social_array.length-1;j++)
		{
			for(int i=0;i<razon_social_array.length-1;i++)
			{
				String aux="";
				
				if(razon_social_array[i].compareTo(razon_social_array[i+1]) > 0){
					aux = razon_social_array[i+1];
					razon_social_array[i+1] = razon_social_array[i];
					razon_social_array[i] = aux;
				}
			}
		}
		
		comboRazonSocialEmpresa = "";
		
        for (int i = 0 ; i < razon_social_array.length ; i++){
        	comboRazonSocialEmpresa = comboRazonSocialEmpresa + "<option value='" + razon_social_array[i].split(":")[1].trim() + "'>" + razon_social_array[i].split(":")[0].trim() + "</option>";
        }
    	
		return comboRazonSocialEmpresa;
    }    
    
    /**
     * Construye un string con todos los ruts para la opción todos del combo rut o razon social
     * @param permisosRolLectorEmpresa
     * @return
     */
    public String construyeStringConTodosLosRuts (HashMap permisosRolLectorEmpresa) {
    	String todosLosRuts = "";
    	
    	for (Iterator it = permisosRolLectorEmpresa.values().iterator(); it.hasNext();) {
    		PermGrupoConvenio pg = (PermGrupoConvenio) it.next();    	
    	
	    	// Get Result set metadata
	    	Iterator i = pg.getPermEmpresas().values().iterator();
	    	while (i.hasNext()) {
				PermEmpresa permEmpresa = (PermEmpresa) i.next();
				todosLosRuts += permEmpresa.getEmpresa().getIdEmpresa() + " ";
			}
    	}
    	return todosLosRuts;
    }    

    /**
     * 
     * @param permisosRolLectorEmpresa
     * @return
     */
    public String cargaComboConvenios (HashMap permisosRolLectorEmpresa) {
    	String comboConvenios = "";
    	boolean hayConvenios = false;
    	Hashtable table = new Hashtable();
    	
    	for (Iterator it = permisosRolLectorEmpresa.values().iterator(); it.hasNext();) {
    		PermGrupoConvenio pg = (PermGrupoConvenio) it.next();    	
    	
	    	// Get Result set metadata
    		Iterator i = pg.getPermEmpresas().values().iterator();
			while (i.hasNext()) {
				PermEmpresa permEmpresa = (PermEmpresa) i.next();
				Iterator y = permEmpresa.getPermConvenios().values().iterator();
				while (y.hasNext()) {
					hayConvenios = true;
					PermConvenio p = (PermConvenio) y.next();
					if (!table.containsKey(new Integer (p.getConvenio().getIdConvenio()))) {
						table.put(new Integer (p.getConvenio().getIdConvenio()), new Integer (p.getConvenio().getIdConvenio()));
					}
				}
			}
    	}

		List ordenados = new ArrayList(table.values());
		Collections.sort(ordenados);
		Iterator it = ordenados.iterator();
		while (it.hasNext()) {
			Integer x = (Integer) it.next();
			comboConvenios += "<option value='" + x.intValue() + "'>" + x.intValue() + "</option>";	
		}
		
		
        if (hayConvenios) {
            comboConvenios += "<option value='800'>" + "800" + "</option>";
            comboConvenios += "<option value='900'>" + "900" + "</option>";
		}		
		return comboConvenios;
    }
    
    /**
     * 
     * @param permisosRolLectorEmpresa
     * @return
     */
    public String construyeStringConTodosLosConvenios (HashMap permisosRolLectorEmpresa) {
    	String todosLosConvenios = "";
    	boolean hayConvenios = false;
    	Hashtable table = new Hashtable();

    	for (Iterator it = permisosRolLectorEmpresa.values().iterator(); it.hasNext();) {
    		PermGrupoConvenio pg = (PermGrupoConvenio) it.next();    	
    	
	    	// Get Result set metadata
    		Iterator i = pg.getPermEmpresas().values().iterator();    	
			while (i.hasNext()) {
				PermEmpresa permEmpresa = (PermEmpresa) i.next();
				Iterator y = permEmpresa.getPermConvenios().values().iterator();
				while (y.hasNext()) {
					hayConvenios = true;
					PermConvenio p = (PermConvenio) y.next();
					if (!table.containsKey(new Integer (p.getConvenio().getIdConvenio()))) {
						table.put(new Integer (p.getConvenio().getIdConvenio()), new Integer (p.getConvenio().getIdConvenio()));
					}
				}
			}
    	}
    	
		Iterator it = table.values().iterator();
		while (it.hasNext()) {
			Integer x = (Integer) it.next();
			todosLosConvenios += " " + x.intValue();	
		}
		
        if (hayConvenios) {
            todosLosConvenios += " 800 900";
		}		
		return todosLosConvenios;
    }
    public String construyeStringDeRutEmpresaFinal (String todos_los_rut, String rut_empresa) {
    	String rut_empresa_final = null;
    	rut_empresa_final += "<select name='RutEmpresa' id='RutEmpresa' size='1' class='campos' onChange='rut_razon()'><option value='" + todos_los_rut + "'>--Todos--</option>" + rut_empresa  + "</select>";
    	return rut_empresa_final;
    }
    
    public String construyeStringDeRazonSocialFinal (String todos_los_rut, String razon_social) {
    	String razon_social_final = null;
    	razon_social_final += "<select name='RutEmpresa' id='RutEmpresa' size='1' class='campos' onChange='rut_razon()'><option value='" + todos_los_rut + "'>--Todos--</option>" + razon_social  + "</select>";
    	return razon_social_final;
    }
    
    public String construyeStringConvenioFinal (String todo_los_conv, String convenio) {
    	String convenio_final = null;
    	convenio_final += "<select name='Convenio' id='Convenio' size='1' class='campos' ><option value='" + todo_los_conv + "'>-- Todos --</option>" + convenio + "</select>";
    	return convenio_final;
    }
    
    public String construyeStringSucursalFinal (String toda_las_sucu, String sucursal) {
    	String sucursal_final = null;
    	sucursal_final += "<select name='Sucursal' id='Sucursal' size='1' class='campos' ><option value='" + toda_las_sucu + "'>-- Todos --</option>" + sucursal + "</select>";
    	return sucursal_final;
    }
    
    /**
     * 
     * @param permisosRolLectorEmpresa
     * @return
     */
    public String cargaComboSucursales (HashMap permisosRolLectorEmpresa) {
    	String comboSucursales = "";
    	Hashtable table = new Hashtable();
    	
    	for (Iterator it = permisosRolLectorEmpresa.values().iterator(); it.hasNext();) {
    		PermGrupoConvenio pg = (PermGrupoConvenio) it.next();    	
    	
	    	// Get Result set metadata
    		Iterator i = pg.getPermEmpresas().values().iterator();
    		while (i.hasNext()) {
				PermEmpresa permEmpresa = (PermEmpresa) i.next();
				Iterator y = permEmpresa.getPermConvenios().values().iterator();
				while (y.hasNext()) {
					PermConvenio permConv = (PermConvenio) y.next();
					
					Iterator z = permConv.getSucursales().values().iterator();
					while (z.hasNext()) {
						SucursalVO s = (SucursalVO) z.next();
						if (!table.containsKey(s.getIdSucursal())) {
							table.put(s.getIdSucursal(), s);
						}
						
					}
				}
    		}
    	}

		List ordenados = new ArrayList(table.values());
		Collections.sort(ordenados);
		Iterator it = ordenados.iterator();
		while (it.hasNext()) {
			SucursalVO s = (SucursalVO) it.next();
			comboSucursales += "<option value='" + s.getIdSucursal().trim() + "'>" + s.getIdSucursal().trim() + "</option>";	
		}

		return comboSucursales;
    }    
    
    /**
     * 
     * @param permisosRolLectorEmpresa
     * @return
     */
    public String construyeStringConTodosLasSucursales (HashMap permisosRolLectorEmpresa) {
    	String todasLasSucursales = "";
    	Hashtable table = new Hashtable();
    	
    	for (Iterator it = permisosRolLectorEmpresa.values().iterator(); it.hasNext();) {
    		PermGrupoConvenio pg = (PermGrupoConvenio) it.next();    	
    	
	    	// Get Result set metadata
    		Iterator i = pg.getPermEmpresas().values().iterator();
			while (i.hasNext()) {
				PermEmpresa permEmpresa = (PermEmpresa) i.next();
				Iterator y = permEmpresa.getPermConvenios().values().iterator();
				while (y.hasNext()) {
					PermConvenio permConv = (PermConvenio) y.next();
					
					Iterator z = permConv.getSucursales().values().iterator();
					while (z.hasNext()) {
						SucursalVO s = (SucursalVO) z.next();
						if (!table.containsKey(s.getIdSucursal())) {
							table.put(s.getIdSucursal(), s);
						}
						
					}
				}
			}
    	}

		List ordenados = new ArrayList(table.values());
		Collections.sort(ordenados);
		Iterator it = ordenados.iterator();
		String espacio = "";
		while (it.hasNext()) {
			SucursalVO s = (SucursalVO) it.next();
			todasLasSucursales += espacio + s.getIdSucursal().trim();
			espacio = " ";
		}

		return todasLasSucursales;
    }     
    
    /**
     * Contruye las opciones que tendrá el combobox de la Entidad indicada.
     * @return String con las opciones
     */
    public String cargaComboEntidad(String entidad, LectorEntidadVO lectorEntidad) {
    	String optionsEntidad = "";
    	//String optionTodas  = "";
    	Iterator it;
    	int contador = 0;

		if("MUTUAL".equalsIgnoreCase(entidad)){
			EntidadMutualVO em;
			for (it = lectorEntidad.getEntidadesMutual().iterator(); it.hasNext();) {
				em = (EntidadMutualVO) it.next();
				optionsEntidad += "<option value='" + String.valueOf(em.getNombre()).trim() + "'>" + String.valueOf(em.getNombre()).trim() + "</option>";
				//optionTodas    += String.valueOf(em.getId()) + ",";
				contador++;
			}
    	}

		if("CCAF".equalsIgnoreCase(entidad)){
			EntidadCCAFVO ec;
    		for (it = lectorEntidad.getEntidadesCcaf().iterator(); it.hasNext();)	{
    			ec = (EntidadCCAFVO) it.next();
    			optionsEntidad += "<option value='" + String.valueOf(ec.getNombre()).trim() + "'>" + String.valueOf(ec.getNombre()).trim() + "</option>";
    			//optionTodas    += String.valueOf(ec.getId()) + ",";
    			contador++;
    		}
    	}
		
		if("AFP".equalsIgnoreCase(entidad)){
			EntidadPensionVO ep;
    		for (it = lectorEntidad.getEntidadesPension().iterator(); it.hasNext();) {
    			ep = (EntidadPensionVO) it.next();
    			optionsEntidad += "<option value='" + String.valueOf(ep.getNombre()).trim() + "'>" + String.valueOf(ep.getNombre()).trim() + "</option>";
    			//optionTodas    += String.valueOf(ep.getId()) + ",";
    			contador++;
    		}
    	}

		if("APV".equalsIgnoreCase(entidad)){
			EntidadApvVO ea;
    		for (it = lectorEntidad.getEntidadesApv().iterator(); it.hasNext();) {
    			ea = (EntidadApvVO) it.next();
    			optionsEntidad += "<option value='" + String.valueOf(ea.getNombre()).trim() + "'>" + String.valueOf(ea.getNombre()).trim() + "</option>";
    			//optionTodas    += String.valueOf(ea.getId()) + ",";
    			contador++;
    		}
    	}

		if("ISAPRE".equalsIgnoreCase(entidad)){
			EntidadSaludVO es;
    		for (it = lectorEntidad.getEntidadesSalud().iterator(); it.hasNext();) {
    			es = (EntidadSaludVO) it.next();
    			optionsEntidad += "<option value='" + String.valueOf(es.getNombre()).trim() + "'>" + String.valueOf(es.getNombre()).trim() + "</option>";
    			//optionTodas    += String.valueOf(es.getId()) + ",";
    			contador++;
    		}
    	}

		//Se agrega la opción "Todas" al combo.
		if (contador > 1){
			//optionTodas    = optionTodas.substring(0, optionTodas.length()-1);
			optionsEntidad = "<option value=''>Todas</option>" + optionsEntidad;
		}

		return optionsEntidad;
    }

	public List cargaComboSucursales2(HashMap permisosRolLectorEmpresa) {
		List sucursales = new ArrayList();
		String comboSucursales = "";
    	Hashtable table = new Hashtable();
    	
    	for (Iterator it = permisosRolLectorEmpresa.values().iterator(); it.hasNext();) {
    		PermGrupoConvenio pg = (PermGrupoConvenio) it.next();    	
    	
	    	// Get Result set metadata
    		Iterator i = pg.getPermEmpresas().values().iterator();
    		while (i.hasNext()) {
				PermEmpresa permEmpresa = (PermEmpresa) i.next();
				Iterator y = permEmpresa.getPermConvenios().values().iterator();
				while (y.hasNext()) {
					PermConvenio permConv = (PermConvenio) y.next();
					
					Iterator z = permConv.getSucursales().values().iterator();
					while (z.hasNext()) {
						SucursalVO s = (SucursalVO) z.next();
						if (!table.containsKey(s.getIdSucursal())) {
							table.put(s.getIdSucursal(), s);
						}
						
					}
				}
    		}
    	}

		List ordenados = new ArrayList(table.values());
		Collections.sort(ordenados);
		Iterator it = ordenados.iterator();
		String sucursal = "";
		while (it.hasNext()) {
			SucursalVO s = (SucursalVO) it.next();
			sucursal = s.getIdSucursal().trim();
			sucursales.add(sucursal);
		}

		return sucursales;
	}

	public List cargaComboEntidad2(String entidad, LectorEntidadVO lectorEntidad) {
		List lista = new ArrayList();
    	//String optionTodas  = "";
    	Iterator it;
    	int contador = 0;

		if("MUTUAL".equalsIgnoreCase(entidad)){
			EntidadMutualVO em;
			for (it = lectorEntidad.getEntidadesMutual().iterator(); it.hasNext();) {
				em = (EntidadMutualVO) it.next();
				String nommutual = String.valueOf(em.getNombre()).trim();
				lista.add(nommutual);
			}
    	}

		if("CCAF".equalsIgnoreCase(entidad)){
			EntidadCCAFVO ec;
    		for (it = lectorEntidad.getEntidadesCcaf().iterator(); it.hasNext();)	{
    			ec = (EntidadCCAFVO) it.next();
    			String nomcaja = ec.getNombre().trim();
    			lista.add(nomcaja);
    			//optionTodas    += String.valueOf(ec.getId()) + ",";
    			contador++;
    		}
    	}
		
		if("AFP".equalsIgnoreCase(entidad)){
			EntidadPensionVO ep;
    		for (it = lectorEntidad.getEntidadesPension().iterator(); it.hasNext();) {
    			ep = (EntidadPensionVO) it.next();
    			String nomafp = String.valueOf(ep.getNombre()).trim();
    			lista.add(nomafp);
    			contador++;
    		}
    	}

		if("APV".equalsIgnoreCase(entidad)){
			EntidadApvVO ea;
    		for (it = lectorEntidad.getEntidadesApv().iterator(); it.hasNext();) {
    			ea = (EntidadApvVO) it.next();
    			String nomapv = String.valueOf(ea.getNombre()).trim();
    			lista.add(nomapv);
    		}
    	}

		if("ISAPRE".equalsIgnoreCase(entidad)){
			EntidadSaludVO es;
    		for (it = lectorEntidad.getEntidadesSalud().iterator(); it.hasNext();) {
    			es = (EntidadSaludVO) it.next();
    			String nomisapre = String.valueOf(es.getNombre()).trim();
    			lista.add(nomisapre);
    		}
    	}

		return lista;
	}
}