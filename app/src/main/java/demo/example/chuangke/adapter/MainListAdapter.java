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

import demo.example.chuangke.R;
import demo.example.chuangke.gson.Prove_issues;

public class MainListAdapter extends RecyclerView.Adapter<MainListAdapter.MyViewHolder> {
    private List<Prove_issues>  itemsList;
    private Context context;

    public MainListAdapter(Context context, List<Prove_issues> itemsList) {
        this.itemsList = itemsList;
        this.context = context;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        Log.d("MainListAdapter",itemsList.get(i).getTitle());
        if(itemsList.size()>0) {
            myViewHolder.text_title.setText(itemsList.get(i).getTitle());
            myViewHolder.text_type.setText(itemsList.get(i).getType());
            myViewHolder.text_read.setText(String.valueOf(itemsList.get(i).getReadnum()));
        }
    }


    @Override
    public int getItemCount() {
        return itemsList.size();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.rc_provelist_items,viewGroup,false));

    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView text_title;
        TextView text_type;
        TextView text_read;

        public MyViewHolder(@NonNull final View itemView) {
            super(itemView);
            //imageView = itemView.findViewById(R.id.prove);
            text_title  = itemView.findViewById(R.id.prove_title);
            text_type = itemView.findViewById(R.id.prove_type);
            text_read = itemView.findViewById(R.id.prove_read);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(itemView.getContext());
                }
            });
        }
    }

    public interface OnItemClickListener{
        public void onItemClick(Context context);
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
