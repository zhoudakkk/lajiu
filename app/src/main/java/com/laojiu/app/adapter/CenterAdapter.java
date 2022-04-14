package com.laojiu.app.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.recyclerview.widget.RecyclerView;

import com.laojiu.app.R;
import com.laojiu.app.bean.CenterDataBean;
import com.laojiu.app.ui.CommentQuestionListActivity;
import com.laojiu.app.ui.QuestionActivity;
import com.laojiu.app.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class CenterAdapter extends RecyclerView.Adapter<CenterAdapter.VH> {

    private Context mContext;

    private List<CenterDataBean> mList = new ArrayList<>();

    public CenterAdapter(Context context) {
        mContext = context;
        mList.add(new CenterDataBean("问题", R.drawable.center_question, 0));
        mList.add(new CenterDataBean("原因", R.drawable.center_reason, 1));
        mList.add(new CenterDataBean("对策", R.drawable.center_method, 2));

    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.item_main_center, null);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        CenterDataBean item = mList.get(position);

        Drawable drawable = getDrawable(item);
        holder.arb.setCompoundDrawables(null, drawable, null, null);
        holder.arb.setText(item.title);
        holder.arb.setOnClickListener(v -> gotoNextActivity(item));
    }

    private void gotoNextActivity(CenterDataBean item) {
        if (item.tag == 0) {
            CommentQuestionListActivity.gotoActivity(mContext);
        }

    }

    private Drawable getDrawable(CenterDataBean item) {
        Drawable drawable = Utils.getAppResources().getDrawable(item.imageId, null);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        return drawable;
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    class VH extends RecyclerView.ViewHolder {

        private final AppCompatRadioButton arb;

        public VH(@NonNull View itemView) {
            super(itemView);
            arb = itemView.findViewById(R.id.item_center_arb);
        }
    }
}
