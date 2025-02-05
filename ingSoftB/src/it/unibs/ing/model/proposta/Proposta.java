package it.unibs.ing.model.proposta;



public class Proposta implements IProposta {
	private String richiesta;
	private String offerta;
	private int durataRichiesta;
	private int durataOfferta;
	private StatoProposta stato;
	private String username;

	
	public Proposta(String richiesta, String offerta, int durataRichiesta,String username) {
		super();
		this.richiesta = richiesta;
		this.offerta = offerta;
		this.durataRichiesta = durataRichiesta;
		this.username = username;
		this.stato = StatoProposta.SOSPESO;
	}


	@Override
	public String getRichiesta() {
		return richiesta;
	}


	@Override
	public String getOfferta() {
		return offerta;
	}


	@Override
	public int getDurataRichiesta() {
		return durataRichiesta;
	}


	@Override
	public int getDurataOfferta() {
		return durataOfferta;
	}

	public void setDurataOfferta(int durataOfferta) {
		this.durataOfferta = durataOfferta;
	}


	@Override
	public Boolean isAperto() {
        return this.stato.equals(StatoProposta.APERTA);
    }


	@Override
	public StatoProposta getStato() {
		return stato;
	}
	

	@Override
	public String getUsername() {
		return username;
	}
	
	
	@Override
	public void chiudiProposta() {
		this.stato=StatoProposta.CHIUSA;
	}
	
	@Override
	public void accettaProposta() {
		this.stato = StatoProposta.APERTA;
	}
	
	@Override
	public void ritiraProposta() {
		this.stato = StatoProposta.RITIRATA;
	}
	
	
	@Override
	public boolean soddisfaRichiestaDi(IProposta altraProposta) {
	    return this.getOfferta().equals(altraProposta.getRichiesta())
	        && this.getDurataOfferta() == altraProposta.getDurataRichiesta();
	}

	@Override
	public boolean richiestaSoddisfattaDa(IProposta altraProposta) {
	    return this.getRichiesta().equals(altraProposta.getOfferta())
	        && this.getDurataRichiesta() == altraProposta.getDurataOfferta();
	}

	public void calcolaDurataOfferta(double f) {
		int durataOfferta= (int) Math.round(durataRichiesta * f);
		this.setDurataOfferta(durataOfferta);
	}
	
}
