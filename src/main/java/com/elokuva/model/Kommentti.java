package com.elokuva.model;

public class Kommentti {
	
	private int kommenttiID, elokuvaID;
	private String kommenttiTeksti;
	public Kommentti() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Kommentti(int kommenttiID, int elokuvaID, String kommenttiTeksti) {
		super();
		this.kommenttiID = kommenttiID;
		this.elokuvaID = elokuvaID;
		this.kommenttiTeksti = kommenttiTeksti;
	}
	public int getKommenttiID() {
		return kommenttiID;
	}
	public void setKommenttiID(int kommenttiID) {
		this.kommenttiID = kommenttiID;
	}
	public int getElokuvaID() {
		return elokuvaID;
	}
	public void setElokuvaID(int elokuvaID) {
		this.elokuvaID = elokuvaID;
	}
	public String getKommenttiTeksti() {
		return kommenttiTeksti;
	}
	public void setKommenttiTeksti(String kommenttiTeksti) {
		this.kommenttiTeksti = kommenttiTeksti;
	}
	@Override
	public String toString() {
		return "Kommentti [kommenttiID=" + kommenttiID + ", elokuvaID="
				+ elokuvaID + ", kommenttiTeksti=" + kommenttiTeksti + "]";
	}

	
}
