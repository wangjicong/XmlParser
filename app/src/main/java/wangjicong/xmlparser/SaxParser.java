package wangjicong.xmlparser;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;

/**
 * Created by Administrator on 2017/6/26 0026.
 */
public class SaxParser implements BaseParser<Center> {
    private static final String TAG = SaxParser.class.getName();

    @Override
    public List<Center> parse(InputStream inputStream) throws Exception {
        try {
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            SAXParser saxParser = saxParserFactory.newSAXParser();
            XMLContentHandler handler = new XMLContentHandler();
            saxParser.parse(inputStream, handler);
            inputStream.close();
            return handler.getCenter();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String serialize(List<Center> data) throws Exception {
        try {
            SAXTransformerFactory factory = (SAXTransformerFactory) TransformerFactory.newInstance();
            TransformerHandler handler = factory.newTransformerHandler();
            Transformer transformer = handler.getTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING,"UTF-8");
            transformer.setOutputProperty(OutputKeys.INDENT,"yes");
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION,"no");

            StringWriter stringWriter = new StringWriter();
            Result result = new StreamResult(stringWriter);
            handler.setResult(result);

            String uri="http://namespace.com";
            String localname="";
            handler.startDocument();
            handler.startElement(uri,localname,"resource",null);

            for (Center c:data){
                handler.startElement(uri,localname,Center.CENTER,null);

                handler.startElement(uri,localname,Center.NAME,null);
                char[] ch = c.getName().toCharArray();
                handler.characters(ch,0,ch.length);
                handler.endElement(uri,localname,Center.NAME);

                handler.startElement(uri,localname,Center.ADDRESS,null);
                ch=c.getAddress().toCharArray();
                handler.characters(ch,0,ch.length);
                handler.endElement(uri,localname,Center.ADDRESS);

                handler.startElement(uri,localname,Center.NUMBERS,null);
                ch=c.getName().toCharArray();
                handler.characters(ch,0,ch.length);
                handler.endElement(uri,localname,Center.NUMBERS);

                handler.endElement(uri,localname,Center.CENTER);
            }
            handler.endElement(uri,localname,"resource");
            handler.endDocument();
            return stringWriter.toString();
        } catch (TransformerFactoryConfigurationError transformerFactoryConfigurationError) {
            transformerFactoryConfigurationError.printStackTrace();
        }
        return null;
    }
}
