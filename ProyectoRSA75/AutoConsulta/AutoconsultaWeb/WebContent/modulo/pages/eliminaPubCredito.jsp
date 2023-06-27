<% 
//frame oculto que elimina la imagen
/**
 * INICIO NUEVO
 * se elimina la imagen generada al vuelo con la publicidad 
 */
String path = session.getAttribute("pathPubEditada") != null ? (String) session.getAttribute("pathPubEditada") : "";
if(path != null && !path.equals(""))
	cl.araucana.autoconsulta.common.ImageProcessing.deleteImg(path,session.getId());
/**
 * FIN NUEVO
 */
%>

