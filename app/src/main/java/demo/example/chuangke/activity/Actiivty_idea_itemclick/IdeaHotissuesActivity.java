package demo.example.chuangke.activity.Actiivty_idea_itemclick;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import demo.example.chuangke.R;

public class IdeaHotissuesActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.idea_hotissue_click);
    }
    public static void actionStart(Context context){
        context.startActivity(new Intent(context,IdeaHotissuesActivity.class));
    }
}
