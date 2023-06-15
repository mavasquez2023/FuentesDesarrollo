package cl.laaraucana.botonpago.web.manager;

import cl.laaraucana.botonpago.web.database.dao.TesoreriaDAO;
import cl.laaraucana.botonpago.web.database.ibatis.domain.TE07F1;
import cl.laaraucana.botonpago.web.database.ibatis.domain.TE07F2;
import cl.laaraucana.botonpago.web.utils.CompletaUtil;
import cl.laaraucana.botonpago.web.utils.Constantes;
import cl.laaraucana.botonpago.web.utils.Util;

public class ManagerDetalleTesoreria {

	public static void ingresarDetallesTesoreria(TE07F1 teso, String origen, String codConcepto) throws Exception {
		TE07F2 detTeso = new TE07F2();
		detTeso.setTe4qa("1");
		detTeso.setTe4ra(" ");
		detTeso.setTe4sa(teso.getTe7na());
		detTeso.setTe2za("0");
		detTeso.setTe4ua("0");
		detTeso.setTe2xa("0");
		detTeso.setTe2ya("0");
		detTeso.setTe4ya("0");
		//SCA-01
		/*if(origen.equals(Constantes.getInstancia().ORIGEN_SAP)){
			detTeso.setTe1ya(Constantes.getInstancia().ITEM_UNO_CODIGO_SAP);
		}else if(origen.equals(Constantes.getInstancia().ORIGEN_AS400)){
			detTeso.setTe1ya(Constantes.getInstancia().ITEM_UNO_CODIGO_AS400);
		}*/
		detTeso.setTe1ya(codConcepto);
		
		detTeso.setTe4ta(Constantes.getInstancia().ITEM_UNO_GLOSA);
		//detTeso.setTe1ya(Constantes.getInstancia().ITEM_UNO_CODIGO); //ANTES
		detTeso.setTe3wa(teso.getTe3wa());
		//detTeso.setObf002();
		//detTeso.setObf003();
		//detTeso.setObf005();
		//detTeso.setObf006();
		detTeso.setSajkm94(Constantes.getInstancia().NOMBRE_USUARIO_APP);
		detTeso.setSajkm92("");

		//item 1
		TesoreriaDAO teDAO = new TesoreriaDAO();
		teDAO.ingresaDetalleTesoreria(detTeso);

		//SCA-01 agregar monto 0 a detalle 2 y 3
		//te2sa Monto del detalle = 0
		
		//item 2
		detTeso.setTe4qa("2");
		detTeso.setTe4ta(Constantes.getInstancia().ITEM_DOS_GLOSA + " " + Util.formatDobleSinDecimal(Double.parseDouble(teso.getTe42a())) + "-" + teso.getTe43a());
		detTeso.setTe1ya(Constantes.getInstancia().ITEM_DOS_CODIGO);
		detTeso.setTe4sa("0");

		teDAO.ingresaDetalleTesoreria(detTeso);

		//item 3
		detTeso.setTe4qa("3");
		detTeso.setTe4ta(Constantes.getInstancia().ITEM_TRES_GLOSA + " " + CompletaUtil.formateaNroOperacion(teso.getOperacion()));
		detTeso.setTe1ya(Constantes.getInstancia().ITEM_TRES_CODIGO);
		detTeso.setTe4sa("0");

		teDAO.ingresaDetalleTesoreria(detTeso);

	}

}
