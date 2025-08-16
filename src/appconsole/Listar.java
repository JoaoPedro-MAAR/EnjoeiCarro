package appconsole;



import requisito.Fachada;

public class Listar {

	public Listar() {
		Fachada.inicializar();
		try {
			System.out.println("\nListagem de fabricantes");
			System.out.println(Fachada.listarFabricantes());


			System.out.println("\nListagem de modelos");
			System.out.println(Fachada.listarModelos());


			System.out.println("\nListagem de carros");
			System.out.println(Fachada.listarCarros());
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		Fachada.finalizar();
		System.out.println("fim do programa");
	}

	public static void main(String[] args) {
		new Listar();
	}

}
