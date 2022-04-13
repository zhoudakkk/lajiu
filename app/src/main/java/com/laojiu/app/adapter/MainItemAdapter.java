package com.laojiu.app.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.laojiu.app.R;
import com.laojiu.app.bean.StemBean;

import java.util.List;

public class MainItemAdapter extends RecyclerView.Adapter<MainItemAdapter.VH> {

    private Context mContext;
    private List<StemBean> mList;

    public MainItemAdapter(Context context, List<StemBean> data) {
        mContext = context;
        mList = data;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.item_item_content, null);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, final int position) {
        final StemBean item = mList.get(position);
        holder.contentTv.setText(item.stem);
        final AppCompatEditText et = holder.et;

        holder.contentTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = et.getText().toString().trim();
                if (TextUtils.isEmpty(str)) {
                    Toast toast = Toast.makeText(mContext, "请输入自己的答案", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }
                item.isShowAnswer = true;
                notifyItemChanged(position);
            }
        });
        if (item.isShowAnswer) {
            holder.answerTv.setText("本段采分点:" + item.answer);
        } else {
            holder.answerTv.setText("本段采分点:");
        }


    }


    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    class VH extends RecyclerView.ViewHolder {

        private final AppCompatTextView contentTv;
        private final AppCompatTextView answerTv;
        private final AppCompatEditText et;

        public VH(@NonNull View itemView) {
            super(itemView);
            contentTv = itemView.findViewById(R.id.item_item_content);
            answerTv = itemView.findViewById(R.id.item_item_answer);
            et = itemView.findViewById(R.id.item_item_answer_et);
        }
    }
}
