package Classes;

public class Carro {
	private String placa;
	private Modelo modelo;
	private int ano;
	private Double valor;
	private String cor;


	public Carro(String placa, int ano, Double valor,String cor, Modelo modelo) {
		this.placa = placa;
		modelo.addCarro(this);
		this.ano = ano;
		this.valor = valor;
		this.cor = cor;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public void setCor(String cor){
		this.cor = cor;
	}

	public String getCor(){
		return this.cor;
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
		return  "Placa: "+placa+", Ano: "+ano+", Valor: "+valor+", Cor: "+cor+", Modelo: "+(modelo!=null? getModelo().getNome():"N/A");   }
}

