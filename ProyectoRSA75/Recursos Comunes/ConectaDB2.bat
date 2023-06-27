setlocal

set classpath=bin;jt400.jar;ConectaDB2.jar;j2ee.jar;

java -Xmx512m cl.recursos.ConectaDB2 146.83.1.3 usermq usermq

endlocal

pause