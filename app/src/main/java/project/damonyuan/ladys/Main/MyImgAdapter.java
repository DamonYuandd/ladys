package project.damonyuan.ladys.Main;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import project.damonyuan.ladys.R;

/**
 * Created by damonyuan on 2016/6/23.
 */

public class MyImgAdapter extends RecyclerView.Adapter<MyImgAdapter.ViewHolder>  {
    private ArrayList<String> mDataset,linkArr;
    private MyPage mp;
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;
    private int positionInt;
    //define interface
    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, String data);
    }


    // Provide a reference to the type of views that you are using
    // (custom viewholder)
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;

        public ViewHolder(TextView v) {
            super(v);
            mTextView = v;
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyImgAdapter(ArrayList<String> myDataset, MyPage mp, ArrayList<String> linkArr) {
        this.mp = mp;
        this.linkArr = linkArr;
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyImgAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                      int viewType) {
        View v;
            v = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.imglist, parent, false);
        // set the view's size, margins, paddings and layout parameters

        ViewHolder vh = new ViewHolder((TextView) v);

        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        if (position == getItemCount() - 1){
            mp.fragmentLast(position);
        }
        this.positionInt = position;
        holder.mTextView.setText(" \u3000"+mDataset.get(position));
        //将数据保存在itemView的Tag中，以便点击时进行获取
        holder.itemView.setTag(linkArr.get(position));
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
}
