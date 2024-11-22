package model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlValue;

public class Amount {

	private double value;
	private String currency="€";
	
	public Amount() {}
	
	public Amount(double value) {
		super();
		this.value = value;
	
	}
		
	@XmlValue
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	
	@XmlAttribute
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		if("Euro".equals(currency)) {
			this.currency ="€";
		}else {
			this.currency = currency;
		}
	}
	@Override
	public String toString() {
		return "Amount [value=" + value + ", currency=" + currency + "]";
	}
	
	
}
