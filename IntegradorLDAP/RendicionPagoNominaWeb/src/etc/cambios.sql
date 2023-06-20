 insert into producto values 
  ('CURBBCI','8','Licencias Curativas BDI','N','10 mm', 'a las 18:30','serv-39','/SIL_TEFBCI/Curativa/','adminftp','Arauca.2021','CurativasBCI','N','2020-08-01','101014927-2');
  
  
  insert into producto values 
  ('MATBCI','7','Licencias Maternales BDI','N','10 mm', 'a las 18:30','serv-39','/SIL_TEFBCI/Maternal/','adminftp','Arauca.2021','MaternalesBCI','N','2020-08-01','101014927-2');
  
  
  update producto
   set FtpCarpeta = '/SIL_TEFBCI/CREDITO/'
   where CodProd = 'CREDITO';
   
  
alter table NominaTefCab
alter column NombreNomina varchar(50);




alter table Convenio add pathECOutput varchar(256);

alter table Convenio add emailEjecutivo varchar(256); 