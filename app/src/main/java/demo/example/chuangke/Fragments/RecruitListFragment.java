package demo.example.chuangke.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import demo.example.chuangke.R;
import demo.example.chuangke.adapter.RecruitListAdapter;
import demo.example.chuangke.gson.RecruitListResult;
import demo.example.chuangke.gson.RecuitItem;
import demo.example.chuangke.util.HttpUtil;
import demo.example.chuangke.util.UserUitl;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RecruitListFragment extends Fragment {
    View mView;
    int startRid = 1;
    RecyclerView mRecruitListRv;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_recruit_list,container,false);
        initView(mView);
        initData();
        return mView;
    }

    private void initView(View view){
        mRecruitListRv = (RecyclerView)view.findViewById(R.id.rv_recruit_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecruitListRv.setLayoutManager(layoutManager);
    }

    private void initData(){
        getRecruitList();
    }

    //将数据填入列表
    private void setRecyclerView(List<RecuitItem> recuitList){
        RecruitListAdapter adapter = new RecruitListAdapter(getContext(),recuitList);
        mRecruitListRv.setAdapter(adapter);
    }

    //处理返回的招募表json信息
    private RecruitListResult handleRecruitResponse(String response){
        try{
            JSONObject jsonObject = new JSONObject(response);
            String jsonContent = jsonObject.toString();
            return new Gson().fromJson(jsonContent,RecruitListResult.class);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    //联网获取招贤列表
    private void getRecruitList(){
        final String url = "http://10.0.2.2/myProjects/create_space/get_recruit.php";
        RequestBody requestBody = HttpUtil.recruitListRequestBody(UserUitl.uid,String.valueOf(startRid));
        HttpUtil.sendRequest(url, requestBody, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getContext(), "网络连接失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseText = response.body().string();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        RecruitListResult result = handleRecruitResponse(responseText);
                        if(result == null ||result.getStatus() != 1){
                            Toast.makeText(getContext(), "招募列表为空", Toast.LENGTH_SHORT).show();
                        }else{
                            if (result.getRecruitList() == null){
                                Toast.makeText(getContext(), "？？？？？", Toast.LENGTH_SHORT).show();
                            }else {
                                setRecyclerView(result.getRecruitList());
                                Toast.makeText(getContext(), "成功获取招募列表", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
            }
        });


    }

}
