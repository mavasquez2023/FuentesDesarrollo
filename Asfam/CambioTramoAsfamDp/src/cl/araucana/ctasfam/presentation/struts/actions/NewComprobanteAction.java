package cl.araucana.ctasfam.presentation.struts.actions;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.ctasfam.integration.jdbc.dao.impl.Mejoras2016DaoImpl;
import cl.araucana.ctasfam.presentation.struts.vo.Empresa;
import cl.araucana.ctasfam.presentation.struts.vo.Encargado;
import cl.araucana.ctasfam.presentation.struts.vo.PeticionProcesamientoDto;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.PdfWriter;

public class NewComprobanteAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Encargado encargado = (Encargado) request.getSession().getAttribute("edocs_encargado");
		String rutEmpresaParam = request.getParameter("rutEmpresa");
		
	
		try{
			
			
			Mejoras2016DaoImpl estProDao = new Mejoras2016DaoImpl();
			
			String rutEmpresa = "";
			String nombreEmpresa = "";
			int cantTotalProcesados = 0;
			boolean flagAllProcesados = true;
			boolean flagEmpresaEncargado = false; 
			List<PeticionProcesamientoDto> listPetPro = new ArrayList<PeticionProcesamientoDto>();
			
			for (Iterator iter = encargado.getEmpresas().iterator(); iter.hasNext();) {
				Empresa empresa = (Empresa) iter.next();
				if(empresa.getRut() == Integer.valueOf(rutEmpresaParam)){
					flagEmpresaEncargado = true;
					rutEmpresa = empresa.getFormattedRut();
					nombreEmpresa = empresa.getName();
					
					listPetPro = estProDao.selectPeticionProcesamiento(Integer.parseInt(rutEmpresaParam));
					
					for (PeticionProcesamientoDto petPro : listPetPro) {
						if(!"F".equalsIgnoreCase(petPro.getEstado())){
							flagAllProcesados = false;
							break;
						}else{
							String rutaArchivo = petPro.getRutaArchivo();
							String nombreArchivo = rutaArchivo.substring(rutaArchivo.lastIndexOf("/")+1, rutaArchivo.length());
							petPro.setRutaArchivo(nombreArchivo);
						}
						cantTotalProcesados += petPro.getTotalRegProcesados();
					}
				}
			}
	
			
			if(flagEmpresaEncargado && flagAllProcesados && listPetPro.size() > 0){
				
				response.setContentType("application/pdf");
				response.setHeader("Content-disposition", "attachment; filename=\"Comrobante-" + rutEmpresa + ".pdf\"");
				
				ServletOutputStream baos = response.getOutputStream();
				
				Document document = new Document(PageSize.LETTER);
			    PdfWriter.getInstance(document, baos);
				
				document.open();
				document.addAuthor("La Araucana");
				document.addCreator("La Araucana");
				document.addSubject("Comprobante");
				document.addCreationDate();
				document.addTitle("Comprobante");

				
				HTMLWorker htmlWorker = new HTMLWorker(document);
				String str = "<html>" +
					"<head></head>" +
					"<body>" +
					"<table>" +
					"	<tr><td align=\"center\"><h1><b>Comprobante de Propuesta<br/></b></h1></td></tr>" +
					"	<tr>" +
					"		<td> " +
					"			<table border=\"1\"> " +
					"				<tr >" +
					"					<td with=\"20\"><b></b></td>" +
					"					<td with=\"50\"><b></b></td>" +
					"					<td with=\"30\"><b></b></td>" +
					"				</tr>" +
					"				<tr>" +
					"					<td align=\"center\">Rut empresa</td>" +
					"					<td align=\"center\">Razon social</td>" +
					"					<td align=\"center\">Total procesados</td>" +
					"				</tr>" +
					"				<tr>" +
					"					<td>" + rutEmpresa +"</td>" +
					"					<td>" + nombreEmpresa + "</td>" +
					"					<td align=\"center\">" + String.valueOf(cantTotalProcesados) + "</td>" +
					"				</tr>" +
					"			</table>" +
					"			<br />" +
					"			<table border=\"1\">" +
					"				<tr>" +
					"					<td align=\"center\" with=\"40\"><b>Archivo</b></td>" +
					"					<td align=\"center\" with=\"20\"><b>Fecha envio</b></td>" +
					"					<td align=\"center\" with=\"20\"><b>Fecha procesamiento</b></td>" +
					"					<td align=\"center\" with=\"20\"><b>Cantidad procesados</b></td>" +
					"				</tr>";
				
				for(PeticionProcesamientoDto obj : listPetPro){
					str += "<tr>";
					str += "<td>" + obj.getRutaArchivo() + "</td>";
					str += "<td align=\"center\">" + obj.getFechaEnvioStr() + "</td>";
					str += "<td align=\"center\">" + obj.getFechaProcesamientoStr() + "</td>";
					str += "<td align=\"center\">" + obj.getTotalRegProcesadosStr() + "</td>";
					str += "</tr>";
				}

				str += "		</table>" +
					"		</td>" +
					"	</tr>" +
					"	<tr>" +
					"		<td></td>" +
					"	</tr>" +
					"</table>" +
					"</body>" +
					"</html>";

				htmlWorker.parse(new StringReader(str));

				document.close();

			}
		
			return null;
		
		}catch(Exception e){
			e.printStackTrace();
			return mapping.findForward("errornewcomprobante");
		}

	}
	
//	private void creaPDF(Document document){
////		Titulo				
//		Paragraph titulo = new Paragraph("Comprobante");
//		titulo.setAlignment(Element.ALIGN_CENTER);
//		titulo.setFont(new Font(BaseFont.createFont(), 12, Font.BOLD));
//		document.add(titulo);
//
////		Tabla resumen
//        PdfPTable tableResumen = new PdfPTable(3);
//         
//        PdfPCell cellRutEmp = new PdfPCell(new Phrase("Rut Empresa"));
//        cellRutEmp.setHorizontalAlignment(Element.ALIGN_CENTER);
//        tableResumen.addCell(cellRutEmp);
//        
//        PdfPCell cellRazSoc = new PdfPCell(new Phrase("Razón Social"));
//        cellRazSoc.setHorizontalAlignment(Element.ALIGN_CENTER);
//        tableResumen.addCell(cellRazSoc);
//        
//        PdfPCell cellTotPro = new PdfPCell(new Phrase("Total Procesados"));
//        cellTotPro.setHorizontalAlignment(Element.ALIGN_CENTER);
//        tableResumen.addCell(cellTotPro);
//        
//        PdfPCell cellValRutEmp = new PdfPCell(new Phrase(rutEmpresa));
//        cellValRutEmp.setHorizontalAlignment(Element.ALIGN_LEFT);
//        tableResumen.addCell(cellValRutEmp);
//        
//        PdfPCell cellValRazSoc = new PdfPCell(new Phrase(nombreEmpresa));
//        cellValRazSoc.setHorizontalAlignment(Element.ALIGN_LEFT);
//        tableResumen.addCell(cellValRazSoc);
//        
//        PdfPCell cellValTotPro = new PdfPCell(new Phrase(cantTotalProcesados));
//        cellValTotPro.setHorizontalAlignment(Element.ALIGN_CENTER);
//        tableResumen.addCell(cellValTotPro);
//        
//        document.add(tableResumen);
//        
////		Linea vacia
//		document.add(new Paragraph(""));
//		
////        Tabla detalle
//        PdfPTable tableDetalle = new PdfPTable(4);
//        
//        PdfPCell cellArch = new PdfPCell(new Phrase("Archivo"));
//        cellArch.setHorizontalAlignment(Element.ALIGN_CENTER);
//        tableDetalle.addCell(cellArch);
//        
//        PdfPCell cellFecEnv = new PdfPCell(new Phrase("Fecha envio"));
//        cellFecEnv.setHorizontalAlignment(Element.ALIGN_CENTER);
//        tableDetalle.addCell(cellFecEnv);
//        
//        PdfPCell cellFecPro = new PdfPCell(new Phrase("Fecha procesamiento"));
//        cellFecPro.setHorizontalAlignment(Element.ALIGN_CENTER);
//        tableDetalle.addCell(cellFecPro);
//        
//        PdfPCell cellCanPro = new PdfPCell(new Phrase("Cantidad procesados"));
//        cellCanPro.setHorizontalAlignment(Element.ALIGN_CENTER);
//        tableDetalle.addCell(cellCanPro);
//        
//        
//        for(PeticionProcesamientoDto obj : listPetPro){
//        	
//        	PdfPCell cellValArch = new PdfPCell(new Phrase(obj.getRutaArchivo()));
//	        cellValArch.setHorizontalAlignment(Element.ALIGN_CENTER);
//	        tableDetalle.addCell(cellValArch);
//	        
//	        PdfPCell cellValFecEnv = new PdfPCell(new Phrase(obj.getFechaEnvioStr()));
//	        cellValFecEnv.setHorizontalAlignment(Element.ALIGN_CENTER);
//	        tableDetalle.addCell(cellValFecEnv);
//	        
//	        PdfPCell cellValFecPro = new PdfPCell(new Phrase(obj.getFechaProcesamientoStr()));
//	        cellValFecPro.setHorizontalAlignment(Element.ALIGN_CENTER);
//	        tableDetalle.addCell(cellValFecPro);
//	        
//	        PdfPCell cellValCanPro = new PdfPCell(new Phrase(obj.getTotalRegProcesadosStr()));
//	        cellValCanPro.setHorizontalAlignment(Element.ALIGN_CENTER);
//	        tableDetalle.addCell(cellValCanPro);
//
//        }
//        
//        document.add(tableDetalle);
//	}

}
