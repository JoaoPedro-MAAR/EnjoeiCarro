package Operacoes;

import java.util.ArrayList;
import java.util.List;

import com.db4o.ObjectContainer;
import com.db4o.query.Query;

import Classes.Carro;
import Classes.Fabricante;
import Classes.Modelo;

public class Apagar {
	private static ObjectContainer manager;

	public static void main(String[] args) {
		manager = Util.conectarBanco();
		System.out.println("Apagar o ford fiesta e seus respectivos carros");

		
		
		Query q = manager.query();
		q.constrain(Modelo.class);
		q.descend("nome").constrain("Fiesta");
		List<Modelo> resultado = q.execute();
		if (resultado.size() > 0) {
			Modelo fiesta = resultado.getFirst();
			System.out.println("Fiesta antes de ser excluido: "+fiesta);
			ArrayList<Carro> carros = fiesta.getLista_de_carros();
			if (carros != null) {
				for (Carro carro : new ArrayList<>(carros)) {
				    fiesta.rmvCarro(carro);
				    manager.delete(carro);
				}

			}

			Fabricante fabricante = fiesta.getFabricante();
			fabricante.rmvModelo(fiesta);
			manager.store(fabricante);
			manager.delete(fiesta);
			manager.commit();
		}

		Util.desconectar();
		System.out.println("fim da aplicacao");
	}

}