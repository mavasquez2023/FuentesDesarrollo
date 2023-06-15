package etc;

import cl.laaraucana.botonpago.web.database.vo.DatosTransferenciaVO;
import cl.laaraucana.botonpago.web.manager.ManagerLibroBanco;
import cl.laaraucana.botonpago.web.utils.ImprimeUtil;

public class TestLibroPAgo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ManagerLibroBanco mgr = new ManagerLibroBanco();
		DatosTransferenciaVO transf = new DatosTransferenciaVO();
		transf.setBanco("10");
		transf.setNumCtaCte("18210072-03");
		transf.setMonto("3000");
		transf.setTipoAbono("A");
		transf.setNumeroDeposito("18422");
		transf.setCodigoOperacionInterna("90");
		transf.setFolioTesoreria("30042689");
		transf.setRutCliente("16502940");
		transf.setDvCliente("2");
		transf = mgr.registrarLibroBanco(transf);

		ImprimeUtil.imprimirCampos(transf);

	}

	/*
	Banco : 10
	CtaCte: 18210072-03
	monto:3000
	tipoAbono: 'A'
	Numero Deposito: 18422
	Codigo Operacion Interna: 90
	Descripcion: opc
	folio: 30042689
	rut: 16502940
	dv: 2
	Indicador Error: 0
	 */

}
