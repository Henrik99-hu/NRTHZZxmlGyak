package hu.meiit.xpathquerynrthzz;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XpathQueryNRTHZZ {

	public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, XPathExpressionException {
        
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse("studentNRTHZZ.xml");

        XPathFactory xpathfactory = XPathFactory.newInstance();
        XPath xpath = xpathfactory.newXPath();

        System.out.println("n//7) Get id 593");

        XPathExpression firstNameExpr = xpath.compile("//student[@rollno=593]/firstname/text()");
        XPathExpression lastNameExpr = xpath.compile("//student[@rollno=593]/lastname/text()");
        XPathExpression nickNameExpr = xpath.compile("//student[@rollno=593]/nickname/text()");
        XPathExpression marksExpr = xpath.compile("//student[@rollno=593]/marks/text()");

        Object firstNameResult = firstNameExpr.evaluate(doc, XPathConstants.NODESET);
        Object lastNameResult = lastNameExpr.evaluate(doc, XPathConstants.NODESET);
        Object nickNameResult = nickNameExpr.evaluate(doc, XPathConstants.NODESET);
        Object marksResult = marksExpr.evaluate(doc, XPathConstants.NODESET);

        NodeList firstNameNodes = (NodeList) firstNameResult;
        NodeList lastNameNodes = (NodeList) lastNameResult;
        NodeList nickNodes = (NodeList) nickNameResult;
        NodeList marksNodes = (NodeList) marksResult;

        for (int i = 0; i < firstNameNodes.getLength(); i++) {
            System.out.println( "firstname: " +firstNameNodes.item(i).getNodeValue());
            System.out.println("lastname: " +lastNameNodes.item(i).getNodeValue());
            System.out.println( "nickname: " + nickNodes.item(i).getNodeValue());
            System.out.println( "marks: " + marksNodes.item(i).getNodeValue());

        }

	}

}
