/**
 * 
 */
package cl.laaraucana.recepcionsil.service.vo;

/**
 * @author IBM Software Factory
 *
 */
public class PersistVO {
	private String afirut;
	private Integer numimpre;
	private byte[] data;
	/**
	 * @return the afirut
	 */
	public String getAfirut() {
		return afirut;
	}
	/**
	 * @param afirut the afirut to set
	 */
	public void setAfirut(String afirut) {
		this.afirut = afirut;
	}
	/**
	 * @return the numimpre
	 */
	public Integer getNumimpre() {
		return numimpre;
	}
	/**
	 * @param numimpre the numimpre to set
	 */
	public void setNumimpre(Integer numimpre) {
		this.numimpre = numimpre;
	}
	/**
	 * @return the data
	 */
	public byte[] getData() {
		return data;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(byte[] data) {
		this.data = data;
	}
}
