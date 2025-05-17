package Operacoes;

import java.util.List;

import com.db4o.ObjectContainer;
import com.db4o.query.Query;

import Classes.Carro;
import Classes.Fabricante;
import Classes.Modelo;



public class Alterar {
	private static ObjectContainer manager;

	public static void main(String[] args) {
		manager = Util.conectarBanco();
		System.out.println("Alteracao 1: Adicionar um relacionamento inexistente entre objetos. Trocar o fabricante de um focus FORD -> CHEVROLET");


		Query q = manager.query();
		q.constrain(Fabricante.class);
		q.descend("nome").constrain("Chevrolet");
		List<Fabricante> fabricantes = q.execute();
		
		Query q2 = manager.query();
		q2.constrain(Modelo.class);
		q2.descend("nome").constrain("Focus");
		List<Modelo> modelos = q2.execute();
		if (modelos.size() == 0) 
			System.out.println("Fabricante nao encontrado");
		Modelo Focus = modelos.getFirst();
		Fabricante ford = Focus.getFabricante();
			
			if (fabricantes.size() == 0)
				System.out.println("Fabricante nao encontrado");
			Fabricante fabricante  = fabricantes.getFirst();
			
			fabricante.addModelo(Focus);
			manager.store(fabricante);
			manager.commit();
			manager.store(ford);
			manager.commit();
			System.out.println("O focus foi removido da ford e adicionado no chevrolet");
			System.out.println(ford);
			System.out.println(fabricante);
			System.out.println(Focus);
				
			

			



		
		
		System.out.println("\nAlteracao 2:Remover um relacionamento existente : Remover um carro do modelo Focus deixa-lo sem modelo ");
		Carro x = Focus.getLista_de_carros().get(0);
		System.out.println("\n");
		System.out.println(x);
		System.out.println(Focus);
		Focus.rmvCarro(x);
		System.out.println("\n");
		System.out.println(x);
		System.out.println(Focus);
		manager.store(Focus);
		manager.commit();
		manager.store(x);
		manager.commit();
		System.out.println("O carro " + x.getPlaca() + " foi removido do modelo " + Focus.getNome());
		
		
		
		
		
		

		Util.desconectar();
		System.out.println("fim...");
	}

}
