package demo.example.chuangke.adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import demo.example.chuangke.loader.GlideImageLoader;
import demo.example.chuangke.R;
import demo.example.chuangke.gson.Hot_issues;

public class HotListAdapter extends RecyclerView.Adapter<HotListAdapter.MyViewHolder> {
    private Context context;
    private List<Hot_issues>  itemList;
    private GlideImageLoader imageLoader;
    public HotListAdapter(Context context, List<Hot_issues> itemList){
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.rc_hotlist_items,viewGroup,false));
    }

  /*  public void setImageLoader(GlideImageLoader imageLoader) {
        this.imageLoader = imageLoader;
    }*/

    @Override
    public void onBindViewHolder(@NonNull HotListAdapter.MyViewHolder holder, int position) {
        if (itemList.size() > 0) {
            Log.d("123",itemList.get(position).toString());
            holder.textView_id.setText(String.valueOf(itemList.get(position).getHid()));
            holder.textView_title.setText(itemList.get(position).getTitle());
            holder.textView_ctag.setText("评论数"+itemList.get(position).getC_tag());
            holder.textView_carenum.setText("点赞数"+itemList.get(position).getCare_num());
                    //holder.imageView;
         /*   if (imageLoader == null) {
                setImageLoader(new GlideImageLoader());
            }
            imageLoader.displayImage(context, itemList.get(position).getImages(), holder.imageView);*/
        }else{

        }
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    class MyViewHolder extends  RecyclerView.ViewHolder{
        TextView textView_id ;
        TextView textView_title;
        TextView textView_carenum;
        TextView textView_ctag;
        ImageView imageView;
        public MyViewHolder(@NonNull final View itemView) {
            super(itemView);
            textView_id = itemView.findViewById(R.id.id_item);
            textView_title = itemView.findViewById(R.id.title_item);
            textView_carenum = itemView.findViewById(R.id.carenum_item);
            textView_ctag = itemView.findViewById(R.id.ctag_item);
            //imageView  = itemView.findViewById(R.id.image_item);

        //通过重写view控件触发的点击事件完成itemView的点击事件回调
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onItemClickListener!=null){
                        onItemClickListener.OnItemClick(itemView.getContext());
                    }
                }
            });
        }
    }
    public interface OnItemClickListener{
        public void OnItemClick(Context context);
    }

    private OnItemClickListener onItemClickListener;
    public void setOnItemClickListener(OnItemClickListener oc){
        this.onItemClickListener = oc;
    }

}
