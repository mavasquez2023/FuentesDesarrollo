package cl.araucana.tesoreria.util;

import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;



public class RolHandler extends TagSupport {
	private static final long serialVersionUID = 1L;
	String roles;

	public int doStartTag() throws JspException {
		try {
			HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();

			String rolUsuario = (String) request.getAttribute("rolUsuario");
			
			if (this.getRoles() == null)
				return SKIP_BODY;
			StringTokenizer st = new StringTokenizer(this.getRoles(), ";");
			while (st.hasMoreTokens()) {
				String rol = st.nextToken();
				if (rol.equals(rolUsuario))
					return EVAL_BODY_INCLUDE;
			}

		} catch (Exception e) {
		}
		return SKIP_BODY;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

}