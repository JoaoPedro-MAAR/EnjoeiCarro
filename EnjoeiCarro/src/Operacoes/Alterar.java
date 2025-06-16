package Operacoes;

import java.util.List;

import Classes.Carro;
import Classes.Modelo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.TypedQuery;

public class Alterar {
	private EntityManager manager;

	public Alterar() {
		try {
			manager = Util.conectarBanco();

			manager.getTransaction().begin();
			System.out.println("Alteração: Criar um novo carro e adicionar em um modelo e apagar um carro do mesmo modelo");
			TypedQuery<Modelo> q = manager.createQuery(
					"select m from Modelo m where m.nome = 'Onix' ", Modelo.class);
			Modelo onix = q.getSingleResult();

			System.out.println("Onix antes de adicionar novo carro:" + onix);
			
			Carro dpp = new Carro("DP2222", 2022, "Laranja", 120000.0);
			dpp.setModelo(onix);
			onix.adicionarCarro(dpp);
			
			manager.persist(dpp);
			
			System.out.println("nCarro que vai ser adicionado: " + dpp);
			System.out.println("Modelo Onix depois de adicionar novo carro:" + onix);
			
			List<Carro> carrosOnix = onix.getLista_de_carros();
			
			Carro carroDeletado = carrosOnix.get(0);
			onix.removerCarro(carroDeletado);
			manager.remove(carroDeletado);
			
			System.out.println("Modelo onix depois de deletar um carro:" + onix);
			
			
			manager.getTransaction().commit();
		} 
		catch (NonUniqueResultException e) {
			manager.getTransaction().rollback();
			System.out.println("encontrou nome repetido ");
		}
		catch (NoResultException e) {
			manager.getTransaction().rollback();
			System.out.println("Onix inexistente");
		}
		catch (Exception e) {
			manager.getTransaction().rollback();
			System.out.println(e.getMessage());
		}


		Util.fecharBanco();
		System.out.println("fim do programa");
	}

	// =================================================
	public static void main(String[] args) {
		new Alterar();
	}

}
