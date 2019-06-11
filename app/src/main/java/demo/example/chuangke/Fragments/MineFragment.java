package demo.example.chuangke.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import demo.example.chuangke.R;
import demo.example.chuangke.activity.LoginActivity;
import demo.example.chuangke.util.UserUitl;
import demo.example.chuangke.view.SettingsItemView;

public class MineFragment extends Fragment implements View.OnClickListener {
    private View mView;                               //本碎片视图

    private TextView mSettingsTv;                    //设置文本框
    private LinearLayout mNameItemLl;                //用户名子项
    private TextView mNameTv;                        //用户名文本框
    private SettingsItemView mNoteSiv;               //笔记本子项
    private SettingsItemView mCollectionSiv;        //收藏子项
    private SettingsItemView mAttentionLSiv;        //关注子项
    private SettingsItemView mHelpSiv;              //帮助子项
    private SettingsItemView mMessageSiv;           //消息子项
    private Button mEditBt;                         //退出登录按钮

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_mine,container,false);
        initView(mView);
        return mView;
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    //初始化控件
    private void initView(View view){
        mSettingsTv = (TextView)view.findViewById(R.id.tv_mine_settings);
        mNameItemLl = (LinearLayout)view.findViewById(R.id.ll_name_item);
        mNameTv = (TextView) view.findViewById(R.id.tv_mine_name);
        mNoteSiv = (SettingsItemView)view.findViewById(R.id.siv_note_item);
        mCollectionSiv = (SettingsItemView) view.findViewById(R.id.siv_collection_item);
        mAttentionLSiv = (SettingsItemView) view.findViewById(R.id.siv_attention_item);
        mHelpSiv = (SettingsItemView)view.findViewById(R.id.siv_help_item);
        mMessageSiv = (SettingsItemView)view.findViewById(R.id.siv_message_item);
        mEditBt = (Button)view.findViewById(R.id.btn_mine_exit);


        mSettingsTv.setOnClickListener(this);
        mNameItemLl.setOnClickListener(this);
        mNoteSiv.setOnClickListener(this);
        mCollectionSiv.setOnClickListener(this);
        mAttentionLSiv.setOnClickListener(this);
        mHelpSiv.setOnClickListener(this);
        mMessageSiv.setOnClickListener(this);
        mEditBt.setOnClickListener(this);
    }

    //初始化控件View数据
    private void initData(){
        mNameTv.setText(UserUitl.name);
        //TODO：根据UserUitl.uid联网查询用户头像
    }

    //点击事件的回调
    @Override
    public void onClick(View view) {
        Context context = getContext();
        switch (view.getId()){
            case R.id.btn_mine_exit://点击退出登录按钮
                LoginActivity.actionStart(context,null,null);
                break;
            case R.id.tv_mine_settings:
                //点击 设置
                break;
            case R.id.ll_name_item:
                //点击 用户名子项
                Toast.makeText(context, "点击用户名子项", Toast.LENGTH_SHORT).show();
                break;
            case R.id.siv_note_item:
                //点击 笔记本子项
                Toast.makeText(context, "点击笔记本子项", Toast.LENGTH_SHORT).show();
                break;
            case R.id.siv_collection_item:
                //点击 收藏子项
                Toast.makeText(context, "点击收藏子项", Toast.LENGTH_SHORT).show();
                break;
            case R.id.siv_attention_item:
                //点击 关注子项
                Toast.makeText(context, "点击关注子项", Toast.LENGTH_SHORT).show();
                break;
            case R.id.siv_help_item:
                //点击 帮助子项
                Toast.makeText(context, "点击帮助子项", Toast.LENGTH_SHORT).show();
                break;
            case R.id.siv_message_item:
                //点击 消息子项
                Toast.makeText(context, "点击消息子项", Toast.LENGTH_SHORT).show();
                break;
        }
    }

}
