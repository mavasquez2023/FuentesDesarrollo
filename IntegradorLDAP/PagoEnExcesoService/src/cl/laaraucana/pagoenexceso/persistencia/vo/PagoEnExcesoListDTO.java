package cl.laaraucana.pagoenexceso.persistencia.vo;

import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class PagoEnExcesoListDTO {
	private List<PagoEnExcesoDTO> pagos;
	private String fechaMasAntigua;
	private int montoTotal;
	private String rut;
	private PagoEnExcesoDTO pagoTotal;
	private int tipo;

	public PagoEnExcesoListDTO() {
		this.pagos = new ArrayList<PagoEnExcesoDTO>();
	}

	public void addPago(PagoEnExcesoDTO pago) {
		this.pagos.add(pago);
		setFechaMasAntigua();
		setMontoTotal();
		setRut(pago);
		setTipo(pago);
	}

	private void setFechaMasAntigua() {
		Date fechaPagoAntigua = null;

		for (int i = 0; i < this.pagos.size(); i++) {
			PagoEnExcesoDTO pago = (PagoEnExcesoDTO) this.pagos.get(i);

			if (i == 0) {
				fechaPagoAntigua = pago.getFechaCreacionDate();
			}

			Date fechaPagoActual = pago.getFechaCreacionDate();
/*			if (fechaPagoActual.getTime() < fechaPagoAntigua.getTime()) {
				fechaPagoAntigua = fechaPagoActual;
			}*/
			
			if (fechaPagoActual.compareTo(fechaPagoAntigua)<0) {
				System.out.println("La fecha " + fechaPagoActual + " es menor a " + fechaPagoAntigua);
				fechaPagoAntigua = fechaPagoActual;
			}
		}

		this.fechaMasAntigua = getFechaToString(fechaPagoAntigua);
	}

	private String getFechaToString(Date fecha) {
		StringBuffer sb = new StringBuffer();

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fecha);

		String year = new Integer(calendar.get(1)).toString();
		String month = new Integer(calendar.get(2) + 1).toString();
		String day = new Integer(calendar.get(5)).toString();

		sb.append(fillWith(day, '0', 2));
		sb.append("/");
		sb.append(fillWith(month, '0', 2));
		sb.append("/");
		sb.append(fillWith(year, '0', 4));

		return sb.toString();
	}

	private String fillWith(String s, char c, int length) {
		StringBuffer sb = new StringBuffer(s);

		while (sb.length() < length) {
			sb.insert(0, c);
		}
		return sb.toString();
	}

	private void setMontoTotal() {
		int total = 0;
		for (int i = 0; i < this.pagos.size(); i++) {
			PagoEnExcesoDTO pago = (PagoEnExcesoDTO) this.pagos.get(i);
			total += pago.getMonto();
		}
		this.montoTotal = total;
	}

	private void setRut(PagoEnExcesoDTO pago) {
		this.rut = pago.getRut();
	}

	private void setTipo(PagoEnExcesoDTO pago) {
		this.tipo = pago.getTipo();
	}

	public PagoEnExcesoDTO getPagoTotal() {
		this.pagoTotal = new PagoEnExcesoDTO(this.fechaMasAntigua, this.montoTotal, this.rut, this.tipo);
		return this.pagoTotal;
	}
}
