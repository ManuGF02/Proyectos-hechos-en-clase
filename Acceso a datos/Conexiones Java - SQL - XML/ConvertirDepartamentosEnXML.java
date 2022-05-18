import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import java.io.*;
import java.util.ArrayList;


public class ConvertirDepartamentosEnXML {
	public static void main(String[] args) throws IOException{
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		LeerYEscribirDepartamentoSecuencial datos = new LeerYEscribirDepartamentoSecuencial();
		
		try {
			
			
			DocumentBuilder builder = factory.newDocumentBuilder();
			DOMImplementation implementation = builder.getDOMImplementation();
			Document document = implementation.createDocument(null, "Departamentos", null);
			document.setXmlVersion("1.0");
			
			ArrayList<Departamento> departamentos = datos.listaDepartamentos;
			
			//Creo el elemento Raíz 
			Element raiz = document.createElement("Departamento");
			document.getDocumentElement().appendChild(raiz);
			
			
			for(Departamento dpto: departamentos) {
				//Añado Núm de Departamentoç
				crearElemento("Numero", Integer.toString(dpto.getNumero()), raiz, document);
				//Añado Nombre
				crearElemento("Nombre", dpto.getNombre(), raiz, document);
				//Añado Localización
				crearElemento("Localizacion", dpto.getLocalizacion(), raiz, document);
				//Añado presupuesto
				crearElemento("Presupuesto", Integer.toString(dpto.getPresupuesto()), raiz, document);
			}
			
			Source source = new DOMSource(document);
			Result result = new StreamResult(new java.io.File("Departamentos.xml"));
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.transform(source, result);
			
		}catch(Exception e) {
			System.err.println("ERROR");
			e.printStackTrace();
		}
	}
	
	static void crearElemento(String nombre, String valor, Element padre, Document documento) {
		Element elem = documento.createElement(nombre);
		Text text = documento.createTextNode(valor);
		padre.appendChild(elem);
		elem.appendChild(text);
	}
}
