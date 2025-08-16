		
package appconsole;


import daojpa.Util;
import modelo.Carro;
import requisito.Fachada;

public class Cadastrar {

	public Cadastrar() {
		try {
			Fachada.inicializar();
			try {
				System.out.println("Cadastrando Fabricantes.");

				Fachada.cadastrarFabricante("Toyota");
				Fachada.cadastrarFabricante("Ford");
				Fachada.cadastrarFabricante("Chevrolet");
			}
			catch (Exception e) {
				System.out.println(e.getMessage());
			}
			try{
				System.out.println("Cadastrando Modelos.");
				Fachada.cadastrarModelo("Corolla");
				Fachada.cadastrarModelo("Hilux");
				Fachada.cadastrarModelo("Fiesta");
				Fachada.cadastrarModelo("Focus");
				Fachada.cadastrarModelo("Onix");
			}catch (Exception e) {
				System.out.println(e.getMessage());
			}

			try{
				System.out.println("Associando modelos a fabricantes.");
				Fachada.TrocarModeloAoFabricante("Corolla","Toyota");
				Fachada.TrocarModeloAoFabricante("Hilux", "Toyota");
				Fachada.TrocarModeloAoFabricante("Fiesta", "Ford");
				Fachada.TrocarModeloAoFabricante("Focus", "Ford");
				Fachada.TrocarModeloAoFabricante("Onix", "Chevrolet");

			}catch (Exception e) {
				System.out.println(e.getMessage());
			}
			try {
				System.out.println("Cadastrando carros.");
				Fachada.cadastrarCarro("AAA1234", 2020, "Preto", 85000.0);
				Fachada.cadastrarCarro("BBB5678", 2021, "Prata", 95000.0);
				Fachada.cadastrarCarro("CC9012", 2019, "Branco", 78000.0);
				Fachada.cadastrarCarro("DDD3456", 2022, "Azul", 65000.0);
				Fachada.cadastrarCarro("PJK5566", 2016, "Azul", 65000.0);
				Fachada.cadastrarCarro("FGH6677", 2015, "Vermelho", 75000.0);
				Fachada.cadastrarCarro("JKL4433", 2022, "Amarelo", 65000.0);

			}catch (Exception e) {
				System.out.println(e.getMessage());
			}

			try {
				System.out.println("Associando carros a modelos.");
				Fachada.adicionarCarroDeModelo("AAA1234","Corolla");

				Fachada.adicionarCarroDeModelo("BBB5678", "Hilux");

				Fachada.adicionarCarroDeModelo("CC9012", "Fiesta");

				Fachada.adicionarCarroDeModelo("DDD3456", "Focus");

				Fachada.adicionarCarroDeModelo("PJK5566", "Focus");

				Fachada.adicionarCarroDeModelo("FGH6677", "Focus");
				Fachada.adicionarCarroDeModelo("JKL4433", "Onix");
			}catch (Exception e) {
				System.out.println(e.getMessage());
			}



			
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
			
		Fachada.finalizar();
		System.out.println("fim do programa");
	}


	// =================================================
	public static void main(String[] args) {
		new Cadastrar();
	}

}
