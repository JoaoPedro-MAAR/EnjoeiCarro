package daojpa;

import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import modelo.Modelo;
import java.util.List;


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




	public List<Modelo> getModeloWithGTThan(int numeroDeCarros){

		try {
			TypedQuery<Modelo> query = manager.createQuery("select m from Modelo m where size(m.lista_de_carros) > :n", Modelo.class);
			query.setParameter("n", 2);
			List<Modelo> modelos = query.getResultList();
			return modelos;

		}catch(NoResultException e){
			return null;
		}

	}
}

