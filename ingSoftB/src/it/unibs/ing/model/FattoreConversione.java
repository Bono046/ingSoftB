package it.unibs.ing.model;

import java.util.ArrayList;

public class FattoreConversione {

	private CategoriaFoglia c1;
	private CategoriaFoglia c2;
	double fattore;

    public static double MAX = 2.0;
    public static double MIN = 0.5;
	
	public FattoreConversione(CategoriaFoglia c1, CategoriaFoglia c2, double fattore) {
		this.c1 = c1;
		this.c2 = c2;
		this.fattore = fattore;
	}

	public CategoriaFoglia getC1() {
		return c1;
	}

	public void setC1(CategoriaFoglia c1) {
		this.c1 = c1;
	}

	public CategoriaFoglia getC2() {
		return c2;
	}

	public void setC2(CategoriaFoglia c2) {
		this.c2 = c2;
	}

	public double getFattore() {
		return fattore;
	}

	public void setFattore(double fattore) {
		this.fattore = fattore;
	}

	public FattoreConversione creaFattoreInverso () {
		CategoriaFoglia c1 = getC1();
		CategoriaFoglia c2 = getC2();
		Double f12 = getFattore();
		
		return(new FattoreConversione(c2, c1, Math.round((1/f12) * 100.0) / 100.0));
	}
	
	public static double getMax() {
		return MAX;
	}

	public static double getMin() {
		return MIN;
	}

	
}
