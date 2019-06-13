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
import okhttp3.Callback;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RegisterActivity extends AppCompatActivity {

    private EditText mAccountTv;    //账号编辑框
    private EditText mPwdTv;        //密码编辑框
    private EditText mRePwdTv;      //确认密码编辑框
    private Button mRegisterBt;     //注册按钮

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }

    //初始化控件View
    private void initView(){
        mAccountTv = (EditText) findViewById(R.id.et_register_account);
        mPwdTv = (EditText)findViewById(R.id.et_register_pwd);
        mRePwdTv = (EditText)findViewById(R.id.et_register_repwd);
        mRegisterBt = (Button) findViewById(R.id.btn_register2);

        mRegisterBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击注册按钮之后，将信息传给服务器,成功后直接进入
                String account = mAccountTv.getText().toString();
                String password = mPwdTv.getText().toString();
                String password2 = mRePwdTv.getText().toString();
                if (check(account,password,password2))
                    registerRequest(account, password);
            }
        });
    }

    //检查账号密码是否格式正确
    public boolean check(String account ,String password,String password2){
        if (account.isEmpty()){
            Toast.makeText(this, "账号不可为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (password.isEmpty()){
            Toast.makeText(this, "密码不可为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (password2.isEmpty()){
            Toast.makeText(this, "确认密码不可为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!password.equals(password2)){
            Toast.makeText(this, "两次密码不相同", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    //注册请求
    public void registerRequest(final String account, final String password){
        final String loginUrl = "http://localhost/register.php";
        RequestBody requestBody= HttpUtil.registerRequestBody(account,password);
        HttpUtil.sendRequest(loginUrl, requestBody, new Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(RegisterActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(okhttp3.Call call, Response response) throws IOException {
                final String responseText = response.body().string();
                Toast.makeText(RegisterActivity.this, "resp:"+responseText+"注册失败,连接数据库失败", Toast.LENGTH_SHORT).show();
                //对服务器返回的信息进行处理
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (responseText.equals("-1"))
                            Toast.makeText(RegisterActivity.this, "已有相同用户名", Toast.LENGTH_SHORT).show();
                        else if (responseText.equals("1")){
                            Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                            //启动登录活动
                            LoginActivity.actionStart(RegisterActivity.this,account,password);
                            finish();
                        }else {
                            Toast.makeText(RegisterActivity.this, "resp:"+responseText+"注册失败,连接数据库失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    //RegisterActivity的启动方法
    public static void actionStart(Context context){
        Intent intent = new Intent(context, RegisterActivity.class);
        context.startActivity(intent);
    }

}
