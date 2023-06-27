
<%@ include file="/WEB-INF/views/layout.jsp"%>
<link href="<c:url value='/static/css/bootstrap.css' />"
	rel="stylesheet"></link>
<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
<div id="mainWrapper">
	<div class="login-container">
		<div class="login-card">
			<div class="login-form">
				<c:url var="loginUrl" value="/login" />
				<form action="${loginUrl}" method="post" class="form-horizontal">
					<c:if test="${param.error != null}">
						<div class="alert alert-danger">
							<p>Usuario o password inv&aacute;lidos.</p>
						</div>
					</c:if>
					<c:if test="${param.logout != null}">
						<div class="alert alert-success">
							<p>Tu has sido loggeado exit&oacute;samente en la
								p&aacute;gina.</p>
						</div>
					</c:if>
					<div class="input-group input-sm">
						<label class="input-group-addon" for="username"><i
							class="fa fa-user"></i></label> <input type="text" class="form-control"
							id="username" name="ssoId"
							placeholder="Ingrese nombre de usuario." required>
					</div>
					<div class="input-group input-sm">
						<label class="input-group-addon" for="password"><i
							class="fa fa-lock"></i></label> <input type="password"
							class="form-control" id="password" name="password"
							placeholder="Ingrese su password." required>
					</div>
					<div class="input-group input-sm">
						<div class="checkbox">
							<label><input type="checkbox" id="rememberme"
								name="remember-me"> Recuerdame.</label>
						</div>
					</div>
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />

					<div class="row">
						<div class="col-md-5">
							<input type="submit"
								class="btn btn-block btn-primary btn-default" value="Log in">
						</div>

						<div class="col-md-5">
							<a href="<c:url value='/newuser'/>"
								class="btn btn-block btn-primary btn-default">Register</a>
						</div>

					</div>
				</form>
			</div>
		</div>
	</div>
</div>


<%@ include file="/WEB-INF/views/footer.jsp"%>