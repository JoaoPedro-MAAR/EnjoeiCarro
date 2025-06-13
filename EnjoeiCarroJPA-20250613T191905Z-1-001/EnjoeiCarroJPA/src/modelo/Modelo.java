package modelo;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Modelo {
	@Id		
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String nome;
	
	
	@ManyToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE})
	private Fabricante fabricante;
	
	@OneToMany(cascade={CascadeType.PERSIST,CascadeType.MERGE})
	private List<Carro> lista_de_carros = new ArrayList<>();
	
	public Modelo() {
		
	}
	public Modelo(String nome) {
		this.nome = nome;
	}
		
	public void adicionarCarro(Carro carro){
		lista_de_carros.add(carro);
	}
	public void removerCarro(Carro carro){
		lista_de_carros.remove(carro);
	}
	
	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;

	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public List<Carro> getLista_de_carros() {
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

	public String toString() {
	    return "Modelo{" +
	            "id=" + id +
	            ", nome='" + nome + '\'' +
	            ", fabricante=" + (fabricante != null ? fabricante.getNome() : "null") +
	            '}';
	}



}
