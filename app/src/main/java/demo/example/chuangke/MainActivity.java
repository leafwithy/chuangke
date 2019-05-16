package demo.example.chuangke;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.hjm.bottomtabbar.BottomTabBar;

import demo.example.chuangke.Fragments.FragmentPerson;
import demo.example.chuangke.Fragments.FragmentShouye;
import demo.example.chuangke.Fragments.Fragmentidea;
import demo.example.chuangke.macro.tabbarresource;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabb_bar);
        BottomTabBar btb = findViewById(R.id.bottomtabbar);
        btb.init(getSupportFragmentManager())
                .setFontSize(8)
                .setChangeColor(Color.BLUE,Color.GRAY)
                .addTabItem(tabbarresource.BottomItem1, tabbarresource.BottomItemimage1, FragmentShouye.class)
                .addTabItem(tabbarresource.BottomItem2, tabbarresource.BottomItemimage4, Fragmentidea.class)
                .addTabItem(tabbarresource.BottomItem3, tabbarresource.BottomItemimage6, FragmentPerson.class)
                .isShowDivider(false);
    }
}
