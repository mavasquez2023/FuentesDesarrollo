/**
 * 
 */
package cl.araucana.integracion.test;

import java.rmi.RemoteException;

import cl.laaraucana.integracion.impl.IntegracionDTProxy;

/**
 * @author usist199
 *
 */
public class TestDT {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		IntegracionDTProxy integra= new IntegracionDTProxy();
		System.out.println(integra.getEndpoint());
		String entrada="<peticion llave=\"\"><peticionservicio tipo=\"AUT\"><parametro nombre=\"usuario\" valor=\"UWSDT\" /><parametro nombre=\"password\" valor=\"S2RV3C34\" /></peticionservicio><peticionservicio tipo=\"SUBCONTR\"><parametro nombre=\"rut_empleador\" valor=\"70016160-9\" /><parametro nombre=\"rut_trabajador\" valor=\"10104927-2\" /><parametro nombre=\"periodo\" valor=\"201509\" /></peticionservicio></peticion>";
		System.out.println(entrada);
		try {
			String resultado= integra.integracionDT(entrada);
			System.out.println(resultado);
		} catch (RemoteException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}

	}

}
