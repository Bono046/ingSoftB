package it.unibs.ing.model.proposta;


public interface IProposta {

    String getRichiesta();

    String getOfferta();

    int getDurataRichiesta();

    int getDurataOfferta();

    Boolean isAperto();

    StatoProposta getStato();

    String getUsername();

    void chiudiProposta();

    void accettaProposta();

    void ritiraProposta();

    boolean soddisfaRichiestaDi(IProposta altraProposta);

    boolean richiestaSoddisfattaDa(IProposta altraProposta);

    void calcolaDurataOfferta(double f);
}