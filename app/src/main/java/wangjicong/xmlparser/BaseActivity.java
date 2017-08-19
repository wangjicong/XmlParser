package wangjicong.xmlparser;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/26 0026.
 */
public abstract class BaseActivity extends Activity {
    private static final String TAG = BaseActivity.class.getName();
    private List<Center> mCenter = new ArrayList<>();
    private InputStream mFile;
    private HandlerThread mThread;
    private MyHandler mHandler;
    private ListView mListView;
    private MyAdapter myAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sax);
        initUI();
        threadInit();
    }

    private void initUI() {
        mListView = (ListView)findViewById(R.id.list);
        mListView.setEmptyView(getLayoutInflater().inflate(R.layout.empty,null));
        myAdapter = new MyAdapter(this);
    }

    private void threadInit() {
        try {
            mFile = getResources().getAssets().open("note.xml");
            if (mFile != null) {
                Log.d(TAG, "file is exist");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        mThread = new HandlerThread("sax");
        mThread.start();
        mHandler = new MyHandler(mThread.getLooper());
        if (mFile != null) {
            mHandler.sendEmptyMessage(0);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mThread.isInterrupted()){
            mThread.quit();
        }
    }

    private class MyHandler extends Handler {


        public MyHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    mCenter = readXml(mFile);//SaxParser.readXml(mFile);
                    for (Center center : mCenter) {
                        Log.d(TAG, "name : " + center.getName() + " numbers : " + center.getNumbers() + " address : " + center.getAddress());
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            updateList();
                        }
                    });
                    break;
                default:
                    break;
            }
        }
    }

    private void updateList() {
        myAdapter.setCenters(mCenter);
        findViewById(R.id.title).setVisibility(View.VISIBLE);
        mListView.setAdapter(myAdapter);
    }

    public abstract List<Center> readXml(InputStream mFile);
}
