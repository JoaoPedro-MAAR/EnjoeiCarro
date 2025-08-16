package appconsole;

import java.util.List;

import daojpa.Util;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import modelo.Carro;
import modelo.Modelo;
import requisito.Fachada;

public class Consultar {

	public Consultar() {
		try {
            Fachada.inicializar();
			List<Carro> carros;
			List<Modelo> modelos;

			System.out.println("\n--- Carros do ano 2022");
            System.out.println(Fachada.procurarCarroporAno(2022));


			System.out.println("\n--- Carros do fabricante 'Ford'");
            carros = Fachada.listarTodosOsCarrosDeUmFabricante("Ford");
			for (Carro c : carros)
				System.out.println(c);

			System.out.println("\n--- Modelos com mais de 2 carros");
            modelos = Fachada.procurarModeloPorqntdDeCarro(2);
			for (Modelo m : modelos)
				System.out.println(m.getNome() + " - Quantidade de carros: " + m.getLista_de_carros().size());

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

        Fachada.finalizar();
        System.out.println("\nfim do programa");
	}

	// =================================================
	public static void main(String[] args) {
		new Consultar();
	}
}
