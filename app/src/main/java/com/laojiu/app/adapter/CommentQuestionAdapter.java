package com.laojiu.app.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.laojiu.app.APP;
import com.laojiu.app.R;
import com.laojiu.app.bean.DaoThemeBean;
import com.laojiu.app.db.gen.DaoThemeBeanDao;
import com.laojiu.app.ui.CommentQuestionDetailsActivity;
import com.laojiu.app.utils.DataUtil;
import com.laojiu.app.utils.Utils;

import java.util.List;

public class CommentQuestionAdapter extends RecyclerView.Adapter<CommentQuestionAdapter.VH> {


    private List<DaoThemeBean> mList;
    private Context mContext;

    public CommentQuestionAdapter(Context context, List<DaoThemeBean> list) {
        mContext = context;
        mList = list;
    }

    public void upItemView(long appID) {
        if (mList == null) return;
        for (int i = 0; i < mList.size(); i++) {
            if (mList.get(i).appID == appID) {
                notifyItemChanged(i);
                return;
            }
        }
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.item_comment_question, null);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        DaoThemeBean item = mList.get(position);
        holder.titleTv.setText(item.theme);

        holder.signBtn.setImageResource(R.drawable.sign_1);

        if (item.isSign != null && item.isSign)
            holder.signBtn.setImageResource(R.drawable.sign_0);


        holder.errorBtn.setImageResource(R.drawable.error_1);
        if (item.isError != null && item.isError)
            holder.errorBtn.setImageResource(R.drawable.error_0);

        holder.ll.setVisibility(View.GONE);
        if (item.completeNumber != null && item.completeNumber > 0) {
            holder.ll.setVisibility(View.VISIBLE);
            holder.numberTv.setText("X" + item.completeNumber);
        }

        holder.signBtn.setOnClickListener(v -> setSign(position, item));
        holder.errorBtn.setOnClickListener(v -> setError(position, item));
        holder.continueBtn.setOnClickListener(v -> gotoNextActivity(item, true));
        holder.redoBtn.setOnClickListener(v -> gotoNextActivity(item, false));
    }

    private void gotoNextActivity(DaoThemeBean item, boolean isContinue) {
        CommentQuestionDetailsActivity.gotoActivity(mContext, item.appID, isContinue);
    }

    private void setSign(int position, DaoThemeBean item) {
        DataUtil.setSign(item);
        notifyItemChanged(position);
        Utils.showToast("操作成功");
    }

    private void setError(int position, DaoThemeBean item) {
        DataUtil.setError(item);
        notifyItemChanged(position);
        Utils.showToast("操作成功");
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    class VH extends RecyclerView.ViewHolder {

        private final View cl;
        private final AppCompatTextView titleTv;
        private final AppCompatImageButton signBtn;
        private final AppCompatImageButton errorBtn;
        private final AppCompatImageButton continueBtn;
        private final AppCompatImageButton redoBtn;

        private final LinearLayout ll;
        private final AppCompatTextView numberTv;


        public VH(@NonNull View itemView) {
            super(itemView);
            cl = itemView.findViewById(R.id.item_comment_question_cl);
            titleTv = itemView.findViewById(R.id.item_comment_question_title);
            signBtn = itemView.findViewById(R.id.item_comment_question_sign);
            errorBtn = itemView.findViewById(R.id.item_comment_question_error);
            continueBtn = itemView.findViewById(R.id.item_comment_question_continue);
            redoBtn = itemView.findViewById(R.id.item_comment_question_redo);

            ll = itemView.findViewById(R.id.item_comment_question_ll);
            numberTv = itemView.findViewById(R.id.item_comment_question_tv);
        }
    }
}
