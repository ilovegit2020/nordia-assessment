package com.app.readwritecache.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PaymentIdentification implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4718162658468703045L;
	@Id
	private String instrId;
    private String endToEndId;
    
	public String getInstrId() {
		return instrId;
	}
	public void setInstrId(String instrId) {
		this.instrId = instrId;
	}
	public String getEndToEndId() {
		return endToEndId;
	}
	public void setEndToEndId(String endToEndId) {
		this.endToEndId = endToEndId;
	}
	@Override
	public String toString() {
		return "PaymentIdentification [instrId=" + instrId + ", endToEndId=" + endToEndId + "]";
	}
}
