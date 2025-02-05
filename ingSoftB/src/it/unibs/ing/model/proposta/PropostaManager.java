package it.unibs.ing.model.proposta;


import java.util.ArrayList;

public class PropostaManager implements IPropostaManager {

    ArrayList<IProposta> listaProposte;
	public IChiusuraProposteStrategy chiusuraProposteStrategy;

    public PropostaManager() {
        listaProposte = new ArrayList<IProposta>();
    }

    @Override
	public ArrayList<IProposta> getLista() {
		return listaProposte;
	}
	
	@Override
	public void addElemento(IProposta p) {
		listaProposte.add(p);
	}


	@Override
	public void setChiusuraProposteStrategy(IChiusuraProposteStrategy chiusuraProposteStrategy) {
		this.chiusuraProposteStrategy = chiusuraProposteStrategy;
	}

	@Override
	public boolean verificaProposta(IProposta proposta, ArrayList<IProposta> listaProposte) {
		return chiusuraProposteStrategy.execute(proposta, listaProposte);
	}

    
	
	@Override
	public ArrayList<IProposta> getListaProposteUser(String user) {
		ArrayList<IProposta> lista = new ArrayList<>();
		for(IProposta p : listaProposte) {
			if(p.getUsername().equals(user))
				lista.add(p);
		}
		return lista;
	}

	@Override
	public ArrayList<IProposta> getListaProposteAperteUser(String user) {
		ArrayList<IProposta> lista = new ArrayList<>();
		for(IProposta p : listaProposte) {
			if(p.getUsername().equals(user) && p.isAperto()){
					lista.add(p);
				}
			}
		return lista;
	}

	@Override
	public ArrayList<IProposta> getProposteAperteFromUsers(ArrayList<String> listaUser) {
		ArrayList<IProposta> proposteFromComprensorio=new ArrayList<>();
		for(String user:listaUser) {
			ArrayList<IProposta> list = getListaProposteAperteUser(user);
			proposteFromComprensorio.addAll(list);
		}
		return proposteFromComprensorio;
	}


}
	