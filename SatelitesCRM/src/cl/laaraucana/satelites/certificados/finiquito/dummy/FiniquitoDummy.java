package cl.laaraucana.satelites.certificados.finiquito.dummy;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cl.laaraucana.satelites.Utils.Utils;
import cl.laaraucana.satelites.certificados.finiquito.VO.CertificadoFiniquitoVO;
import cl.laaraucana.satelites.certificados.finiquito.VO.SalidaDetalleFiniquitoVO;
import cl.laaraucana.satelites.certificados.finiquito.VO.SalidaFiniquitoVO;
import cl.laaraucana.satelites.certificados.finiquito.VO.SalidaListaDetalleFiniquitoVO;
import cl.laaraucana.satelites.certificados.finiquito.VO.SalidaListaFiniquitoVO;
import cl.laaraucana.satelites.certificados.finiquito.utils.FiniquitoLocalUtil;
import cl.laaraucana.satelites.certificados.utils.CertificadoConst;

public class FiniquitoDummy {

	FiniquitoLocalUtil finiquitoUtil = new FiniquitoLocalUtil();

	public CertificadoFiniquitoVO getFiniquitoDummyVO(String rut, String fechaFiniquito) {
		SalidaListaFiniquitoVO salidaListaFiniquitoVO = new SalidaListaFiniquitoVO();
		List<SalidaFiniquitoVO> listaCreditos = new ArrayList<SalidaFiniquitoVO>();

		SalidaFiniquitoVO salidaFinVO = new SalidaFiniquitoVO("2-108937-8", "9", "24", 1854497, "Consumo", 0, "000000", "000000");
		listaCreditos.add(salidaFinVO);
		salidaFinVO = new SalidaFiniquitoVO("4-108937-2", "24", "36", 2854497, "Consumo", 0, "", "");
		listaCreditos.add(salidaFinVO);
		salidaFinVO = new SalidaFiniquitoVO("4-108937-2", "15", "24", 5854497, CertificadoConst.RES_CERTIFICADOS.getString("certificado.tipoCredito.especial").trim(), 0, "", "");
		listaCreditos.add(salidaFinVO);
		salidaFinVO = new SalidaFiniquitoVO("4-208937-5", "36", "48", 3854497, CertificadoConst.RES_CERTIFICADOS.getString("certificado.tipoCredito.especial").trim(), 0, "500", "5");
		listaCreditos.add(salidaFinVO);

		// detalle cuotas
		salidaFinVO = new SalidaFiniquitoVO("4-108937-2", "4", "12", 2854497, CertificadoConst.RES_CERTIFICADOS.getString("certificado.tipoCredito.educacionSuperior").trim(), 0, "500", "5");
		
		SalidaListaDetalleFiniquitoVO listaDetalle = new SalidaListaDetalleFiniquitoVO();
		List<SalidaDetalleFiniquitoVO> listaCuotas = new ArrayList<SalidaDetalleFiniquitoVO>();
		SalidaDetalleFiniquitoVO salidaDetalle = new SalidaDetalleFiniquitoVO("1", 50000, 50);
		salidaDetalle.setFolio("4-108937-2");
		listaCuotas.add(salidaDetalle);
		salidaDetalle = new SalidaDetalleFiniquitoVO("2", 400000, 100);
		salidaDetalle.setFolio("4-108937-2");
		listaCuotas.add(salidaDetalle);
		salidaDetalle = new SalidaDetalleFiniquitoVO("3", 50000, 200);
		salidaDetalle.setFolio("4-108937-2");
		listaCuotas.add(salidaDetalle);
		salidaDetalle = new SalidaDetalleFiniquitoVO("4", 200000, 300);
		salidaDetalle.setFolio("4-108937-2");
		listaCuotas.add(salidaDetalle);
		salidaDetalle = new SalidaDetalleFiniquitoVO("5", 100000, 400);
		salidaDetalle.setFolio("4-108937-2");
		listaCuotas.add(salidaDetalle);
		salidaDetalle = new SalidaDetalleFiniquitoVO("6", 50000, 500);
		salidaDetalle.setFolio("4-108937-2");
		listaCuotas.add(salidaDetalle);
		salidaDetalle = new SalidaDetalleFiniquitoVO("7", 50000, 600);
		salidaDetalle.setFolio("4-108937-2");
		listaCuotas.add(salidaDetalle);
		salidaDetalle = new SalidaDetalleFiniquitoVO("8", 50000, 700);
		salidaDetalle.setFolio("4-108937-2");
		listaCuotas.add(salidaDetalle);
		salidaDetalle = new SalidaDetalleFiniquitoVO("9", 50000, 800);
		salidaDetalle.setFolio("4-108937-2");
		listaCuotas.add(salidaDetalle);
		salidaDetalle = new SalidaDetalleFiniquitoVO("10", 50000, 900);
		salidaDetalle.setFolio("4-108937-2");
		listaCuotas.add(salidaDetalle);
		salidaDetalle = new SalidaDetalleFiniquitoVO("11", 50000, 1000);
		salidaDetalle.setFolio("4-108937-2");
		listaCuotas.add(salidaDetalle);
		salidaDetalle = new SalidaDetalleFiniquitoVO("12", 50000, 200);
		salidaDetalle.setFolio("4-108937-2");
		listaCuotas.add(salidaDetalle);
		// cargando detalles
		listaDetalle.setListaCuotas(listaCuotas);
		salidaFinVO.setListaDetalle(listaDetalle);
		listaCreditos.add(salidaFinVO);

		// detalle cuotas
		salidaFinVO = new SalidaFiniquitoVO("4-108937-5", "8", "12", 2854497, CertificadoConst.RES_CERTIFICADOS.getString("certificado.tipoCredito.educacionSuperior").trim(), 0, "500", "5");
		SalidaListaDetalleFiniquitoVO listaDetalle2 = new SalidaListaDetalleFiniquitoVO();
		List<SalidaDetalleFiniquitoVO> listaCuotas2 = new ArrayList<SalidaDetalleFiniquitoVO>();
		SalidaDetalleFiniquitoVO salidaDetalle2 = new SalidaDetalleFiniquitoVO("1", 50000, 50);
		salidaDetalle2.setFolio("4-108937-5");
		listaCuotas2.add(salidaDetalle2);
		salidaDetalle2 = new SalidaDetalleFiniquitoVO("2", 400000, 100);
		salidaDetalle2.setFolio("4-108937-5");
		listaCuotas2.add(salidaDetalle2);
		salidaDetalle2 = new SalidaDetalleFiniquitoVO("3", 50000, 200);
		salidaDetalle2.setFolio("4-108937-5");
		listaCuotas2.add(salidaDetalle2);
		salidaDetalle2 = new SalidaDetalleFiniquitoVO("4", 200000, 300);
		salidaDetalle2.setFolio("4-108937-5");
		listaCuotas2.add(salidaDetalle2);
		salidaDetalle2 = new SalidaDetalleFiniquitoVO("5", 100000, 400);
		salidaDetalle2.setFolio("4-108937-5");
		listaCuotas2.add(salidaDetalle2);
		salidaDetalle2 = new SalidaDetalleFiniquitoVO("6", 50000, 500);
		salidaDetalle2.setFolio("4-108937-5");
		listaCuotas2.add(salidaDetalle2);
		salidaDetalle2 = new SalidaDetalleFiniquitoVO("7", 50000, 600);
		salidaDetalle2.setFolio("4-108937-5");
		listaCuotas2.add(salidaDetalle2);
		salidaDetalle2 = new SalidaDetalleFiniquitoVO("8", 50000, 700);
		salidaDetalle2.setFolio("4-108937-5");
		listaCuotas2.add(salidaDetalle2);
		salidaDetalle2 = new SalidaDetalleFiniquitoVO("9", 50000, 800);
		salidaDetalle2.setFolio("4-108937-5");
		listaCuotas2.add(salidaDetalle2);
		salidaDetalle2 = new SalidaDetalleFiniquitoVO("10", 50000, 900);
		salidaDetalle2.setFolio("4-108937-5");
		listaCuotas2.add(salidaDetalle2);
		salidaDetalle2 = new SalidaDetalleFiniquitoVO("11", 50000, 1000);
		salidaDetalle2.setFolio("4-108937-5");
		listaCuotas2.add(salidaDetalle2);
		salidaDetalle2 = new SalidaDetalleFiniquitoVO("12", 50000, 200);
		salidaDetalle2.setFolio("4-108937-5");
		listaCuotas2.add(salidaDetalle2);
		// cargando detalles
		listaDetalle2.setListaCuotas(listaCuotas2);
		salidaFinVO.setListaDetalle(listaDetalle2);
		listaCreditos.add(salidaFinVO);

		// cargando lista
		
		salidaListaFiniquitoVO.setListaCreditos(listaCreditos);

		// objecto que completa la informacion del certificado
		CertificadoFiniquitoVO certificadoFiniquito = new CertificadoFiniquitoVO();
		certificadoFiniquito.setRut(rut);
		certificadoFiniquito.setNombre("GARIN GUERRER ENRIQUE");
		certificadoFiniquito.setRutEmpresa("70.016.160-9");
		certificadoFiniquito.setNombreEmpresa("LA ARAUCANA C.C.A.F");
		certificadoFiniquito.setFechaFiniquito(fechaFiniquito);
		certificadoFiniquito.setFechaSolicitud(Utils.dateToString(new Date()));
		certificadoFiniquito.setListaCreditos(listaCreditos);
		certificadoFiniquito.setListaCreditosSocial(finiquitoUtil.getCreditosVigentesSocialesList(listaCreditos));
		certificadoFiniquito.setListaCreditosEspecial(finiquitoUtil.getCreditosVigentesEspecialesList(listaCreditos));
		certificadoFiniquito.setListaCreditosEducacion(finiquitoUtil.getCreditosVigentesEducacionList(listaCreditos));
		certificadoFiniquito.setTotalSocial(finiquitoUtil.getTotalSocial());
		certificadoFiniquito.setTotalEspecial(finiquitoUtil.getTotalEspecial());
		certificadoFiniquito.setTotalEducacion(finiquitoUtil.getTotalEducacion());

		return certificadoFiniquito;
	}
}
