package com.example.ym.draggridview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;


import com.bumptech.glide.Glide;

import java.util.List;

public class ManageAlbumAdpter extends BaseAdapter {
    private List<ImgBean> list = null;
    private LayoutInflater inflater = null;
    private Context context;
    public ManageAlbumAdpter(Context context, List<ImgBean> list) {
        this.list = list;
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public ImgBean getItem(int position) {
        return list.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ManageAlbumAdpter.ViewHolder mHolder ;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_team_manage, parent, false);
            mHolder = new ManageAlbumAdpter.ViewHolder(convertView);
            convertView.setTag(mHolder);
        } else {
            mHolder = (ManageAlbumAdpter.ViewHolder) convertView.getTag();
        }
        if (list.get(position).isChecked()){
            mHolder.checkBox.setChecked(true);
        }else {
            mHolder.checkBox.setChecked(false);
        }
        mHolder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (list.get(position).isChecked()){
                    list.get(position).setChecked(false);
                 notifyDataSetChanged();
                }else {
                    list.get(position).setChecked(true);
                    notifyDataSetChanged();
                }
            }
        });
        Glide.with(context).load(list.get(position).getImgurl()).transform(new GlideRoundTransform(context,8)).into(mHolder.iv_img);

        return convertView;
    }
    class ViewHolder {
        CheckBox checkBox;
        private ImageView iv_img,iv_label;
        public ViewHolder(View view) {
            checkBox = (CheckBox) view.findViewById(R.id.checkHaveCar);//完成状态
            iv_img = (ImageView) view.findViewById(R.id.iv_img);//图片

        }
    }
}
