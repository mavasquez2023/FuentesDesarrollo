package cl.laaraucana.giftcardsencosud.vo;

public class ProductVo {

	private String user_transaction_code;
	private String product_code;
	private String product_description;
	private String receptor_rut;
	private String receptor_name;
	private String receptor_email;
	private String created_at;
	private String stock;

	public String getUser_transaction_code() {
		return user_transaction_code;
	}

	public void setUser_transaction_code(String user_transaction_code) {
		this.user_transaction_code = user_transaction_code;
	}

	public String getProduct_code() {
		return product_code;
	}

	public void setProduct_code(String product_code) {
		this.product_code = product_code;
	}

	public String getProduct_description() {
		return product_description;
	}

	public void setProduct_description(String product_description) {
		this.product_description = product_description;
	}

	public String getReceptor_rut() {
		return receptor_rut;
	}

	public void setReceptor_rut(String receptor_rut) {
		this.receptor_rut = receptor_rut;
	}

	public String getReceptor_name() {
		return receptor_name;
	}

	public void setReceptor_name(String receptor_name) {
		this.receptor_name = receptor_name;
	}

	public String getReceptor_email() {
		return receptor_email;
	}

	public void setReceptor_email(String receptor_email) {
		this.receptor_email = receptor_email;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public String getStock() {
		return stock;
	}

	public void setStock(String stock) {
		this.stock = stock;
	}

}
