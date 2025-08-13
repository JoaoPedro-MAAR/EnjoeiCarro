package appconsole;


import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import modelo.Carro;
import modelo.Fabricante;
import modelo.Modelo;

public class Listar {
	private EntityManager manager;
	
	public Listar() {
		manager = Util.conectarBanco();
		
		try {
			System.out.println("\nListagem de fabricantes");
			TypedQuery<Fabricante> query1 = manager.createQuery("select f from Fabricante f", Fabricante.class);
			List<Fabricante> resultados1 = query1.getResultList();
			for (Fabricante f : resultados1)
				System.out.println(f);


			System.out.println("\nListagem de modelos");
			TypedQuery<Modelo> query2 = manager.createQuery("select m from Modelo m", Modelo.class);
			List<Modelo> resultados2 = query2.getResultList();
			for (Modelo m : resultados2)
				System.out.println(m);


			System.out.println("\nListagem de carros");
			TypedQuery<Carro> query3 = manager.createQuery("select c from Carro c", Carro.class); 
			List<Carro> resultados3 = query3.getResultList();
			for (Carro c : resultados3)
				System.out.println(c);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		Util.fecharBanco();
		System.out.println("fim do programa");
	}
	
	public static void main(String[] args) {
		new Listar();
	}

}
