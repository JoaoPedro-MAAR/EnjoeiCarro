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
		System.out.println(
				"Alteracao 1: Adicionar um relacionamento inexistente entre objetos. Adicionar modelo Novo a chevrolet e excluir um modelo antigo de chevrolet");

		Query q = manager.query();
		q.constrain(Fabricante.class);
		q.descend("nome").constrain("Chevrolet");
		List<Fabricante> resultados = q.execute();
		if(resultados.size()>0) {
			System.out.println("OI");
			Fabricante chev = resultados.getFirst();
			chev.addModelo(new Modelo("Novo", chev));
			chev.rmvModelo(chev.getModelos().getFirst());
			manager.store(chev);
			manager.commit();
		}
		
		
		
//		Query q2 = manager.query();
//		q2.constrain(Modelo.class);
//		q2.descend("nome").constrain("Focus");
//		List<Modelo> modelos = q2.execute();
//		if (modelos.size() == 0)
//			System.out.println("Fabricante nao encontrado");
//		else {
//			Modelo Focus = modelos.getFirst();
//			Fabricante ford = Focus.getFabricante();
//			if (fabricantes.size() == 0)
//				System.out.println("Fabricante nao encontrado");
//			else {
//				Fabricante fabricante = fabricantes.getFirst();
//
//				fabricante.addModelo(Focus);
//				manager.store(fabricante);
//				manager.commit();
//				manager.store(ford);
//				manager.commit();
//				System.out.println("O focus foi removido da ford e adicionado no chevrolet");
//				System.out.println(ford);
//				System.out.println(fabricante);
//				System.out.println(Focus);
//
//			}
//			System.out.println(
//					"\nAlteracao 2:Remover um relacionamento existente : Remover um carro do modelo Focus deixa-lo sem modelo ");
//			if (Focus.getLista_de_carros().size() > 0) {
//
//				Carro x = Focus.getLista_de_carros().getFirst();
//				System.out.println("\n");
//				System.out.println(x);
//				System.out.println(Focus);
//				Focus.rmvCarro(x);
//				System.out.println("\n");
//				System.out.println(x);
//				System.out.println(Focus);
//				manager.store(Focus);
//				manager.commit();
//				manager.delete(x);
//				manager.commit();
//				System.out.println("O carro " + x.getPlaca() + " foi removido do modelo " + Focus.getNome()
//						+ " e o carro foi excluido do banco");
//			} else {
//				System.out.println("Sem carros para remover do modelo focus");
//			}
//		}

		Util.desconectar();
		System.out.println("fim...");
	}

}