package com.showjsonapp.entity;

public class PaymentIdentification{

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
