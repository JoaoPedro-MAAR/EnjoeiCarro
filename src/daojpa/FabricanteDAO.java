package daojpa;

import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import modelo.Fabricante;
import modelo.Modelo;

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
}


