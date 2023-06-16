package cl.laaraucana.simat.entidades;

public class PersonasSubsidiadasVO {
	private int num_pers_subs_Pre_natal;

	private int num_pers_subs_Post_natal_Madre;
	private int num_pers_subs_Post_natal_Padre;

	private int num_pers_subs_Parental_Madre;
	private int num_pers_subs_Parental_Padre;

	private int num_pers_subs_Enf_menor_Madre;
	private int num_pers_subs_Enf_menor_Padre;

	public PersonasSubsidiadasVO() {

	}

	public PersonasSubsidiadasVO(int num_pers_subs_Pre_natal, int num_pers_subs_Post_natal_Madre, int num_pers_subs_Post_natal_Padre, int num_pers_subs_Parental_Madre,
			int num_pers_subs_Parental_Padre, int num_pers_subs_Enf_menor_Madre, int num_pers_subs_Enf_menor_Padre) {
		super();
		this.num_pers_subs_Pre_natal = num_pers_subs_Pre_natal;
		this.num_pers_subs_Post_natal_Madre = num_pers_subs_Post_natal_Madre;
		this.num_pers_subs_Post_natal_Padre = num_pers_subs_Post_natal_Padre;
		this.num_pers_subs_Parental_Madre = num_pers_subs_Parental_Madre;
		this.num_pers_subs_Parental_Padre = num_pers_subs_Parental_Padre;
		this.num_pers_subs_Enf_menor_Madre = num_pers_subs_Enf_menor_Madre;
		this.num_pers_subs_Enf_menor_Padre = num_pers_subs_Enf_menor_Padre;
	}

	public int getNum_pers_subs_Enf_menor_Madre() {
		return num_pers_subs_Enf_menor_Madre;
	}

	public int getNum_pers_subs_Enf_menor_Padre() {
		return num_pers_subs_Enf_menor_Padre;
	}

	public int getNum_pers_subs_Parental_Madre() {
		return num_pers_subs_Parental_Madre;
	}

	public int getNum_pers_subs_Parental_Padre() {
		return num_pers_subs_Parental_Padre;
	}

	public int getNum_pers_subs_Post_natal_Madre() {
		return num_pers_subs_Post_natal_Madre;
	}

	public int getNum_pers_subs_Post_natal_Padre() {
		return num_pers_subs_Post_natal_Padre;
	}

	public int getNum_pers_subs_Pre_natal() {
		return num_pers_subs_Pre_natal;
	}

	public void setNum_pers_subs_Enf_menor_Madre(int num_pers_subs_Enf_menor_Madre) {
		this.num_pers_subs_Enf_menor_Madre = num_pers_subs_Enf_menor_Madre;
	}

	public void setNum_pers_subs_Enf_menor_Padre(int num_pers_subs_Enf_menor_Padre) {
		this.num_pers_subs_Enf_menor_Padre = num_pers_subs_Enf_menor_Padre;
	}

	public void setNum_pers_subs_Parental_Madre(int num_pers_subs_Parental_Madre) {
		this.num_pers_subs_Parental_Madre = num_pers_subs_Parental_Madre;
	}

	public void setNum_pers_subs_Parental_Padre(int num_pers_subs_Parental_Padre) {
		this.num_pers_subs_Parental_Padre = num_pers_subs_Parental_Padre;
	}

	public void setNum_pers_subs_Post_natal_Madre(int num_pers_subs_Post_natal_Madre) {
		this.num_pers_subs_Post_natal_Madre = num_pers_subs_Post_natal_Madre;
	}

	public void setNum_pers_subs_Post_natal_Padre(int num_pers_subs_Post_natal_Padre) {
		this.num_pers_subs_Post_natal_Padre = num_pers_subs_Post_natal_Padre;
	}

	public void setNum_pers_subs_Pre_natal(int num_pers_subs_Pre_natal) {
		this.num_pers_subs_Pre_natal = num_pers_subs_Pre_natal;
	}

}
