/**
 * 
 */
package cl.araucana.wssiagf.vo;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Claudio Lillo
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
		name = "requestWS")
public class RequestWS implements Serializable{
	
@XmlElement(name="TRABAJADOR", required=true)
private List<DataRequest> data;

public List<DataRequest> getData() {
	return data;
}

public void setData(List<DataRequest> data) {
	this.data = data;
}



}