package appconsole;

import java.util.ArrayList;
import java.util.List;

import daojpa.Util;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.TypedQuery;
import modelo.Carro;
import modelo.Modelo;
import requisito.Fachada;

public class Apagar {

	public Apagar() {
		try {
            Fachada.inicializar();
			System.out.println("Remover todos os carros do modelo 'Fiesta'");
            Modelo modelo = Fachada.localizarModelo("Fiesta");

			List<Carro> carros = modelo.getLista_de_carros();

			if (carros.isEmpty()) {
				System.out.println("Nenhum carro do modelo 'Fiesta' encontrado");
			} else {
				System.out.println("Carro(s) deletado(s): ");
				for (Carro carro : new ArrayList<>(carros)) {
					System.out.println(carro);
                    Fachada.removerCarroDeModelo(carro.getPlaca(), "Fiesta");
                }
			}

		} catch (NonUniqueResultException e) {
			System.out.println("Mais de um fiesta encontrado");
		} catch (NoResultException e) {
			System.out.println("Nenhum fiesta encontrado");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
        Fachada.finalizar();
		System.out.println("fim do programa");
	}

	// =================================================
	public static void main(String[] args) {
		new Apagar();
	}

}
