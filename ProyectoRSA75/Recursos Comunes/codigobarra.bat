setlocal

set classpath=bin;c:\RAD70\lib\itext-1.02b.jar;c:\RAD70\lib\JimiProClasses.zip;c:\RAD70\lib\

java -Xmx1024m cl.recursos.GeneraCodigoBarra 81000001085000 81000001086000 /tmp/CodigoBarra/

endlocal

pause