<%@ include file="/comun/tld.jsp"%>
<fieldset class="form-fieldset fieldset-med">
	<div class="padding-m">
		<div class="field li">
			<label class="span2-8">Código tabla</label> 
			<input type="text" name="tipcod" value="${sinat03.tipcod}" id="tipcodEdit" class="text tipcod" disabled="disabled"/> 
			<input type="hidden" name="tipcod" value="${sinat03.tipcod}" id="tipcodEdit" class="text tipcod"/> 
			<label class="span2-8"></label>
		</div>
		<div class="field li">
			<label class="span2-8">% Condonación</label> 
			<input type="text" name="porcen" value="${sinat03.porcen}" id="porcenEdit" class="text porcen" />
		</div>

		<div class="field li">
			<label class="span2-8">Fecha cambio</label> 
			<input type="text" name="fecsis" value="${sinat03.fecsis}" id="fecsisEdit" class="text fecsis" />
		</div>
		<div class="field li">
			<label class="span2-8">Hora cambio</label> 
			<input type="text" name="horsis" value="${sinat03.horsis}" id="horsisEdit" class="text horsis" />
		</div>
		<div class="field li">
			<label class="span2-8">Usuario</label> 
			<input type="text" name="iduser" value="${sinat03.iduser}" id="iduserEdit" class="text iduser" maxlength="10" />
		</div>
		<input type="hidden" name="op" value="editarSinat03">
	</div>
	<br>
</fieldset>