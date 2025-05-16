package Operacoes;

import java.util.List;

import com.db4o.ObjectContainer;
import com.db4o.query.Query;

import Classes.Carro;
import Classes.Fabricante;
import Classes.Modelo;

public class Listar1 {
	private static ObjectContainer manager;
	
	public static void main(String[] args) {
		manager = Util.conectarBanco();

		System.out.println("Fabricantes:");
		Query q = manager.query();
		q.constrain(Fabricante.class);
		List<Fabricante> fabricantes = q.execute();
		fabricantes.stream().forEach(item -> System.out.println(item));

		System.out.println("Modelo:");
		q = manager.query();
		q.constrain(Modelo.class);
		List<Modelo> modelos = q.execute();
		modelos.stream().forEach(item -> System.out.println(item));

		System.out.println("Carros:");
		q = manager.query();
		q.constrain(Carro.class);
		List<Carro> carros = q.execute();
		carros.stream().forEach(item -> System.out.println(item));
	}
}