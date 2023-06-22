package cl.laaraucana.simulacion;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.laaraucana.servicios.simuladorCreditoSocial.DetalleVO;
import cl.laaraucana.servicios.simuladorCreditoSocial.RequestWS;
import cl.laaraucana.servicios.simuladorCreditoSocial.SimuladorCreditoSocialServiceLocator;
import cl.laaraucana.simulacion.ibatis.dao.UtilesDAO;
import cl.laaraucana.simulacion.ibatis.vo.ParametroVO;
import cl.laaraucana.simulacion.utils.ConstantesFormalizar;
import cl.laaraucana.simulacion.utils.Utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ResultadoAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		String forward = "resultado";
		String endpoint = ConstantesFormalizar.SIMULADOR_CREDITO_SOCIAL_WSDL;
		cl.laaraucana.servicios.simuladorCreditoSocial.SimuladorCreditoSocialPortBindingStub binding;
		SimuladorCreditoSocialServiceLocator locator = new cl.laaraucana.servicios.simuladorCreditoSocial.SimuladorCreditoSocialServiceLocator();
		locator.setSimuladorCreditoSocialPortAddress(endpoint);
		binding = (cl.laaraucana.servicios.simuladorCreditoSocial.SimuladorCreditoSocialPortBindingStub) locator
			.getSimuladorCreditoSocialPort();
		binding.setTimeout(60000);
		cl.laaraucana.servicios.simuladorCreditoSocial.CreditoVO value = null;
		RequestWS req = new cl.laaraucana.servicios.simuladorCreditoSocial.RequestWS();
		int cuotas = Integer.valueOf(request.getParameter("cuotas"));
		int monto = Integer.valueOf((String) request.getParameter("monto").replaceAll("\\.", "").replaceAll("\\$", ""));
		String sucursal = request.getParameter("sucursal");
		String tipoAfiliado = request.getParameter("tipo-afiliado");
		String seguroCesantia = "No";
		if (request.getParameter("seguro-cesantia") != null && tipoAfiliado.equals("1")) {
			seguroCesantia = request.getParameter("seguro-cesantia").equals("on") ? "SI" : "NO";
		}
		String rut = request.getParameter("rut");
		// TODO Completar logica para activar/desactivar seguro
		String seguroDesgravamen = "SI";
		req.setCUOTAS(cuotas);
		req.setMONTO(monto);
		req.setSUCURSAL(sucursal);
		req.setTIPO_AFILIADO(tipoAfiliado);
		req.setSEGURO_CESANTIA(seguroCesantia);
		req.setSEGURO_DESGRAVAMEN(seguroDesgravamen);
		value = binding.getSimuladorCreditoSocial(req);
		// System.out.println(value.getFECHA_PRIMER_VENCIMIENTO());
		TestForm testForm = (TestForm) form;
		testForm.setCuotas(String.valueOf(cuotas));
		testForm.setMonto(String.valueOf(monto));
		testForm.setSucursal((String) ConstantesFormalizar.getSucursales().get(sucursal));
		testForm.setTipoAfiliado(tipoAfiliado.equals("1") ? "Trabajador" : "Pensionado");
		testForm.setSeguroCesantia(seguroCesantia.equals("SI") ? "Si" : "No");
		testForm.setSeguroDesgravamen(seguroDesgravamen.equals("SI") ? "Si" : "No");
		testForm.setRut(rut);
		NumberFormat format = NumberFormat.getCurrencyInstance(Locale.US);
		String gastoNotarial = format.format(value.getGASTO_NOTARIAL()).replaceAll("\\,", "\\.");
		String impuesto = format.format(value.getIMPUESTO()).replaceAll("\\,", "\\.");
		testForm.setGASTO_NOTARIAL(gastoNotarial.substring(0, gastoNotarial.length() - 3));
		testForm.setIMPUESTO(impuesto.substring(0, impuesto.length() - 3));
		String valorCuotaMensual = format.format(value.getMONTO_CUOTA()).replaceAll("\\,", "\\.");
		testForm.setMONTO_CUOTA(valorCuotaMensual.substring(0, valorCuotaMensual.length() - 3));
		String tasaIntMensual = Utils.formateaDobleConDecimal(value.getTASA_INT_MENSUAL());
		testForm.setTASA_INT_MENSUAL(tasaIntMensual);
		DateFormat primeraCuotaDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String primeraCuota = String.valueOf(value.getFECHA_PRIMER_VENCIMIENTO());
		String primeraCuotaFinal = primeraCuota.substring(6, 8) + "/" + primeraCuota.substring(4, 6) + "/" + primeraCuota.substring(0, 4);
		testForm.setFECHA_PRIMER_VENCIMIENTO(primeraCuotaFinal);
		String cae = Utils.formateaDobleConDecimal(value.getCAE());
		testForm.setCAE(cae);
		testForm.setMonto(request.getParameter("monto"));
		String ctc = format.format(value.getCTC()).replaceAll("\\,", "\\.");
		testForm.setCTC(ctc.substring(0, ctc.length() - 3));
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy | HH:mm");
		Date date = new Date();
		testForm.setFecha(dateFormat.format(date));
		ArrayList detalleCuotas = new ArrayList();
		DetalleVO[] detalleArray = value.getDETALLE_CUOTA();
		for (int i = 0; i < detalleArray.length; i++) {
			DetalleForm df = new DetalleForm();
			DetalleVO d = (DetalleVO) detalleArray[i];
			String fechaVencimiento = String.valueOf(d.getFECHA_VENCIMIENTO());
			String fechaVencimientoFinal = fechaVencimiento.substring(6, 8) + "-" + fechaVencimiento.substring(4, 6) + "-" + fechaVencimiento.substring(0, 4);
			df.setFECHA_VENCIMIENTO(fechaVencimientoFinal);
			df.setNUM_CUOTA(String.valueOf(d.getNUM_CUOTA()));
			String montoCapital = format.format(d.getSALDO_CAPITAL()).replaceAll("\\,", "\\.");
			df.setSALDO_CAPITAL(montoCapital.substring(0, montoCapital.length() - 3));
			String montoInteres = format.format(d.getMONTO_INTERES()).replaceAll("\\,", "\\.");
			df.setMONTO_INTERES(montoInteres.substring(0, montoInteres.length() - 3));
			String seguroDesgravamenCuota = format.format(d.getSEGURO_DESGRAVAMEN()).replaceAll("\\,", "\\.");
			df.setSEGURO_DESGRAVAMEN(seguroDesgravamenCuota.substring(0, seguroDesgravamenCuota.length() - 3));
			String seguroCesantiaCuota = format.format(d.getSEGURO_CESANTIA()).replaceAll("\\,", "\\.");
			df.setSEGURO_CESANTIA(seguroCesantiaCuota.substring(0, seguroCesantiaCuota.length() - 3));
			String valorCuota = format.format(d.getMONTO_CUOTA()).replaceAll("\\,", "\\.");
			df.setMONTO_CUOTA(valorCuota.substring(0, valorCuota.length() - 3));
			detalleCuotas.add(df);
		}
		try {
			ParametroVO parametroVO = new ParametroVO();
			parametroVO = UtilesDAO.consultaParametro(1, 3);
			String parametrosCondiciones = parametroVO.getValor();
			if (parametrosCondiciones != null) {
				request.setAttribute("parametrosCondiciones", parametrosCondiciones);
			} else {
				request.setAttribute("observaciones", "Error al procesar información de la base de datos");
			}
		} catch (Exception e) {
			request.setAttribute("observaciones", "Error al obtener información desde la base de datos");
			e.printStackTrace();
		}
		request.setAttribute("credito", value);
		request.setAttribute("detalleCuotas", detalleCuotas);
		HttpSession session = request.getSession();
		session.setAttribute("credito", value);
		session.setAttribute("detalleCuotas", detalleCuotas);
		session.setAttribute("rut", rut);
		session.setAttribute("testForm", testForm);
		
		return mapping.findForward(forward);
	}
}
