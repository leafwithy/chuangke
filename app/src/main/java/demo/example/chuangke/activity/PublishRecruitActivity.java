package demo.example.chuangke.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import demo.example.chuangke.R;
import demo.example.chuangke.util.HttpUtil;
import demo.example.chuangke.util.UserUitl;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.RequestBody;
import okhttp3.Response;

public class PublishRecruitActivity extends AppCompatActivity {

    private EditText teamInfoEt;
    private EditText demandEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pushrecruit);
        initView();
    }

    private void initView(){
        teamInfoEt = findViewById(R.id.et_teamInfo_publish);
        demandEt = findViewById(R.id.et_demand_publish);
        Button publishBtn = findViewById(R.id.btn_publish);

        publishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String introEt = teamInfoEt.getText().toString();
                String demandText = demandEt.getText().toString();
                if (introEt.equals("")){
                    Toast.makeText(PublishRecruitActivity.this, "团队简介不可为空", Toast.LENGTH_SHORT).show();
                    return;
                }else if (demandText.equals("")){
                    Toast.makeText(PublishRecruitActivity.this, "招贤需求不可为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                //联网发布招募信息
                publishRecruitRequest(introEt,demandText);
            }
        });
    }

    private void publishRecruitRequest(String intro,String demand){
        final String url = "http://10.0.2.2/myProjects/create_space/insert_recruit.php";
        RequestBody requestBody = HttpUtil.IdeaRequestBody.postRecruitRequestBody(UserUitl.uid,intro,demand);
        HttpUtil.sendRequest(url, requestBody, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(PublishRecruitActivity.this, "联网失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) {
                String responseText = response.body().toString();
                if (responseText.equals("0")){
                    Toast.makeText(PublishRecruitActivity.this, "发布失败", Toast.LENGTH_SHORT).show();
                }else if (responseText.equals("1")){
                    Toast.makeText(PublishRecruitActivity.this, "发布成功", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //PublishRecruitActivity的启动方法
    public static void actionStart(Context context){
        Intent intent = new Intent(context, PublishRecruitActivity.class);
        context.startActivity(intent);
    }
}
