package com.laojiu.app.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.laojiu.app.R;
import com.laojiu.app.bean.DaoThemeBean;
import com.laojiu.app.bean.StemBean;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.VH> {

    private Context mContext;
    private List<DaoThemeBean> mList;

    public MainAdapter(Context context) {
        mContext = context;
    }

    public void setDate(List<DaoThemeBean> themeList) {
        mList = themeList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.item_main, null);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        DaoThemeBean item = mList.get(position);
        holder.title.setText("" + item.theme);
        List<StemBean> beanList = item.stemBeanList;
        initRV(holder.itemRv, beanList);
    }


    private void initRV(RecyclerView view, List<StemBean> beanList) {
        view.setLayoutManager(new GridLayoutManager(mContext, 1));
        MainItemAdapter adapter = new MainItemAdapter(mContext, beanList);
        view.setAdapter(adapter);
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    class VH extends RecyclerView.ViewHolder {

        private final AppCompatTextView title;
        private final RecyclerView itemRv;

        public VH(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.item_title);
            itemRv = itemView.findViewById(R.id.item_rv);
        }
    }
}
