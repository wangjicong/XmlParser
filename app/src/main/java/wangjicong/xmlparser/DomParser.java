package wangjicong.xmlparser;

import android.database.sqlite.SQLiteCantOpenDatabaseException;
import android.nfc.cardemulation.CardEmulation;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

/**
 * Created by Administrator on 2017/8/16.
 */
public class DomParser implements BaseParser<Center> {
    @Override
    public List<Center> parse(InputStream inputStream) throws Exception {
        List<Center> result = new ArrayList<>();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(inputStream);
        Element element = doc.getDocumentElement();
        NodeList list = element.getElementsByTagName(Center.CENTER);
        for (int i=0;i<list.getLength();i++){
            Center center = new Center();
            Node node = list.item(i);
            NodeList properties = node.getChildNodes();
            for (int j=0;j<properties.getLength();j++){
                Node property = properties.item(j);
                String nodename = property.getNodeName();
                if (nodename.equals(Center.NAME)){
                    center.setName(property.getFirstChild().getNodeValue());
                } else if (nodename.equals(Center.ADDRESS)){
                    center.setAddress(property.getFirstChild().getNodeValue());
                } else if (nodename.equals(Center.NUMBERS)){
                    center.setNumbers(property.getFirstChild().getNodeValue());
                }
            }
            result.add(center);
        }
        return result;
    }

    @Override
    public String serialize(List<Center> data) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.newDocument();

        Element element = doc.createElement("resource");
        element.setAttribute("id","1");

        for (Center c:data){
            Element center = doc.createElement(Center.CENTER);

            Element name = doc.createElement(Center.NAME);
            name.setTextContent(c.getName());
            center.appendChild(name);

            Element address = doc.createElement(Center.ADDRESS);
            address.setTextContent(c.getAddress());
            center.appendChild(address);

            Element number = doc.createElement(Center.NUMBERS);
            number.setTextContent(c.getNumbers());
            center.appendChild(number);

            element.appendChild(center);
        }
        doc.appendChild(element);

        TransformerFactory factory1 = TransformerFactory.newInstance();
        Transformer transformer = factory1.newTransformer();
        transformer.setOutputProperty(OutputKeys.ENCODING,"UTF-8");
        transformer.setOutputProperty(OutputKeys.INDENT,"yes");
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION,"no");

        StringWriter stringWriter = new StringWriter();
        Source source = new DOMSource(doc);
        Result result = new StreamResult(stringWriter);
        transformer.transform(source,result);

        return stringWriter.toString();
    }
}
