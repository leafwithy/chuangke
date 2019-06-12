package demo.example.chuangke.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import demo.example.chuangke.R;
import demo.example.chuangke.adapter.CommentListAdapter;
import demo.example.chuangke.gson.CommentItem;
import demo.example.chuangke.gson.CommentListResult;
import demo.example.chuangke.gson.ReplyItem;
import demo.example.chuangke.util.HttpUtil;
import demo.example.chuangke.util.UserUitl;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.RequestBody;
import okhttp3.Response;

public class CommentActivity extends AppCompatActivity {

    private List<CommentItem> mCommentList;
    private ExpandableListView mCommentElv;
    int rid;
    private Button commentBt;
    private PopupWindow mCommentWindow;
    private CommentListAdapter mCommentListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        initView();
    }

    private void initView(){
        mCommentElv = (ExpandableListView)findViewById(R.id.elv_comment);
        commentBt = (Button)findViewById(R.id.btn_comment);
        commentBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCommentWindow();
            }
        });
        Intent intent = getIntent();
        rid = intent.getIntExtra("rid",1);
        getCommentListRequest();
    }

    //显示“评论编辑框”
    private void showCommentWindow(){
        View contentView = LayoutInflater.from(this).inflate(R.layout.window_comment,null);
        mCommentWindow = new PopupWindow(contentView);
        mCommentWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        mCommentWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        mCommentWindow.setFocusable(true);
        mCommentWindow.setTouchable(true);
        mCommentWindow.setBackgroundDrawable(new BitmapDrawable());
        mCommentWindow.setOutsideTouchable(true);

        //防止PopupWindow被软件盘挡住（当然要在showAtLocation前使用啦）
        mCommentWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        //在指定位置显示PopupWindow
        View parentView = LayoutInflater.from(this).inflate(R.layout.activity_comment,null);
        mCommentWindow.showAtLocation(parentView,Gravity.BOTTOM,0,0);

        final EditText commentEt = (EditText)contentView.findViewById(R.id.et_comment);
        Button sendCommentBt = (Button)contentView.findViewById(R.id.btn_send_comment);
        sendCommentBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String commentText = commentEt.getText().toString();
                if (commentText.equals("") || commentText == null){
                    Toast.makeText(CommentActivity.this, "评论不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                //TODO：联网发送“评论”
                sendCommentRequest(commentText);
                addCommentItem(commentText);
                //Toast.makeText(CommentActivity.this, "发送了:"+commentText, Toast.LENGTH_SHORT).show();
                mCommentWindow.dismiss();//发送完评论后，dismiss()
            }
        });
    }

    //发送“添加评论”请求
    private void sendCommentRequest(String content){
        final String url = "http://10.0.2.2/myProjects/create_space/insert_comment.php";
        RequestBody requestBody = HttpUtil.insertCommentRequestBody(String.valueOf(rid),UserUitl.uid,content);
        HttpUtil.sendRequest(url, requestBody, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(CommentActivity.this, "联网失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseText = response.body().toString();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (responseText.equals("0")){
                            Toast.makeText(CommentActivity.this, "服务器出错", Toast.LENGTH_SHORT).show();
                        } else if (responseText.equals("1")) {
                            Toast.makeText(CommentActivity.this, "评论成功", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    private void addCommentItem(String content){
        CommentItem newItem = new CommentItem(String.valueOf(rid),
                UserUitl.uid,UserUitl.name,content,null);
        mCommentListAdapter.addGounpItem(0,newItem);
    }

    //设置View的数据
    private void setListViewData(){
        if (mCommentList == null)
            return;
        mCommentListAdapter = new CommentListAdapter(this,mCommentList);
        mCommentElv.setAdapter(mCommentListAdapter);
        mCommentElv.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
                Toast.makeText(CommentActivity.this, "点击了评论", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        mCommentElv.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                Toast.makeText(CommentActivity.this, "点击了回复", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

    //测试：设置评论表的数据
    private void setDataTest(){
        int i,j;
        List<CommentItem> clist = new ArrayList<CommentItem>();
        for(i = 0; i < 8; i++){
            CommentItem cItem = new CommentItem();
            cItem.setName("小白" + i);
            cItem.setContent("评论：" + i);
            List<ReplyItem> rlist = new ArrayList<ReplyItem>();
            for(j = 0; j < 3; j++){
                ReplyItem rItem = new ReplyItem();
                rItem.setName("小白" + i + " - " +j);
                rItem.setContent("回复：" + i + " - " +j);
                rlist.add(rItem);
            }
            cItem.setReplyList(rlist);
            clist.add(cItem);
        }
        mCommentList = clist;
    }

    //将返回的json数据解析成CommentListResult实体类
    private CommentListResult handleCommentResponse(String response){
        CommentListResult result = null;
        try {
            JSONObject jsonObject = new JSONObject(response);
            String jsonContent = jsonObject.toString();
            result = new Gson().fromJson(jsonContent, CommentListResult.class);
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            return result;
        }
    }

    //发送“获取评论表”的请求
    private void getCommentListRequest(){
        final String commentUrl = "http://10.0.2.2/myProjects/create_space/get_comment.php";
        RequestBody requestBody = HttpUtil.commentListRequestBody(String.valueOf(rid));
        HttpUtil.sendRequest(commentUrl, requestBody, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(CommentActivity.this, "联网失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseText = response.body().string();
                final CommentListResult result = handleCommentResponse(responseText);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (result.getStatus() == 1){
                            mCommentList = result.getCommentList();
                            setListViewData();
                        }else {
                            Toast.makeText(CommentActivity.this, "评论列表为空", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
    }

    //TestActivity的启动方法
    public static void actionStart(Context context,int rid){
        Intent intent = new Intent(context,CommentActivity.class);
        intent.putExtra("rid",rid);
        context.startActivity(intent);
    }
}
