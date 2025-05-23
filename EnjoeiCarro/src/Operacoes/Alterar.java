package Operacoes;

import java.util.List;

import com.db4o.ObjectContainer;
import com.db4o.query.Query;

import Classes.Carro;
import Classes.Modelo;

public class Alterar {
	private static ObjectContainer manager;

	public static void main(String[] args) {
		manager = Util.conectarBanco();

		
		System.out.println("Alteracao: Criar um novo carro e adicionar em um modelo e apagar um carro do mesmo modelo");
		Query q1 = manager.query();
		q1.constrain(Modelo.class);
		q1.descend("nome").constrain("Onix");
		List<Modelo> modelos = q1.execute();
		if(modelos.size()>0) { 
			Modelo onix = modelos.getFirst();
			System.out.println("\nOnix antes de adicionar novo carro: \n"+onix);
			Carro dpp = new Carro("DPP2222", 2018, 120000.0,"Laranja", onix);
			System.out.println("\nCarro que vai ser adicionado: "+dpp);
			System.out.println("\nOnix depois de adicionar novo carro: \n"+onix);
			
			List<Carro> carrosOnix = onix.getLista_de_carros();
			if(carrosOnix.size()>0) {
				System.out.println("\nOnix antes de deletar um carro: \n"+onix);
				Carro carroDeletado = carrosOnix.get(0);
				onix.rmvCarro(carroDeletado);
				System.out.println("\nOnix depois de deletar um carro: \n"+onix);

				manager.delete(carroDeletado);
			}
			
			manager.store(onix);
			manager.commit();
			
			
			
		}
		
		
		
		
		Util.desconectar();
		System.out.println("\nFim do Alterar");
		
		
		
		
		
		
	}

}