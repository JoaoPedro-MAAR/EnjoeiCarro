package Operacoes;

import java.util.List;

import com.db4o.ObjectContainer;
import com.db4o.query.Query;

import Classes.Carro;
import Classes.Fabricante;
import Classes.Modelo;

public class Listar2 {
	private static ObjectContainer manager;
	
	public static void main(String[] args) {
		manager = Util.conectarBanco();

		System.out.println("Fabricantes:\n");
		Query q1 = manager.query();
		q1.constrain(Fabricante.class);
		List<Fabricante> resultados = q1.execute();
		for(Fabricante f : resultados)
		System.out.println(f);

		System.out.println("\nModelo:\n");
		Query q2 = manager.query();
		q2.constrain(Modelo.class);
		List<Modelo> resultadosMo = q2.execute();
		for(Modelo m : resultadosMo)
		System.out.println(m);

		System.out.println("\nCarros:\n");
		Query q3 = manager.query();
		q3.constrain(Carro.class);
		List<Carro> resultadosCA = q3.execute();
		for(Carro c : resultadosCA)
		System.out.println(c);
		Util.desconectar();
	}
}