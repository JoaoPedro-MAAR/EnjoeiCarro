package Operacoes;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.db4o.ObjectContainer;
import com.db4o.query.Candidate;
import com.db4o.query.Evaluation;
import com.db4o.query.Query;

import Classes.Carro;
import Classes.Fabricante;
import Classes.Modelo;

public class Consultar {
	private ObjectContainer manager;

	public Consultar() {
		try {
			manager = Util.conectarBanco();
			consultar();
		} catch (Exception e) {
			System.out.println("Erro ao conectar no banco de dados: " + e.getMessage());
			return;
		}

		System.out.println("\n\n aviso: feche sempre o plugin OME antes de executar aplicação");
		Util.desconectar();

	}

	public void consultar() {
		List<Fabricante> fabricantes;
		List<Modelo> modelos;
		List<Carro> carros;
		Query q;
		int x;

		/* Primeira consulta quantos carros são do ano X */

		System.out.println("\n Primeira consulta: quantos carros são do ano X \n");

		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("Digite o ano para procurar pelos carros: ");
			x = scanner.nextInt();
			if (x < 0) {
				System.out.println("\nNumero invalido bote dnv, tem que ser maior que zero");
				continue;
			}
			break;
		}

		q = manager.query();
		q.constrain(Carro.class);
		q.descend("ano").constrain(x);
		carros = q.execute();
		if (carros.size() == 0) {
			System.out.println("Nenhum carro encontrado com o ano " + x);

		} else {
			for (Carro c : carros) {
				System.out.println("\n" + c);

			}
		}

		/* Segunda consulta: listar todos os carros de um fabricante */
		scanner.nextLine();
		String nome = " ";
		System.out.print("\nDigite o nome do fabricante: ");
		nome = scanner.nextLine().trim();

		q = manager.query();
		q.constrain(Carro.class);
		q.descend("modelo").descend("fabricante").descend("nome").constrain(nome).like();
		List<Carro> resultadoCarro = q.execute();
		String s = "";
		for (Carro c : resultadoCarro) {
			s += c.getPlaca() + ", ";
		}
		System.out.println(s);

		Integer n = 0;
		while (true) {
			System.out.println(
					"\nDigite um numero , retornaremos os modelos que tem mais carros doque o numero especificado ");
			n = scanner.nextInt();
			if (n < 0) {
				System.out.println("Digite um numero maior que zero");
				continue;
			}
			break;

		}

		System.out.println(n);
		q = manager.query();
		q.constrain(Modelo.class);
		q.constrain(new FiltroBuscarModelosComMaisdeNcarros(n));
		modelos = q.execute();
		s = "";
		for (Modelo m : modelos) {
			s += m.getNome() + ", ";
		}
		if (s.length() > 0)
			s = s.substring(0, s.length() - 2);
		if (s.length() == 0)
			s = "N/A";
		System.out.println("\nEsse(s) modelo(s) tem mais de " + n + " carros: " + s);

		scanner.close();
	}

	public static void main(String[] args) {
		new Consultar();
	}

}


class FiltroBuscarModelosComMaisdeNcarros implements Evaluation {
	private Integer n;

	public FiltroBuscarModelosComMaisdeNcarros(Integer n) {
		this.n = n;
	}

	public void evaluate(Candidate candidate) {
		Modelo modelo = (Modelo) candidate.getObject();
		ArrayList<Carro> c = modelo.getLista_de_carros();
		if (c.size() > n) {
			candidate.include(true);
		} else {
			candidate.include(false);
		}
	}
}
