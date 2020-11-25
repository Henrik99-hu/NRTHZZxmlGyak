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
			System.out.println("Gy�k�r elem :" + doc.getDocumentElement().getNodeName());
			System.out.println("----------------------------");
			
			NodeList nList = doc.getElementsByTagName("muto");
			for (int i = 0; i < nList.getLength(); i++) {
				Node nNode = nList.item(i);
				System.out.println("\nJelen elem:" + nNode.getNodeName());
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					System.out.println("M�t� id : " + eElement.getAttribute("mutoId"));
					System.out.println("M�t�doktor: " + eElement.getElementsByTagName("mutodoktor").item(0).getTextContent());
					System.out.println("M�t�t ideje: " + eElement.getElementsByTagName("mutetIdeje").item(0).getTextContent());
					System.out.println("P�ciens neve: " + eElement.getElementsByTagName("paciensNeve").item(0).getTextContent());
					System.out.println("M�t�t oka: "+ eElement.getElementsByTagName("mutesOka").item(0).getTextContent());
				}
			}
			
			nList = doc.getElementsByTagName("doktor");
			for (int i = 0; i < nList.getLength(); i++) {
				Node nNode = nList.item(i);
				System.out.println("\nJelen elem:" + nNode.getNodeName());
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					System.out.println("Doktor id : " + eElement.getAttribute("dolgozoId"));
					System.out.println("M�t� id : " + eElement.getAttribute("mutoId"));
					System.out.println("N�v: " + eElement.getElementsByTagName("nev").item(0).getTextContent());
					System.out.println("Telefonsz�m: " + eElement.getElementsByTagName("telefonszam").item(0).getTextContent());
					System.out.println("Csatlakoz�s �ve: " + eElement.getElementsByTagName("csatlakozasEve").item(0).getTextContent());
					//Opcion�lis, ez�rt amiatt, hogy ne dobjon null �rt�k hib�t, el�re ellen�rizz�k.
					//Ha nem null a mez�, akkor ki�rjuk
					//Ha null, akkor pedig jelezz�k, hogy neki nincs ilyenje (jelen esetben e-mailje
					if(eElement.getElementsByTagName("email").item(0) != null){
						System.out.println("E-mail c�m: "+ eElement.getElementsByTagName("email").item(0).getTextContent());
					}else {
						System.out.println("E-mail c�m: Jelen doktornak nincs e-mail c�me!");
					}
				}
			}
			
			//P�ciensek ki�rat�sa
			nList = doc.getElementsByTagName("paciens");
			for (int i = 0; i < nList.getLength(); i++) {
				Node nNode = nList.item(i);
				System.out.println("\nJelen elem:" + nNode.getNodeName());
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					System.out.println("Beteg id : " + eElement.getAttribute("betegId"));
					System.out.println("Doktor id : " + eElement.getAttribute("dolgozoId"));
					System.out.println("M�t� id : " + eElement.getAttribute("mutoId"));
					System.out.println("N�v: " + eElement.getElementsByTagName("nev").item(0).getTextContent());
					System.out.println("Megbeteged�s ideje: " + eElement.getElementsByTagName("megbetegedesIdeje").item(0).getTextContent());
					System.out.println("Beteg c�me: ");
					System.out.println("Ir�ny�tosz�m: " + eElement.getElementsByTagName("iranyitoszam").item(0).getTextContent());
					System.out.println("V�ros: " + eElement.getElementsByTagName("varos").item(0).getTextContent());
					System.out.println("Utca: " + eElement.getElementsByTagName("utca").item(0).getTextContent());
					System.out.println("H�zsz�m: " + eElement.getElementsByTagName("hazszam").item(0).getTextContent());
					System.out.println("Telefonsz�m: " + eElement.getElementsByTagName("telefonszam").item(0).getTextContent());
					int betegsegTipusokSzama = eElement.getElementsByTagName("betegsegTipusa").getLength();
					//Mivel t�bb �rt�ke is lehet a betegs�g t�pus�nak, ez�rt ki kell �rtani egy for ciklussal az �sszeset
					for(int j = 0; j < betegsegTipusokSzama; j++) {
						System.out.println("Betegs�g t�pusa: "+ eElement.getElementsByTagName("betegsegTipusa").item(j).getTextContent());
					}
					
				}
			}
			//Gy�gyszerek kiirat�sa
			nList = doc.getElementsByTagName("gyogyszer");
			for (int i = 0; i < nList.getLength(); i++) {
				Node nNode = nList.item(i);
				System.out.println("\nJelen elem:" + nNode.getNodeName());
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					System.out.println("Gy�gyszer Id : " + eElement.getAttribute("gyogyszerId"));
					System.out.println("N�v: " + eElement.getElementsByTagName("nev").item(0).getTextContent());
					System.out.println("Betegs�g ellen: " + eElement.getElementsByTagName("betegsegEllen").item(0).getTextContent());
					System.out.println("V�ny n�lk�l: " + eElement.getElementsByTagName("venyNelkul").item(0).getTextContent());
					int allergenAnyagokSzama = doc.getElementsByTagName("allergenAnyagok").getLength();
					//Mivel t�bb �rt�ke is lehet az allerg�n anyagoknak, ez�rt ki kell �rtani egy for ciklussal az �sszeset
					for(int j = 0; j < allergenAnyagokSzama; j++) {
						System.out.println("Allerg�n anyag: "+ eElement.getElementsByTagName("allergenAnyagok").item(j).getTextContent());
					}				
				}
			}
			
			//Szed kapcsol�t�bla kir�rat�sa
			nList = doc.getElementsByTagName("szed");
			for (int i = 0; i < nList.getLength(); i++) {
				Node nNode = nList.item(i);
				System.out.println("\nJelen elem:" + nNode.getNodeName());
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					System.out.println("Beteg id : " + eElement.getAttribute("betegId"));
					System.out.println("Gy�gyszer id : " + eElement.getAttribute("gyogyszerId"));
					System.out.println("Mennyit kell szednie? " + eElement.getElementsByTagName("mennyit").item(0).getTextContent());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
