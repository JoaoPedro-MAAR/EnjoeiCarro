package daojpa;

import java.util.List;

import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import modelo.Carro;

public class CarroDAO extends DAO<Carro>{

	public Carro read (Object chave){
		try{
			String placa = (String) chave;
			TypedQuery<Carro> q = manager.createQuery("select c from Carro c where c.placa=:pla",Carro.class);
			q.setParameter("pla", placa);
			Carro c =  q.getSingleResult();
			return c;
		}catch(NoResultException e){
			return null;
		}
	}

	public List<Carro> findCarByYear(int year){
		try {
			TypedQuery<Carro> q = manager.createQuery("select c from Carro c where c.ano=:year",Carro.class);
			q.setParameter("year", year);
			List<Carro> lista = q.getResultList();
			return lista;
		}
		catch(NoResultException e){
			return null;
		}

	}

	public List<Carro> getCarrosbyFabricante(String nomeFabricante){
		try{

			TypedQuery<Carro> query = manager.createQuery(
					"select c from Carro c join c.modelo m join m.fabricante f where f.nome = :nome",Carro.class);
			query.setParameter("nome", nomeFabricante);
			List<Carro> carros = query.getResultList();
			return carros;

		}catch (NoResultException e){
			return null;
		}
	}


}
