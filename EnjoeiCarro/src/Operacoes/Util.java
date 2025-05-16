package Operacoes;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.config.EmbeddedConfiguration;

import Classes.Fabricante;
import Classes.Modelo;
import Classes.Carro;



public class Util {
	private static ObjectContainer manager;
	public static ObjectContainer conectarBanco(){
		if (manager != null)
			return manager;		

		EmbeddedConfiguration config =  Db4oEmbedded.newConfiguration(); 
		config.common().messageLevel(0); 
		
		config.common().objectClass(Fabricante.class).cascadeOnDelete(false);;
		config.common().objectClass(Fabricante.class).cascadeOnUpdate(true);;
		config.common().objectClass(Fabricante.class).cascadeOnActivate(true);
		config.common().objectClass(Modelo.class).cascadeOnDelete(false);;
		config.common().objectClass(Modelo.class).cascadeOnUpdate(true);;
		config.common().objectClass(Modelo.class).cascadeOnActivate(true);
		config.common().objectClass(Carro.class).cascadeOnDelete(false);;
		config.common().objectClass(Carro.class).cascadeOnUpdate(true);;
		config.common().objectClass(Carro.class).cascadeOnActivate(true);
		
		manager = Db4oEmbedded.openFile(config, "banco.db4o");
		return manager;
	}
	
	public static void desconectar() {
		if(manager!=null) {
			manager.close();
			manager=null;
		}
	}
}