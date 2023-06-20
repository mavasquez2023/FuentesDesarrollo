package cl.laaraucana.silmsil.vo;

/**
 * Entidad proceso-estado.
 * 
 * @author usist42
 *
 */
public class ILFJA058VO {

	//Identificador proceso.
	private int idproc;
	//Identificador estado.
	private int idesta;
	//Período proceso - estado.
	private String pepret;
	//Fecha ingreso operación.
	private String fepret;
	//Hora ingreso operación.
	private String hrpret;
	//Número de registros.
	private long nrpret;
	//Número de registros con error.
	private long nepret;
	//Usuario ingreso operación.
	private String uspret;
	//Fecha y hora máxima recuperado.
	private String fechaHora;
	
	
	public ILFJA058VO() {
		super();
	}

	public ILFJA058VO(int idproc, int idesta, String pepret, String fepret,
			String hrpret, long nrpret, long nepret, String uspret,
			String fechaHora) {
		super();
		this.idproc = idproc;
		this.idesta = idesta;
		this.pepret = pepret;
		this.fepret = fepret;
		this.hrpret = hrpret;
		this.nrpret = nrpret;
		this.nepret = nepret;
		this.uspret = uspret;
		this.fechaHora = fechaHora;
	}


	public int getIdproc() {
		return idproc;
	}


	public void setIdproc(int idproc) {
		this.idproc = idproc;
	}


	public int getIdesta() {
		return idesta;
	}


	public void setIdesta(int idesta) {
		this.idesta = idesta;
	}


	public String getPepret() {
		return pepret;
	}


	public void setPepret(String pepret) {
		this.pepret = pepret;
	}


	public String getFepret() {
		return fepret;
	}


	public void setFepret(String fepret) {
		this.fepret = fepret;
	}


	public String getHrpret() {
		return hrpret;
	}


	public void setHrpret(String hrpret) {
		this.hrpret = hrpret;
	}


	public String getUspret() {
		return uspret;
	}


	public void setUspret(String uspret) {
		this.uspret = uspret;
	}


	public long getNrpret() {
		return nrpret;
	}


	public void setNrpret(long nrpret) {
		this.nrpret = nrpret;
	}


	public long getNepret() {
		return nepret;
	}


	public void setNepret(long nepret) {
		this.nepret = nepret;
	}

	
	public String getFechaHora() {
		return fechaHora;
	}

	
	public void setFechaHora(String fechaHora) {
		this.fechaHora = fechaHora;
	}
	
}
