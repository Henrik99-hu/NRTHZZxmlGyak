import java.io.File;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SaxNRTHZZ {
	
public static void main(String[] args) {
		
		try {
	         File inputFile = new File("szemelyek.xml");
	         SAXParserFactory factory = SAXParserFactory.newInstance();
	         SAXParser saxParser = factory.newSAXParser();
	         UserHandler userhandler = new UserHandler();
	         saxParser.parse(inputFile, userhandler);     
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	}
	
	

}

class UserHandler extends DefaultHandler {

	   boolean bName = false;
	   boolean bAge = false;
	   boolean bCity = false;
	   boolean bpersons = false;

	   
	   public void startElement(
	      String uri, String localName, String qName, Attributes attributes)
	      throws SAXException {
	      
	      if (qName.equalsIgnoreCase("szemely")) {
	         String id = attributes.getValue("id");
	         System.out.println("szemely, [id : " + id+"] start");
	         
	      } else if (qName.equalsIgnoreCase("nev")) {
	         bName = true;
	         System.out.println("nev start");
	      } else if (qName.equalsIgnoreCase("kor")) {
	         bAge = true;
	         System.out.println("kor start");
	      } else if (qName.equalsIgnoreCase("varos")) {
	         bCity = true;
	         System.out.println("varos start");
	      } else if (qName.equalsIgnoreCase("szemelyek")) {
	    	  bpersons = true;
	    	  System.out.println("szemelyek start");
	      }
	      
	   }

	   
	   public void endElement(String uri, 
	      String localName, String qName) throws SAXException {
	      
	      if (qName.equalsIgnoreCase("szemely")) {
	         System.out.println("szemely end");
	      }else if (qName.equalsIgnoreCase("nev")) {
	    	  System.out.println("nev end");
	      }else if (qName.equalsIgnoreCase("kor")) {
	    	  System.out.println("kor end");
	      }else if (qName.equalsIgnoreCase("varos")) {
	    	  System.out.println("varos end");
	      }else if (qName.equalsIgnoreCase("szemelyek")) {
	    	  System.out.println("szemelyek end");
	      }
	      
	   }

	   
	   public void characters(char ch[], int start, int length) throws SAXException {

	      if (bName) {
	         System.out.println( new String(ch, start, length) );
	         bName = false;
	       
	      } else if (bAge) {
	         System.out.println( new String(ch, start, length));
	         bAge = false;
	      } else if (bCity) {
	         System.out.println(  new String(ch, start, length));
	         bCity = false;
	      }
	   }
}