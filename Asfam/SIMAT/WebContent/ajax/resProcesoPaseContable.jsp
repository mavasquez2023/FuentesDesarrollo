<%@ include file="../layout/headerJsp.jsp"%>		
			            <c:choose>
			            	<c:when test="${tipoMensaje=='3'}">
			            		<div class="alert alert-success">${mensaje}</div>
			            		<script type="text/javascript">
			            			$("#procesar").css("display","none");
			            		</script>
			            	</c:when>
			            	<c:otherwise>
					            <div class="alert alert-danger">${mensaje}</div>
			            	</c:otherwise>
			            </c:choose>