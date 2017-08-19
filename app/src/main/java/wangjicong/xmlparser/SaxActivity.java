package wangjicong.xmlparser;

import android.os.Bundle;
import android.util.Log;

import java.io.InputStream;
import java.util.List;

/**
 * Created by Administrator on 2017/8/16.
 */
public class SaxActivity extends BaseActivity {

    private static final String TAG = SaxActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public List<Center> readXml(InputStream mFile) {
        try {
            List<Center> list = new SaxParser().parse(mFile);
            Log.d(TAG,new SaxParser().serialize(list));
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
