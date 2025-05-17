package Classes;

public class teste {

	public static void main(String[] args) {
		 Fabricante fabricante1 = new Fabricante("Fabricante A");
	        Fabricante fabricante2 = new Fabricante("Fabricante B");

	        // Criando modelos
	        Modelo modelo1 = new Modelo("Modelo 1", fabricante1);
	        Modelo modelo2 = new Modelo("Modelo 2", fabricante1);
	        Modelo modelo3 = new Modelo("Modelo 3", fabricante2);
	        Modelo modelo4 = new Modelo("Modelo 4", fabricante2);

	        // Criando carros
	        Carro carro1 = new Carro("ABC-1234",2000,2000.0, modelo1);
	        Carro carro2 = new Carro("DEF-5678",2000,2000.0, modelo1);
	        Carro carro3 = new Carro("GHI-9012",2000,2000.0, modelo2);
	        Carro carro4 = new Carro("JKL-3456",2000,2000.0, modelo2);
	        Carro carro5 = new Carro("MNO-7890",2000,2000.0, modelo3);
	        Carro carro6 = new Carro("PQR-2345",2000,2000.0, modelo3);
	        Carro carro7 = new Carro("STU-6789",2000,2000.0, modelo4);
	        Carro carro8 = new Carro("VWX-0123",2000,2000.0, modelo4);
	        System.out.println(modelo2);
	        System.out.println(carro1);
	        modelo2.addCarro(carro1);
	        System.out.println("\n");
	        System.out.println(modelo2);
	        System.out.println(carro1);;
	        
	        System.out.println("\n");
	        System.out.println(fabricante1);
	        System.out.println(modelo3);
	        fabricante1.addModelo(modelo3);
	        System.out.println("\n");
	        System.out.println(fabricante1);
	        System.out.println(modelo3);
	    
	        

	}

}
