package com.baufest.josm2.web.entity;

import com.baufest.josm2.web.utils.Constant.OPERATIONS;
import com.baufest.josm2.web.utils.Constant.STATUS;

public class Operation {
	
	private OPERATIONS typeOperation;
	private String description;
	private STATUS status;
	private Double amount;
	
	public Operation(){};
	
	public Operation(OPERATIONS typeOperation,String description,STATUS status){
		this.typeOperation = typeOperation;
		this.description = description;
		this.status = status;
	}
	
	public OPERATIONS getTypeOperation() {
		return typeOperation;
	}
	public void setTypeOperation(OPERATIONS typeOperation) {
		this.typeOperation = typeOperation;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public STATUS getStatus() {
		return status;
	}
	public void setStatus(STATUS status) {
		this.status = status;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}
}
