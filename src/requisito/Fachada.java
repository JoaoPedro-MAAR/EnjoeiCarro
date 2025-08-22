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
import modelo.Carro;
import modelo.Fabricante;
import modelo.Modelo;

public class Fachada {
	private Fachada() {}

	private static CarroDAO carroDAO = new CarroDAO();  
	private static FabricanteDAO fabricanteDAO = new FabricanteDAO() ;
	private static ModeloDAO modeloDAO = new ModeloDAO();
	
	
	
	public static void inicializar(){
		DAO.open();
	}
	public static void finalizar(){
		DAO.close();
	}


	public static void trocarModeloAoFabricante(String nomeModelo, String nomeFabricante) throws Exception{
		try{
			Modelo modelo = modeloDAO.read(nomeModelo);
			Fabricante fabricante = fabricanteDAO.read(nomeFabricante);
			if (modelo == null || fabricante == null){
				throw new Exception("Modelo ou fabricante não encontrados");
			}
			fabricante.adicionarModelo(modelo);
			modeloDAO.update(modelo);
			fabricanteDAO.update(fabricante);
			DAO.commit();

		}catch (Exception e){
			throw e;
		}
	}

	public static void trocarModeloAoFabricante(int idModelo, String nomeFabricante) throws Exception{
		try{
			Modelo modelo = modeloDAO.getByid(idModelo);
			Fabricante fabricante = fabricanteDAO.read(nomeFabricante);
			if (modelo == null || fabricante == null){
				throw new Exception("Modelo ou fabricante não encontrado");
			}
			fabricante.adicionarModelo(modelo);
			modeloDAO.update(modelo);
			fabricanteDAO.update(fabricante);
			DAO.commit();
		}catch (Exception e){
			throw e;
		}
	}



//
//	public static void alterarModelo(int id,  String nomeModelo, String nomeFabricante) throws Exception{
//		try{
//			Modelo modelo = modeloDAO.getByid(id);
//			if mod
//			Fabricante fabricante = fabricanteDAO.read(nomeFabricante);
//			if (modelo == null || fabricante == null){
//				throw new Exception("Fabricante ou modelo não encontrado");
//			}
//		}
//	}



	public static void cadastrarCarro(String placa, int ano, String cor, double valor) throws Exception{
		DAO.begin();
		if (placa == null || placa.isEmpty()){
			DAO.rollback();
			throw new Exception("Placa não poder nula nem vazia");

		}
		if  (cor == null || cor.isEmpty()){
			DAO.rollback();
			throw new Exception("Cor não poder ser nula nem vazia");

		}
		if (ano < 1886){
			DAO.rollback();
			throw new Exception("Ano não poder ser menor que 1886");
		}
		if (valor <= 0){
			DAO.rollback();
			throw new Exception("Valor não poder ser menor que 0");
		}
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

	public static void cadastrarModelo(String nome) throws Exception {
		DAO.begin();
		if (nome == null || nome.isEmpty()){
			DAO.rollback();
			throw new Exception("Nome do modelo não pode ser vazio ou nulo");
		}
		Modelo m = modeloDAO.read(nome);
		if (m != null) {
			DAO.rollback();
			throw new Exception("Modelo ja cadastrado: " + nome);
		}
		Modelo novo_modelo = new Modelo(nome);

		modeloDAO.create(novo_modelo);
		DAO.commit();
	}

	public static void excluirModelo(String nome) throws Exception{
		DAO.begin();
		Modelo modelo =  modeloDAO.read(nome);
		if(modelo==null)  {
			DAO.rollback();
			throw new Exception ("Modelo incorreto para exclusao " + nome);
		}

		modeloDAO.delete(modelo);
		DAO.commit();
	}

	
	public static void cadastrarFabricante(String nome) throws Exception{
		DAO.begin();
		if (nome == null || nome.isEmpty()){
			DAO.rollback();
			throw new Exception("Nome do fabricante não pode ser vazio ou nulo");
		}
		Fabricante fabricante = fabricanteDAO.read(nome);
		if (fabricante!=null) {
			DAO.rollback();
			throw new Exception("Fabricante ja cadastrado:" + nome);
		}
		Fabricante f = new Fabricante(nome);

		fabricanteDAO.create(f);
		DAO.commit();
	}
	
	public static void excluirFabricante(String nome) throws Exception{
		DAO.begin();
		Fabricante fabricante = fabricanteDAO.read(nome);
		if (fabricante==null) {
			DAO.rollback();
			throw new Exception ("Fabricante incorreto para exclusão: "+nome);
		}
		fabricanteDAO.delete(fabricante);
		DAO.commit();
		
	}
	public static List<Carro>  listarCarros(){
		List<Carro> resultados =  carroDAO.readAll();
		return resultados;
	} 

	public static List<Modelo>  listarModelos(){
		List<Modelo> resultados =  modeloDAO.readAll();
		return resultados;
	}

	public static List<Fabricante> listarFabricantes(){
		List<Fabricante> resultados =  fabricanteDAO.readAll();
		return resultados;
	}

	public static Carro localizarCarro(String placa){
		return carroDAO.read(placa);
	}
	public static Modelo localizarModelo(String nome){return modeloDAO.read(nome);}
	public static Fabricante localizarFabricante(String nome){return fabricanteDAO.read(nome);}

	public static List<Carro> listarTodosOsCarrosDeUmFabricante(String nome){
		return carroDAO.getCarrosbyFabricante(nome);
	}

	public static List<Carro> procurarCarroporAno(int ano){
		return carroDAO.findCarByYear(ano);
	}

	public static void atualizarCarro(String placa, String cor, Double valor, int ano)throws Exception{
		DAO.begin();
		Carro carro = carroDAO.read(placa);
		if(carro == null){
			throw new Exception("carro inexistente para atualizar " + placa);

		}
		if (cor != null) {
			carro.setCor(cor);

		}
		if (valor != null)
			carro.setValor(valor);
		if (ano > 1885 && ano <= 9999)
			carro.setAno(ano);
		carroDAO.update(carro);
		DAO.commit();


	}
	
	
	public static void trocaFotoCarro(byte[] foto, String placa) throws Exception {
		DAO.begin();
		Carro carro = carroDAO.read(placa);
		if(carro == null) {
			throw new Exception("Carro inexistente para definir a foto: "+ placa);
		}
		carro.setFoto(foto);
		carroDAO.update(carro);
		DAO.commit();
		
	}


	public static void alterarNomeFabricante(int id,String nome) throws Exception {
		DAO.begin();
		Fabricante fabricante = fabricanteDAO.getByid(id);
		if (fabricante==null) {
			throw new Exception("Fabricante inexistente para alterar " + id);
		}
		fabricante.setNome(nome);
		fabricanteDAO.update(fabricante);
		DAO.commit();
	}
	
	public static void alterarNomeModelo(int id, String nomeNovo) throws Exception {
		DAO.begin();
		Modelo modelo = modeloDAO.getByid(id);

		if(modelo == null){
			throw new Exception("Modelo inexistente para definir trocar o nome ");
		}

		String nomeAntigo = modelo.getNome();



		if(nomeAntigo.equals(nomeNovo)) {
			return;
		}

		Modelo modeloNovo = modeloDAO.read(nomeNovo);
		if(modeloNovo != null) {
			throw new Exception("Ja existe um modelo com esse nome");
		}

		modelo.setNome(nomeNovo);
		modeloDAO.update(modelo);
		DAO.commit();
	
	}


	public static Modelo localizarModelo(int id) throws Exception {
		return modeloDAO.getByid(id);
	}



	public static List<Modelo> procurarModeloPorqntdDeCarro(int qntd){
		if (qntd>-1)
			return modeloDAO.getModeloWithGTThan(qntd);
		return null;
	}


	public static void removerCarroDeModelo(String placa, String nomeModelo) throws Exception {
		DAO.begin();
		try {
			Modelo modelo = modeloDAO.read(nomeModelo);
			Carro c = null;

			for (Carro carro : modelo.getLista_de_carros()) {
				if (carro.getPlaca().equals(placa)) {
					c = carro;
					break;
				}
			}

			if (c == null) {
				DAO.rollback();
			} else {
				modelo.getLista_de_carros().remove(c);
				modeloDAO.update(modelo);


				carroDAO.update(c);

				DAO.commit();
			}
		} catch (Exception ex) {
			DAO.rollback();
			throw ex;
		}
	}

	public static void adicionarCarroDeModelo(String placa,String nomeModelo)throws Exception{
		DAO.begin();
		try {
			Modelo modelo = modeloDAO.read(nomeModelo);
			if (modelo == null) {
				DAO.rollback();
				throw new Exception("Modelo não pode ser encontrado");
			}
			Carro carro = carroDAO.read(placa);
			if (carro == null) {
				DAO.rollback();
				throw new Exception("Carro não pode ser encontrado");
			}
			modelo.adicionarCarro(carro);
			modeloDAO.update(modelo);
			System.out.println("Modelo adicionado com sucesso");
			carroDAO.update(carro);
			System.out.println("Carro update com sucesso");
			DAO.commit();
		}catch(Exception ex){
			DAO.rollback();
			throw ex;
		}
		DAO.commit();
	}







}
