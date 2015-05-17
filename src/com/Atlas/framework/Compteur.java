package com.Atlas.framework;

public class Compteur {

	private boolean b1;
	private boolean b2;
	
	public Compteur(){
		this.b1 = false;
		this.b2 = false;
	}
	
	public boolean getB1(){
		return b1;
	}
	
	public boolean getB2(){
		return b2;
	}
	
	public void setB1(Boolean newBool){
		this.b1 = newBool;
	}
	
	public void setB2(Boolean newBool){
		this.b2 = newBool;
	}
		
	public void reinitialize(){
		this.b2 = false;
		this.b1 = false;
	}
	
	
	
}
