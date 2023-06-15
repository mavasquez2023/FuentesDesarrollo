package cl.laaraucana.ventafullweb.util;


/*
 * @author J-Factory
 *
 */
public class ObtenerHoraSingleton {

	private static ObtenerHoraSingleton instance = null;
	String horaAM;
	String horaPM;
	


	protected ObtenerHoraSingleton() {
		this.horaAM= "08";
		this.horaPM= "12";
	}


	public static ObtenerHoraSingleton getInstance() {
	      if(instance == null) {
	         instance = new ObtenerHoraSingleton();
	      }
	      return instance;
	   }


	/**
	 * @return the horaAM
	 */
	public String getHoraAM() {
		int hora= Integer.parseInt(horaAM) + 1;
		if(hora== 13) {
			hora=9;
		}
		String horastr= "" + hora;
		if(horastr.length()==1) {
			horastr= "0" + hora;
		}

		setHoraAM(horastr);
		return horastr+":00:00";
	}


	/**
	 * @param horaAM the horaAM to set
	 */
	public void setHoraAM(String horaAM) {
		this.horaAM = horaAM;
	}


	/**
	 * @return the horaPM
	 */
	public String getHoraPM() {
		int hora= Integer.parseInt(horaPM) + 1;
		if(hora== 19) {
			hora=13;
		}
		
		String horastr= ""+ hora;
		setHoraPM(horastr);
		return horastr+":00:00";
	}


	/**
	 * @param horaPM the horaPM to set
	 */
	public void setHoraPM(String horaPM) {
		this.horaPM = horaPM;
	}







}