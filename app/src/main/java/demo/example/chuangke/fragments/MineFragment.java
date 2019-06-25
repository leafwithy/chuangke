package demo.example.chuangke.fragments;

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


import demo.example.chuangke.R;
import demo.example.chuangke.activity.Activity_mine_btn.MineAttentionActivity;
import demo.example.chuangke.activity.Activity_mine_btn.MineCollectActivity;
import demo.example.chuangke.activity.Activity_mine_btn.MineHelpActivity;
import demo.example.chuangke.activity.Activity_mine_btn.MineMessageActivity;
import demo.example.chuangke.activity.Activity_mine_btn.MineNotebookActivity;
import demo.example.chuangke.activity.Activity_mine_btn.MineUserActivity;
import demo.example.chuangke.activity.LoginActivity;
import demo.example.chuangke.util.UserUitl;
import demo.example.chuangke.view.SettingsItemView;

public class MineFragment extends Fragment implements View.OnClickListener {

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
        //本碎片视图
        View view = inflater.inflate(R.layout.mine, container, false);
        mSettingsTv = view.findViewById(R.id.tv_mine_settings);
        mNameItemLl = view.findViewById(R.id.ll_name_item);
        mNameTv =  view.findViewById(R.id.tv_mine_name);
        mNoteSiv = view.findViewById(R.id.siv_note_item);
        mCollectionSiv = view.findViewById(R.id.siv_collection_item);
        mAttentionLSiv =  view.findViewById(R.id.siv_attention_item);
        mHelpSiv = view.findViewById(R.id.siv_help_item);
        mMessageSiv = view.findViewById(R.id.siv_message_item);
        mEditBt = view.findViewById(R.id.btn_mine_exit);
        initData();
        initView();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    //初始化控件
    private void initView(){
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
        if(context!=null) {
            switch (view.getId()) {
                case R.id.btn_mine_exit://点击退出登录按钮
                    LoginActivity.actionStart(context, null, null);
                    break;
                case R.id.tv_mine_settings:
                    //点击 设置
                    break;
                case R.id.ll_name_item:
                    //点击 用户名子项
                    MineUserActivity.actionStart(context);
                    //Toast.makeText(context, "点击用户名子项", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.siv_note_item:
                    //点击 笔记本子项
                    MineNotebookActivity.actionStart(context);
                    //Toast.makeText(context, "点击笔记本子项", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.siv_collection_item:
                    //点击 收藏子项
                    MineCollectActivity.actionStart(context);
                    //Toast.makeText(context, "点击收藏子项", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.siv_attention_item:
                    //点击 关注子项
                    MineAttentionActivity.actionStart(context);
                    //Toast.makeText(context, "点击关注子项", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.siv_help_item:
                    //点击 帮助子项
                    MineHelpActivity.actionStart(context);
                    //Toast.makeText(context, "点击帮助子项", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.siv_message_item:
                    //点击 消息子项
                    MineMessageActivity.actionStart(context);
                    //Toast.makeText(context, "点击消息子项", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }

}
