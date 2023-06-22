/**
 * CreditScoring_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * o0445.04 v11904204728
 */

package cl.araucana.www;

public interface CreditScoring_PortType extends java.rmi.Remote {
    public void evaluarCreditoSync(java.lang.String rut, java.lang.String digito, int monto, int tipoAfiliado, java.lang.String oficina, javax.xml.rpc.holders.StringHolder idSolicitud, javax.xml.rpc.holders.StringHolder perfil, javax.xml.rpc.holders.DoubleHolder score, cl.araucana.www.holders.CondicionArrayHolder condiciones) throws java.rmi.RemoteException, cl.araucana.www.EvaluarCreditoSyncFault;
    public void evaluarCreditoSyncFull(int rut, java.lang.String digito, int tipoAfiliado, java.lang.String genero, java.lang.String fechaNac, java.lang.String estadoCivil, int remuneracion, int monto, java.lang.String diasMorosidad, int numCreditosAnteriores, int numDiasLicencia, java.lang.String antiguedadLaboral, int rutEmpleador, java.lang.String digitoEmpleador, java.lang.String clasifRiesgoEmpresa, javax.xml.rpc.holders.IntHolder rutCli, javax.xml.rpc.holders.StringHolder dvCli, javax.xml.rpc.holders.StringHolder idSolicitud, javax.xml.rpc.holders.StringHolder perfil, javax.xml.rpc.holders.FloatHolder score, javax.xml.rpc.holders.IntHolder scoreEquifax, javax.xml.rpc.holders.IntHolder rentas, javax.xml.rpc.holders.IntHolder endeudMax, cl.araucana.www.holders.CondicionArrayHolder condiciones) throws java.rmi.RemoteException, cl.araucana.www.EvaluarCreditoSyncFullFault;
}
