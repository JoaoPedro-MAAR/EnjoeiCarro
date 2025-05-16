package Operacoes;

import java.util.List;

import com.db4o.ObjectContainer;
import com.db4o.query.Query;

import Classes.Fabricante;
import Classes.Modelo;



public class Alterar {
	private static ObjectContainer manager;

	public static void main(String[] args) {
		manager = Util.conectarBanco();
		System.out.println("Alteracao 1: Adicionar um relacionamento inexistente entre objetos. Trocar o fabricante de um fiesta FORD -> CHEVROLET");


		Query q = manager.query();
		q.constrain(Fabricante.class);
		q.descend("nome").constrain("Chevrolet");
		List<Fabricante> fabricantes = q.execute();
		
		Query q2 = manager.query();
		q.constrain(Modelo.class);
		q.descend("nome").constrain("Fiesta");
		List<Modelo> modelos = q2.execute();
		Modelo Fiesta = modelos.getFirst();
		
		
		if (fabricantes.size() > 0) {
			Fabricante fabricante  = fabricantes.getFirst();
			
			if (fabricante.getModelos().isEmpty())
				System.out.println("Chevrolet nao possui modelos");
			else {
				fabricante.addModelo(Fiesta);
				manager.store(fabricante);
				manager.commit();
				System.out.println("O fiesta foi removido da ford e adicionado no chevrolet");
			}
		}

		Util.desconectar();
		System.out.println("fim da aplica��o");
	}

}
