package cl.araucana.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cl.araucana.wssiagfclient.business.model.ActualizaReconocimientoSiagfDTO;
import cl.araucana.wssiagfclient.business.model.RespuestaConsultaSiagfDTO;
import cl.araucana.wssiagfclient.business.model.TramoSiagfDTO;
import cl.araucana.wssiagfclient.business.model.TuplaSiagfDTO;
import cl.araucana.wssiagfclient.business.services.WSSiagfClientService;
import cl.araucana.wssiagfclient.business.services.impl.WSSiagfClientServiceImpl;
import cl.araucana.wssiagfclient.common.exception.ServiceException;
import cl.araucana.wssiagfclient.common.exception.XmlSiagfException;
import cl.araucana.wssiagfclient.commons.utils.FechaUtil;
import cl.araucana.wssiagfclient.commons.utils.XmlSiagfUtil;

public class Test1 {

	private static String xmlParam;
	public static void main(String[] args) throws XmlSiagfException, ServiceException {
		setXmlParam();
		WSSiagfClientService wsSiagfClientService = new WSSiagfClientServiceImpl();
		
		ActualizaReconocimientoSiagfDTO actRecDTO = XmlSiagfUtil.parseActualizaReconocimiento(xmlParam);
		RespuestaConsultaSiagfDTO respWSSiagf = wsSiagfClientService.consultaSiagf(actRecDTO.getRutCausante());
		
		if(respWSSiagf != null && respWSSiagf.getTuplas() != null){
			TuplaSiagfDTO tuplaSel = seleccionaTupla(respWSSiagf.getTuplas(), actRecDTO.getRutCausante(), 
					actRecDTO.getRutBeneficiario(), actRecDTO.getCodTipoBeneficio(), actRecDTO.getFecRecCausante()); 

			if(tuplaSel != null){
//				Prepara los datos a comparar
				int codTipoCauXml = (actRecDTO.getCodTipoCausante()!=null)?
						Integer.parseInt(actRecDTO.getCodTipoCausante().trim()):0;
				int codEntAdminXml = (actRecDTO.getCodEntidadAdm()!=null)?
						Integer.parseInt(actRecDTO.getCodEntidadAdm().trim()):0;
				int codTramoActXml = (actRecDTO.getTramoAsigFam()!=null)?
						Integer.parseInt(actRecDTO.getTramoAsigFam().trim()):0;
				String rutEmpresaXml = (actRecDTO.getRutEmpleador()!=null)?
						actRecDTO.getRutEmpleador().trim():"";
						
				int codTipoCauTuplaSel = (tuplaSel.getCodTipoCausante()!=null)?
						tuplaSel.getCodTipoCausante().intValue():0;
				int codEntAdminTuplaSel = (tuplaSel.getCodEntidadAdm()!=null)?
						tuplaSel.getCodEntidadAdm().intValue():0;
				int codTramoActTuplaSel = (tuplaSel.getTramoAsigFam()!=null)?
						tuplaSel.getTramoAsigFam().intValue():0;
				String rutEmpresaTuplaSel = (tuplaSel.getRutEmpleador()!=null)?
						tuplaSel.getRutEmpleador().trim():"";
						
				if(codTipoCauXml != codTipoCauTuplaSel
					|| codEntAdminXml != codEntAdminTuplaSel
					|| codTramoActXml != codTramoActTuplaSel
					|| !rutEmpresaXml.equals(rutEmpresaTuplaSel)){
//							Marcar la operacion como no ejecutada en siagf
					System.out.println("-45");
				}else{
					List listTramoIngreso = actRecDTO.getTramos();
					List listTramoTupla = tuplaSel.getTramos();
					if(listTramoIngreso==null) listTramoIngreso = new ArrayList();
					if(listTramoTupla==null) listTramoTupla = new ArrayList();
					
					if(listTramoTupla.size() != listTramoIngreso.size()){
//						Marcar la operacion como no ejecutada en siagf		
						System.out.println("-45");
					}else{
						for(int i = 0; i < listTramoTupla.size(); i++){
							TramoSiagfDTO tramoTupla = (TramoSiagfDTO)listTramoTupla.get(i);
							TramoSiagfDTO tramoIngreso = (TramoSiagfDTO)listTramoIngreso.get(i);
							if(tramoTupla.getNumTramo().intValue() != tramoIngreso.getNumTramo().intValue()){
//								Marcar la operacion como no ejecutada en siagf
								System.out.println("-45");
								break;
								
							}
						}
					}
				}
			}else{
//				Marcar la operacion como no ejecutada en siagf
				System.out.println("-45");
			}
		}
	}
	
	private static TuplaSiagfDTO seleccionaTupla(List tuplas, String rutCausante, String rutBeneficiario, 
			String codBeneficio, String fecRecCausante){
		if(tuplas!=null){
			rutCausante = (rutCausante!=null)?rutCausante.trim():"";
			rutBeneficiario = (rutBeneficiario!=null)?rutBeneficiario.trim():"";
			int codigoBeneficio = (codBeneficio!=null)?Integer.parseInt(codBeneficio.trim()):0;
			fecRecCausante = (fecRecCausante!=null)?fecRecCausante.trim():"";
			
			Iterator it = tuplas.iterator();
			while(it.hasNext()){
				TuplaSiagfDTO tupla = (TuplaSiagfDTO) it.next();
				
				String rutCauTupla = (tupla.getRutCausante()!=null)?tupla.getRutCausante().trim():"";
				String rutBeneTupla = (tupla.getRutBeneficiario()!=null)?tupla.getRutBeneficiario().trim():"";
				int codTipoBenTupla = (tupla.getCodTipoBeneficio()!=null)?tupla.getCodTipoBeneficio().intValue():0;
				String fecRecCauTupla = FechaUtil.formatearFecha("yyyy-MM-dd",tupla.getFecRecCausante());
				fecRecCauTupla = (fecRecCauTupla!=null)?fecRecCauTupla:"";
				
				if(rutCausante.equals(rutCauTupla)
					&& rutBeneficiario.equals(rutBeneTupla)
					&& codigoBeneficio == codTipoBenTupla
					&& fecRecCausante.equals(fecRecCauTupla)){
					return tupla;
				}
			}
		}
		return null;
	}


	private static void setXmlParam(){
		xmlParam = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?><ActualizacionCausante version=\"1.0\">    <FechaEmision>2014-10-29</FechaEmision>    <Tupla>        <RutCausante>19753475-3</RutCausante>        <CodTipoCausante>04</CodTipoCausante>        <CodEntidadAdm>10105</CodEntidadAdm>        <FecRecCausante>2014-03-01</FecRecCausante>        <RutBeneficiario>13496577-0</RutBeneficiario>        <CodTipoBeneficio>01</CodTipoBeneficio>    </Tupla>    <Modificar>        <IngPromedio>360333</IngPromedio>        <MontoUnitarioBeneficio>1793</MontoUnitarioBeneficio>        <TramoAsigFam>3</TramoAsigFam>	<Tramos>		<Tramo>			<Periodo>2013</Periodo>			<NumTramo>2</NumTramo>			<IngPromedio>285601</IngPromedio>			<MontoUnitarioBeneficio>5294</MontoUnitarioBeneficio>		</Tramo>	</Tramos>        <NombreBeneficiario>ARANEDA NUNEZ CLAUDIA MARCELA</NombreBeneficiario>        <RutEmpleador>69255400-0</RutEmpleador>        <NomEmpleador>I. MUNICIPALIDAD DE HUECHURABA</NomEmpleador>        <Acteco>751120</Acteco>        <RegionEmpleador>13</RegionEmpleador>        <ComunaEmpleador>13107</ComunaEmpleador>    </Modificar></ActualizacionCausante>";
	}
}
