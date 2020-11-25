package hu.domparse.nrthzz;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import java.io.File;
import java.io.IOException;

public class DOMReadNRTHZZ {

	public static void main(String args[]) throws ParserConfigurationException, IOException, SAXException, TransformerException {
		try {
			File inputFile = new File("XMLNRTHZZ.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();
			System.out.println("Gyökér elem :" + doc.getDocumentElement().getNodeName());
			System.out.println("----------------------------");
			
			NodeList nList = doc.getElementsByTagName("muto");
			for (int i = 0; i < nList.getLength(); i++) {
				Node nNode = nList.item(i);
				System.out.println("\nJelen elem:" + nNode.getNodeName());
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					System.out.println("Mûtõ id : " + eElement.getAttribute("mutoId"));
					System.out.println("Mûtõdoktor: " + eElement.getElementsByTagName("mutodoktor").item(0).getTextContent());
					System.out.println("Mûtét ideje: " + eElement.getElementsByTagName("mutetIdeje").item(0).getTextContent());
					System.out.println("Páciens neve: " + eElement.getElementsByTagName("paciensNeve").item(0).getTextContent());
					System.out.println("Mûtét oka: "+ eElement.getElementsByTagName("mutesOka").item(0).getTextContent());
				}
			}
			
			nList = doc.getElementsByTagName("doktor");
			for (int i = 0; i < nList.getLength(); i++) {
				Node nNode = nList.item(i);
				System.out.println("\nJelen elem:" + nNode.getNodeName());
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					System.out.println("Doktor id : " + eElement.getAttribute("dolgozoId"));
					System.out.println("Mûtõ id : " + eElement.getAttribute("mutoId"));
					System.out.println("Név: " + eElement.getElementsByTagName("nev").item(0).getTextContent());
					System.out.println("Telefonszám: " + eElement.getElementsByTagName("telefonszam").item(0).getTextContent());
					System.out.println("Csatlakozás éve: " + eElement.getElementsByTagName("csatlakozasEve").item(0).getTextContent());
					//Opcionális, ezért amiatt, hogy ne dobjon null érték hibát, elõre ellenõrizzük.
					//Ha nem null a mezõ, akkor kiírjuk
					//Ha null, akkor pedig jelezzük, hogy neki nincs ilyenje (jelen esetben e-mailje
					if(eElement.getElementsByTagName("email").item(0) != null){
						System.out.println("E-mail cím: "+ eElement.getElementsByTagName("email").item(0).getTextContent());
					}else {
						System.out.println("E-mail cím: Jelen doktornak nincs e-mail címe!");
					}
				}
			}
			
			//Páciensek kiíratása
			nList = doc.getElementsByTagName("paciens");
			for (int i = 0; i < nList.getLength(); i++) {
				Node nNode = nList.item(i);
				System.out.println("\nJelen elem:" + nNode.getNodeName());
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					System.out.println("Beteg id : " + eElement.getAttribute("betegId"));
					System.out.println("Doktor id : " + eElement.getAttribute("dolgozoId"));
					System.out.println("Mûtõ id : " + eElement.getAttribute("mutoId"));
					System.out.println("Név: " + eElement.getElementsByTagName("nev").item(0).getTextContent());
					System.out.println("Megbetegedés ideje: " + eElement.getElementsByTagName("megbetegedesIdeje").item(0).getTextContent());
					System.out.println("Beteg címe: ");
					System.out.println("Irányítoszám: " + eElement.getElementsByTagName("iranyitoszam").item(0).getTextContent());
					System.out.println("Város: " + eElement.getElementsByTagName("varos").item(0).getTextContent());
					System.out.println("Utca: " + eElement.getElementsByTagName("utca").item(0).getTextContent());
					System.out.println("Házszám: " + eElement.getElementsByTagName("hazszam").item(0).getTextContent());
					System.out.println("Telefonszám: " + eElement.getElementsByTagName("telefonszam").item(0).getTextContent());
					int betegsegTipusokSzama = eElement.getElementsByTagName("betegsegTipusa").getLength();
					//Mivel több értéke is lehet a betegség típusának, ezért ki kell írtani egy for ciklussal az összeset
					for(int j = 0; j < betegsegTipusokSzama; j++) {
						System.out.println("Betegség típusa: "+ eElement.getElementsByTagName("betegsegTipusa").item(j).getTextContent());
					}
					
				}
			}
			//Gyógyszerek kiiratása
			nList = doc.getElementsByTagName("gyogyszer");
			for (int i = 0; i < nList.getLength(); i++) {
				Node nNode = nList.item(i);
				System.out.println("\nJelen elem:" + nNode.getNodeName());
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					System.out.println("Gyógyszer Id : " + eElement.getAttribute("gyogyszerId"));
					System.out.println("Név: " + eElement.getElementsByTagName("nev").item(0).getTextContent());
					System.out.println("Betegség ellen: " + eElement.getElementsByTagName("betegsegEllen").item(0).getTextContent());
					System.out.println("Vény nélkül: " + eElement.getElementsByTagName("venyNelkul").item(0).getTextContent());
					int allergenAnyagokSzama = doc.getElementsByTagName("allergenAnyagok").getLength();
					//Mivel több értéke is lehet az allergén anyagoknak, ezért ki kell írtani egy for ciklussal az összeset
					for(int j = 0; j < allergenAnyagokSzama; j++) {
						System.out.println("Allergén anyag: "+ eElement.getElementsByTagName("allergenAnyagok").item(j).getTextContent());
					}				
				}
			}
			
			//Szed kapcsolótábla kiríratása
			nList = doc.getElementsByTagName("szed");
			for (int i = 0; i < nList.getLength(); i++) {
				Node nNode = nList.item(i);
				System.out.println("\nJelen elem:" + nNode.getNodeName());
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					System.out.println("Beteg id : " + eElement.getAttribute("betegId"));
					System.out.println("Gyógyszer id : " + eElement.getAttribute("gyogyszerId"));
					System.out.println("Mennyit kell szednie? " + eElement.getElementsByTagName("mennyit").item(0).getTextContent());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
