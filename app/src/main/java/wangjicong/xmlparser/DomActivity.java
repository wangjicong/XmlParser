package wangjicong.xmlparser;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import java.io.InputStream;
import java.util.List;

public class DomActivity extends BaseActivity {

    private static final String TAG = DomActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public List<Center> readXml(InputStream mFile) {
        try {
            List<Center> data = new DomParser().parse(mFile);
            Log.d(TAG,new DomParser().serialize(data));
            return data;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
