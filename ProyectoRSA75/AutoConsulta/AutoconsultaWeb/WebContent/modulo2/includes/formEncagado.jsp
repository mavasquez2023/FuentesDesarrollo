<%-- 
    Document   : formEncagado
    Created on : 17-04-2012, 09:13:26 PM
    Author     : desajee
--%>
<form name="fEncargado" action="" method="POST">
    <input type="hidden" name="byPass" value="si"/>
    <input type="hidden" name="rutAfiliado" value="<bean:write name="afiliado.fullRut"/>"/>
    <input type="hidden" name="md2_opcion"  value='<bean:write name="md2_opcion"/>'/>
    <input type="hidden" name="md2_opcionMnu" value='<bean:write name="md2_opcionMnu"/>'/>
</form>
