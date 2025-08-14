package daojpa;

import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import modelo.Carro;
import modelo.Fabricante;

import java.util.List;

public class FabricanteDAO extends DAO<Fabricante>{
	public Fabricante read (Object chave){
		try{
			String nome = (String) chave;
			TypedQuery<Fabricante> q = manager.createQuery("select Fabricante from Fabricante where nome=:nome",Fabricante.class);
			q.setParameter("nome", nome);
			Fabricante f =  q.getSingleResult();
			return f;
		}catch(NoResultException e){
			return null;
		}
	}


	public List<Carro> getCarrosbyFabricante(String nomeFabricante){
		try{

			TypedQuery<Carro> query = manager.createQuery(
					"select Carro from Carro c join c.modelo m join m.fabricante f where f.nome = :nome",Carro.class);
			query.setParameter("nome", nomeFabricante);
			List<Carro> carros = query.getResultList();
			return carros;

		}catch (NoResultException e){
			return null;
		}
	}
}


