package requisito;
/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 *
 */

import java.util.List;

import daojpa.CarroDAO;
import daojpa.DAO;
import daojpa.FabricanteDAO;
import daojpa.ModeloDAO;
//import daodb4o.DAO;
//import daodb4o.DAOAluguel;
//import daodb4o.DAOCarro;
//import daodb4o.DAOCliente;
//import daodb4o.DAOUsuario;
import modelo.Carro;
import modelo.Modelo;

public class Fachada {
	private Fachada() {}

	private static CarroDAO carroDAO = new CarroDAO();  
//contem o usuario que logou na TelaLogin.java
	private static FabricanteDAO fabricanteDAO = new FabricanteDAO() ;
	private static ModeloDAO modeloDAO = new ModeloDAO();
	
	
	
	public static void inicializar(){
		DAO.open();
	}
	public static void finalizar(){
		DAO.close();
	}


	public static void cadastrarCarro(String placa, int ano, String cor, double valor) throws Exception{
		DAO.begin();
		Carro carro = carroDAO.read(placa);
		if (carro!=null) {
			DAO.rollback();
			throw new Exception("carro ja cadastrado:" + placa);
		}
		carro = new Carro(placa,ano,cor,valor);

		carroDAO.create(carro);
		DAO.commit();
	}



	public static void excluirCarro(String placa) throws Exception{
		DAO.begin();
		Carro carro =  carroDAO.read(placa);
		if(carro==null)  {
			DAO.rollback();
			throw new Exception ("carro incorreto para exclusao " + placa);
		}

		carroDAO.delete(carro);
		DAO.commit();
	}

	public static void cadastrarModelo(String nome) throws Exception{
		DAO.begin();
		Modelo m  = modeloDAO.read(nome);
		if (m!=null) {
			DAO.rollback();
			throw new Exception("Modelo ja cadastrado: "+ nome);
		}
		Modelo novo_modelo = new Modelo(nome);

		modeloDAO.create(novo_modelo);
		DAO.commit();
	}
	public static void excluirModelo(String nome) throws Exception{
		DAO.begin();
		Modelo modelo =  MododeloDAO.read(nome);
		if(modelo==null)  {
			DAO.rollback();
			throw new Exception ("Modelo incorreto para exclusao " + nome);
		}

		ModeloDAO.delete(modelo);
		DAO.commit();
	}

	
	public static void cadastrarFabricante(String nome) throws Exception{
		DAO.begin();
		Fabricante fabricante = FabricanteDAO.read(nome);
		if (carro!=null) {
			DAO.rollback();
			throw new Exception("Fabricante ja cadastrado:" + nome);
		}
		Fabricante fabricante = new Fabricante(nome);

		FabricanteDAO.create(fabricante);
		DAO.commit();
	}
	
	public static void excluirFabricante(String nome) throws Exception{
		DAO.begin()
		Fabricante fabricante = FabricanteDAO.read(nome);
		if (carro==null) {
			DAO.rollback()
			throw new Exception ("Fabricante incorreto para exclusão: "+nome);
		}
		FabricanteDAO.delete(fabricante)
		DAO.commit()
		
	}		
		}
	}
	
	
	
	public static void excluirAluguel(int id) throws Exception{
		DAO.begin();
		Aluguel aluguel =  aluguelDAO.read(id);
		if(aluguel==null)  {
			DAO.rollback();
			throw new Exception ("aluguel incorreto para exclusao " + id);
		}
		if(! aluguel.isFinalizado())  {
			DAO.rollback();
			throw new Exception ("aluguel nao finalizado nao pode ser excluido " + id);
		}
		
		//remover o cliente e carro do aluguel
		Cliente cli = aluguel.getCliente();
		Carro carro = aluguel.getCarro();
		cli.remover(aluguel);
		carro.remover(aluguel);
		
		aluguelDAO.delete(aluguel);
		DAO.commit();
	}

	public static List<Cliente>  listarClientes(){
		List<Cliente> resultados =  clienteDAO.readAll();
		return resultados;
	} 

	public static List<Carro>  listarCarros(){
		List<Carro> resultados =  carroDAO.readAll();
		return resultados;
	}

	public static List<Aluguel> listarAlugueis(){
		List<Aluguel> resultados =  aluguelDAO.readAll();
		return resultados;
	}

	public static List<Usuario>  listarUsuarios(){
		List<Usuario> resultados =  usuarioDAO.readAll();
		return resultados;
	} 

	public static List<Aluguel> alugueisModelo(String modelo){	
		List<Aluguel> resultados =  aluguelDAO.alugueisModelo(modelo);
		return resultados;
	}

	public static List<Aluguel> alugueisFinalizados(){	
		List<Aluguel> resultados =  aluguelDAO.alugueisFinalizados();
		return resultados;
	}

	public static List<Carro>  carrosNAlugueis(int n){	
		List<Carro> resultados =  carroDAO.carrosNAlugueis(n);
		return resultados;
	}

	public static Carro localizarCarro(String placa){
		return carroDAO.read(placa);
	} 
	public static Cliente localizarCliente(String cpf){
		return clienteDAO.read(cpf);
	}


	//------------------Usuario------------------------------------
	public static Usuario cadastrarUsuario(String nome, String senha) throws Exception{
		DAO.begin();
		Usuario usu = usuarioDAO.read(nome);
		 if (usu!=null) {
			DAO.rollback();
			throw new Exception("Usuario ja cadastrado:" + nome);
		}
		usu = new Usuario(nome, senha);

		usuarioDAO.create(usu);
		DAO.commit();
		return usu;
	}
	public static Usuario localizarUsuario(String nome, String senha) {
		Usuario usu = usuarioDAO.read(nome);
		if (usu==null) {
			return null;
		}
		if (! usu.getSenha().equals(senha)) {
			return null;
		}
		return usu;
	}
}
