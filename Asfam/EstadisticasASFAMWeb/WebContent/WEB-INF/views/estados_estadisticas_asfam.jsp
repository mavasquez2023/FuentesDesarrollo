<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<c:set var="estadoGenerada">Generada</c:set>
<c:set var="estadoNoGenerada">No generada</c:set>
<c:set var="estadoEnProceso">En Proceso</c:set>
	
<table width="1020" align="center" cellpadding="0" cellspacing="1" border="0">
	<tr>
		<td height="20" width="50" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Código</td>
		<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center" width="427">Descripción</td>
		<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center" width="136">Ejecutar Proceso</td>
		<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center" width="106">Estado</td>
		<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center" width="127">Descargar Estad&iacute;stica</td>
	</tr>
	<tr>
		<td height="20" class="texto" style="text-align: center">ASI5490</td>
		<td height="20" class="texto" style="text-align: left" width="427">
			Anexos liquidados y no liquidados mas pagos directos impresos o cursados.
		</td>
		<td height="20" class="texto" style="text-align: center" width="136">
			<form:checkbox path="generadorEstAsfam.codigoEstadistica" value="ASI5490"/>
		</td>
		<td height="20" class="texto" style="text-align: center" width="106">
			<div id="ASI5490_estado">
				<c:choose>
					<c:when test="${generadorEstAsfam.estASI5490 == 'ASI5490'}">
						<span>${estadoGenerada}</span>
					</c:when>
					<c:when test="${generadorEstAsfam.estASI5490 == 'NO_GENERADA'}">
						<span>${estadoNoGenerada}</span>
					</c:when>
					<c:when test="${generadorEstAsfam.estASI5490 == 'GENERANDO'}">
						<span>${estadoEnProceso}</span>
					</c:when>
				</c:choose>
			</div>
		</td>
		<td height="20" class="texto" style="text-align: center" width="127">
			<div id="ASI5490_excel">
				<c:choose>
					<c:when test="${generadorEstAsfam.estASI5490 == 'ASI5490'}">
						<a href="descargar?ruta_archivo=<c:out value="${generadorEstAsfam.estASI5490}"/>">Excel</a>
					</c:when>
					<c:otherwise>
						<span>No disponible</span>
					</c:otherwise>
				</c:choose>
			</div>
		</td>
	</tr>
	<tr>
		<td height="20" class="texto" style="text-align: center">ASI5491</td>
		<td height="20" class="texto" style="text-align: left" width="427">
			Anexos Liquidados y no Liquidados del proceso saldo a favor del último retorno realizado.
		</td>
		<td height="20" class="texto" style="text-align: center" width="136">
			<form:checkbox path="generadorEstAsfam.codigoEstadistica" value="ASI5491"/>
		</td>
		<td height="20" class="texto" style="text-align: center" width="106">
			<c:choose>
				<c:when test="${generadorEstAsfam.estASI5491 == 'ASI5491'}">
					<span>${estadoGenerada}</span>
				</c:when>
				<c:when test="${generadorEstAsfam.estASI5491 == 'NO_GENERADA'}">
					<span>${estadoNoGenerada}</span>
				</c:when>
				<c:when test="${generadorEstAsfam.estASI5491 == 'GENERANDO'}">
					<span>${estadoEnProceso}</span>
				</c:when>
			</c:choose>
		</td>
		<td height="20" class="texto" style="text-align: center" width="127">
			<c:choose>
				<c:when test="${generadorEstAsfam.estASI5491 == 'ASI5491'}">
					<a href="descargar?ruta_archivo=<c:out value="${generadorEstAsfam.estASI5491}"/>">Excel</a>
				</c:when>
				<c:otherwise>
					<span>No disponible</span>
				</c:otherwise>
			</c:choose>
		</td>
	</tr>
	<tr>
		<td height="20" class="texto" style="text-align: center">ASI5460</td>
		<td height="20" class="texto" style="text-align: left" width="427">
			Resumen de pagos directos informados en la estadística ASI5490.
		</td>
		<td height="20" class="texto" style="text-align: center" width="136">
			<form:checkbox path="generadorEstAsfam.codigoEstadistica" value="ASI5460"/>
		</td>
		<td height="20" class="texto" style="text-align: center" width="106">
			<c:choose>
				<c:when test="${generadorEstAsfam.estASI5460 == 'ASI5460'}">
					<span>${estadoGenerada}</span>
				</c:when>
				<c:when test="${generadorEstAsfam.estASI5460 == 'NO_GENERADA'}">
					<span>${estadoNoGenerada}</span>
				</c:when>
				<c:when test="${generadorEstAsfam.estASI5460 == 'GENERANDO'}">
					<span>${estadoEnProceso}</span>
				</c:when>
			</c:choose>
		</td>
		<td height="20" class="texto" style="text-align: center" width="127">
			<c:choose>
				<c:when test="${generadorEstAsfam.estASI5460 == 'ASI5460'}">
					<a href="descargar?ruta_archivo=<c:out value="${generadorEstAsfam.estASI5460}"/>">Excel</a>
				</c:when>
				<c:otherwise>
					<span>No disponible</span>
				</c:otherwise>
			</c:choose>
		</td>
	</tr>
	<tr>
		<td height="20" class="texto" style="text-align: center">ASI4580</td>
		<td height="20" class="texto" style="text-align: left" width="427">
			Resumen de anexos informados en la estadística ASI5490.
		</td>
		<td height="20" class="texto" style="text-align: center" width="136">
			<form:checkbox path="generadorEstAsfam.codigoEstadistica" value="ASI4580"/>
		</td>
		<td height="20" class="texto" style="text-align: center" width="106">
			<c:choose>
				<c:when test="${generadorEstAsfam.estASI4580 == 'ASI4580'}">
					<span>${estadoGenerada}</span>
				</c:when>
				<c:when test="${generadorEstAsfam.estASI4580 == 'NO_GENERADA'}">
					<span>${estadoNoGenerada}</span>
				</c:when>
				<c:when test="${generadorEstAsfam.estASI4580 == 'GENERANDO'}">
					<span>${estadoEnProceso}</span>
				</c:when>
			</c:choose>
		</td>
		<td height="20" class="texto" style="text-align: center" width="127">
			<c:choose>
				<c:when test="${generadorEstAsfam.estASI4580 == 'ASI4580'}">
					<a href="descargar?ruta_archivo=<c:out value="${generadorEstAsfam.estASI4580}"/>">Excel</a>
				</c:when>
				<c:otherwise>
					<span>No disponible</span>
				</c:otherwise>
			</c:choose>
		</td>
	</tr>
	<tr>
		<td height="20" class="texto" style="text-align: center">ASI4560</td>
		<td height="20" class="texto" style="text-align: left" width="427">
			Resumen de pagos directos informados en la estadística ASI5490.
		</td>
		<td height="20" class="texto" style="text-align: center" width="136">
			<form:checkbox path="generadorEstAsfam.codigoEstadistica" value="ASI4560"/>
		</td>
		<td height="20" class="texto" style="text-align: center" width="106">
			<c:choose>
				<c:when test="${generadorEstAsfam.estASI4560 == 'ASI4560'}">
					<span>${estadoGenerada}</span>
				</c:when>
				<c:when test="${generadorEstAsfam.estASI4560 == 'NO_GENERADA'}">
					<span>${estadoNoGenerada}</span>
				</c:when>
				<c:when test="${generadorEstAsfam.estASI4560 == 'GENERANDO'}">
					<span>${estadoEnProceso}</span>
				</c:when>
			</c:choose>
		</td>
		<td height="20" class="texto" style="text-align: center" width="127">
			<c:choose>
				<c:when test="${generadorEstAsfam.estASI4560 == 'ASI4560'}">
					<a href="descargar?ruta_archivo=<c:out value="${generadorEstAsfam.estASI4560}"/>">Excel</a>
				</c:when>
				<c:otherwise>
					<span>No disponible</span>
				</c:otherwise>
			</c:choose>
		</td>
	</tr>
	<tr>
		<td height="20" class="texto" style="text-align: center">CUADRO8</td>
		<td height="20" class="texto" style="text-align: left" width="427">
			Afiliados con cargas pagadas por anexo de trabajador, pago directo y subsidio de cesantía.
		</td>
		<td height="20" class="texto" style="text-align: center" width="136">
			<form:checkbox path="generadorEstAsfam.codigoEstadistica" value="CUADRO8"/>
		</td>
		<td height="20" class="texto" style="text-align: center" width="106">
			<c:choose>
				<c:when test="${generadorEstAsfam.estCUADRO8 == 'CUADRO8'}">
					<span>${estadoGenerada}</span>
				</c:when>
				<c:when test="${generadorEstAsfam.estCUADRO8 == 'NO_GENERADA'}">
					<span>${estadoNoGenerada}</span>
				</c:when>
				<c:when test="${generadorEstAsfam.estCUADRO8 == 'GENERANDO'}">
					<span>${estadoEnProceso}</span>
				</c:when>
			</c:choose>
		</td>
		<td height="20" class="texto" style="text-align: center" width="127">
			<c:choose>
				<c:when test="${generadorEstAsfam.estCUADRO8 == 'CUADRO8'}">
					<a href="descargar?ruta_archivo=<c:out value="${generadorEstAsfam.estCUADRO8}"/>">Excel</a>
				</c:when>
				<c:otherwise>
					<span>No disponible</span>
				</c:otherwise>
			</c:choose>
		</td>
	</tr>
	<tr>
		<td height="20" class="texto" style="text-align: center">CUADRO10</td>
		<td height="20" class="texto" style="text-align: left" width="427">
			Cargas Familiares presentes en anexos de trabajador, considerando con pago los tramos 1, 2 y 3; y sin pago el tramo 4.
		</td>
		<td height="20" class="texto" style="text-align: center" width="136">
			<form:checkbox path="generadorEstAsfam.codigoEstadistica" value="CUADRO10"/>
		</td>
		<td height="20" class="texto" style="text-align: center" width="106">
			<c:choose>
				<c:when test="${generadorEstAsfam.estCUADRO10 == 'CUADRO10'}">
					<span>${estadoGenerada}</span>
				</c:when>
				<c:when test="${generadorEstAsfam.estCUADRO10 == 'NO_GENERADA'}">
					<span>${estadoNoGenerada}</span>
				</c:when>
				<c:when test="${generadorEstAsfam.estCUADRO10 == 'GENERANDO'}">
					<span>${estadoEnProceso}</span>
				</c:when>
			</c:choose>
		</td>
		<td height="20" class="texto" style="text-align: center" width="127">
			<c:choose>
				<c:when test="${generadorEstAsfam.estCUADRO10 == 'CUADRO10'}">
					<a href="descargar?ruta_archivo=<c:out value="${generadorEstAsfam.estCUADRO10}"/>">Excel</a>
				</c:when>
				<c:otherwise>
					<span>No disponible</span>
				</c:otherwise>
			</c:choose>
		</td>
	</tr>
</table>

<table width="1020" align="center" cellpadding="0" cellspacing="1" border="0">
	<tr><td width="1020">&nbsp;</td></tr>
	<tr>
		<td width="1020" align="center" >
			<c:choose>
				<c:when test="${generadorEstAsfam.generacion == 'true'}">
					<input type="button" value="Generar Estadisiticas" class="btn_generar" 
						onclick="alert('Ya existe una solicitud de generacion de estadistica en curso.'); return false;"/>
				</c:when>
				<c:otherwise>
					<input type="submit" value="Generar Estadisiticas" class="btn_generar" />
				</c:otherwise>
			</c:choose>
			
		</td>
	</tr>
</table>