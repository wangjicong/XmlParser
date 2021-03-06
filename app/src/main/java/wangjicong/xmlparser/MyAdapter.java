package wangjicong.xmlparser;

import android.app.Activity;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/27 0027.
 */
public class MyAdapter extends BaseAdapter {
    private static final String TAG = MyAdapter.class.getName();
    private List<Center> mData = new ArrayList<>();
    private Activity mActivity;
    public MyAdapter(Activity activity) {
        mActivity = activity;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int i) {
        return mData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder=null;

        if (view==null){
            View v = mActivity.getLayoutInflater().inflate(R.layout.item,viewGroup,false);
            view = v;
            viewHolder = new ViewHolder();
            viewHolder.setName((TextView)v.findViewById(R.id.name));
            viewHolder.setNumbers((TextView)v.findViewById(R.id.numbers));
            viewHolder.setAddress((TextView)view.findViewById(R.id.address));
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        if (viewHolder!=null){
            viewHolder.update(i);
        }
        return view;
    }

    public void setCenters(List<Center> center) {
        this.mData = center;
    }

    private class ViewHolder{
        private TextView name;
        private TextView numbers;
        private TextView address;

        public TextView getName() {
            return name;
        }

        public void setName(TextView name) {
            this.name = name;
        }

        public TextView getNumbers() {
            return numbers;
        }

        public void setNumbers(TextView numbers) {
            this.numbers = numbers;
        }

        public TextView getAddress() {
            return address;
        }

        public void setAddress(TextView address) {
            this.address = address;
        }

        public void update(int i) {
            name.setText(mData.get(i).getName());
            //numbers.setText(Html.fromHtml(mActivity.getString(R.string.tel)+"<font color=gray>"+mData.get(i).getNumbers()+"</font>"));
            String[] nums = mData.get(i).getNumbers().split(",");
            StringBuffer stringBuffer = new StringBuffer();
            for (int index=0;index<nums.length;index++) {
                if (index>0){
                    stringBuffer.append(",");
                }
                stringBuffer.append("<a href='tel:");
                stringBuffer.append(nums[index]);
                stringBuffer.append("'>");
                //stringBuffer.append("' style='text-decoration: none'>");
                stringBuffer.append(nums[index]);
                stringBuffer.append("</a>");
            }
            numbers.setText(Html.fromHtml(mActivity.getString(R.string.tel)+stringBuffer.toString()));
            numbers.setMovementMethod(LinkMovementMethod.getInstance());
            address.setText(Html.fromHtml(mActivity.getString(R.string.address)+"<font color=gray>"+mData.get(i).getAddress()+"</font>"));
        }
    }
}
