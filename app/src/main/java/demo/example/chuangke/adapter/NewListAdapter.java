package demo.example.chuangke.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import demo.example.chuangke.R;
import demo.example.chuangke.gson.New_issues;

public class NewListAdapter extends RecyclerView.Adapter<NewListAdapter.MyViewHolder> {
    private List<New_issues> itemsList ;
    private Context context;

    public NewListAdapter(Context context, List<New_issues> itemsList){
        this.context = context;
        this.itemsList = itemsList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.rc_newlist_items,viewGroup,false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull NewListAdapter.MyViewHolder myViewHolder, int i) {
        if(itemsList.size()>0) {

            myViewHolder.textView1.setText(String.valueOf(itemsList.get(i).getTitle()));
            myViewHolder.textView2.setText(String.valueOf(itemsList.get(i).getContent()));
            myViewHolder.textView3.setText(String.valueOf(itemsList.get(i).getName()));
            myViewHolder.textView4.setText(itemsList.get(i).getDeadline() +"小时前");
        }
    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }

    class MyViewHolder extends  RecyclerView.ViewHolder{
         TextView textView1;
         TextView textView2;
         TextView textView3;
         TextView textView4;

        MyViewHolder(@NonNull final View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.title_item);
            textView2  = itemView.findViewById(R.id.content_item);
            textView3 = itemView.findViewById(R.id.name_item);
            textView4 = itemView.findViewById(R.id.deadline_item);

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

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
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
