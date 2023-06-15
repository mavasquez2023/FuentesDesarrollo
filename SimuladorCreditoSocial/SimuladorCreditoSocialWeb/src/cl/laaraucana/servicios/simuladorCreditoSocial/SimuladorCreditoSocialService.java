package cl.laaraucana.servicios.simuladorCreditoSocial;

public interface SimuladorCreditoSocialService extends javax.xml.rpc.Service {
    public java.lang.String getSimuladorCreditoSocialPortAddress();

    public cl.laaraucana.servicios.simuladorCreditoSocial.SimuladorCreditoSocialImpl getSimuladorCreditoSocialPort() throws javax.xml.rpc.ServiceException;

    public cl.laaraucana.servicios.simuladorCreditoSocial.SimuladorCreditoSocialImpl getSimuladorCreditoSocialPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
