package demo.example.chuangke.activity.Activity_mine_btn;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import demo.example.chuangke.R;

public class MineMessageActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_message);
    }
    /**
     * 跳转到目标活动
     * @param context 当前活动
     */
    public static void actionStart(Context context){
        context.startActivity(new Intent(context,MineMessageActivity.class));
    }
}
