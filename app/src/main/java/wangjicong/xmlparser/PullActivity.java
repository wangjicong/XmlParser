package wangjicong.xmlparser;

import android.util.Log;

import java.io.InputStream;
import java.util.List;

/**
 * Created by Administrator on 2017/8/16.
*/
public class PullActivity extends BaseActivity {
    private static final String TAG = PullActivity.class.getName();

    @Override
    public List<Center> readXml(InputStream mFile) {
        try {
            List<Center> data = new PullParser().parse(mFile);
            Log.d(TAG,new PullParser().serialize(data));
            return data;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
