package daojpa;

import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import modelo.Modelo;


public class ModeloDAO extends DAO<Modelo> {
	public Modelo read (Object chave){
		try{
			String placa = (String) chave;
			TypedQuery<Modelo> q = manager.createQuery("select c from Modelo c where c.nome=:pla",Modelo.class);
			q.setParameter("pla", placa);
			Modelo c =  q.getSingleResult();
			return c;
		}catch(NoResultException e){
			return null;
		}
	}
}

