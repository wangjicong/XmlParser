package wangjicong.xmlparser;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by Administrator on 2017/6/26 0026.
 */
public class SaxParser {
    private static final String TAG = SaxParser.class.getName();

    public static List<Center> readXml(InputStream inputStream) {
        try {
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            SAXParser saxParser = saxParserFactory.newSAXParser();
            //saxParser.setProperty("http://xml.org/sax/features/namespaces",true);
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
}
