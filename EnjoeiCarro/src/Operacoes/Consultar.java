package Operacoes;

import java.util.List;

import Classes.Carro;
import Classes.Modelo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class Consultar {
	private EntityManager manager;

	public Consultar() {
		manager = Util.conectarBanco();
		try {
			TypedQuery<Carro> q1;
			TypedQuery<Modelo> q2;
			List<Carro> carros;
			List<Modelo> modelos;

			System.out.println("\n--- Carros do ano 2022");
			q1 = manager.createQuery("""
					select  c from Carro c where c.ano = :ano """, Carro.class);
			q1.setParameter("ano", 2022);
			carros = q1.getResultList();
			for (Carro c : carros)
				System.out.println(c);

			System.out.println("\n--- Carros do fabricante 'Ford'");
			q1 = manager.createQuery("""
					select c from Carro c
					where c.modelo.fabricante.nome = :nome
					""", Carro.class);
			q1.setParameter("nome", "Ford");
			carros = q1.getResultList();
			for (Carro c : carros)
				System.out.println(c);

			System.out.println("\n--- Modelos com mais de 2 carros");
			q2 = manager.createQuery("""
					select m from Modelo m
					where size(m.lista_de_carros) > :n""", Modelo.class);
			q2.setParameter("n", 2);
			modelos = q2.getResultList();
			for (Modelo m : modelos)
				System.out.println(m.getNome() + " - Quantidade de carros:: " + m.getLista_de_carros().size());

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		Util.fecharBanco();
		System.out.println("\nfim do programa");
	}

	// =================================================
	public static void main(String[] args) {
		new Consultar();
	}
}
