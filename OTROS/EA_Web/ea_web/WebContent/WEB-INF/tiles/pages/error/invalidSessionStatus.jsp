<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<style type="text/css">
.ph1 {color: blue; font-family: Verdana; font-size: 14px; font-weight: bold;}
.ph2 {color: blue; font-family: Verdana; font-size: 12px;}
.labelbold {font-family: Verdana; font-size: 12px; font-weight: bold;}
.buttonbold {font-family: verdana; font-size: 12px; font-weight: bold;}
}
</style>

<table width="520" border="0" align="center" cellpadding="1" cellspacing="1">
	<tbody>
		<tr style="height : 20px;">
			<td>
				<html:img page="/img/c.gif" alt="" height="1" width="4" />
			</td>
		</tr>
		<tr>
			<td>
				<table width="100%">
					<tr>
						<td width="20" style="vertical-align: top;">
							<html:img page="/img/exclaim.gif" alt="" width="40" height="40" />
						</td>
						<td>
							&nbsp;
						</td>
						<td colspan="2" class="nota" style="color: red; font-family: Verdana; font-size: 14px; font-weight: bold;">
							<table cellpadding="1" cellspacing="1">
								<tbody>
									<tr>
										<td class="ph1">
											Estimado(a) Cliente.
										</td>
									</tr>
									<tr>
										<td class="ph2">
											<!-- specific error message -->
											Su session se encuentra en un estado no valido.<br />
											Si desea seguir, ingrese su id y contrasena.<br />
											Al contrario haga click al boton salir.
										</td>										
									<tr>
									<tr>
										<td>
											<form action="j_security_check" method="post">
											<table width="300px">
												<tbody>
													<tr>
														<td class="labelbold" style="width : 100px;">Identificador:</td>
														<td><input size="20" type="text" name="j_username" maxlength="25" /></td>
													</tr>							
													<tr>
														<td class="labelbold">Contraseña:</td>
														<td><input size="20" type="password" name="j_password" maxlength="25" /></td>
													</tr>
													<tr>
														<td colspan="2">&nbsp;</td>
													</tr>
													<tr>
														<td style="text-align: center;" colspan="2">
															<input class="buttonbold" type="submit" name="action" value="Ingresar" />
														</td>
													</tr>
												</tbody>
											</table>
											</form>	
										</td>
									</tr>
									<tr>
										<td class="ph2">
											&nbsp;<br />&nbsp;
										</td>
									<tr>
									<tr>
										<td class="ph2">
											<!-- specific error message -->
											Si desea volver a la página inicio, haga click al boton Aceptar.
										</td>										
									<tr>
									<tr>
										<td class="ph2">
											<!-- error code if applied-->
										</td>
									</tr>
									<tr>
										<td class="ph2">
											<!-- error message if applied-->
										</td>
									</tr>
								</tbody>
							</table>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td style="text-align: center;">
				<input type="button" class="buttonbold" name="inicio" value="Aceptar" onclick="location.href='<%=request.getContextPath()%>/'" />
			</td>
		</tr>								
	</tbody>
</table>																				
