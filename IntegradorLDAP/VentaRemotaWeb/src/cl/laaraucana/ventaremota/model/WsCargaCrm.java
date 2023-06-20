package cl.laaraucana.ventaremota.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import cl.laaraucana.ventaremota.util.Configuraciones;

import com.lautaro.xi.CRM.WEB_Mobile.Ia_CargaFirmaWebFS_DT;
import com.lautaro.xi.CRM.WEB_Mobile.Oa_CargaFirmaWebFS_DT;
import com.lautaro.xi.CRM.WEB_Mobile.Os_CargaFirmaWebFS_SIBindingStub;

public class WsCargaCrm {

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	SimpleDateFormat sdh = new SimpleDateFormat("HH:mm:ss");

	private String estado = "";
	private String numeroOferta = "";

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getNumeroOferta() {
		return numeroOferta;
	}

	public void setNumeroOferta(String numeroOferta) {
		this.numeroOferta = numeroOferta;
	}

	public Oa_CargaFirmaWebFS_DT wsCargaCrm(WsCargaCrm crm) throws Exception{
		
		Oa_CargaFirmaWebFS_DT res = new Oa_CargaFirmaWebFS_DT();
		
		try {

		String ep = Configuraciones.getConfig("ep.crm");
		String usuario = Configuraciones.getConfig("servicios.sap.username");
		String clave = Configuraciones.getConfig("servicios.sap.pass");

		Os_CargaFirmaWebFS_SIBindingStub _stub = new Os_CargaFirmaWebFS_SIBindingStub();
		_stub.setUsername(usuario);
		_stub.setPassword(clave);
		_stub._setProperty(Os_CargaFirmaWebFS_SIBindingStub.ENDPOINT_ADDRESS_PROPERTY, ep);

		

		Ia_CargaFirmaWebFS_DT cargaFirma = new Ia_CargaFirmaWebFS_DT();

		cargaFirma.setESTADO(crm.getEstado());
		cargaFirma.setFECHA(sdf.format(new Date()));
		cargaFirma.setHORA(sdh.format(new Date()));
		cargaFirma.setNUM_OFERTA(crm.numeroOferta);

		res = _stub.os_CargaFirmaWebFS_SI(cargaFirma);

		}catch (Exception e) {
			// TODO: handle exception
			
			throw new Exception("Error crm ", e);
		}
		
		return res;

	}

}
