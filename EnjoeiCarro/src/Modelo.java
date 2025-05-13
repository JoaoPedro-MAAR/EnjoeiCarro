import java.util.ArrayList;

public class Modelo {
	private String nome;
	private Fabricante fabricante;
	private ArrayList<Carro> lista_de_carros;

	public Modelo(String nome, Fabricante fabricante) {
		this.nome = nome;
		this.fabricante = fabricante;
		this.lista_de_carros = new ArrayList<Carro>();
	}

	public Modelo(String nome) {
		this.nome = nome;
		this.lista_de_carros = new ArrayList<Carro>();
	}

	public void addCarro(Carro carro) {
		lista_de_carros.add(carro);
		carro.setModelo(this);

	}

	public void rmvCarro(Carro carro) {
		lista_de_carros.remove(carro);
		carro.setModelo(null);
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;

	}

	public ArrayList<Carro> getLista_de_carros() {
		return lista_de_carros;
	}

	public Fabricante getFabricante() {
		return fabricante;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
