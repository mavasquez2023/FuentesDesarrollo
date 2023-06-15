/**
 * MotorCreditScoringSOAPImpl.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * o0445.04 v11904204728
 */

package cl.araucana.www;

import java.rmi.RemoteException;

import javax.xml.namespace.QName;
import javax.xml.rpc.holders.FloatHolder;
import javax.xml.rpc.holders.IntHolder;
import javax.xml.rpc.holders.StringHolder;
import javax.xml.rpc.soap.SOAPFaultException;
import javax.xml.soap.Detail;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFactory;

import cl.araucana.www.holders.CondicionArrayHolder;

import cse.model.businessobject.CondicionOtorgamiento;
import cse.model.businessobject.Monto;
import cse.model.businessobject.Rut;
import cse.model.exception.ScoringModuleException;
import cse.model.service.CondicionesOtorgamientoService;
import cse.model.service.data.EvaluarCondicionesResponse;
import cse.model.service.impl.CondicionesOtorgamientoServiceImpl;
import cse.model.util.Params;

public class MotorCreditScoringSOAPImpl implements cl.araucana.www.CreditScoring_PortType {
	
	public void evaluarCreditoSync(
			String rut,
			String digito, 
			int monto,
			int tipoAfiliado,
			String oficina,
			javax.xml.rpc.holders.StringHolder idSolicitud,
			javax.xml.rpc.holders.StringHolder perfil, 
			javax.xml.rpc.holders.DoubleHolder score,
			cl.araucana.www.holders.CondicionArrayHolder condiciones
			) throws java.rmi.RemoteException, cl.araucana.www.EvaluarCreditoSyncFault {

		CondicionesOtorgamientoService myService;
		try {
			myService = new CondicionesOtorgamientoServiceImpl();
			Rut myRut = new Rut(rut, digito);
			Monto myMonto = new Monto(monto);

			EvaluarCondicionesResponse serviceResponse = (EvaluarCondicionesResponse) myService
					.evaluarCondicionesOtorgamiento(myRut, myMonto, tipoAfiliado);
			
			if(tipoAfiliado!=Integer.parseInt(Params.getInstancia().CODIGO_AFI_EXCEP)){
				CondicionOtorgamiento[] listado = serviceResponse.getCondiciones();
				Condicion[] arrayCondiciones = new Condicion[listado.length];
				
				for (int i = 0; i < listado.length; i++) {
					CondicionOtorgamiento otorgamiento = listado[i];
					Condicion condicion = new Condicion();
					condicion.setNombre(otorgamiento.getNombre());
					condicion.setDescripcion(otorgamiento.getDescripcion());
					arrayCondiciones[i] = condicion;
				}
				condiciones.value = arrayCondiciones;
				String myPerfil = serviceResponse.getPerfilRiesgo();
				perfil.value = myPerfil;
				score.value = serviceResponse.getScore().intValue();
			}
			
			idSolicitud.value = serviceResponse.getIdSolicitud();
		} catch (ScoringModuleException e) {
			String message = e.getMessage();
			if (e.getCause()!=null){
				message+= "- Cause: " + e.getCause().getMessage();   
			}
			throw new EvaluarCreditoSyncFault(message);
		}
	}


	public void evaluarCreditoSyncFull(
			int rut,
			java.lang.String digito,
			int tipoAfiliado,
			java.lang.String genero,
			java.lang.String fechaNac,
			java.lang.String estadoCivil,
			int remuneracion, int monto,
			java.lang.String diasMorosidad,
			int numCreditosAnteriores,
			int numDiasLicencia,
			java.lang.String antiguedadLaboral,
			int rutEmpleador,
			java.lang.String digitoEmpleador,
			java.lang.String clasifRiesgoEmpresa,
			java.lang.String codSectorEmpresa,
			java.lang.String nroTrabajadoresEmpresa,
			int esProspectoLeyInsolvencia, 
			String perfilEmpresaGR,
			javax.xml.rpc.holders.IntHolder rutCli,
			javax.xml.rpc.holders.StringHolder dvCli,
			javax.xml.rpc.holders.StringHolder idSolicitud,
			javax.xml.rpc.holders.StringHolder perfil,
			javax.xml.rpc.holders.FloatHolder score,
			javax.xml.rpc.holders.IntHolder scoreEquifax,
			javax.xml.rpc.holders.IntHolder rentas,
			javax.xml.rpc.holders.IntHolder endeudMax,
			cl.araucana.www.holders.CondicionArrayHolder condiciones,
			javax.xml.rpc.holders.StringHolder codSectorEmpresaOut,
			javax.xml.rpc.holders.StringHolder perfilAfiliadoEmpresaGR
			)
			throws java.rmi.RemoteException, cl.araucana.www.EvaluarCreditoSyncFullFault {

		CondicionesOtorgamientoService myService;
		try {
			myService = new CondicionesOtorgamientoServiceImpl();
			Rut myRut = new Rut(String.valueOf(rut), digito);
			Rut empRut = new Rut(String.valueOf(rutEmpleador), digitoEmpleador);
			Monto myMonto = new Monto(monto);
			//tipoAfiliado
			EvaluarCondicionesResponse serviceResponse =

			(EvaluarCondicionesResponse)

			myService.evaluarCondicionesOtorgamientoFull(
					myRut, 
					tipoAfiliado, 
					myMonto, 
					genero,
					fechaNac,
					estadoCivil, 
					remuneracion,
					diasMorosidad, 
					numCreditosAnteriores,
					numDiasLicencia, 
					antiguedadLaboral, 
					empRut, 
					clasifRiesgoEmpresa,
					codSectorEmpresa,
					nroTrabajadoresEmpresa,
					String.valueOf(esProspectoLeyInsolvencia), 
					perfilEmpresaGR
					);
			
			if(tipoAfiliado!=Integer.parseInt(Params.getInstancia().CODIGO_AFI_EXCEP)){
				CondicionOtorgamiento[] listado = serviceResponse.getCondiciones();
				Condicion[] arrayCondiciones = new Condicion[listado.length];
				
				for (int i = 0; i < listado.length; i++) {
					CondicionOtorgamiento otorgamiento = listado[i];
					Condicion condicion = new Condicion();
					condicion.setNombre(otorgamiento.getNombre());
					condicion.setDescripcion(otorgamiento.getDescripcion());
					arrayCondiciones[i] = condicion;
				}
				condiciones.value = arrayCondiciones;
				String myPerfil = serviceResponse.getPerfilRiesgo();
				perfil.value = myPerfil;
				
				score.value = serviceResponse.getScore().floatValue();
				scoreEquifax.value = serviceResponse.getScoreEquifax().intValue();
				rentas.value = serviceResponse.getNumSueldos().intValue();
				
				//TODO: Esto debe cambiarse posteriormente.		
				rutCli.value = rut;
				dvCli.value = digito;
				
				endeudMax.value = serviceResponse.getEndeudMax().intValue();
				perfilAfiliadoEmpresaGR.value = serviceResponse.getPerfilAfiliadoEmpresaGR();

			}
			idSolicitud.value = serviceResponse.getIdSolicitud();
			codSectorEmpresaOut.value = serviceResponse.getCodSectorEmpresa();
			
		} catch (ScoringModuleException e) {
			String message = e.getMessage();
			if (e.getCause()!=null){
				message+= "- Causa: " + e.getCause().getMessage();   
			}
			
			SOAPFactory fac;
			try {
				fac = SOAPFactory.newInstance();
				Detail sdetail = fac.createDetail();
				sdetail.addTextNode(message);
				throw new SOAPFaultException(new QName("araucana.Server"), message, message, sdetail);
				//TODO: Mapear números de código para el futuro.

			} catch (SOAPException e1) {
				System.out.println("Estoy entrando aca tb ");
				e1.printStackTrace();
			}  
			
		}
	}




}
