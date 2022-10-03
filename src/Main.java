import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class Main {

    public static void main(String[] args) {
        /*obtener el promedio de la cilindrada*/

        File file = new File("src/concesionario.xml");
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document documento = (Document) dBuilder.parse(file);

            documento.getDocumentElement().normalize();

            NodeList listaCilindrada = documento.getElementsByTagName("coche");

            calcularPromedioCilindrada(listaCilindrada);

        }catch (Exception e){
            System.out.println("Error inesperado");
            e.printStackTrace();
        }
    }

    private static void calcularPromedioCilindrada(NodeList listaCilindrada) {
        double totalCilindrada = 0;
        for (int i = 0; i < listaCilindrada.getLength(); i++) {
            Node nodo = listaCilindrada.item(i);

            if (nodo.getNodeType() == Node.ELEMENT_NODE){
                Element elemento = (Element) nodo;
                //elemento.getAttribute("id");
                totalCilindrada += Double.parseDouble(elemento.getElementsByTagName("cilindrada").item(0).getTextContent());
            }
        }

        System.out.println("Promedio cilindrada: "+totalCilindrada/ listaCilindrada.getLength());
    }
}