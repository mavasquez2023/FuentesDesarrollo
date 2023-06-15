package cl.laaraucana.servicios.simuladorCreditoSocial;

public interface SimuladorCreditoSocialImpl extends java.rmi.Remote {
    public cl.laaraucana.servicios.simuladorCreditoSocial.CreditoVO getSimuladorCreditoSocial(cl.laaraucana.servicios.simuladorCreditoSocial.RequestWS request) throws java.rmi.RemoteException, cl.laaraucana.servicios.simuladorCreditoSocial.SOAPException;
}
