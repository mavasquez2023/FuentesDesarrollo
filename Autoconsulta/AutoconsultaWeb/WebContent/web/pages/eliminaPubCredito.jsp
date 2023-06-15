<%  
//frame oculto que elimina la imagen
/** 
 * INICIO NUEVO
 * se elimina la imagen generada al vuelo con la publicidad 
 * (ULTIMA PRUEBA, ya que se borra en modulo/welcome.jsp y web/)
 */
String path = session.getAttribute("pathPubEditada") != null ? (String) session.getAttribute("pathPubEditada") : "";
if(path != null && !path.equals(""))
	cl.araucana.autoconsulta.common.ImageProcessing.deleteImg(path,session.getId());
/**
 * FIN NUEVO
 */
%>
