package demo.example.chuangke.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;


import com.hjm.bottomtabbar.BottomTabBar;

import demo.example.chuangke.fragments.MainFragment;
import demo.example.chuangke.fragments.IdeaFragment;
import demo.example.chuangke.fragments.MineFragment;
import demo.example.chuangke.R;
import demo.example.chuangke.macro.Tabbarresource;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabb_bar);
        BottomTabBar btb = findViewById(R.id.bottomtabbar);
        btb.init(getSupportFragmentManager())
                .setFontSize(8)
                .setChangeColor(Color.BLUE,Color.GRAY)
                .addTabItem(Tabbarresource.BottomItem1, Tabbarresource.BottomItemimage1, MainFragment.class)
                .addTabItem(Tabbarresource.BottomItem2, Tabbarresource.BottomItemimage4, IdeaFragment.class)
                .addTabItem(Tabbarresource.BottomItem3, Tabbarresource.BottomItemimage6, MineFragment.class)
                .isShowDivider(false);

    }

    //MainActivity的启动方法
    public static void actionStart(Context context){
        Intent intent = new Intent(context,MainActivity.class);
        context.startActivity(intent);
    }
}
