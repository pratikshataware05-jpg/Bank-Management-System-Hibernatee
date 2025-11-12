package com.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Bank {
	@Id 
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	private int accountnumber;
	private String customername;
	private double balance;
	public int getAccountnumber() {
		return accountnumber;
	}
	public void setAccountnumber(int accountnumber) {
		this.accountnumber = accountnumber;
	}
	public String getCustomername() {
		return customername;
	}
	public void setCustomername(String customername) {
		this.customername = customername;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	

}
