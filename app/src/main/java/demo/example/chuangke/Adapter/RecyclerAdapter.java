package demo.example.chuangke.Adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import demo.example.chuangke.R;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.myViewHolder> {
    private Context context;
    private String[]  itemList;

    public RecyclerAdapter(Context context,String[] itemList){
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = View.inflate(context, R.layout.new_rc_items,null);
        return new myViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.myViewHolder holder, int position) {
        String str  = itemList[position];
        holder.textView.setText(str);
    }

    @Override
    public int getItemCount() {
        return itemList.length;
    }

    class myViewHolder extends  RecyclerView.ViewHolder{
        private TextView textView ;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView_new);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onItemClickListener!=null){
                        onItemClickListener.OnItemClick(v,itemList[getLayoutPosition()]);
                    }
                }
            });
        }
    }
    public interface OnItemClickListener{
        public void OnItemClick(View view, String str);
    }
    private OnItemClickListener onItemClickListener;
    private void OnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }
}
