package demo.example.chuangke.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import demo.example.chuangke.R;
import demo.example.chuangke.Reality.New_issues;

public class RecycleAdapter_new extends RecyclerView.Adapter<RecycleAdapter_new.myViewHolder> {
    private List<New_issues> itemsList ;
    private Context context;

    public RecycleAdapter_new(Context context, List<New_issues> itemsList){
        this.context = context;
        this.itemsList = itemsList;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context,R.layout.new_rc_items,null);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleAdapter_new.myViewHolder myViewHolder, int i) {
        if(itemsList.size()>0) {
            myViewHolder.textView1.setText(itemsList.get(i).getTitle());
            myViewHolder.textView2.setText(itemsList.get(i).getContent());
            myViewHolder.textView3.setText(itemsList.get(i).getUsename());
            myViewHolder.textView4.setText(itemsList.get(i).getTime());
        }else{

        }
    }

    @Override
    public int getItemCount() {
        return itemsList.size()>0?itemsList.size():1;
    }

    class myViewHolder extends  RecyclerView.ViewHolder{
        private TextView textView1;
        private TextView textView2;
        private TextView textView3;
        private TextView textView4;

        public myViewHolder(@NonNull final View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.new_title_items);
            textView2  = itemView.findViewById(R.id.new_content_items);
            textView3 = itemView.findViewById(R.id.new_name_items);
            textView4 = itemView.findViewById(R.id.new_time_items);

            Log.d("tags","加载过了");
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onItemClickListener!=null){
                        onItemClickListener.OnItemClick(itemView,"哦？");
                    }
                }
            });
        }
    }
    public interface OnItemClickListener{
        public void OnItemClick(View v,String str);
    }
    OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
