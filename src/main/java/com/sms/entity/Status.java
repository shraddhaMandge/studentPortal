package com.sms.entity;

public class Status {
	
	
	private String msg;
	private boolean status;
	
	public Status(String msg, boolean status) {
		this.msg = msg;
		this.status = status;
	}
	
	public Status()
	{}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "[msg=" + msg + ", status=" + status + "]";
	}
	
	
	
	
	

}
