package cl.laaraucana.botonpago.web.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.core.registry.User;
import cl.araucana.core.registry.UserRegistryConnection;
import cl.laaraucana.botonpago.web.cobol.vo.SalidaDeudorVO;
import cl.laaraucana.botonpago.web.database.dao.DatosDeudorDAO;
import cl.laaraucana.botonpago.web.database.ibatis.domain.Bpagf001;
import cl.laaraucana.botonpago.web.forms.UserForm;
import cl.laaraucana.botonpago.web.manager.ManagerDeudor;
import cl.laaraucana.botonpago.web.utils.Constantes;

/**
 * @version 1.0
 * @author
 */
public class CrearUserAction extends Action

{
	Logger logger = Logger.getLogger(this.getClass());

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		User creador = (User) session.getAttribute("user");

		UserForm datosUser = (UserForm) form;
		String mensaje = "";
		String tipo = "";
		try {
			String rutDeudor = datosUser.getId().replace(".", "");

			User user = new User();
			user.setID(rutDeudor);
			user.setName(datosUser.getNombre());
			user.setFirstName(datosUser.getApellidoPaterno());
			user.setLastName(datosUser.getApellidoMaterno());
			user.setEmail(datosUser.getEmail());
			user.setPhone(datosUser.getFono());
			user.setSex(datosUser.getSexo());
			user.setSituation("");
			user.setQuestion("");
			user.setAnswer("");
			user.setServices("");

			UserRegistryConnection urConnection = new UserRegistryConnection();
			User u = urConnection.getUser(rutDeudor);
			if (u == null) {
				try {

					urConnection.createUser(user, rutDeudor.substring(0, 4));
					logger.debug("Usuario " + rutDeudor + " creado con exito en LDAP Por: " + creador.getID());
				} catch (Exception e) {
					try {
						urConnection.close();
					} catch (Exception ex) {
						ex.printStackTrace();
					}
					e.printStackTrace();
					logger.error("Error al crear usuario en LDAP:" + datosUser.getId());
					throw new Exception("Error al crear usuario en LDAP");
				} finally {
					try {
						urConnection.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				mensaje = "Usuario creado con exito en LDAP";
				tipo = Constantes.getInstancia().MSG_TIPO_EXITO;
			}else{
				mensaje = "Usuario ya existe en LDAP";
				tipo = Constantes.getInstancia().MSG_TIPO_INFO;
				logger.debug("El usuario ya existe en LDAP");
			}


			String[] roles = datosUser.getPerfil();
			for (String rol : roles) {
				if (rol.equals(Constantes.getInstancia().LDAP_ROL_DEUDOR)) {
					ManagerDeudor mgrDeu = new ManagerDeudor();
					SalidaDeudorVO salida = mgrDeu.esDeudorNoAfiliadoSapYAs400(rutDeudor);
					if (salida.isDeudor()) {
						try {
							Bpagf001 entradaInsert = mgrDeu.mapeoDeudor(salida);
							DatosDeudorDAO dao = new DatosDeudorDAO();
							dao.insertDeudor(entradaInsert);
							mensaje = mensaje+", usuario creado en BD de deudores";
						} catch (Exception e) {
							e.printStackTrace();
							logger.error("Error al crear usuario deudor en BD:" + datosUser.getId());
							throw new Exception(", Error al crear usuario en BD deudores");
						}
						urConnection.assignAppRole(user.getID(), Constantes.getInstancia().LDAP_APLICACION, rol);
					} else {
						mensaje = mensaje+", no se puede asignar rol deudor";
						tipo = Constantes.getInstancia().MSG_TIPO_ALERTA;
					}

				} else {
					urConnection.assignAppRole(user.getID(), Constantes.getInstancia().LDAP_APLICACION, rol);
				}

			}

		} catch (Exception e) {

			e.printStackTrace();
			logger.error(" Error al crear el usuario: " + e.getMessage());
			mensaje = " Error al crear el usuario";
			tipo = Constantes.getInstancia().MSG_TIPO_ERROR;

		}

		request.setAttribute("mensaje", mensaje);
		request.setAttribute("tipo", tipo);

		return mapping.findForward("mensaje");
	}
}
