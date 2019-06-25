package demo.example.chuangke.fragments.Fragment_frag;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import demo.example.chuangke.R;
import demo.example.chuangke.adapter.RecruitListAdapter;
import demo.example.chuangke.gson.RecruitListResult;
import demo.example.chuangke.gson.RecuitItem;
import demo.example.chuangke.util.HttpUtil;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RecruitListFragment extends Fragment {
    View mView;
    int startRid = 1;
    RecyclerView mRecruitListRv;
    private RecruitListAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.idea_recruit,container,false);
        mRecruitListRv = mView.findViewById(R.id.rv_recruit_list);
        initData();
        initView();

        return mView;
    }

    private void initView(){

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecruitListRv.setLayoutManager(layoutManager);

    }

    private void initData(){
        getRecruitList();
    }

    //将数据填入列表
    private void setRecyclerView(List<RecuitItem> recuitList){
        adapter = new RecruitListAdapter(getActivity(),recuitList);
        adapter.onAttachedToRecyclerView(mRecruitListRv);
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
        final String url = "http://192.168.191.1/get_recruit.php";
        RequestBody requestBody = HttpUtil.IdeaRequestBody.getrecruitListRequestBody("1",String.valueOf(startRid));
        Log.d("tag2","执行缓存周边列表");
        HttpUtil.sendRequest(url, requestBody, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                Objects.requireNonNull(getActivity()).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        Log.d("recruitListFragment", "网络连接失败");
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseText = response.body().string();
                final RecruitListResult result = handleRecruitResponse(responseText);
                Objects.requireNonNull(getActivity()).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        if(result == null ||result.getStatus() != 1){

                            Log.d("recruitListFragment", "数据库连接失败");
                        }else{
                            if (result.getStatus()==1&&result.getRecruitList() == null){

                                Log.d("recruitListFragment", "列表为空");
                            }else {
                                setRecyclerView(result.getRecruitList());

                                Log.d("recruitListFragment", "成功获取招募列表");
                            }
                        }
                    }
                });
            }
        });


    }

}
