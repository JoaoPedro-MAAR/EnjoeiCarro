package daojpa;

import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import modelo.Carro;
import modelo.Fabricante;

import java.lang.reflect.ParameterizedType;
import java.util.List;

public class FabricanteDAO extends DAO<Fabricante>{
	public Fabricante read (Object chave){
		try{
			String nome = (String) chave;
			TypedQuery<Fabricante> q = manager.createQuery("select f from Fabricante f where nome=:nome",Fabricante.class);
			q.setParameter("nome", nome);
			Fabricante f =  q.getSingleResult();
			return f;
		}catch(NoResultException e){
			return null;
		}
	}




	public List<Fabricante> readAll(){
		TypedQuery<Fabricante> query = manager.createQuery("select f from Fabricante f", Fabricante.class);
		System.out.println(query);

		return query.getResultList();
	}

}


