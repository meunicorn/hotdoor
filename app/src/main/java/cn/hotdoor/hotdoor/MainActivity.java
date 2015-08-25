package cn.hotdoor.hotdoor;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;
import com.bigkoo.convenientbanner.CBPageAdapter;
import com.bigkoo.convenientbanner.CBViewHolderCreator;
import com.bigkoo.convenientbanner.ConvenientBanner;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ConvenientBanner banner;//顶部图片
    private List<Integer> images;
    private ImageView imageView;
    private ViewPager pager;
    private PagerSlidingTabStrip tabs;
    private List<View>views;
    private View view1,view2,view3,view4;
    private MyPagerAdapter adapter;
    private List<String>titles;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        MyAsynTask task=new MyAsynTask();
        task.execute();
        pager.setAdapter(adapter);
        tabs.setViewPager(pager);
        banner.startTurning(2000);

    }

    private void bannerMethod() {
        banner.setPages(new CBViewHolderCreator() {
            @Override
            public Object createHolder() {
                return new LocalImageHolderView();
            }
        },images).setPageIndicator(new int[]{R.drawable.ic_page_indicator, R.drawable.ic_page_indicator_focused}).setPageTransformer(ConvenientBanner.Transformer.DefaultTransformer);
    }

    private void init() {
        banner= (ConvenientBanner) findViewById(R.id.main_banner_top);
        pager= (ViewPager) findViewById(R.id.main_pager_content);
        adapter=new MyPagerAdapter();
        tabs= (PagerSlidingTabStrip) findViewById(R.id.tabs);
        tabs.setUnderlineColor(Color.TRANSPARENT);
        tabs.setIndicatorHeight(4);
        tabs.setIndicatorColor(Color.parseColor("#34B9E4"));
        titles=new ArrayList<>();
        titles.add("公司介绍");
        titles.add("专利证书");
        titles.add("公司荣誉");
        titles.add("合作伙伴");

//        tabs= (PagerSlidingTabStrip) findViewById(R.id.main_psts_tabs);
//        tabs.setViewPager(pager);

        views=new ArrayList<>();
        view1=View.inflate(this, R.layout.layout_test, null);
        view2=View.inflate(this, R.layout.layout_test, null);
        view3=View.inflate(this, R.layout.layout_test, null);
        view4=View.inflate(this, R.layout.layout_test, null);
        views.add(view1);
        views.add(view2);
        views.add(view3);
        views.add(view4);

        images=new ArrayList<>();
        images.add(R.drawable.pic1);
        images.add(R.drawable.pic2);
        images.add(R.drawable.pic3);
        images.add(R.drawable.pic4);
        images.add(R.drawable.pic5);
        images.add(R.drawable.pic6);
        images.add(R.drawable.pic7);

    }
    public class LocalImageHolderView implements CBPageAdapter.Holder<Integer>{

        @Override
        public View createView(Context context) {
            imageView=new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            return imageView;
        }

        @Override
        public void UpdateUI(Context context, final int position, Integer data) {
            imageView.setImageResource(data);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this, "你点了第 "+position+" 张图片", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
    class MyAsynTask extends AsyncTask<Integer,Void,Void>{
        @Override
        protected Void doInBackground(Integer... params) {
            bannerMethod();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }

    class MyPagerAdapter extends PagerAdapter{
        @Override
        public int getCount() {
            return views.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public int getItemPosition(Object object) {
            return super.getItemPosition(object);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(views.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(views.get(position));
            return views.get(position);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }
    }



}
