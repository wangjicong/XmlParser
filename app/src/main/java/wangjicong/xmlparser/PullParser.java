package wangjicong.xmlparser;

import android.util.Xml;

import org.w3c.dom.Document;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlSerializer;

import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/16.
 */
public class PullParser implements BaseParser<Center> {
    @Override
    public List<Center> parse(InputStream inputStream) throws Exception {
        List<Center> result = null;
        Center center = null;

        XmlPullParser parser = Xml.newPullParser();
        parser.setInput(inputStream,"UTF-8");
        int eventType = parser.getEventType();
        while (eventType!= XmlPullParser.END_DOCUMENT){
            switch (eventType){
                case XmlPullParser.START_DOCUMENT:
                    result = new ArrayList<>();
                    break;
                case XmlPullParser.START_TAG:
                    if (parser.getName().equals(Center.CENTER)){
                        center = new Center();
                    }else if (parser.getName().equals(Center.NAME)){
                        eventType = parser.next();
                        center.setName(parser.getText());
                    }else if (parser.getName().equals(Center.ADDRESS)){
                        eventType = parser.next();
                        center.setAddress(parser.getText());
                    }else if (parser.getName().equals(Center.NUMBERS)){
                        eventType=parser.next();
                        center.setNumbers(parser.getText());
                    }
                    break;
                case XmlPullParser.END_TAG:
                    if (parser.getName().equals(Center.CENTER)){
                        result.add(center);
                        center = null;
                    }
                    break;
            }
            eventType = parser.next();
        }
        return result;
    }

    @Override
    public String serialize(List<Center> data) throws Exception {
        XmlSerializer serializer = Xml.newSerializer();
        StringWriter stringWriter = new StringWriter();
        serializer.setOutput(stringWriter);
        serializer.startDocument("UTF-8",true);

        serializer.startTag("","resource");

        for (Center c:data){
            serializer.startTag("",Center.CENTER);

            serializer.startTag("",Center.NAME);
            serializer.text(c.getName());
            serializer.endTag("",Center.NAME);

            serializer.startTag("",Center.ADDRESS);
            serializer.text(c.getAddress());
            serializer.endTag("",Center.ADDRESS);

            serializer.startTag("",Center.NUMBERS);
            serializer.text(c.getNumbers());
            serializer.endTag("",Center.NUMBERS);

            serializer.endTag("",Center.CENTER);
        }
        serializer.endTag("","resource");
        serializer.endDocument();
        return stringWriter.toString();
    }
}
