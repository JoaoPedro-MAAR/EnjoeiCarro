package appconsole;

import java.util.List;


import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.TypedQuery;
import modelo.Carro;
import modelo.Modelo;
import requisito.Fachada;

public class Alterar {

	public Alterar() {
		try {
            Fachada.inicializar();

			System.out.println("Alteração: Criar um novo carro e adicionar em um modelo e apagar um carro do mesmo modelo");
            Modelo onix = Fachada.localizarModelo("Onix");

			System.out.println("Onix antes de adicionar novo carro tem " + onix.getLista_de_carros().size() + " carros");

			Fachada.cadastrarCarro("DP2222",2022,"laranja", 12000.0);
			Fachada.adicionarCarroDeModelo("DP2222","Onix");

			onix = Fachada.localizarModelo("Onix");

			System.out.println("\nCarro que vai ser adicionado: " + "DP2222");
			System.out.println("Modelo Onix depois de adicionar novo carro tem " + onix.getLista_de_carros().size() + " carros");

			List<Carro> carrosOnix = onix.getLista_de_carros();
			System.out.println("Deletando...");
			Carro carroDeletado = carrosOnix.get(0);
			System.out.println("\nCarro a ser deletado: " + carroDeletado);
			Fachada.removerCarroDeModelo(carroDeletado.getPlaca(), "Onix");

			onix =  Fachada.localizarModelo("Onix");
			System.out.println("Modelo onix depois de deletar um carro tem " + onix.getLista_de_carros().size() + " carros");


		}
		catch (NonUniqueResultException e) {
			System.out.println("encontrou nome repetido ");
		}
		catch (NoResultException e) {
			System.out.println("Onix inexistente");
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}


		Fachada.finalizar();
		System.out.println("fim do programa");
	}

	// =================================================
	public static void main(String[] args) {
		new Alterar();
	}

}
