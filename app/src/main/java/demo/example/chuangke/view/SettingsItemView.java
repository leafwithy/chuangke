package demo.example.chuangke.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import demo.example.chuangke.R;

/**
 * 自定义控件：设置界面("我的"界面)的子项
 */
public class SettingsItemView extends ConstraintLayout {
    private ImageView themeIv;
    private TextView themeTv;
    private View bottomLineView;
    private View itemView;
    private SettingsItemViewClickListener mListener;

    //构造器
    public SettingsItemView(Context context, AttributeSet attrs){
        super(context,attrs);
        initView(context,attrs);
        initData(context, attrs);
    }
    //初始化View的实例、监听器等
    private void initView(Context context,AttributeSet attrs){
        itemView = LayoutInflater.from(context).inflate(R.layout.item_settings,this);
        themeIv = (ImageView)findViewById(R.id.iv_theme);
        themeTv = (TextView)findViewById(R.id.tv_theme);
        bottomLineView = (View)findViewById(R.id.view_bottom_line);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null)
                    mListener.click();
            }
        });
    }
    //初始化View的数据
    private void initData(Context context,AttributeSet attrs){
        //将我们在atts.xml定义的<declare-styleable>的所有属性存储在TypedArray中
        TypedArray ta = context.obtainStyledAttributes(attrs,R.styleable.SettingsItemView);
        //themeIv.setBackgroundResource(ta.getResourceId(R.styleable.SettingsItemView_themeIcon,0));
        //themeIv.setBackground(ta.getDrawable(R.styleable.SettingsItemView_themeIcon));    //好像因为版本问题不能用了
        themeIv.setImageDrawable(ta.getDrawable(R.styleable.SettingsItemView_themeIcon));
        themeTv.setText(ta.getString(R.styleable.SettingsItemView_titleText));
        Boolean isBottomLine = ta.getBoolean(R.styleable.SettingsItemView_isBottomLine,false);
        bottomLineView.setVisibility(isBottomLine ? View.VISIBLE : View.INVISIBLE);
        ta.recycle();       //回收TypedArray资源，避免重新创建时的错误
    }

    //定义SettingsItemView的点击接口
    public interface SettingsItemViewClickListener{
        void click();
    }

    //提供给外界的“点击事件”接口
    public void setOnSettingsItemViewClickListener(SettingsItemViewClickListener listener){
        mListener = listener;
    }

}
