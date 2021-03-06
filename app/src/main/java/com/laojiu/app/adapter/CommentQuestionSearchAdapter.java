package com.laojiu.app.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.laojiu.app.AppContent;
import com.laojiu.app.R;
import com.laojiu.app.bean.CommentQuestionModeBean;
import com.laojiu.app.ui.CommentQuestionListActivity;
import com.laojiu.app.utils.SpUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class CommentQuestionSearchAdapter extends RecyclerView.Adapter<CommentQuestionSearchAdapter.VH> {

    public List<String> mList = new ArrayList<>();
    private String tagStr = "_";
    private Context mContext;
    private String mContentTag;

    public CommentQuestionSearchAdapter(Context context, String contentTag) {
        mContext = context;
        mContentTag = contentTag;
        setList();
    }

    public void addData(String tag) {
        if (mList == null || mList.size() <= 0) {
            SpUtils.putString(mContentTag, tag);
            mList.add(0, tag);
        } else {
            String str = SpUtils.getString(mContentTag);
            str = tag + tagStr + str;
            SpUtils.putString(mContentTag, str);
            setList();

        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.item_comment_question_search, null);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        String item = mList.get(position);
        holder.tv.setText(item);
        holder.tv.setOnClickListener(v -> gotoNextActivity(item));
        holder.tv.setOnLongClickListener(v -> delete(item));
    }

    private boolean delete(String item) {
        String str = SpUtils.getString(mContentTag);
        if (item.contains(tagStr)) {
            String replace = str.replace(item + tagStr, "");
            SpUtils.putString(mContentTag, replace);
            setList();
        } else {
            SpUtils.putString(mContentTag, "");
            mList.clear();
        }
        notifyDataSetChanged();
        return false;
    }

    private void gotoNextActivity(String str) {
        CommentQuestionModeBean bean = new CommentQuestionModeBean(CommentQuestionModeBean.answer);
        if (!TextUtils.equals(mContentTag, AppContent.answerTag))
            bean.title = CommentQuestionModeBean.titleStr;

        bean.str = str;
        CommentQuestionListActivity.gotoActivity(mContext, bean);
    }

    private void setList() {
        String str = SpUtils.getString(mContentTag);
        if (!TextUtils.isEmpty(str)) {
            mList.clear();
            mList.addAll(Arrays.asList(str.split(tagStr)));
        }
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    class VH extends RecyclerView.ViewHolder {

        private final AppCompatTextView tv;

        public VH(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.item_comment_question_search_tv);
        }
    }
}
