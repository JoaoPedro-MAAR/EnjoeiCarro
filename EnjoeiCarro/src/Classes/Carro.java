package Classes;

public class Carro {
	private String placa;
	private Modelo modelo;
	private int ano;
	private Double valor;


	public Carro(String placa, int ano, Double valor, Modelo modelo) {
		this.placa = placa;
		setModelo(modelo);
		modelo.addCarro(this);
		this.ano = ano;
		this.valor = valor;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public int getAno() {
		return ano;
	}

	public Modelo getModelo() {
		return modelo;
	}

	public void setModelo(Modelo model) {
		modelo = model;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}
	@Override
	public String toString() {
		return  "Placa:"+placa+", Ano: "+ano+", Valor: "+valor+", Modelo: "+(modelo!=null? getModelo().getNome():"N/A");   }
}

