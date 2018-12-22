package com.example.ym.draggridview;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private DragGridView gridView;
    private ManageAlbumAdpter managerMusicAdpter;
    private List<ImgBean> ImageList;
    private Context mContext = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridView=findViewById(R.id.mGridView);
        initData();
        setAdpter();
    }
    private void initData(){
        ImageList=new ArrayList<>();
        for (int i = 0; i <10; i++) {
            ImgBean imgBean=new ImgBean();
            if (i%2==0)
            {
                imgBean.setImgurl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1545476392400&di=b582faad60ca22994c5ed6f42d4ea43a&imgtype=0&src=http%3A%2F%2Fimg18.3lian.com%2Fd%2Ffile%2F201709%2F21%2F59e94ba7e662f397788f7d101fad511c.jpg");
            }
          else if(i%3==0)
            {
                imgBean.setImgurl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1545476573271&di=f7b9e94afdb2a3932660809b3ac40b3d&imgtype=0&src=http%3A%2F%2Fpic30.nipic.com%2F20130605%2F7447430_160115320000_2.jpg");
            }
        else if (i%5==0)
            {
                imgBean.setImgurl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1545476627473&di=2c5a52374aea8cde841248a7fe5289e0&imgtype=0&src=http%3A%2F%2Fpic22.photophoto.cn%2F20120301%2F0005018344891909_b.jpg");
            }
            else {
                imgBean.setImgurl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1545476658765&di=dbbb9101a56831b1bc0d38bf044bb309&imgtype=0&src=http%3A%2F%2Fseopic.699pic.com%2Fphoto%2F50066%2F5114.jpg_wh1200.jpg");
            }
            ImageList.add(imgBean);
        }
    }
    /**
     * 设置适配器
     */
    private void setAdpter() {
        managerMusicAdpter = new ManageAlbumAdpter(mContext, ImageList);
        gridView.setAdapter(managerMusicAdpter);
        gridView.setOnItemChangeListener(new DragGridView.OnItemChangeListener() {
            @Override
            public void onChange(int from, int to) {
                Toast.makeText(mContext, "From:" + from + "  To:" + to, Toast.LENGTH_LONG);

                ImgBean temp = ImageList.get(from);
                //直接交互
                //Collections.swap(dataSourceList,from,to);

                //非直接交互 这里的处理需要注意下 排序交换
                if (from < to) {
                    for (int i = from; i < to; i++) {
                        Collections.swap(ImageList, i, i + 1);
                    }
                } else if (from > to) {
                    for (int i = from; i > to; i--) {
                        Collections.swap(ImageList, i, i - 1);
                    }
                }
                ImageList.set(to, temp);
                managerMusicAdpter.notifyDataSetChanged();
            }
        });
    }
}
