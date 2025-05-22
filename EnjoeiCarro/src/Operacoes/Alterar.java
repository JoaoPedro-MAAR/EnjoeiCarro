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
		

		Util.desconectar();
		System.out.println("fim...");
	}

}