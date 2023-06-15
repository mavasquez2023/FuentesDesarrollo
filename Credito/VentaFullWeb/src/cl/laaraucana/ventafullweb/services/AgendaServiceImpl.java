package cl.laaraucana.ventafullweb.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lautaro.xi.CRM.Legacy.OfertasVigentes_DT;

import cl.laaraucana.ventafullweb.dto.BitacoraGenesysDto;
import cl.laaraucana.ventafullweb.util.ObtenerHoraSingleton;
import cl.laaraucana.ventafullweb.util.Utils;
import cl.laaraucana.ventafullweb.vo.AfiliadoVo;
import cl.laaraucana.ventafullweb.vo.SalidaEvaluadorModeloAISVo;
import cl.laaraucana.ventafullweb.ws.ClientRESGenesys;

@Service
public class AgendaServiceImpl implements AgendaService {
	protected Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private BitacoraService bitacoraService;
		
	@Override
	public String asignaCasoGenesys(AfiliadoVo datos_afiliado, SalidaEvaluadorModeloAISVo respuestaMotorAIS, OfertasVigentes_DT respuestaWSOfertasVigentes, String fechaSeleccionada, String horario) {
		String idConversation=null;
		logger.info("asignando caso a Genesys");
		// TODO Auto-generated method stub
		String hora="";
		try {
			if(fechaSeleccionada!= null) {
				if(horario.equals("AM")) {
					hora=ObtenerHoraSingleton.getInstance().getHoraAM();
				}else {
					hora=ObtenerHoraSingleton.getInstance().getHoraPM();
				}
				fechaSeleccionada= fechaSeleccionada + " " + hora;
			}
			
			//Antes de invocar servicio Genesys se graba bitacora
			BitacoraGenesysDto bitacora=null;
			try {
				String fechaAgenda= fechaSeleccionada;
				if (fechaSeleccionada==null) {
					fechaAgenda= Utils.getDateHour();
				}
				
				//Se graba bitácora Agenda
				bitacora= bitacoraService.saveBitacoraGenesys(datos_afiliado, fechaAgenda);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			ClientRESGenesys client= new ClientRESGenesys();
			String token=client.authorization();
			if(token!= null) {
				idConversation= client.callback(token, datos_afiliado, respuestaMotorAIS, respuestaWSOfertasVigentes, fechaSeleccionada);
				if(idConversation!=null && bitacora!=null) {
					bitacora.setIdConversation(idConversation);
					bitacoraService.updateBitacoraGenesys(bitacora);
				}
			}
			logger.info("id callback=" + idConversation);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return idConversation;
	}


	@Override
	public boolean campagnaOutbaund(String afiliado) {
		logger.info("asignando campaña outbaund");
		// TODO Auto-generated method stub
		return false;
	}
	
	
}
