package com.laojiu.app.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.widget.ContentLoadingProgressBar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.laojiu.app.APP;
import com.laojiu.app.AppContent;
import com.laojiu.app.R;
import com.laojiu.app.bean.CenterDataBean;
import com.laojiu.app.bean.CommentQuestionModeBean;
import com.laojiu.app.db.gen.DaoThemeBeanDao;
import com.laojiu.app.ui.CommentQuestionListActivity;
import com.laojiu.app.ui.CommentQuestionSearchActivity;
import com.laojiu.app.utils.Utils;


import java.util.ArrayList;
import java.util.List;

public class CommentQuestionModeAdapter extends RecyclerView.Adapter<CommentQuestionModeAdapter.VH> {

    private Context mContext;

    private List<CommentQuestionModeBean> mList = new ArrayList<>();

    public CommentQuestionModeAdapter(Context context) {
        mContext = context;
        mList.add(new CommentQuestionModeBean(0));

        mList.add(new CommentQuestionModeBean(1, R.drawable.all, CommentQuestionModeBean.all));
        mList.add(new CommentQuestionModeBean(1, R.drawable.complete_0, CommentQuestionModeBean.completed));
        mList.add(new CommentQuestionModeBean(1, R.drawable.complete_1, CommentQuestionModeBean.incomplete));

        mList.add(new CommentQuestionModeBean(1, R.drawable.sign, CommentQuestionModeBean.sign));
        mList.add(new CommentQuestionModeBean(1, R.drawable.error, CommentQuestionModeBean.error));
        mList.add(new CommentQuestionModeBean(1, R.drawable.random, CommentQuestionModeBean.everyday));
        mList.add(new CommentQuestionModeBean(1, R.drawable.answer, CommentQuestionModeBean.answer));
        mList.add(new CommentQuestionModeBean(1, R.drawable.title, CommentQuestionModeBean.titleStr));
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == 0) {
            view = View.inflate(mContext, R.layout.item_comment_question_mode_title, null);
        } else {
            view = View.inflate(mContext, R.layout.item_comment_question_mode_btn, null);
        }
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        CommentQuestionModeBean item = mList.get(position);
        setPbView(holder, item);
        if (item.type == 1) {
            Drawable drawable = getDrawable(item);
            holder.btn.setCompoundDrawables(null, drawable, null, null);
            holder.btn.setText(item.title);
            holder.btn.setOnClickListener(v -> gotoNextActivity(item));
        }
    }


    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    private void gotoNextActivity(CommentQuestionModeBean item) {
        if (TextUtils.equals(CommentQuestionModeBean.answer, item.title)) {
            CommentQuestionSearchActivity.gotoActivity(mContext, item);
            return;
        }

        if (TextUtils.equals(CommentQuestionModeBean.titleStr, item.title)) {
            CommentQuestionSearchActivity.gotoActivity(mContext, item);
            return;
        }
        CommentQuestionListActivity.gotoActivity(mContext, item);
    }

    private Drawable getDrawable(CommentQuestionModeBean item) {
        Drawable drawable = Utils.getAppResources().getDrawable(item.image, null);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        return drawable;
    }

    private void setPbView(VH holder, CommentQuestionModeBean item) {
        if (item.type == 0) {
            DaoThemeBeanDao dao = APP.getDaoSession().getDaoThemeBeanDao();
            long question = dao.queryBuilder().where(DaoThemeBeanDao.Properties.Type.eq(AppContent.QuestionType), DaoThemeBeanDao.Properties.CompleteNumber.gt(0)).count();
            long questionAll = dao.queryBuilder().where(DaoThemeBeanDao.Properties.Type.eq(AppContent.QuestionType)).count();
            holder.questionPd.setMax((int) questionAll);
            holder.questionPd.setProgress((int) question);
            holder.questionTv.setText("问题概括:" + question + "/" + questionAll);

            long reason = dao.queryBuilder().where(DaoThemeBeanDao.Properties.Type.eq(AppContent.ReasonType), DaoThemeBeanDao.Properties.CompleteNumber.gt(0)).count();
            long reasonAll = dao.queryBuilder().where(DaoThemeBeanDao.Properties.Type.eq(AppContent.ReasonType)).count();
            holder.reasonPd.setMax((int) reasonAll);
            holder.reasonPd.setProgress((int) reason);
            holder.reasonTv.setText("原因概括:" + reason + "/" + reasonAll);

            long method = dao.queryBuilder().where(DaoThemeBeanDao.Properties.Type.eq(AppContent.MethodType), DaoThemeBeanDao.Properties.CompleteNumber.gt(0)).count();
            long methodAll = dao.queryBuilder().where(DaoThemeBeanDao.Properties.Type.eq(AppContent.MethodType)).count();
            holder.methodPd.setMax((int) methodAll);
            holder.methodPd.setProgress((int) method);
            holder.methodTv.setText("对策概括:" + method + "/" + methodAll);
        }
    }

    class VH extends RecyclerView.ViewHolder {

        private ContentLoadingProgressBar questionPd;
        private AppCompatTextView questionTv;
        private ContentLoadingProgressBar reasonPd;
        private AppCompatTextView reasonTv;
        private ContentLoadingProgressBar methodPd;
        private AppCompatTextView methodTv;
        private final AppCompatRadioButton btn;


        public VH(@NonNull View itemView) {
            super(itemView);

            questionPd = itemView.findViewById(R.id.comment_question_mode_question_pb);
            questionTv = itemView.findViewById(R.id.comment_question_mode_question_tv);
            reasonPd = itemView.findViewById(R.id.comment_question_mode_reason_pb);
            reasonTv = itemView.findViewById(R.id.comment_question_mode_reason_tv);
            methodPd = itemView.findViewById(R.id.comment_question_mode_method_pb);
            methodTv = itemView.findViewById(R.id.comment_question_mode_method_tv);

            btn = itemView.findViewById(R.id.comment_question_mode_btn);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return mList.get(position).type;
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            ((GridLayoutManager) manager).setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    CommentQuestionModeBean item = mList.get(position);
                    if (item.type == 0) return 4;
                    else return 1;
                }
            });
        }

    }
}
