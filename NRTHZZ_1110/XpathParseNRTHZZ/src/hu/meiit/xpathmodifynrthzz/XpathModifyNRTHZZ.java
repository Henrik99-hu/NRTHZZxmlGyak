package hu.meiit.xpathmodifynrthzz;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.IOException;

public class XpathModifyNRTHZZ {

	public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, XPathExpressionException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse("studentNRTHZZ.xml");

        XPathFactory xpathfactory = XPathFactory.newInstance();
        XPath xpath = xpathfactory.newXPath();

        System.out.println("n//7) Get id 393");

        XPathExpression firstNameExpr = xpath.compile("//student[@rollno=393]/firstname/text()");
        XPathExpression lastNameExpr = xpath.compile("//student[@rollno=393]/lastname/text()");
        XPathExpression nickNameExpr = xpath.compile("//student[@rollno=393]/nickname/text()");
        XPathExpression marksExpr = xpath.compile("//student[@rollno=393]/marks/text()");

        Object firstNameResult = firstNameExpr.evaluate(doc, XPathConstants.NODESET);
        Object lastNameResult = lastNameExpr.evaluate(doc, XPathConstants.NODESET);
        Object nickNameResult = nickNameExpr.evaluate(doc, XPathConstants.NODESET);
        Object marksResult = marksExpr.evaluate(doc, XPathConstants.NODESET);

        NodeList firstNameNodes = (NodeList) firstNameResult;
        NodeList lastNameNodes = (NodeList) lastNameResult;
        NodeList nickNodes = (NodeList) nickNameResult;
        NodeList marksNodes = (NodeList) marksResult;
        
        lastNameNodes.item(0).setNodeValue("Zöld");

        for (int i = 0; i < firstNameNodes.getLength(); i++) {
            System.out.println( "firstname: " +firstNameNodes.item(i).getNodeValue());
            System.out.println("lastname: " +lastNameNodes.item(i).getNodeValue());
            System.out.println( "nickname: " + nickNodes.item(i).getNodeValue());
            System.out.println( "marks: " + marksNodes.item(i).getNodeValue());

        }
		
	}

}
