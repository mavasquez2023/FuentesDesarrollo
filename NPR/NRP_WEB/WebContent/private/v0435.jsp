
<%@page import="java.sql.SQLException"%>
<%@page import="java.io.File"%>
<%@page import="java.util.ResourceBundle"%>
<%@page import="cl.liv.comun.utiles.MiHashMap"%>
<%@page import="cl.liv.persistencia.ibatis.impl.SqlMapLocator"%>
<%@page import="cl.jfactory.planillas.service.util.ConstantesUtiles"%>
<%@page import="com.ibatis.sqlmap.client.SqlMapClient"%>
<%@page import="java.util.ArrayList"%>
<%


ResourceBundle properties = ResourceBundle.getBundle("etc/config_txt");

SqlMapClient sqlMap = SqlMapLocator.getSqlMap(ConstantesUtiles.ID_CFG_IBATIS_CARGA);

ArrayList result = null;
ArrayList formatosNoEncontrados = new ArrayList();
try {
	result = (ArrayList)sqlMap.queryForList("carga_SAP.obtenerConfiguracionesNominasEspeciales");
	if(result != null) {
		for(int i=0; i<result.size(); i++) {
			MiHashMap configAux = (MiHashMap) result.get(i);
			if(config != null) {
				String ruta = properties.getString("export.path.resources.txt")+"txts/"+configAux.get("FORMATO_NOMINA")+"/conf.xml";
				System.out.println("->"+ ruta);
				if(  ! new File(ruta).exists()  ) {
					System.out.println("formato no encontrado ->"+ configAux.get("FORMATO_NOMINA"));
					formatosNoEncontrados.add(configAux.get("FORMATO_NOMINA"));
				}
			}
		}
	}
	else{
		System.out.println("no hay nominas especiales");
	}
	
	
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
	
System.out.println("cantidad formatos no encontrados ->"+ formatosNoEncontrados.size());

ArrayList formatos = formatosNoEncontrados;

if(formatos != null){
%>
Formatos encontrados <%=formatos.size()%><br/><br/>
<%

for(int i=0; i<formatos.size(); i++){
	%>
	Formato -> <%=i%> - <%=formatos.get(i)%><br/>
	<%
}

}
else{
	%>
	Formatos en null
	<%
}

 
%>