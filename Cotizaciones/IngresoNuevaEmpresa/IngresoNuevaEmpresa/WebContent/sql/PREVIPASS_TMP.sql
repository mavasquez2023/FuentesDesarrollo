DROP TABLE PREVIPASS_TMP;

CREATE TABLE PREVIPASS_TMP(
rut_admin varchar(10) default '' ,
nombre_admin varchar(30) default '' ,
apellido_paterno_admin varchar(30) default '' ,
apellido_materno_admin varchar(30) default '' ,
codigo_telefono_fijo_admin varchar(3) default '' ,
telefono_fijo_admin varchar(8) default '' ,
codigo_fax_admin varchar(3) default '' ,
fax_admin varchar(8) default '' ,
celular_admin varchar(8) default '' ,
email_admin varchar(50) default '' ,
direccion_admin varchar(50) default '' ,
direccion_numero_admin varchar(10) default '' ,
departamento_numero_admin varchar(10) default '' ,
region_admin varchar(10) default '' ,
ciudad_admin varchar(10) default '' ,
comuna_admin varchar(10) default '' ,

rut_empresa varchar(10) default '' ,
email_empresa varchar(50) default '' ,
razon_social_empresa varchar(50) default '' ,
tipo_empresa varchar(2) default '' ,
nombre_holding_empresa varchar(20) default '' ,
rut_representante_legal_empresa varchar(10) default '' ,
nombre_representante_legal_empresa varchar(30) default '' ,
apellido_paterno_representante_legal_empresa varchar(30) default '' ,
apellido_materno_representante_legal_empresa varchar(30) default '' ,
estado_representante_legal_empresa varchar(2) default '' ,
vigencia_representante_legal_empresa varchar(10) default '' ,
codigo_acividad_economica_empresa varchar(10) default '' ,
nombre_acividad_economica_empresa varchar(20) default '',
emppsw varchar(4) 


codigo_casa_matriz varchar(20) default '',
nombre_casa_matriz varchar(20) default '',
direccion_casa_matriz varchar(20) default '',
direccion_numero_casa_matriz varchar(20) default '',
depto_numero_casa_matriz varchar(20) default '',
region_casa_matriz varchar(20) default '',
ciudad_casa_matriz varchar(20) default '',
comuna_casa_matriz varchar(20) default '',
codigo_telefono_casa_matriz varchar(20) default '',
telefono_fijo_casa_matriz varchar(20) default '',
celular_casa_matriz varchar(20) default '',
codigo_fax_casa_matriz varchar(20) default '',
fax_casa_matriz varchar(20) default '',
email_casa_matriz varchar(30) default '',

nombre_mutual varchar(30) default '',
numero_adherentes_mutual varchar(30) default '',
calculo_individual_mutual varchar(30) default '',
tasa_adicional_mutual varchar(30) default '',
caja_compensacion varchar(30) default '',


generar_planilla_inp_sucursal varchar(30) default 'SI',
calcular_monto_total_ccaf varchar(30) default '',
generar_planilla_mutual_sucursal varchar(30) default '',
calcular_monto_total_salud varchar(30) default '',
generar_planilla_ccaf_sucursal varchar(30) default '',
calcular_monto_total_prevision varchar(30) default '',
calcular_monto_total_fonasa varchar(30) default '',
imprimir_planillas varchar(30) default '',
calcular_monto_total_mutual varchar(30) default '',
calcular_movimiento_personal varchar(30) default '',
formato_nomina_sel varchar(30) default ''
);

insert into PREVIPASS_TMP ( rut_empresa,email_empresa ) values ('15057836-1','luisibacache@gmail.com');

select rut_empresa, email_empresa,clave_empresa from PREVIPASS_TMP;
