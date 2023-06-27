 
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="cuerpo">

	<h2>Autenticaci&oacute;n en Sistema</h2>

	<p>Ingrese su Usuario o Email y su password</p>
	
	<s:url var="authUrl" value="/static/j_spring_security_check" />
	<form method="post" class="signin" action="${authUrl}">

		<fieldset style="width: 60%; border-radius: 15px">
			<table cellspacing="0">
				<tr>
					<td width="200px" align="left" colspan="2"><c:if
							test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
							<font style="color: red; margin-left: 30px">Usuario o
								password incorrectos<br /> <!-- 	<br /> <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}" />.-->
							</font>
						</c:if></td>

				</tr>
				<tr>
					<th width="200px" align="right"><p style="margin-right: 30px">
							<label for="username_or_email">Usuario o Email</label>
						</p>
					</th>
					<td><input id="username_or_email" name="j_username"
						type="text" /></td>
				</tr>
				<tr>
					<th width="200px" align="right"><p style="margin-right: 30px">
							<label for="password">Password</label>
						</p>
					</th>
					<td><input id="password" name="j_password" type="password" />
						<small><a href="/account/resend_password">&nbsp;&nbsp;¿Olvid&oacute;
								su password?</a> </small></td>
				</tr>
				<tr>
					<th></th>
					<td><input id="remember_me"
						name="_spring_security_remember_me" type="checkbox" /> <label
						for="remember_me" class="inline">Recuerdame</label>
					</td>
				</tr>
				<tr>
					<th></th>
					<td><input name="commit" type="submit" value="Enviar" />
					</td>
				</tr>
			</table>
		</fieldset>
	</form>

	<script type="text/javascript">
		document.getElementById('username_or_email').focus();
	</script>
</div>