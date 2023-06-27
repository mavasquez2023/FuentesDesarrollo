package com.alexis.dto;

public class Message {

  public Message(String _mensaje) {
	  
	  this.mensaje = _mensaje; 
  }
  
  private String mensaje;

public String getMensaje() {
	return mensaje;
}

public void setMensaje(String mensaje) {
	this.mensaje = mensaje;
}
  
  
}
