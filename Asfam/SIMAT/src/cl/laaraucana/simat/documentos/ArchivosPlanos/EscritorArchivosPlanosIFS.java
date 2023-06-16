package cl.laaraucana.simat.documentos.ArchivosPlanos;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;

import cl.laaraucana.simat.entidades.ControlDocuVO;
import cl.laaraucana.simat.entidades.DatosLicCobVO;
import cl.laaraucana.simat.entidades.DatosLicResolVO;
import cl.laaraucana.simat.entidades.DocsRevalReemVO;
import cl.laaraucana.simat.entidades.ReintegrosVO;
import cl.laaraucana.simat.entidades.SubsParentalVO;
import cl.laaraucana.simat.entidades.SubsPrePostNMVO;
import cl.laaraucana.simat.utiles.EscritorArchivoSimple;
import cl.laaraucana.simat.utiles.LectorPropiedades;

public class EscritorArchivosPlanosIFS {

	/**
	 * metodo que escribe los datos de plano REINTEGROS
	 * **/
	public boolean escitorSMF01(String periodo, String nameFile, ArrayList listaGeneral, SimpleDateFormat sdf) throws Exception {

		LectorPropiedades lp = new LectorPropiedades();
		String datosActualizar = null;
		Writer out = null;
		//String rutaTemporal=getClass().getResource(lp.getAtributoRepositorio("rutaLocalTemporales")).getPath()+nameFile;
		String rutaTemporal = lp.getAtributoRepositorio("rutaLocalTemporales") + nameFile;
		String carpetaDestino = periodo + "/";
		System.out.println("* * * * " + rutaTemporal + "* * * * *");
		out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(rutaTemporal), "UTF-8"));
		int key = listaGeneral.size();
		int count = 1;
		/*escritura archivo temporal*/
		Iterator it = listaGeneral.iterator();
		while (it.hasNext()) {
			ReintegrosVO reintegros = (ReintegrosVO) it.next();
			datosActualizar = reintegros.getMes_informacion() + "|" + reintegros.getCodigo_entidad() + "|" + reintegros.getMes_nomina() + "|" + reintegros.getRun_beneficiario() + "|"
					+ reintegros.getNombre_beneficiario() + "|" + reintegros.getTipo_subsidio() + "|" + reintegros.getNro_licencia() + "|" + reintegros.getRut_empleador() + "|"
					+ reintegros.getNombre_empleador() + "|" + reintegros.getOrigen_reintegro() + "|" + reintegros.getMonto_total_a_reintegrar() + "|" + reintegros.getMonto_recuperado() + "|"
					+ reintegros.getMonto_recuperado_acum() + "|" + reintegros.getTotal_saldo_a_reintegrar();
			if (count != key) {
				out.write(datosActualizar + "\n");
			} else {
				out.write(datosActualizar);
			}
			count++;
		}
		if (null != out) {
			out.flush();
			out.close();
		}
		/*escritura hacia carpeta destino*/
		EscritorArchivoSimple eas = new EscritorArchivoSimple();
		return eas.setCopyByIFS(carpetaDestino, nameFile, rutaTemporal);
	}//END escitorSMF01

	/**
	 * metodo que escribe los datos de plano SUBPREPOSTNM
	 * **/
	public boolean escitorSMF02(String periodo, String nameFile, ArrayList listaGeneral, SimpleDateFormat sdf) throws Exception {
		LectorPropiedades lp = new LectorPropiedades();
		String datosActualizar = null;
		Writer out = null;
		//		String rutaTemporal=getClass().getResource(lp.getAtributoRepositorio("rutaLocalTemporales")).getPath()+nameFile;
		String rutaTemporal = lp.getAtributoRepositorio("rutaLocalTemporales") + nameFile;
		String carpetaDestino = periodo + "/";
		System.out.println("* * * * " + rutaTemporal + "* * * * *");
		out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(rutaTemporal), "UTF-8"));
		int key = listaGeneral.size();
		int count = 1;
		/*escritura archivo temporal*/
		Iterator it = listaGeneral.iterator();
		while (it.hasNext()) {
			SubsPrePostNMVO subsprepostnm = (SubsPrePostNMVO) it.next();
			datosActualizar = subsprepostnm.getMes_informacion() + "|" + subsprepostnm.getCodigo_entidad() + "|" + subsprepostnm.getAgencia_entidad() + "|" + subsprepostnm.getRut_empleador() + "|"
					+ subsprepostnm.getNombre_empleador() + "|" + subsprepostnm.getRun_beneficiario() + "|" + subsprepostnm.getNombre_beneficiario() + "|" + subsprepostnm.getNro_licencia() + "|"
					+ subsprepostnm.getCodigo_diagnostico() + "|" + subsprepostnm.getVinculo_beneficiario_menor() + "|"
					+ subsprepostnm.getActividad_laboral_trabajador()
					+ "|"
					+ subsprepostnm.getCod_comuna_beneficiario()
					+ "|"
					+ subsprepostnm.getNro_resolucion()

					//+"|"+subsprepostnm.getExtension_postnatal()
					+ "|"
					+ this.getExtPostnSuseso(subsprepostnm.getExtension_postnatal())

					+ "|"
					+ subsprepostnm.getNro_nacimientos()
					+ "|"
					+ subsprepostnm.getNum_dias_licencia_autorizados()

					//+"|"+this.getFechaSuseso(subsprepostnm.getFecha_inicio_reposo(), sdf)
					+ "|"
					+ this.getFechaSuseso(subsprepostnm.getFecha_inicio_reposo_Char(), sdf)
					//+"|"+subsprepostnm.getFecha_inicio_reposo_Char()

					//+"|"+this.getFechaSuseso(subsprepostnm.getFecha_termino_reposo(), sdf)
					+ "|"
					+ this.getFechaSuseso(subsprepostnm.getFecha_termino_reposo_Char(), sdf)
					//+"|"+subsprepostnm.getFecha_termino_reposo_Char()

					+ "|" + subsprepostnm.getNum_dias_sub_pagadados() + "|" + subsprepostnm.getTipo_de_pago() + "|" + subsprepostnm.getMonto_sub_dfl44_1978() + "|"
					+ subsprepostnm.getMonto_sub_pagado() + "|" + subsprepostnm.getTipo_emision() + "|"
					+ subsprepostnm.getMes_nomina()

					//+"|"+subsprepostnm.getMod_pago()
					+ "|" + this.getModPagoSuseso(subsprepostnm.getMod_pago())

					+ "|" + subsprepostnm.getSerie_documento() + "|"
					+ subsprepostnm.getNum_documento()

					//+"|"+this.getFechaSuseso(subsprepostnm.getFecha_emision_documento(), sdf)
					+ "|"
					+ this.getFechaSuseso(subsprepostnm.getFecha_emision_documento_Char(), sdf)
					//+"|"+subsprepostnm.getFecha_emision_documento_Char()

					+ "|" + subsprepostnm.getMonto_documento()

					+ "|" + this.getCodBancoSuseso(subsprepostnm.getCodigo_banco())

					+ "|" + subsprepostnm.getCta_cte() + "|" + subsprepostnm.getCausal_emision() + "|" + subsprepostnm.getNum_dias_cotiz_pagados() + "|" + subsprepostnm.getMonto_renum_imponible()
					+ "|" + subsprepostnm.getMonto_fp() + "|" + subsprepostnm.getMonto_salud() + "|" + subsprepostnm.getMonto_salud_ad() + "|" + subsprepostnm.getMonto_desahucio() + "|"
					+ subsprepostnm.getMonto_cotiz_fu() + "|" + subsprepostnm.getMonto_cotiz_sc() + "|" + subsprepostnm.getEntidad_previsional() + "|" + subsprepostnm.getSubsidio_iniciado();
			if (count != key) {
				out.write(datosActualizar + "\n");
			} else {
				out.write(datosActualizar);
			}
			count++;
		}
		if (null != out) {
			out.flush();
			out.close();
		}
		/*escritura hacia carpeta destino*/
		EscritorArchivoSimple eas = new EscritorArchivoSimple();
		return eas.setCopyByIFS(carpetaDestino, nameFile, rutaTemporal);
	}//END escitorSMF02

	/**
	 * metodo que escribe los datos de plano SUBSPARENTAL
	 * **/
	public boolean escitorSMF03(String periodo, String nameFile, ArrayList listaGeneral, SimpleDateFormat sdf) throws Exception {
		LectorPropiedades lp = new LectorPropiedades();
		String datosActualizar = null;
		Writer out = null;
		//		String rutaTemporal=getClass().getResource(lp.getAtributoRepositorio("rutaLocalTemporales")).getPath()+nameFile;
		String rutaTemporal = lp.getAtributoRepositorio("rutaLocalTemporales") + nameFile;
		String carpetaDestino = periodo + "/";
		System.out.println("* * * * " + rutaTemporal + "* * * * *");
		out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(rutaTemporal), "UTF-8"));
		int key = listaGeneral.size();
		int count = 1;
		/*escritura archivo temporal*/
		Iterator it = listaGeneral.iterator();
		while (it.hasNext()) {
			SubsParentalVO subsParental = (SubsParentalVO) it.next();
			datosActualizar = subsParental.getMes_informacion() + "|" + subsParental.getCodigo_entidad() + "|" + subsParental.getAgencia_entidad() + "|" + subsParental.getRut_empleador() + "|"
					+ subsParental.getNombre_empleador() + "|" + subsParental.getRun_beneficiario_parental() + "|" + subsParental.getNombre_beneficiario_parental() + "|" + subsParental.getEdad()
					+ "|" + subsParental.getSexo()
					+ "|"
					+ subsParental.getCalidad_trabajador()
					+ "|"
					+ subsParental.getActividad_laboral_trabajador()
					+ "|"
					+ subsParental.getCod_comuna_beneficiario()
					+ "|"
					+ subsParental.getRun_beneficiario_postnatal()
					+ "|"
					+ subsParental.getNombre_beneficiario_postnatal()
					+ "|"
					+ subsParental.getNro_licencia()
					+ "|"
					+ subsParental.getVinculo_beneficiario_menor()
					+ "|"
					+ subsParental.getNro_resolucion()
					+ "|"
					+ subsParental.getTipo_extension_postnatal_parental()

					//+"|"+this.getFechaSuseso(subsParental.getFecha_inicio_postnatal_parental(),sdf)
					+ "|"
					+ this.getFechaSuseso(subsParental.getFecha_inicio_postnatal_parental_Char(), sdf)
					//+"|"+subsParental.getFecha_inicio_postnatal_parental_Char()

					//+"|"+this.getFechaSuseso(subsParental.getFecha_termino_postnatal_parental(),sdf)
					+ "|"
					+ this.getFechaSuseso(subsParental.getFecha_termino_postnatal_parental_Char(), sdf)
					//+"|"+subsParental.getFecha_termino_postnatal_parental_Char()

					+ "|" + subsParental.getNum_dias_permiso_pagado() + "|" + subsParental.getTipo_de_pago() + "|" + subsParental.getMonto_sub_dfl44_1978() + "|" + subsParental.getMonto_sub_pagado()
					+ "|" + subsParental.getTipo_emision() + "|" + subsParental.getMes_nomina() + "|" + subsParental.getMod_pago()
					+ "|"
					+ subsParental.getSerie_documento()
					+ "|"
					+ subsParental.getNum_documento()

					//+"|"+this.getFechaSuseso(subsParental.getFecha_emision_documento(),sdf)
					+ "|"
					+ this.getFechaSuseso(subsParental.getFecha_emision_documento_Char(), sdf)
					//+"|"+subsParental.getFecha_emision_documento_Char()

					+ "|" + subsParental.getMonto_documento() + "|" + subsParental.getCodigo_banco() + "|" + subsParental.getCta_cte() + "|" + subsParental.getCausal_emision() + "|"
					+ subsParental.getNum_dias_cotiz_pagados() + "|" + subsParental.getMonto_remun_imponible() + "|" + subsParental.getMonto_fp() + "|" + subsParental.getMonto_salud() + "|"
					+ subsParental.getMonto_salud_ad() + "|" + subsParental.getMonto_desahucio() + "|" + subsParental.getMonto_cotiz_fu() + "|" + subsParental.getMonto_cotiz_sc() + "|"
					+ subsParental.getEntidad_previsional() + "|" + subsParental.getTraspaso() + "|" + subsParental.getSubsidio_iniciado();
			if (count != key) {
				out.write(datosActualizar + "\n");
			} else {
				out.write(datosActualizar);
			}
			count++;
		}
		if (null != out) {
			out.flush();
			out.close();
		}
		/*escritura hacia carpeta destino*/
		EscritorArchivoSimple eas = new EscritorArchivoSimple();
		return eas.setCopyByIFS(carpetaDestino, nameFile, rutaTemporal);
	}//END escitorSMF03

	/**
	 * metodo que escribe los datos de plano SUBSVIG
	 * **/
	public boolean escitorSMF04(String periodo, String nameFile, ArrayList listaGeneral, SimpleDateFormat sdf) throws Exception {
		LectorPropiedades lp = new LectorPropiedades();
		String datosActualizar = null;
		Writer out = null;
		//		String rutaTemporal=getClass().getResource(lp.getAtributoRepositorio("rutaLocalTemporales")).getPath()+nameFile;
		String rutaTemporal = lp.getAtributoRepositorio("rutaLocalTemporales") + nameFile;
		String carpetaDestino = periodo + "/";
		System.out.println("* * * * " + rutaTemporal + "* * * * *");
		out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(rutaTemporal), "UTF-8"));
		int key = listaGeneral.size();
		int count = 1;
		/*
		//escritura archivo temporal
			Iterator it = listaGeneral.iterator();				
			while(it.hasNext()){	
				if(count!=key){
					out.write(datosActualizar+"\n");
				}else{
					out.write(datosActualizar);
				}
				count++;
			}
			*/
		if (null != out) {
			out.flush();
			out.close();
		}

		//escritura hacia carpeta destino
		EscritorArchivoSimple eas = new EscritorArchivoSimple();
		return eas.setCopyByIFS(carpetaDestino, nameFile, rutaTemporal);
	}//END escitorSMF04

	/**
	 * metodo que escribe los datos de plano CONTROLDOCU
	 * **/
	public boolean escitorSMF05(String periodo, String nameFile, ArrayList listaGeneral, SimpleDateFormat sdf) throws Exception {
		LectorPropiedades lp = new LectorPropiedades();
		String datosActualizar = null;
		Writer out = null;
		//		String rutaTemporal=getClass().getResource(lp.getAtributoRepositorio("rutaLocalTemporales")).getPath()+nameFile;
		String rutaTemporal = lp.getAtributoRepositorio("rutaLocalTemporales") + nameFile;
		String carpetaDestino = periodo + "/";
		System.out.println("* * * * " + rutaTemporal + "* * * * *");
		out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(rutaTemporal), "UTF-8"));
		int key = listaGeneral.size();
		int count = 1;
		/*escritura archivo temporal*/
		Iterator it = listaGeneral.iterator();
		while (it.hasNext()) {
			ControlDocuVO control = (ControlDocuVO) it.next();
			datosActualizar = control.getMes_informacion() + "|" + control.getCodigo_entidad() + "|" + control.getTipo_subsidio() + "|" + control.getRut_empleador() + "|"
					+ control.getNombre_empleador() + "|" + control.getRun_beneficiario() + "|" + control.getNombre_beneficiario() + "|" + control.getMod_pago() + "|" + control.getSerie_documento()
					+ "|" + control.getNum_documento()

					//+"|"+this.getFechaSuseso(control.getFecha_emision_documento(),sdf)
					+ "|" + this.getFechaSuseso(control.getFecha_emision_documento_Char(), sdf)
					//+"|"+control.getFecha_emision_documento_Char()

					+ "|" + control.getMonto_documento() + "|" + control.getCodigo_banco() + "|" + control.getEstado_documento()

					//+"|"+this.getFechaSuseso(control.getFecha_cambio_estado(),sdf)
					+ "|" + this.getFechaSuseso(control.getFecha_cambio_estado_Char(), sdf)
			//+"|"+control.getFecha_cambio_estado_Char()
			;
			if (count != key) {
				out.write(datosActualizar + "\n");
			} else {
				out.write(datosActualizar);
			}
			count++;
		}
		if (null != out) {
			out.flush();
			out.close();
		}
		/*escritura hacia carpeta destino*/
		EscritorArchivoSimple eas = new EscritorArchivoSimple();
		return eas.setCopyByIFS(carpetaDestino, nameFile, rutaTemporal);
	}//END escitorSMF05

	/**
	 * metodo que escribe los datos de plano DOCSREVALREEM
	 * **/
	public boolean escitorSMF06(String periodo, String nameFile, ArrayList listaGeneral, SimpleDateFormat sdf) throws Exception {
		LectorPropiedades lp = new LectorPropiedades();
		String datosActualizar = null;
		Writer out = null;
		//		String rutaTemporal=getClass().getResource(lp.getAtributoRepositorio("rutaLocalTemporales")).getPath()+nameFile;
		String rutaTemporal = lp.getAtributoRepositorio("rutaLocalTemporales") + nameFile;
		String carpetaDestino = periodo + "/";
		System.out.println("* * * * " + rutaTemporal + "* * * * *");
		out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(rutaTemporal), "UTF-8"));
		int key = listaGeneral.size();
		int count = 1;
		/*escritura archivo temporal*/
		Iterator it = listaGeneral.iterator();
		while (it.hasNext()) {
			DocsRevalReemVO docsRevalReem = (DocsRevalReemVO) it.next();
			datosActualizar = docsRevalReem.getMes_informacion() + "|" + docsRevalReem.getCodigo_entidad() + "|" + docsRevalReem.getTiposubsidio() + "|" + docsRevalReem.getMod_pago_original() + "|"
					+ docsRevalReem.getCodigo_banco_original() + "|" + docsRevalReem.getSerie_documento_original() + "|" + docsRevalReem.getNum_documento_original()

					//+"|"+this.getFechaSuseso(docsRevalReem.getFecha_emision_documento_original(),sdf)
					+ "|" + this.getFechaSuseso(docsRevalReem.getFecha_emision_documento_original_Char(), sdf)
					//+"|"+docsRevalReem.getFecha_emision_documento_original_Char()

					+ "|" + docsRevalReem.getMonto_documento_original() + "|" + docsRevalReem.getEstado_documento_original() + "|" + docsRevalReem.getMod_pago_nuevo() + "|"
					+ docsRevalReem.getCodigo_banco_nuevo() + "|" + docsRevalReem.getSerie_documento_nuevo() + "|" + docsRevalReem.getNum_documento_nuevo()

					//+"|"+this.getFechaSuseso(docsRevalReem.getFecha_emision_documento_nuevo(),sdf)
					+ "|" + this.getFechaSuseso(docsRevalReem.getFecha_emision_documento_nuevo_Char(), sdf)
					//+"|"+docsRevalReem.getFecha_emision_documento_nuevo_Char()

					+ "|" + docsRevalReem.getMonto_documento_nuevo();
			if (count != key) {
				out.write(datosActualizar + "\n");
			} else {
				out.write(datosActualizar);
			}
			count++;
		}
		if (null != out) {
			out.flush();
			out.close();
		}
		/*escritura hacia carpeta destino*/
		EscritorArchivoSimple eas = new EscritorArchivoSimple();
		return eas.setCopyByIFS(carpetaDestino, nameFile, rutaTemporal);
	}//END escitorSMF06

	/**
	 * metodo que escribe los datos de plano DATOSLICCOB
	 * **/
	public boolean escitorSMF07(String periodo, String nameFile, ArrayList listaGeneral, SimpleDateFormat sdf) throws Exception {
		LectorPropiedades lp = new LectorPropiedades();
		String datosActualizar = null;
		Writer out = null;
		//		String rutaTemporal=getClass().getResource(lp.getAtributoRepositorio("rutaLocalTemporales")).getPath()+nameFile;
		String rutaTemporal = lp.getAtributoRepositorio("rutaLocalTemporales") + nameFile;
		String carpetaDestino = periodo + "/";
		System.out.println("* * * * " + rutaTemporal + "* * * * *");
		out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(rutaTemporal), "UTF-8"));
		int key = listaGeneral.size();
		int count = 1;
		/*escritura archivo temporal*/
		Iterator it = listaGeneral.iterator();
		while (it.hasNext()) {
			DatosLicCobVO datosLicCob = (DatosLicCobVO) it.next();
			datosActualizar = datosLicCob.getMes_informacion()
					+ "|"
					+ datosLicCob.getCodigo_entidad()
					+ "|"
					+ datosLicCob.getNro_licencia()
					+ "|"
					+ datosLicCob.getRun_beneficiario()
					+ "|"
					+ datosLicCob.getNombre_beneficiario()
					+ "|"
					+ datosLicCob.getEdad()
					+ "|"
					+ datosLicCob.getSexo()

					//+"|"+this.getFechaSuseso(datosLicCob.getFecha_emision_licencia(),sdf)
					+ "|"
					+ this.getFechaSuseso(datosLicCob.getFecha_emision_licencia_Char(), sdf)
					//+"|"+datosLicCob.getFecha_emision_licencia_Char()

					//+"|"+this.getFechaSuseso(datosLicCob.getFecha_inicio_reposo(),sdf)
					+ "|"
					+ this.getFechaSuseso(datosLicCob.getFecha_inicio_reposo_Char(), sdf)
					//+"|"+datosLicCob.getFecha_inicio_reposo_Char()

					//+"|"+this.getFechaSuseso(datosLicCob.getFecha_termino_reposo(),sdf)
					+ "|"
					+ this.getFechaSuseso(datosLicCob.getFecha_termino_reposo_Char(), sdf)
					//+"|"+datosLicCob.getFecha_termino_reposo_Char()

					+ "|" + datosLicCob.getNro_dias_licencia() + "|" + datosLicCob.getNum_dias_licencia_autorizados() + "|" + datosLicCob.getRun_menor_enfermo()
					+ "|"
					+ datosLicCob.getNombre_menor_enfermo()

					//+"|"+this.getFechaSuseso(datosLicCob.getFecha_nac_menor_enfermo(),sdf)
					+ "|"
					+ this.getFechaSuseso(datosLicCob.getFecha_nac_menor_enfermo_Char(), sdf)
					//+"|"+datosLicCob.getFecha_nac_menor_enfermo_Char()

					+ "|" + datosLicCob.getCod_tipo_licencia() + "|" + datosLicCob.getCod_comuna_beneficiario() + "|" + datosLicCob.getRun_profesional() + "|" + datosLicCob.getNombre_profesional()
					+ "|" + datosLicCob.getRegistro_colegio_profesional() + "|" + datosLicCob.getCodigo_diagnostico() + "|" + datosLicCob.getRut_empleador() + "|" + datosLicCob.getNombre_empleador()
					+ "|" + datosLicCob.getCalidad_trabajador();
			if (count != key) {
				out.write(datosActualizar + "\n");
			} else {
				out.write(datosActualizar);
			}
			count++;
		}
		if (null != out) {
			out.flush();
			out.close();
		}
		/*escritura hacia carpeta destino*/
		EscritorArchivoSimple eas = new EscritorArchivoSimple();
		return eas.setCopyByIFS(carpetaDestino, nameFile, rutaTemporal);
	}//END escitorSMF07

	/**
	 * metodo que escribe los datos de plano DATOSLICRESOL
	 * **/
	public boolean escitorSMF08(String periodo, String nameFile, ArrayList listaGeneral, SimpleDateFormat sdf) throws Exception {
		LectorPropiedades lp = new LectorPropiedades();
		String datosActualizar = null;
		Writer out = null;
		//		String rutaTemporal=getClass().getResource(lp.getAtributoRepositorio("rutaLocalTemporales")).getPath()+nameFile;
		String rutaTemporal = lp.getAtributoRepositorio("rutaLocalTemporales") + nameFile;
		String carpetaDestino = periodo + "/";
		System.out.println("* * * * " + rutaTemporal + "* * * * *");
		out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(rutaTemporal), "UTF-8"));
		int key = listaGeneral.size();
		int count = 1;
		/*escritura archivo temporal*/
		Iterator it = listaGeneral.iterator();
		while (it.hasNext()) {
			DatosLicResolVO datosLicResol = (DatosLicResolVO) it.next();
			datosActualizar = 
			datosLicResol.getMes_informacion() + "|" + 
			datosLicResol.getCodigo_entidad() + "|" + 
			datosLicResol.getNro_licencia() + "|" + 
			datosLicResol.getReconsideracion() + "|" + 
			datosLicResol.getRun_beneficiario() + "|" + 
			datosLicResol.getNombre_beneficiario() + "|" + 
			datosLicResol.getVinculo_beneficiario_menor() + "|" + 
			datosLicResol.getEdad() + "|" + 
			datosLicResol.getSexo() + "|" +
			datosLicResol.getActividad_laboral_trabajador() + "|" + 
			datosLicResol.getNro_nacimientos() + "|" + 
			this.getFechaSuseso(datosLicResol.getFecha_emision_licencia(), sdf) + "|" + 
			this.getFechaSuseso(datosLicResol.getFecha_inicio_reposo(), sdf) + "|" + 
			this.getFechaSuseso(datosLicResol.getFecha_termino_reposo(), sdf) + "|" + 
			datosLicResol.getNum_dias_licencia() + "|" + 
			datosLicResol.getNum_dias_licencia_autorizados() + "|" + 
			datosLicResol.getNum_dias_licencia_rechazados() + "|" + 
			datosLicResol.getRun_menor_enfermo() + "|" + 
			datosLicResol.getNombre_menor_enfermo() + "|" + 
			this.getFechaSuseso(datosLicResol.getFecha_nac_menor_enfermo(), sdf) + "|" + 
			datosLicResol.getCod_comuna_beneficiario() + "|" + 
			datosLicResol.getRun_profesional() + "|" + 
			datosLicResol.getNombre_profesional() + "|" + 
			datosLicResol.getRegistro_colegio_profesional() + "|" + 
			datosLicResol.getCod_tipo_licencia() + "|" + 
			datosLicResol.getCodigo_diagnostico() + "|" + 
			datosLicResol.getRut_empleador() + "|" + 
			datosLicResol.getNombre_empleador() + "|" + 
			datosLicResol.getCalidad_trabajador() + "|" + 
			datosLicResol.getEstado_tramitacion() + "|" + 
			datosLicResol.getCausal_rechazo_licencia();
			
			if (count != key) {
				out.write(datosActualizar + "\n");
			} else {
				out.write(datosActualizar);
			}
			count++;
		}
		if (null != out) {
			out.flush();
			out.close();
		}
		/*escritura hacia carpeta destino*/
		EscritorArchivoSimple eas = new EscritorArchivoSimple();
		return eas.setCopyByIFS(carpetaDestino, nameFile, rutaTemporal);
	}//END escitorSMF08

	private String getFechaSuseso(Date in, SimpleDateFormat sdf) {
		/*sdf en formato aaaaMMdd*/
		String out = "";
		try {
			out = sdf.format(in);
			if (out.equals("00010101") || out.equals("19400101")) {
				out = "";
			}
		} catch (Exception ex) {
			out = "";
		}
		return out;
	}

	private String getFechaSuseso(String in, SimpleDateFormat sdf) {
		System.out.println("* * * * getFechaSuseso(in): " + in);
		/*sdf en formato aaaaMMdd*/
		String out = "";
		try {
			out = in;
			if (out.equalsIgnoreCase("0001-01-01") || out.equalsIgnoreCase("00010101") || out.equalsIgnoreCase("")) {
				out = "";
			} else {
				//out=sdf.format(out);
				out = out.replaceAll("-", "");
			}
		} catch (Exception ex) {
			out = "";
		}
		System.out.println("* * * * getFechaSuseso return: " + out);
		return out;
	}

	//SMEXTPOSTN
	private String getExtPostnSuseso(int dato) {
		String out = "";
		try {
			out = String.valueOf(dato);
			if (out.equalsIgnoreCase("0")) {
				out = "";
			}
		} catch (Exception ex) {
			out = "";
		}
		return out;
	}

	//SMMODPAGO 
	private String getModPagoSuseso(int dato) {
		String out = "";
		try {
			out = String.valueOf(dato);
			if (out.equalsIgnoreCase("0")) {
				out = "";
			}
		} catch (Exception ex) {
			out = "";
		}
		return out;
	}

	//	SMMODPAGO 
	private String getCodBancoSuseso(String dato) {
		String out = "";
		if (dato.equalsIgnoreCase("000") || dato.equalsIgnoreCase("0") || dato.equalsIgnoreCase("0") || dato == null) {
			out = "";
		} else {
			out = dato;
		}
		return out;
	}

}//END EscritorArchivosPlanosSMB
