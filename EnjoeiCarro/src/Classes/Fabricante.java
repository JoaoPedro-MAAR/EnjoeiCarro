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
		getModelos().add(model);
		model.setFabricante(this);
	}

	public void rmvModelo(Modelo model) {
		getModelos().remove(model);
		model.setFabricante(null);
	}

	public ArrayList<Modelo> getModelos() {
		return modelos;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
