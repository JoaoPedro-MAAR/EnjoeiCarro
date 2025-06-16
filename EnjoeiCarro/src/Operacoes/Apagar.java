package Operacoes;

import java.util.ArrayList;
import java.util.List;

import Classes.Carro;
import Classes.Modelo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.TypedQuery;

public class Apagar {
	private EntityManager manager;

	public Apagar() {
		try {
			manager = Util.conectarBanco();

			System.out.println("Remover todos os carros do modelo 'Fiesta'");
			manager.getTransaction().begin();
			TypedQuery<Modelo> q = manager.createQuery("select m from Modelo m where m.nome = 'Fiesta' ", Modelo.class);
			Modelo modelo = q.getSingleResult();

			List<Carro> carros = modelo.getLista_de_carros();

			if (carros.isEmpty()) {
				System.out.println("Nenhum carro do modelo 'Fiesta' encontrado");
			} else {
				for (Carro carro : new ArrayList<>(carros)) {
					System.out.println(carro);
					modelo.removerCarro(carro);
					manager.remove(carro);
				}
			}

			manager.getTransaction().commit();
		} catch (NonUniqueResultException e) {
			manager.getTransaction().rollback();
			System.out.println("Mais de um fiesta encontrado");
		} catch (NoResultException e) {
			manager.getTransaction().rollback();
			System.out.println("Nenhum fiesta encontrado");
		} catch (Exception e) {
			manager.getTransaction().rollback();
			System.out.println(e.getMessage());
		}
		Util.fecharBanco();
		System.out.println("fim do programa");
	}

	// =================================================
	public static void main(String[] args) {
		new Apagar();
	}

}
