package hu.domparse.nrthzz;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DOMModifyNRTHZZ {
	public static void main(String args[]) throws ParserConfigurationException, IOException, SAXException, TransformerException {
	
		String filePath = "XMLNRTHZZ.xml";
		File xmlFile = new File(filePath);
		
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			//XML bet�lt�se �s parseol�sa
			Document doc = dBuilder.parse(xmlFile);
			
			doc.getDocumentElement().normalize();
			
			//Elem m�dos�t�sa
			mutetIdejenekModositasa(doc);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//F�ggv�ny a m�dos�tand� m�t� idj�nek beolvas�s�ra
	public static String idBeolvasas() {
		Scanner sc = new Scanner(System.in);
		System.out.print("M�t� id:");
		String id = sc.nextLine();
		return id;
	}
	
	//F�ggv�ny, amely l�trehozza az �j filet
	public static void xmlFileIras(Document doc) throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File("src/hu/domparse/nrthzz/XMLNRTHZZ.updated.xml"));
        transformer.transform(source, result);
	}
	
	//F�ggv�ny amely megteszi a m�dos�t�st a felhaszn�l� �ltal megadott id�pontra
	//Megh�vja az xmlFileIras f�ggv�nyt, amellyel l�trehoz egy �j f�jlt
	public static void mutetIdejenekModositasa(Document doc) throws TransformerException {
		
		//El�sz�r beolvassuk az ID-t
		System.out.println("Melyik m�t�ben v�ltozik a m�t�s ideje?");
		String beolvasottId = idBeolvasas();

		//Majd a m�t�t �j idej�t
		Scanner sc = new Scanner(System.in);
		System.out.print("M�t�t �j ideje: ");
		String mutetIdeje = sc.nextLine();

		//Megkeress�k azt a nodeot, aminek az ID-je egyezik a user �ltal megadott idvel
		NodeList nList = doc.getElementsByTagName("muto");
		for (int i = 0; i < nList.getLength(); i++) {

			Node nNode = nList.item(i);

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				Element element = (Element) nNode;

				String mutoId = element.getAttribute("mutoId");

				if (mutoId.equals(beolvasottId)) {

					Node node1 = element.getElementsByTagName("mutetIdeje").item(0);
					node1.setTextContent(mutetIdeje);

					System.out.println("Sikeres Modos�tas");
					//Meg�rjuk az �j XML filet
					xmlFileIras(doc);
				}
			}
		}
	}
}
