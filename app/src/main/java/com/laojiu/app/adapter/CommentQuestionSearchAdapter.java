package com.laojiu.app.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.laojiu.app.AppContent;
import com.laojiu.app.R;
import com.laojiu.app.utils.SpUtils;


public class CommentQuestionSearchAdapter extends RecyclerView.Adapter<CommentQuestionSearchAdapter.VH> {

    public String[] mList;

    private Context mContext;

    public CommentQuestionSearchAdapter(Context context) {
        mContext = context;
        String str = SpUtils.getString(AppContent.answerTag);
        if (str != null) {
            mList = str.split("_");
        }
    }

    public void addData(String tag) {
        if (mList == null || mList.length <= 0) {
            SpUtils.putString(AppContent.answerTag, tag);
            mList[0] = tag;
        } else {
            String str = SpUtils.getString(AppContent.answerTag);
            str = tag + "_" + str;
            SpUtils.putString(AppContent.answerTag, str);
            mList = str.split("_");
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
        String item = mList[position];
        holder.btn.setText(item);
        holder.btn.setOnClickListener(v -> gotoNextActivity(item));
    }

    private void gotoNextActivity(String str) {

    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.length;
    }

    class VH extends RecyclerView.ViewHolder {

        private final AppCompatButton btn;

        public VH(@NonNull View itemView) {
            super(itemView);
            btn = itemView.findViewById(R.id.item_comment_question_search_btn);
        }
    }
}
