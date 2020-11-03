import java.io.File;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;

public class XsdNRTHZZ {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		
		File schemaFile = new File("szemelyek.xsd");
		Source xmlFile = new StreamSource(new File("szemelyek.xml"));
		SchemaFactory schemaFactory = SchemaFactory
		    .newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		try {
		  Schema schema = schemaFactory.newSchema(schemaFile);
		  Validator validator = schema.newValidator();
		  validator.validate(xmlFile);
		  System.out.println("XSD Validation Successful");
		} catch (SAXException e) {
		  System.out.println("XSD Validation Unsuccessful. The problem was: " + e);
		} catch (IOException e) {}
	}
}
