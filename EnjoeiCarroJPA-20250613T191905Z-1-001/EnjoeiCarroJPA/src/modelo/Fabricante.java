package modelo;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Fabricante {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String nome;
	
	
	@OneToMany(mappedBy = "fabricante", cascade = {CascadeType.PERSIST, CascadeType.MERGE }, orphanRemoval = true) 
	private List<Modelo> modelos = new ArrayList<>();

	
	public Fabricante() {
	}
	public Fabricante(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getId () {
		return id;
	}
	
	
	public void setId(int id) {
		this.id = id;
	}


	public void adicionarModelo(Modelo m) {
		modelos.add(m);
		m.setFabricante(this);
	}

	public void removerModelo(Modelo m) {
		modelos.remove(m);
		m.setFabricante(null);
	}

	
	public String toString() {
	    return "Fabricante{" + "id=" + id + ", nome='" + nome + '\'' +'}';
	}

	public List<Modelo> getModelos() {
		return modelos;
	}

	

}
