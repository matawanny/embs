package com.yieldbook.mortgage.concurrency;

import com.yieldbook.mortgage.domain.fnma.ArmPoolMonthly;

public final class Message {
	private final String id;
	private ArmPoolMonthly payload;
	
	public Message(String id, ArmPoolMonthly payload) {
		super();
		this.id = id;
		this.payload = payload;
	}
	
	public String getId() {
		return id;
	}

	public ArmPoolMonthly getPayload() {
		return payload;
	}

	public void setPayload(ArmPoolMonthly payload) {
		this.payload = payload;
	}
	
}
