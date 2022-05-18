import Recursos.*;
import com.db4o.*;
import com.db4o.query.Query;

public class EjerciciosDB4O {
	public static void main(String[] args) {
		ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(),"congreso.db4o");
	
		insertarCharla(db);
				
		modificarCharla(db);
		
		filtrarCharlasCacheEntre200y300(db);
		
		db.close();
	}
	
	//Primer punto
	private static void insertarCharla(ObjectContainer db) {
		charla ch1 = new charla("Ciberseguridad", 8);
		charla ch2 = new charla("Estudio de la c閘ula", 12);
		ponente ac = new ponente("71351265K", "Antonio Camacho", "antoniocmc@gmail.com", 250);
		
		ch1.setPonente(ac);
		ch2.setPonente(ac);
		
		db.store(ac);
		db.store(ch1);
		db.store(ch2);
	}
	
	//Segundo Punto
	private static void modificarCharla(ObjectContainer db) {
		charla c = new charla(null, 0);
		c.setPonente(new ponente(null, "Antonio Camacho", null));
		ObjectSet res = db.queryByExample(c);
		
		charla ch = (charla) res.next();
		float duracionAnterior = ch.getDuracion();
		c.setDuracion(duracionAnterior + 1);
		db.store(ch);
	}
	
	//Tercer Punto
	public static void filtrarCharlasCacheEntre200y300(ObjectContainer db) {
		Query query = db.query(); //declaraci贸n de un objeto query(). 
	    //establece la clase a la que se aplicar谩 la restricci贸n
	    query.constrain(ponente.class);
	    query.descend("cache").constrain(200).greater();
	    query.descend("cache").constrain(300).smaller();
	    query.descend("pl").constrain(query);//establece la restricci贸n de b煤squeda
	    query.constrain(charla.class);
	    ObjectSet resul = query.execute();//ejecuta consulta con restricci贸n b煤squeda
	    while (resul.hasNext()) { //bucle que recupera los objetos charla y elimina de la BDOO
	      charla c = (charla) resul.next();
	      System.out.println(c);
	    }
	}
	
	public static void mostrarConsulta(ObjectSet resul) {
	    //mensaje indicando el total de objetos recuperados
	    System.out.println("Recuperados " + resul.size() + " Objetos");
	    while (resul.hasNext()) {//bucle que obtiene objeto a objeto
	      System.out.println(resul.next());
	    }
	}
}
