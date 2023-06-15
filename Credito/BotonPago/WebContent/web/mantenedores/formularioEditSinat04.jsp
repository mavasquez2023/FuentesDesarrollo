<%@ include file="/comun/tld.jsp"%>
<fieldset class="form-fieldset fieldset-med">
	<div class="padding-m">
		<div class="field li">
			<label class="span2-8">Año tabla</label> 
			<input type="text" name="anopro" value="${sinat04.anopro}" class="text anopro" />
		</div>
		<div class="field li">
			<label class="span2-8">Código producto</label> 
			<input type="text" name="codpro" value="${sinat04.codpro}" class="text codpro" disabled="disabled" />
		</div>
		<div class="field li">
			<label class="span2-8">Cuota desde</label> 
			<input type="text" name="nrodes" value="${sinat04.nrodes}" class="text nrodes" />
		</div>
		<div class="field li">
			<label class="span2-8">Cuota hasta</label> 
			<input type="text" name="nrohas" value="${sinat04.nrohas}" class="text nrohas" />
		</div>
		<div class="field li">
			<label class="span2-8">% Condonación</label> 
			<input type="text" name="porcen" value="${sinat04.porcen}" class="text porcen" />
		</div>
		<div class="field li">
			<label class="span2-8">fecha cambio</label> 
			<input type="text" name="fecsis" value="${sinat04.fecsis}" class="text fecsis" />
		</div>
		<div class="field li">
			<label class="span2-8">Hora cambio</label> 
			<input type="text" name="horsis" value="${sinat04.horsis}" class="text horsis" />
		</div>
		<div class="field li">
			<label class="span2-8">Usuario</label> 
			<input type="text" name="iduser" value="${sinat04.iduser}" class="text iduser" maxlength="10" />
		</div>
		<input type="hidden" name="anoproEdit" value="${sinat04.anopro}" />
		<input type="hidden" name="codproEdit" value="${sinat04.codpro}" />
		<input type="hidden" name="nrodesEdit" value="${sinat04.nrodes}" />
		<input type="hidden" name="nrohasEdit" value="${sinat04.nrohas}" />
		<input type="hidden" name="op" value="editarSinat04" />
	</div>
	<br>
</fieldset>