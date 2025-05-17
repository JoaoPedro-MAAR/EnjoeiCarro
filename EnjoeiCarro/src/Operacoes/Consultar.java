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
			Util.desconectar();
		} catch (Exception e) {
			System.out.println("Erro ao conectar no banco de dados: " + e.getMessage());
			return;
		}


		System.out.println("\n\n aviso: feche sempre o plugin OME antes de executar aplicação");
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
		while (true){
		System.out.println("Digite o ano para procurar pelos carros: ");	
		x = scanner.nextInt();
		if(x < 0 ) {
			System.out.println("Numero invalido bote dnv, tem que ser maior que zero");
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
			
		}
		else {
		for(Carro c : carros) {
			System.out.println("\n"+c);
		
		}
		}
		
		
		
		
		
		/* Segunda consulta: listar todos os carros de um fabricante */
		scanner.nextLine(); 
		String nome = "";
        while (true) {
            System.out.print("Digite o nome do fabricante: ");
            nome = scanner.nextLine().trim();
        
		q = manager.query();
		q.constrain(Fabricante.class);
		q.constrain(new FiltroFabricantePorNome(nome));
		fabricantes = q.execute();
		if (fabricantes.size() == 0)
			System.out.println("Nao tem fabricantes com esse nome");
		else { 
			String s = "";
				Fabricante fabricante = fabricantes.getFirst();
				for(Modelo m : fabricante.getModelos() ){
					for (Carro c : m.getLista_de_carros()) {
						s += c.getPlaca() + ", ";
					}
					
					
				};
				s = s.substring(0, s.length()-2);
				System.out.println(fabricante.getNome()+" possui os carros com essas placas: "+s);
				break;
				
			}
		
        };
        Integer n;
        while(true) {
        	System.out.println("\nDigite um numero , retornaremos os modelos que tem mais carros doque o numero especificado ");
        	n = scanner.nextInt();
        	if(n<0) {
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
        	String s = "";
        	for (Modelo m : modelos) {
        		s += m.getNome()+", ";
        	}
        	if (s.length()>0)
        		s = s.substring(0, s.length()-2);
        	if(s.length() == 0)
        		s = "N/A";
        	System.out.println("Esse(s) modelo(s) tem mais de "+n+" carros: "+s);
        
        
			
			
			scanner.close();
		}
	
	
	
	

	
		public static void main(String[] args) {
			new Consultar();
		}


}




class FiltroFabricantePorNome implements Evaluation {
    private String nomeBuscado;

    public FiltroFabricantePorNome(String nomeBuscado) {
        this.nomeBuscado = nomeBuscado;
    }

    @Override
    public void evaluate(Candidate candidate) {
        Fabricante fabricante = (Fabricante) candidate.getObject();
        if (fabricante.getNome() != null && fabricante.getNome().equalsIgnoreCase(nomeBuscado)) {
            candidate.include(true);
        } else {
            candidate.include(false);
        }
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
        if (c.size()>n) {
            candidate.include(true);
        } else {
            candidate.include(false);
        }
    }
}

