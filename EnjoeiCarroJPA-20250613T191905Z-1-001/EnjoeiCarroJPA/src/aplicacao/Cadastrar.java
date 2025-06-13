
package aplicacao;


import jakarta.persistence.EntityManager;
import modelo.Carro;
import modelo.Fabricante;
import modelo.Modelo;

public class Cadastrar {
	private EntityManager manager;

	public Cadastrar() {
		try {
			manager = Util.conectarBanco();
			System.out.println("Cadastrando Fabricantes, Modelos e Carros.");
			
			
			
			
			manager.getTransaction().begin();
			Fabricante f1 = new Fabricante("Toyota");
			Fabricante f2 = new Fabricante("Ford");
			Fabricante f3 = new Fabricante("Chevrolet");
			
			Modelo m1 = new Modelo("Corolla");
			m1.setFabricante(f1);
			f1.adicionarModelo(m1);
			
			Modelo m2 = new Modelo("Hilux");
			m2.setFabricante(f1);
			f1.adicionarModelo(m2);
			
			Modelo m3 = new Modelo("Fiesta");
			m3.setFabricante(f2);
			f2.adicionarModelo(m3);
			
			Modelo m4 = new Modelo("Onix");
			m4.setFabricante(f3);
			f3.adicionarModelo(m4);
			
			Carro c1 = new Carro("AAA1234", 2020, "Preto", 85000.0);
			c1.setModelo(m1);
			m1.adicionarCarro(c1);
			
			Carro c2 = new Carro("BBB5678", 2021, "Prata", 95000.0);
			c2.setModelo(m2);
			m2.adicionarCarro(c2);
			
			Carro c3 = new Carro("CCC9012", 2019, "Branco", 78000.0);
			c3.setModelo(m3);
			m3.adicionarCarro(c3);
			
			Carro c4 = new Carro("DDD3456", 2022, "Azul", 65000.0);;
			c4.setModelo(m4);
			m4.adicionarCarro(c4);
			
			manager.persist(f1);
			manager.persist(f2);
			manager.persist(f3);
			
			manager.getTransaction().commit();
			
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
			
		Util.fecharBanco();
		System.out.println("fim do programa");
	}


	// =================================================
	public static void main(String[] args) {
		new Cadastrar();
	}

}
