package demo.example.chuangke.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import demo.example.chuangke.R;
import demo.example.chuangke.gson.CommentItem;
import demo.example.chuangke.gson.ReplyItem;

public class CommentListAdapter extends BaseExpandableListAdapter {
    private Context mContext;
    private List<CommentItem> mCommentList;

    static class GroupViewHolder{
        CircleImageView profileCiv;     //头像
        TextView cUserTv;               //评论用户名
        TextView cContentTv;            //评论内容

        public GroupViewHolder(View convertView){
            profileCiv = (CircleImageView) convertView.findViewById(R.id.civ_comment_profile);
            cUserTv = (TextView)convertView.findViewById(R.id.tv_comment_name);
            cContentTv = (TextView)convertView.findViewById(R.id.tv_comment_content);
        }
    }

    static class ChildViewHolder{
        TextView rUserTv;               //回复用户名
        TextView rContentTv;            //回复内容
        //实例化View
        public ChildViewHolder(View convertView) {
            rUserTv = (TextView)convertView.findViewById(R.id.tv_reply_name);
            rContentTv = (TextView)convertView.findViewById(R.id.tv_reply_content);
        }
    }

    //构造函数
    public CommentListAdapter(Context context,List<CommentItem> commentList){
        mContext = context;
        mCommentList = commentList;
    }
    // 获取分组的个数
    @Override
    public int getGroupCount() {
        if (null == mCommentList)
            return 0;
        return mCommentList.size();
    }

    //获取指定分组中的子选项的个数
    @Override
    public int getChildrenCount(int i) {
        if (null == mCommentList.get(i).getReplyList())
            return 0;
        return mCommentList.get(i).getReplyList().size();
    }

    //获取指定的分组数据
    @Override
    public Object getGroup(int i) {
        return mCommentList.get(i);
    }

    //获取指定分组中的指定子选项数据
    @Override
    public Object getChild(int i, int i1) {
        return mCommentList.get(i).getReplyList().get(i1);
    }

    //获取指定分组的ID, ID必须唯一（这里以分组的序号作为ID）
    @Override
    public long getGroupId(int i) {
        return i;
    }

    //获取子选项的ID, ID必须唯一（这里以子项的序号作为ID）
    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    //分组和子选项是否持有稳定的ID, 即底层数据的改变会不会影响到它们
    @Override
    public boolean hasStableIds() {
        return true;
    }

    //指定位置上的子元素是否可以被选中
    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

    /**
     * 获取显示指定组的视图对象
     * @param groupPosition 组位置
     * @param isExpanded 该组是展开状态还是伸缩状态
     * @param convertView 重用已有的视图对象
     * @param parent 返回的视图对象始终依附于的父视图组
     */
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder groupViewHolder;
        if (convertView == null){
            //参3：只让在父布局中声明的layout属性生效，但不为这个View添加父布局;
            // 因为convertView有个父布局之后，他就不能再被添加到ListView中了
            convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_comment,parent,false);
            groupViewHolder = new GroupViewHolder(convertView);
            convertView.setTag(groupViewHolder);
        }else {
            groupViewHolder = (GroupViewHolder)convertView.getTag();
        }
        //TODO:为评论视图（一级列表）设置参数
        //groupViewHolder.groupItemTv.setText(mGroupList.get(groupPosition));
        CommentItem nowCommentItem = mCommentList.get(groupPosition);   //当前评论子项
        Glide.with(mContext)
                .load(nowCommentItem.getProfileUrl())
                .placeholder(R.drawable.profile_temp)
                .error(R.drawable.profile_temp)
                .into(groupViewHolder.profileCiv);
        groupViewHolder.cUserTv.setText(nowCommentItem.getName());
        groupViewHolder.cContentTv.setText(nowCommentItem.getContent());
        return convertView;
    }

    /**
     * 获取一个视图对象，显示指定组中的指定子元素数据。
     * @param groupPos 组位置
     * @param childPos 子元素位置
     * @param isLastChild 子元素是否处于组中的最后一个
     * @param convertView 重用已有的视图(View)对象
     * @param parent 返回的视图(View)对象始终依附于的视图组
     */
    @Override
    public View getChildView(int groupPos, int childPos, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder childViewHolder;
        if (convertView == null){
            convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_reply,parent,false);
            childViewHolder = new ChildViewHolder(convertView);
            convertView.setTag(childViewHolder);
        }else {
            childViewHolder = (ChildViewHolder)convertView.getTag();
        }
        //TODO:为回复视图（二级列表）设置参数
        //childViewHolder.childItemTv.setText(mChildList.get(groupPos).get(childPos));
        ReplyItem nowReplyItem = mCommentList.get(groupPos).getReplyList().get(childPos);//当前回复子项
        childViewHolder.rUserTv.setText(nowReplyItem.getName());
        childViewHolder.rContentTv.setText(nowReplyItem.getContent());
        return convertView;
    }

    //增加goup子项（一级列表子项）
    public void addGounpItem(int pos,CommentItem newGounpItemData){
        mCommentList.add(pos,newGounpItemData);
        notifyDataSetChanged();
    }

    //删除goup子项（一级列表子项）
    public void removeGounpItem(int pos){
        mCommentList.remove(pos);
        notifyDataSetChanged();
    }

    //增加child子项（二级列表子项）
    public void addChildItem(int pos, int pos1, ReplyItem newChildItemData){
        List<ReplyItem> replyList = mCommentList.get(pos).getReplyList();
        replyList.add(pos1,newChildItemData);
        notifyDataSetChanged();
    }

    //删除child子项（二级列表子项）
    public void removeChildItem(int pos,int pos1){
        List<ReplyItem> replyList = mCommentList.get(pos).getReplyList();
        replyList.remove(pos1);
        notifyDataSetChanged();
    }

}
