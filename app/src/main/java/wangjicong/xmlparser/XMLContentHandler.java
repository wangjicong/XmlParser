package wangjicong.xmlparser;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/27 0027.
 */
public class XMLContentHandler extends DefaultHandler {
    private static final String TAG = XMLContentHandler.class.getName();
    private List<Center> mDate;
    private String tagName;
    private Center mCurrent;

    public List<Center> getCenter() {
        if (mDate != null) {
            return mDate;
        }
        return null;
    }

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        mDate = new ArrayList<>();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        // Log.d(TAG,"local name is "+localName + " qName is "+qName);
        if (localName.equals(Center.CENTER)) {
            mCurrent = new Center();
        }
        tagName = localName;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        if (tagName != null) {
            String data = new String(ch, start, length);
            if (length <= 1) {
                return;
            }
            if (tagName.equals(Center.NAME)) {
                mCurrent.setName(data);
                //Log.d(TAG,"NAME IS "+data);
            } else if (tagName.equals(Center.NUMBERS)) {
                mCurrent.setNumbers(data);
                //Log.d(TAG,"NUMBERS IS "+data);
            } else if (tagName.equals(Center.ADDRESS)) {
                mCurrent.setAddress(data);
                // Log.d(TAG,"ADDRESS IS "+data);
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        if (localName.equals(Center.CENTER)) {
            mDate.add(mCurrent);
            //Log.d(TAG,"name : "+mCurrent.getName()+" numbers : "+mCurrent.getNumbers() +" address : "+mCurrent.getAddress());
            mCurrent = null;
        }
        tagName = null;
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }
}
