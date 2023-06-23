package cl.araucana.spl.actions.admin;

import javax.servlet.http.HttpServletRequest;

import cl.araucana.spl.beans.FiltroInconsistencias;

import com.bh.paginacion.HttpPageParameters;

public class InconsistenciasPageParameter extends HttpPageParameters {
	FiltroInconsistencias filtro;
	public InconsistenciasPageParameter(HttpServletRequest request, int limit, FiltroInconsistencias filtro) {
		super(request, limit);
		this.filtro = filtro;
	}

	public FiltroInconsistencias getFiltro() {
		return filtro;
	}

	public String getJavascriptFuncionName() {
		return "bhp_jsPage";
	}
	public String getOffsetName() {
		return "bhp_offset";
	}
}
