  
CREATE TABLE PARAMETRO ( 
	ID_PARAMETRO SMALLINT NOT NULL , 
	NOMBRE CHAR(15) NOT NULL , 
	TIPO_PARAMETRO CHAR(25) NOT NULL , 
	TIPO_VALOR CHAR(25) NOT NULL , 
	DESCRIPCION CHAR(100) NOT NULL DEFAULT '' , 
	VALOR CHAR(100) NOT NULL DEFAULT ''  ) ; 
