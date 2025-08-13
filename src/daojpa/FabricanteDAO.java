package daojpa;

import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import modelo.Fabricante;
import modelo.Modelo;

public class FabricanteDAO extends DAO<Fabricante>{
	public Fa read (Object chave){
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

}
