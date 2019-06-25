package demo.example.chuangke.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import demo.example.chuangke.R;
import demo.example.chuangke.util.HttpUtil;
import demo.example.chuangke.util.UserUitl;
import okhttp3.Callback;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mAccountTv;        //账号编辑框
    private EditText mPwdTv;            //密码编辑框
    private CheckBox mIsRememberCb;    //“是否记住密码”复选框
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        initView();
        initData();
    }

    //初始化控件View
    private void initView(){
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        mAccountTv =  findViewById(R.id.et_login_account);
        mPwdTv =  findViewById(R.id.et_login_pwd);
        mIsRememberCb = findViewById(R.id.cb_login_isRemember);
        //登录按钮
        Button mLoginBt = findViewById(R.id.btn_login);
        //注册按钮
        Button mRegisterBt = findViewById(R.id.btn_register);

        mLoginBt.setOnClickListener(this);
        mRegisterBt.setOnClickListener(this);

    }

    //初始化控件数据
    private void initData(){
        boolean isRemember = pref.getBoolean("remember_password",false);
        if(isRemember){
            //将账号和密码设置到文本框中
            String account = pref.getString("account","");
            String password = pref.getString("password","");
            mAccountTv.setText(account);
            mPwdTv.setText(password);
            mIsRememberCb.setChecked(true);
        }
        Intent intent = getIntent();
        String account = intent.getStringExtra("account");
        String password = intent.getStringExtra("password");
        if ( account != null && password != null){
            mAccountTv.setText(account);
            mPwdTv.setText(password);
        }
    }

    //点击事件回调
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_login://登录按钮的点击事件
                String account = mAccountTv.getText().toString();
                String password = mPwdTv.getText().toString();
                loginRequest(account, password);  //登录代码
                //requestTest();//test
                //requestTest2(name,password);
                //MainActivity.actionStart(LoginActivity.this);  //测试MainActivity
                break;
            case R.id.btn_register://注册按钮的点击事件
                RegisterActivity.actionStart(LoginActivity.this);
                break;
        }
    }

    //发送“登录”请求
    public void loginRequest(final String account, final String password){
        final String loginUrl = "http://192.168.191.1/login.php";
        RequestBody requestBody= HttpUtil.loginRequestBody(account,password);
        HttpUtil.sendRequest(loginUrl, requestBody, new Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(okhttp3.Call call, Response response) throws IOException {
                final String responseText = response.body().string();
                //对服务器返回的信息进行处理
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(!responseText.equals("0")){
                            Toast.makeText(LoginActivity.this, "登录成功了，是的", Toast.LENGTH_SHORT).show();
                            UserUitl.uid = responseText;             //在用户工具类存储当前用户UID
                            UserUitl.name = account;                 //在用户工具类存储当前用户名
                            //是否要记住密码
                            editor = pref.edit();
                            if (mIsRememberCb.isChecked()){         //检查复选框是否被选中
                                editor.putBoolean("remember_password",true);
                                editor.putString("account",account);
                                editor.putString("password",password);
                            }else {
                                editor.clear();
                            }
                            editor.apply();
                            //TestActivity.actionStart(LoginActivity.this);
                            MainActivity.actionStart(LoginActivity.this);
                            finish();
                        }else{
                            Toast.makeText(LoginActivity.this, "账号或密码错误", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
        });
    }

    //LoginActivity的启动方法
    public static void actionStart(Context context,String name,String password){
        Intent intent = new Intent(context,LoginActivity.class);
        intent.putExtra("name",name);
        intent.putExtra("password",password);
        context.startActivity(intent);
    }

}
