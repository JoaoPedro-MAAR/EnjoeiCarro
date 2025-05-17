package Classes;
import java.util.ArrayList;

public class Fabricante {
	private String nome;
	private ArrayList<Modelo> modelos;

	public Fabricante(String nome) {
		this.setNome(nome);
		this.modelos = new ArrayList<Modelo>();
	}

	public void addModelo(Modelo model) {
		Fabricante f = model.getFabricante();
		if (f!=null)
			f.rmvModelo(model);
		modelos.add(model);
		model.setFabricante(this);
	}

	public void rmvModelo(Modelo model) {
		modelos.remove(model);
		model.setFabricante(null);
	}

	public ArrayList<Modelo> getModelos() {
		return modelos;
	}
	
	public Modelo LocalizarModelo(String nome) {
		for (Modelo m : modelos){
			if (m.getNome() == nome)
				return m;
			
		}
		return null;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Override
	public String toString() {
		String modelNome = "";
		for (Modelo m : modelos) {
			if(m == null) {
				continue;
			}
			modelNome = modelNome + m.getNome()+", " ;
		}
		
	    if (modelNome.length() > 0) {
	    	modelNome = modelNome.substring(0, modelNome.length() - 2);
	    };
		
	

		
		return "Nome: "+nome+", Modelos: "+modelNome;
	}
	
}
