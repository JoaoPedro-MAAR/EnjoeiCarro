package Classes;
import java.util.ArrayList;

public class Modelo {
	private String nome;
	private Fabricante fabricante;
	private ArrayList<Carro> lista_de_carros;

	public Modelo(String nome, Fabricante fabricante) {
		this.nome = nome;
		setFabricante(fabricante);
		fabricante.addModelo(this);
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
	
	public Carro localizarCarro(String placa) {
		placa = placa.toLowerCase();
		for (Carro c : lista_de_carros){
			if (c.getPlaca() == placa)
				return c;
						
		}
		return null;
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
	
	@Override
	public String toString() {
	    String carrosNome = "";
	    for (Carro c : lista_de_carros) {
	        carrosNome= carrosNome+c.getPlaca()+", ";
	    }
	    // Remove a última vírgula e espaço, se existirem carros
	    if (carrosNome.length() > 0) {
	        carrosNome = carrosNome.substring(0, carrosNome.length() - 2);
	    };
		
		
		
	    return "Nome: " + nome + ", Fabricante: " + (fabricante != null ? fabricante.getNome() : "N/A") + 
	            ", Carros: [" + carrosNome + "]";
	 }
	}


