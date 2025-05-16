package Operacoes;

import java.util.List;

import javax.management.Query;

import com.db4o.ObjectContainer;

import modelo.Loja;

public class Apagar {
	private static ObjectContainer manager;

	public static void main(String[] args) {
		manager = Util.conectarBanco();
		System.out.println("Apagar a loja 222222");

		// localizar loja 222222
		Query q = manager.query();
		q.constrain(Loja.class);
		q.descend("cnpj").constrain("222222");
		List<Loja> resultado = q.execute();
		if (resultado.size() > 0) {
			Loja loja = resultado.getFirst();
			// remover todos os empregados da loja
			loja.getEmpregados().stream().forEach(emp -> {
				emp.setLoja(null);
				manager.store(emp);
			});

			manager.delete(loja);
			manager.commit();
		}

		Util.desconectar();
		System.out.println("fim da aplica��o");
	}

}