package demo.example.chuangke.adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import demo.example.chuangke.loader.GlideImageLoader;
import demo.example.chuangke.R;
import demo.example.chuangke.gson.Hot_issues;

public class HotListAdapter extends RecyclerView.Adapter<HotListAdapter.myViewHolder> {
    private Context context;
    private List<Hot_issues>  itemList;
    private GlideImageLoader imageLoader;
    public HotListAdapter(Context context, List<Hot_issues> itemList){
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        final View view = LayoutInflater.from(context).inflate(R.layout.rc_hotlist_items,viewGroup,false);
        final myViewHolder holder = new myViewHolder(view);
        return holder;
    }

    public void setImageLoader(GlideImageLoader imageLoader) {
        this.imageLoader = imageLoader;
    }

    @Override
    public void onBindViewHolder(@NonNull HotListAdapter.myViewHolder holder, int position) {
        if (itemList.size() > 0) {
            holder.textView.setText("01");
            if (imageLoader == null) {
                setImageLoader(new GlideImageLoader());
            }
            imageLoader.displayImage(context, itemList.get(position).getImages(), holder.imageView);
        }else{

        }
    }

    @Override
    public int getItemCount() {
        return itemList.size()>0?itemList.size():1;
    }

    class myViewHolder extends  RecyclerView.ViewHolder{
        private TextView textView ;
        private ImageView imageView;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.title_item);
            imageView  = itemView.findViewById(R.id.image_item);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onItemClickListener!=null){
                        onItemClickListener.OnItemClick(v,"还有谁?");
                    }
                }
            });
        }
    }
    public interface OnItemClickListener{
        public void OnItemClick(View view, String str);
    }

    private OnItemClickListener onItemClickListener;
    public void setOnItemClickListener(OnItemClickListener oc){
        this.onItemClickListener = oc;
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
    }
}
