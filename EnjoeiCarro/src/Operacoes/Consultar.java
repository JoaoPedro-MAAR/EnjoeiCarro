package Operacoes;

import java.util.List;

import com.db4o.ObjectContainer;
import com.db4o.query.Query;

import Classes.Carro;
import Classes.Fabricante;
import Classes.Modelo;

public class Consultar {
	private ObjectContainer manager;
	
	public Consultar() {
			manager = Util.conectarBanco();
			consultar();
			Util.desconectar();
	}
	
	public void consultar() {
		List<Fabricante> fabricantes;
		List<Modelo> modelos;
		List<Carro> carros;
		Query q;
		
		System.out.println("Quais os carros do ano X");	
		q = manager.query();
		q.constrain(Carro.class);
		q.descend("ano").constrain("2020");
		carros = q.execute();
		for(Carro c : carros) {
			System.out.println(c);
		}
	
	

}