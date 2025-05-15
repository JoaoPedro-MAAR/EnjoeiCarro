package Operacoes;

import Classes.Carro;

import Classes.Fabricante;
import Classes.Modelo;


import com.db4o.*;





public class Cadastrar {
	public static void main(String[] args) {

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

	}
}