package modelo;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity 
public class Carro {
	@Id		
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(unique = true, nullable = false)
	private String placa;	
	
	private int ano;
	private String cor;
	private double valor;
	
	@ManyToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE})	
	private Modelo modelo;
	
	
	public Carro(){
		
	}
	public Carro(String placa, int ano, String cor, double valor) {
		this.ano = ano;
		this.cor = cor;
		this.valor = valor;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public String getPlaca() {
		return placa;
	}

	public String getCor() {
		return cor;
	}
	public void setCor(String cor) {
		this.cor = cor;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public Modelo getModelo() {
		return modelo;
	}
	public void setModelo(Modelo m) {
		modelo = m;
	}
	
	@Override
	public String toString() {
		return  "Placa: "+placa+", Ano: "+ano+", Valor: "+valor+", Cor: "+cor+", Modelo: "+(modelo!=null? getModelo().getNome():"N/A");   }
	
}
