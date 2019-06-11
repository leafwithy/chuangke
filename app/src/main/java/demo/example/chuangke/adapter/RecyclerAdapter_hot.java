package demo.example.chuangke.adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import demo.example.chuangke.R;
import demo.example.chuangke.Reality.Hot_issues;

public class RecyclerAdapter_hot extends RecyclerView.Adapter<RecyclerAdapter_hot.myViewHolder> {
    private Context context;
    private List<Hot_issues>  itemList;

    public RecyclerAdapter_hot(Context context, List<Hot_issues> itemList){
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = View.inflate(context, R.layout.hot_rc_items,null);
        return new myViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter_hot.myViewHolder holder, int position) {
        holder.textView.setText("01");
        holder.imageView.setImageResource(itemList.get(position).getImages());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    class myViewHolder extends  RecyclerView.ViewHolder{
        private TextView textView ;
        private ImageView imageView;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.title_item);
            imageView  = itemView.findViewById(R.id.image_item);

            Log.d("tags","加载过了");
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
}
