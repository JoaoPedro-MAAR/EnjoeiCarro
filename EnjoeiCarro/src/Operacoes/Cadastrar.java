package Operacoes;

import com.db4o.ObjectContainer;

import Classes.Carro;
import Classes.Fabricante;
import Classes.Modelo;


public class Cadastrar {
	private static ObjectContainer manager;
	
	public static void main(String[] args) {
		
		
		try {
			manager = Util.conectarBanco();
		} catch (Exception e) {
			System.out.println("Erro ao conectar no banco de dados: " + e.getMessage());
			return;
		}
		System.out.println("conectando ao banco");
		manager = Util.conectarBanco();
		System.out.println("conectou ao banco");
		
		Fabricante f1 = new Fabricante("Toyota");

		Fabricante f2 = new Fabricante("Ford");

		Fabricante f3 = new Fabricante("Chevrolet");


		Modelo m1 = new Modelo("Corolla", f1);	
		Modelo m2 = new Modelo("Hilux", f1);
		Modelo m3 = new Modelo("Fiesta", f2);
		Modelo m4 = new Modelo("Focus", f2);
		Modelo m5 = new Modelo("Onix", f3);

		
		
		Carro c1 = new Carro("ABC1234", 2020, 80000.0, m1);
		Carro c2 = new Carro("DEF5678", 2019, 75000.0, m1);

		Carro c3 = new Carro("GHI9012", 2021, 120000.0, m2);

		Carro c4 = new Carro("JKL3456", 2018, 65000.0, m3);

		Carro c5 = new Carro("MNO7890", 2020, 70000.0, m3);

		Carro c6 = new Carro("PQR1234", 2022, 90000.0, m4);
		Carro c7 = new Carro("STU5678", 2023, 95000.0, m4);
		Carro c8 = new Carro("VWX9012", 2021, 60000.0, m5);
		Carro c9 = new Carro("YZA3456", 2020, 58000.0, m5);
		Carro c10 = new Carro("BCD7890", 2019, 55000.0,m5);
		manager.store(c1);

		manager.store(c2);

		manager.store(c3);

		manager.store(c4);

		manager.store(c5);

		manager.store(c6);

		manager.store(c7);

		manager.store(c8);

		manager.store(c9);

		manager.store(c10);
		manager.commit();
		
		Util.desconectar();
		System.out.println("cadastrou os fabricantes, modelos e carros");
		

	}
}