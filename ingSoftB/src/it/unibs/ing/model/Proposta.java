package it.unibs.ing.model;



public class Proposta {
	private String richiesta;
	private String offerta;
	private int durataRichiesta;
	private int durataOfferta;
	private StatoProposta stato;
	private String username;
	private enum StatoProposta {SOSPESO, APERTA, CHIUSA, RITIRATA}; 

	
	public Proposta(String richiesta, String offerta, int durataRichiesta, int durataOfferta, String username) {
		super();
		this.richiesta = richiesta;
		this.offerta = offerta;
		this.durataRichiesta = durataRichiesta;
		this.durataOfferta = durataOfferta;
		this.username = username;
		this.stato = StatoProposta.SOSPESO;
	}


	public String getRichiesta() {
		return richiesta;
	}


	public void setRichiesta(String richiesta) {
		this.richiesta = richiesta;
	}


	public String getOfferta() {
		return offerta;
	}


	public void setOfferta(String offerta) {
		this.offerta = offerta;
	}


	public int getDurataRichiesta() {
		return durataRichiesta;
	}


	public void setDurataRichiesta(int durataRichiesta) {
		this.durataRichiesta = durataRichiesta;
	}


	public int getDurataOfferta() {
		return durataOfferta;
	}


	public void setDurataOfferta(int durataOfferta) {
		this.durataOfferta = durataOfferta;
	}


	public Boolean isAperto() {
		if(this.stato.equals(StatoProposta.APERTA))
			return true;
		return false;
	}



	public StatoProposta getStato() {
		return stato;
	}
	


	public String getUsername() {
		return username;
	}
	
	
	
	public void chiudiProposta() {
		this.stato=StatoProposta.CHIUSA;
		//statiProposta.add(StatoProposta.CHIUSA);
	}
	
	public void accettaProposta() {
		this.stato = StatoProposta.APERTA;
		//statiProposta.add(StatoProposta.APERTA);
	}
	
	public void ritiraProposta() {
		this.stato = StatoProposta.RITIRATA;
		//statiProposta.add(StatoProposta.RITIRATA);
	}
	
	

	public boolean soddisfaRichiestaDi(Proposta altraProposta) {
	    return this.getOfferta().equals(altraProposta.getRichiesta())
	        && this.getDurataOfferta() == altraProposta.getDurataRichiesta();
	}

	public boolean richiestaSoddisfattaDa(Proposta altraProposta) {
	    return this.getRichiesta().equals(altraProposta.getOfferta())
	        && this.getDurataRichiesta() == altraProposta.getDurataOfferta();
	}
	
}
