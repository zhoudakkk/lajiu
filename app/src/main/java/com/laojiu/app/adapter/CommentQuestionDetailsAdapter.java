package com.laojiu.app.adapter;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.laojiu.app.APP;
import com.laojiu.app.R;
import com.laojiu.app.bean.DaoThemeBean;
import com.laojiu.app.bean.StemBean;
import com.laojiu.app.ui.CommentQuestionDetailsActivity;
import com.laojiu.app.utils.Utils;

import java.util.List;

public class CommentQuestionDetailsAdapter extends RecyclerView.Adapter<CommentQuestionDetailsAdapter.VH> {

    private CommentQuestionDetailsActivity mContext;
    private List<StemBean> mList;
    private DaoThemeBean mData;

    public CommentQuestionDetailsAdapter(CommentQuestionDetailsActivity context, DaoThemeBean data) {
        mContext = context;
        mData = data;
        mList = data.stemBeanList;
    }

    public void setData (DaoThemeBean data){
        mData = data;
        mList = data.stemBeanList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.item_comment_question_details, null);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, final int position) {
        StemBean item = mList.get(position);
        int tag = position + 1;
        holder.titleTv.setText("材料" + tag + ":");
        holder.contentTv.setText("\r\r\r\r" + item.stem);

        holder.historyAnswerTv.setVisibility(View.GONE);

        if (!TextUtils.isEmpty(item.myAnswer) && !TextUtils.isEmpty(item.historyAnswer)) {
            holder.historyAnswerTv.setVisibility(View.VISIBLE);
            String historyAnswer = getHistoryAnswer(item.historyAnswer);
            holder.historyAnswerTv.setText(historyAnswer);
        }

        holder.contentTv.setOnClickListener(v -> mContext.hideSoftInput());

        holder.btn.setOnClickListener(v -> upData(position, item, holder.et));

        if (TextUtils.isEmpty(item.myAnswer)) {
            holder.answerTv.setText("本段采分点:");
        } else {
            holder.answerTv.setText("本段采分点:" + item.answer);
        }
        holder.et.setText(item.myAnswer);

    }

    private void upData(int position, StemBean item, AppCompatEditText et) {
        String trim = et.getText().toString().trim();
        if (TextUtils.isEmpty(trim)) {
            Utils.showToast("请输入答案");
            return;
        }
        item.myAnswer = trim;

        if (TextUtils.isEmpty(item.historyAnswer)) {
            item.historyAnswer = trim;
        } else {
            item.historyAnswer = trim + "_" + item.historyAnswer;
        }

        mData.stemBeanList.set(position, item);
        APP.getDaoSession().getDaoThemeBeanDao().update(mData);
        notifyItemChanged(position);
        mContext.hideSoftInput();
    }


    private String getHistoryAnswer(String str) {
        String[] split = str.split("_");
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < split.length; i++) {
            String item = split[i];
            if (!TextUtils.isEmpty(item)) buffer.append(item + "\n");
            if (i > 5) break;
        }
        return buffer.toString();
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    class VH extends RecyclerView.ViewHolder {

        private final AppCompatTextView titleTv;
        private final AppCompatTextView contentTv;
        private final AppCompatTextView answerTv;
        private final AppCompatEditText et;
        private final AppCompatTextView historyAnswerTv;
        private final AppCompatButton btn;


        public VH(@NonNull View itemView) {
            super(itemView);
            titleTv = itemView.findViewById(R.id.item_comment_question_details_title);
            contentTv = itemView.findViewById(R.id.item_comment_question_details_content);
            answerTv = itemView.findViewById(R.id.item_comment_question_details_answer);
            et = itemView.findViewById(R.id.item_comment_question_details_answer_et);

            historyAnswerTv = itemView.findViewById(R.id.item_comment_question_details_history_answer);
            btn = itemView.findViewById(R.id.item_comment_question_details_history_btn);
        }
    }
}
