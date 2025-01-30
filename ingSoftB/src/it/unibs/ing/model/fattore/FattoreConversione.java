package it.unibs.ing.model.fattore;

public class FattoreConversione implements IFattore {

	private String c1;
	private String c2;
	double fattore;

    public static double MAX = 2.0;
    public static double MIN = 0.5;
	
	public FattoreConversione(String c1, String c2, double fattore) {
		this.c1 = c1;
		this.c2 = c2;
		this.fattore = fattore;
	}

	@Override
	public String getC1() {
		return c1;
	}


	@Override
	public String getC2() {
		return c2;
	}


	@Override
	public double getFattore() {
		return fattore;
	}
	

	@Override
	public FattoreConversione creaFattoreInverso () {
		String c1 = getC1();
		String c2 = getC2();
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
