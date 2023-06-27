package cl.araucana.contabilidad.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author asepulveda
 *
 */
public class Asiento implements Serializable{

	/** Serial */
	private static final long serialVersionUID = 1L;

	private String bhId="";
	private String bhPgm="";
	private long bhSeq=0;
	private String bhLdes="";
	private String bhUs01="";
	private String bhUs02="";
	private String bhUs03="";
	private String bhUs04="";
	private String bhUs05="";
	private String bhUs06="";
	private int bhAmt1=0;
	private int bhAmt2=0;
	private int bhAmt3=0;
	private int bhAmt4=0;
	private int bhAmt5=0;
	private int bhAmt6=0;
	private int bhDrat=0;
	private int bhCrat=0;
	private int bhToln=0;
	private int bhTost=0;	
	private String bhCurr="";
	private int bhRate=0;
	private int bhOrte=0;
	private String bhEvnt="";
	private int bhStat=0;
	private String bhJdat="";
	private int bhUdt1=0;
	private int bhUdt2=0;
	private String bhReas="";
	private String bhUser="";
	private int bhDate=0;
	private int bhTime=0;
	private int bhLock=0;
	private int bhCo=0;
	private String bhBcur="";
	private String bhRtyp="";
	private int bhOdat=0;
	private String bhUs07="";
	private String bhUs08="";
	private String bhUs09="";
	private String bhUs10="";
	private String bhUs11="";
	private String bhUs12="";
	private String bhUs13="";
	private String bhUs14="";
 	private ArrayList lineas = new ArrayList();
 	
	/**
	 * Agrega una línea
	 */
	public void addLinea(Linea linea) {
		if (linea == null) {
			lineas = new ArrayList();
		}
		lineas.add(linea);
	}
	
	/**
	 * Elimina una línea
	 */
	public void removeLinea(int index) {
		if (lineas.size() > 0 && index < lineas.size())
		lineas.remove(index);
	}
	
	/**
	 * Remueve todas las líneas
	 *
	 */
	public void removeAllLineas() {
		lineas.clear();
	}
 	
 	
	/**
	 * @return
	 */
	public int getBhAmt1() {
		return bhAmt1;
	}

	/**
	 * @return
	 */
	public int getBhAmt2() {
		return bhAmt2;
	}

	/**
	 * @return
	 */
	public int getBhAmt3() {
		return bhAmt3;
	}

	/**
	 * @return
	 */
	public int getBhAmt4() {
		return bhAmt4;
	}

	/**
	 * @return
	 */
	public int getBhAmt5() {
		return bhAmt5;
	}

	/**
	 * @return
	 */
	public int getBhAmt6() {
		return bhAmt6;
	}

	/**
	 * @return
	 */
	public String getBhBcur() {
		return bhBcur;
	}

	/**
	 * @return
	 */
	public int getBhCo() {
		return bhCo;
	}

	/**
	 * @return
	 */
	public int getBhCrat() {
		return bhCrat;
	}

	/**
	 * @return
	 */
	public String getBhCurr() {
		return bhCurr;
	}

	/**
	 * @return
	 */
	public int getBhDate() {
		return bhDate;
	}

	/**
	 * @return
	 */
	public int getBhDrat() {
		return bhDrat;
	}

	/**
	 * @return
	 */
	public String getBhEvnt() {
		return bhEvnt;
	}

	/**
	 * @return
	 */
	public String getBhId() {
		return bhId;
	}

	/**
	 * @return
	 */
	public String getBhJdat() {
		return bhJdat;
	}

	/**
	 * @return
	 */
	public String getBhLdes() {
		return bhLdes;
	}

	/**
	 * @return
	 */
	public int getBhLock() {
		return bhLock;
	}

	/**
	 * @return
	 */
	public int getBhOrte() {
		return bhOrte;
	}

	/**
	 * @return
	 */
	public String getBhPgm() {
		return bhPgm;
	}

	/**
	 * @return
	 */
	public int getBhOdat() {
		return bhOdat;
	}

	/**
	 * @return
	 */
	public int getBhRate() {
		return bhRate;
	}

	/**
	 * @return
	 */
	public String getBhReas() {
		return bhReas;
	}

	/**
	 * @return
	 */
	public String getBhRtyp() {
		return bhRtyp;
	}

	/**
	 * @return
	 */
	public long getBhSeq() {
		return bhSeq;
	}

	/**
	 * @return
	 */
	public int getBhStat() {
		return bhStat;
	}

	/**
	 * @return
	 */
	public int getBhTime() {
		return bhTime;
	}

	/**
	 * @return
	 */
	public int getBhToln() {
		return bhToln;
	}

	/**
	 * @return
	 */
	public int getBhTost() {
		return bhTost;
	}

	/**
	 * @return
	 */
	public int getBhUdt1() {
		return bhUdt1;
	}

	/**
	 * @return
	 */
	public int getBhUdt2() {
		return bhUdt2;
	}

	/**
	 * @return
	 */
	public String getBhUs01() {
		return bhUs01;
	}

	/**
	 * @return
	 */
	public String getBhUs02() {
		return bhUs02;
	}

	/**
	 * @return
	 */
	public String getBhUs03() {
		return bhUs03;
	}

	/**
	 * @return
	 */
	public String getBhUs04() {
		return bhUs04;
	}

	/**
	 * @return
	 */
	public String getBhUs05() {
		return bhUs05;
	}

	/**
	 * @return
	 */
	public String getBhUs06() {
		return bhUs06;
	}

	/**
	 * @return
	 */
	public String getBhUs07() {
		return bhUs07;
	}

	/**
	 * @return
	 */
	public String getBhUs08() {
		return bhUs08;
	}

	/**
	 * @return
	 */
	public String getBhUs09() {
		return bhUs09;
	}

	/**
	 * @return
	 */
	public String getBhUs10() {
		return bhUs10;
	}

	/**
	 * @return
	 */
	public String getBhUs11() {
		return bhUs11;
	}

	/**
	 * @return
	 */
	public String getBhUs12() {
		return bhUs12;
	}

	/**
	 * @return
	 */
	public String getBhUs13() {
		return bhUs13;
	}

	/**
	 * @return
	 */
	public String getBhUs14() {
		return bhUs14;
	}

	/**
	 * @return
	 */
	public String getBhUser() {
		return bhUser;
	}

	/**
	 * @param i
	 */
	public void setBhAmt1(int i) {
		bhAmt1 = i;
	}

	/**
	 * @param i
	 */
	public void setBhAmt2(int i) {
		bhAmt2 = i;
	}

	/**
	 * @param i
	 */
	public void setBhAmt3(int i) {
		bhAmt3 = i;
	}

	/**
	 * @param i
	 */
	public void setBhAmt4(int i) {
		bhAmt4 = i;
	}

	/**
	 * @param i
	 */
	public void setBhAmt5(int i) {
		bhAmt5 = i;
	}

	/**
	 * @param i
	 */
	public void setBhAmt6(int i) {
		bhAmt6 = i;
	}

	/**
	 * @param string
	 */
	public void setBhBcur(String string) {
		bhBcur = string;
	}

	/**
	 * @param i
	 */
	public void setBhCo(int i) {
		bhCo = i;
	}

	/**
	 * @param i
	 */
	public void setBhCrat(int i) {
		bhCrat = i;
	}

	/**
	 * @param string
	 */
	public void setBhCurr(String string) {
		bhCurr = string;
	}

	/**
	 * @param i
	 */
	public void setBhDate(int i) {
		bhDate = i;
	}

	/**
	 * @param i
	 */
	public void setBhDrat(int i) {
		bhDrat = i;
	}

	/**
	 * @param string
	 */
	public void setBhEvnt(String string) {
		bhEvnt = string;
	}

	/**
	 * @param string
	 */
	public void setBhId(String string) {
		bhId = string;
	}

	/**
	 * @param string
	 */
	public void setBhJdat(String string) {
		bhJdat = string;
	}

	/**
	 * @param string
	 */
	public void setBhLdes(String string) {
		bhLdes = string;
	}

	/**
	 * @param i
	 */
	public void setBhLock(int i) {
		bhLock = i;
	}

	/**
	 * @param i
	 */
	public void setBhOrte(int i) {
		bhOrte = i;
	}

	/**
	 * @param string
	 */
	public void setBhPgm(String string) {
		bhPgm = string;
	}

	/**
	 * @param i
	 */
	public void setBhOdat(int i) {
		bhOdat = i;
	}

	/**
	 * @param i
	 */
	public void setBhRate(int i) {
		bhRate = i;
	}

	/**
	 * @param string
	 */
	public void setBhReas(String string) {
		bhReas = string;
	}

	/**
	 * @param string
	 */
	public void setBhRtyp(String string) {
		bhRtyp = string;
	}

	/**
	 * @param l
	 */
	public void setBhSeq(long l) {
		bhSeq = l;
	}

	/**
	 * @param i
	 */
	public void setBhStat(int i) {
		bhStat = i;
	}

	/**
	 * @param i
	 */
	public void setBhTime(int i) {
		bhTime = i;
	}

	/**
	 * @param i
	 */
	public void setBhToln(int i) {
		bhToln = i;
	}

	/**
	 * @param i
	 */
	public void setBhTost(int i) {
		bhTost = i;
	}

	/**
	 * @param i
	 */
	public void setBhUdt1(int i) {
		bhUdt1 = i;
	}

	/**
	 * @param i
	 */
	public void setBhUdt2(int i) {
		bhUdt2 = i;
	}

	/**
	 * @param string
	 */
	public void setBhUs01(String string) {
		bhUs01 = string;
	}

	/**
	 * @param string
	 */
	public void setBhUs02(String string) {
		bhUs02 = string;
	}

	/**
	 * @param string
	 */
	public void setBhUs03(String string) {
		bhUs03 = string;
	}

	/**
	 * @param string
	 */
	public void setBhUs04(String string) {
		bhUs04 = string;
	}

	/**
	 * @param string
	 */
	public void setBhUs05(String string) {
		bhUs05 = string;
	}

	/**
	 * @param string
	 */
	public void setBhUs06(String string) {
		bhUs06 = string;
	}

	/**
	 * @param string
	 */
	public void setBhUs07(String string) {
		bhUs07 = string;
	}

	/**
	 * @param string
	 */
	public void setBhUs08(String string) {
		bhUs08 = string;
	}

	/**
	 * @param string
	 */
	public void setBhUs09(String string) {
		bhUs09 = string;
	}

	/**
	 * @param string
	 */
	public void setBhUs10(String string) {
		bhUs10 = string;
	}

	/**
	 * @param string
	 */
	public void setBhUs11(String string) {
		bhUs11 = string;
	}

	/**
	 * @param string
	 */
	public void setBhUs12(String string) {
		bhUs12 = string;
	}

	/**
	 * @param string
	 */
	public void setBhUs13(String string) {
		bhUs13 = string;
	}

	/**
	 * @param string
	 */
	public void setBhUs14(String string) {
		bhUs14 = string;
	}

	/**
	 * @param string
	 */
	public void setBhUser(String string) {
		bhUser = string;
	}

	/**
	 * @return
	 */
	public ArrayList getLineas() {
		return lineas;
	}

	/**
	 * @param list
	 */
	public void setLineas(ArrayList list) {
		lineas = list;
	}

}
