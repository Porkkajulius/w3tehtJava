package com.elokuva.bean;

public class Elokuva {
	
	private int elokuvaID;
	private String nimi;
	public Elokuva() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Elokuva(int elokuvaID, String nimi) {
		super();
		this.elokuvaID = elokuvaID;
		this.nimi = nimi;
	}
	public int getElokuvaID() {
		return elokuvaID;
	}
	public void setElokuvaID(int elokuvaID) {
		this.elokuvaID = elokuvaID;
	}
	public String getNimi() {
		return nimi;
	}
	public void setNimi(String nimi) {
		this.nimi = nimi;
	}
	@Override
	public String toString() {
		return "Elokuva [elokuvaID=" + elokuvaID + ", nimi=" + nimi + "]";
	}
	
	

}
