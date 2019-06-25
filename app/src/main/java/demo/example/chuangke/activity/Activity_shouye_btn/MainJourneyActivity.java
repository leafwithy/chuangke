package demo.example.chuangke.activity.Activity_shouye_btn;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import demo.example.chuangke.R;

public class MainJourneyActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_journey);
    }
    public static void actionStart(Context context){
        context.startActivity(new Intent(context,MainJourneyActivity.class));
    }
}
