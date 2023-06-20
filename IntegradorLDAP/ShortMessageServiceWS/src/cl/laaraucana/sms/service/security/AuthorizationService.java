package cl.laaraucana.sms.service.security;

import cl.laaraucana.sms.ibatis.dao.SistemaDAO;
import cl.laaraucana.sms.ibatis.model.Sistema;
import cl.laaraucana.sms.utils.Configuration;
import org.apache.log4j.Logger;

import cl.laaraucana.sms.ibatis.dao.UsuarioDAO;
import cl.laaraucana.sms.ibatis.model.Usuario;
import cl.laaraucana.sms.utils.Encryption;

public class AuthorizationService {
    private static final Logger logger = Logger.getLogger(AuthorizationService.class);

    private static final String securityUserRoleSingle = Configuration.securityUserRoleSingle;

    public static final String systemStatusEnabled = "Habilitado";
    public static final String systemStatusDisabled = "Deshabilitado";

    public boolean isUserAuthorized(String username, String password) {
        boolean isUserAuthorized = false;
        Encryption crypto = new Encryption();
        String userId = crypto.AESDecrypt(username);
        try {
            UsuarioDAO dao = new UsuarioDAO();
            Usuario usuario = dao.selectUsuario(new Usuario(userId, securityUserRoleSingle));
            if (null != usuario) {
                String passwordEncrypted = crypto.AESEncrypt(usuario.getClave());
                isUserAuthorized = passwordEncrypted.equals(password);
            }
        } catch (Exception e) {
            logger.error(String.format("Authentication failed for user %s with exception", username), e);
        }
        return isUserAuthorized;
    }

    public boolean isSystemAuthorized(String system) {
        Sistema sistema = getSystem(system);
        if (null == sistema) return false;
        return sistema.getEstado().equals(systemStatusEnabled);
    }

    private Sistema getSystem(String system) {
        try {
            SistemaDAO sistemaDAO = new SistemaDAO();
            Sistema sistema = new Sistema();
            sistema.setSistema(system);
            return sistemaDAO.selectSistema(sistema);
        } catch (Exception e) {
            logger.error(String.format("Error getting system information for %s", system), e);
        }
        return null;
    }
}
