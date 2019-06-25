package demo.example.chuangke.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.like.LikeButton;
import com.like.OnLikeListener;

import java.io.IOException;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import demo.example.chuangke.R;
import demo.example.chuangke.activity.CommentActivity;
import demo.example.chuangke.gson.RecuitItem;
import demo.example.chuangke.util.HttpUtil;
import demo.example.chuangke.util.UserUitl;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RecruitListAdapter extends RecyclerView.Adapter<RecruitListAdapter.ViewHolder> {
    private Context mContext;
    private List<RecuitItem> mRecuitList;


    //实例暂存器（内部类）
    static class ViewHolder extends RecyclerView.ViewHolder{

        CircleImageView recruitProfileIv;
        TextView recruitNameTv;
        TextView recruitTimeTv;
        TextView recruitInfoTv;
        TextView recruitDemandTv;
        TextView recruitCollectSumTv;
        LikeButton recruitCollectLbtn;
        ImageView recruitDiscussIv;

        public ViewHolder(View view){
            super(view);
            recruitProfileIv = (CircleImageView)view.findViewById(R.id.civ_recruit_profile);
            recruitNameTv = (TextView)view.findViewById(R.id.tv_recruit_name);
            recruitTimeTv = (TextView)view.findViewById(R.id.tv_recruit_time);
            recruitInfoTv = (TextView)view.findViewById(R.id.tv_recruit_info);
            recruitDemandTv = (TextView)view.findViewById(R.id.tv_recruit_demand);
            recruitCollectSumTv = (TextView)view.findViewById(R.id.tv_recruit_collect_sum);
            recruitCollectLbtn = (LikeButton) view.findViewById(R.id.lbtn_recruit_collect);
            recruitDiscussIv = (ImageView)view.findViewById(R.id.iv_recruit_discuss);
        }

    }

    //构造函数
    public RecruitListAdapter(Context context,List<RecuitItem> recuitList){
        mContext = context;
        mRecuitList = recuitList;
    }

    @NonNull
    @Override//填充子项布局，并为控件设置监听器，类似activity的onCreate()
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, final int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.rc_recruit_items,viewGroup,false));
    }

    //当子项被滚动到屏幕时调用
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        if (mRecuitList.size()>0) {
            RecuitItem item = mRecuitList.get(i);
            viewHolder.recruitNameTv.setText(String.valueOf(item.getUid()));
            viewHolder.recruitTimeTv.setText(item.getTime());
            viewHolder.recruitDemandTv.setText(item.getDemand());
            viewHolder.recruitCollectLbtn.setLiked(item.getStar());
            viewHolder.recruitCollectSumTv.setText(String.valueOf(item.getStar_sum()));//TODO：名字有点问题
            viewHolder.recruitInfoTv.setText(item.getIntro());
        }
        final int position = i;
        final int nowRid = mRecuitList.get(position).getRid();
        //点击“评论”
        viewHolder.recruitDiscussIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CommentActivity.actionStart(mContext,nowRid);
            }
        });
        //设置“点赞”控件的点击监听时间
        viewHolder.recruitCollectLbtn.setOnLikeListener(new OnLikeListener() {
            final String url = "http://192.168.191.1/star.php";
            final String sumText = viewHolder.recruitCollectSumTv.getText().toString();
            @Override
            public void liked(LikeButton likeButton) {
                //TODO:点赞
                int sum = Integer.valueOf(sumText)+1;
                viewHolder.recruitCollectSumTv.setText(String.valueOf(sum));
                //并发送点赞网络请求
                RequestBody requestBody = HttpUtil.starRequestBody(1,UserUitl.uid,String.valueOf(nowRid));
                HttpUtil.sendRequest(url, requestBody, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                    }
                });
            }

            @Override
            public void unLiked(LikeButton likeButton) {
                //TODO:取消点赞
                int sum = Integer.valueOf(sumText)-1;
                viewHolder.recruitCollectSumTv.setText(String.valueOf(sum));
                //并发送取消点赞网络请求
                RequestBody requestBody = HttpUtil.starRequestBody(0,UserUitl.uid,String.valueOf(nowRid));
                HttpUtil.sendRequest(url, requestBody, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {

                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return mRecuitList.size()>0?mRecuitList.size():1;
    }

    //将RecycleView附加到Adapter上
    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

    }

    //将RecycleView从Adapter解除
    @Override
    public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);

    }
}
