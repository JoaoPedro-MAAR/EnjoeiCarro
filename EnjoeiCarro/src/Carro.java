
public class Carro {
	private String placa;
	private Modelo modelo;
	private int ano;
	private Double valor;

	public Carro(String placa, int ano, Double valor) {
		this.placa = placa;
		this.ano = ano;
		this.valor = valor;
	}

	public Carro(String placa, int ano, Double valor, Modelo modelo) {
		this.placa = placa;
		this.modelo = modelo;
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

	Modelo getModelo() {
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

}
