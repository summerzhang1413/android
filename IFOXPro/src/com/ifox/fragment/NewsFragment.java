package com.ifox.fragment;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.ifox.layout.SlideImageLayout;
import com.ifox.main.R;
import com.ifox.parser.NewsXmlParser;
import com.ifox.util.ActivityUtils;

public class NewsFragment extends ListFragment {
	private com.ifox.util.ScrollViewExtend scroll; 
	private ImageView imageView1;
	private ListView news_listview;
	private ArrayList<HashMap<String, Object>> listItems;   //存放文字、图片信息  
    private SimpleAdapter listItemAdapter;                  //适配器     
//	private DisplayMetrics dm;

	// 滑动图片的集合
	private ArrayList<View> imagePageViews = null;
	private ViewGroup main = null;
	private ViewPager viewPager = null;
	// 当前ViewPager索引
	private int pageIndex = 0; 
	
	// 包含圆点图片的View
	private ViewGroup imageCircleView = null;
	private ImageView[] imageCircleViews = null; 
	
	// 滑动标题
	private TextView tvSlideTitle = null;
	
	// 布局设置类
	private SlideImageLayout slideLayout = null;
	// 数据解析类
	private NewsXmlParser parser = null; 

	LayoutInflater inflater;
	private float rawX ;
	private float rawY ;
	private float x ;
	private float y ;
	
	// 滑动图片的集合

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		this.inflater = inflater ;
		initeViews() ;
		return inflater.inflate(R.layout.suibian, main);
		
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
	}
	
	
	
	private void initeViews(){
		initListview();
		this.setListAdapter(listItemAdapter);
		
		// 滑动图片区域
		imagePageViews = new ArrayList<View>();
		//LayoutInflater inflater = getLayoutInflater();  
		main = (ViewGroup)inflater.inflate(R.layout.news_fragment, null);
		viewPager = (ViewPager) main.findViewById(R.id.image_slide_page);  //得到ViewPager
		//取得屏幕参数 并设置viewpager属性
//		dm = new DisplayMetrics();
//		getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
//		LayoutParams para = (LayoutParams)viewPager.getLayoutParams() ;
//		para.width = dm.widthPixels ;
//		para.height = dm.heightPixels/3 ;
//		viewPager.setLayoutParams(para) ;
		
		
		scroll=(com.ifox.util.ScrollViewExtend)main.findViewById(R.id.it_is_scroll);
		//scroll.setOnTouchListener(new ImageOnTouchListener()) ;
		// 圆点图片区域
		parser = new NewsXmlParser();
		int length = parser.getSlideImages().length;
		imageCircleViews = new ImageView[length];//实例化圆点图片 
		imageCircleView = (ViewGroup) main.findViewById(R.id.layout_circle_images);
		slideLayout = new SlideImageLayout(getActivity());
		slideLayout.setCircleImageLayout(length);
		
		for(int i = 0;i < length;i++){
			imagePageViews.add(slideLayout.getSlideImageLayout(parser.getSlideImages()[i]));//得到滚动图片
			imageCircleViews[i] = slideLayout.getCircleImageLayout(i);
			imageCircleView.addView(slideLayout.getLinearLayout(imageCircleViews[i], 10, 10));
		}
		
		// 设置默认的滑动标题
		tvSlideTitle = (TextView) main.findViewById(R.id.tvSlideTitle);
		tvSlideTitle.setText(parser.getSlideTitles()[0]);
		
		//getActivity().setContentView(main);
		//inflater.inflate(R.layout.main, main)  ;
		
		// 设置ViewPager
        viewPager.setAdapter(new SlideImageAdapter());  
        viewPager.setCurrentItem(0) ;
        viewPager.setOnPageChangeListener(new ImagePageChangeListener());
	}
	//加载新闻列表内容
	private void initListview(){
		listItems = new ArrayList<HashMap<String, Object>>();  
        for(int i=1;i<10;i++)     
       {     
           HashMap<String, Object> map = new HashMap<String, Object>();     
//           map.put("ItemImage", R.drawable.image03);
           map.put("ItemTitle", i+".【华硕推出廉价版Fonepad 7】");
           map.put("ItemContent", "在其他方面，ME175相比之前发布的Fonepad 7除去了前置双喇叭，降低了处理器。但是500万后置摄像头与120万前置摄像头依然保留。ME175定价7990新台币，约合人民币1650元左右。");
           listItems.add(map);     
       }     
       //生成适配器的Item和动态数组对应的元素     
        listItemAdapter = new SimpleAdapter(getActivity(),listItems,//数据源      
                R.layout.news_list_item,//ListItem的XML布局实现     
                //动态数组与ImageItem对应的子项             
                new String[] {"ItemTitle","ItemContent"},      
                //ImageItem的XML文件里面的一个ImageView,两个TextView ID     
                new int[] {R.id.newsTitle,R.id.newsContent}     
            ); 
	}
 // 滑动图片数据适配器
    private class SlideImageAdapter extends PagerAdapter {  
        @Override  
        public int getCount() { 
            return imagePageViews.size();  
        }  
  
        @Override  
        public boolean isViewFromObject(View arg0, Object arg1) {  
            return arg0 == arg1;  
        }  
  
        @Override  
        public int getItemPosition(Object object) {  
            // TODO Auto-generated method stub  
            return super.getItemPosition(object);  
        }  
  
        @Override  
        public void destroyItem(View arg0, int arg1, Object arg2) {  
            // TODO Auto-generated method stub  
            ((ViewPager) arg0).removeView(imagePageViews.get(arg1));  
        }  
  
        @Override  
        public Object instantiateItem(View arg0, int arg1) {  
            // TODO Auto-generated method stub  
        	((ViewPager) arg0).addView(imagePageViews.get(arg1));
            
            return imagePageViews.get(arg1);  
        }  
  
        @Override  
        public void restoreState(Parcelable arg0, ClassLoader arg1) {  
            // TODO Auto-generated method stub  
  
        }  
  
        @Override  
        public Parcelable saveState() {  
            // TODO Auto-generated method stub  
            return null;  
        }  
  
        @Override  
        public void startUpdate(View arg0) {  
            // TODO Auto-generated method stub  
  
        }  
  
        @Override  
        public void finishUpdate(View arg0) {  
            // TODO Auto-generated method stub  
  
        }  
    }
    
    // 滑动页面更改事件监听器
    private class ImagePageChangeListener implements OnPageChangeListener {
        @Override  
        public void onPageScrollStateChanged(int arg0) {  
            // TODO Auto-generated method stub  
//        	if(arg0==1){
//        		menu.setSlidingEnabled(false);
//        	}else{
//        		menu.setSlidingEnabled(false);
//        	}
        }  
  
        @Override  
        public void onPageScrolled(int arg0, float arg1, int arg2) {  
            // TODO Auto-generated method stub  
//        	menu.setSlidingEnabled(false);
        }  
  
        @Override  
        public void onPageSelected(int index) {  
        	pageIndex = index;
        	slideLayout.setPageIndex(index);
        	tvSlideTitle.setText(parser.getSlideTitles()[index]);
        	
            for (int i = 0; i < imageCircleViews.length; i++) {  
            	imageCircleViews[index].setBackgroundResource(R.drawable.dot_selected);
                
                if (index != i) {  
                	imageCircleViews[i].setBackgroundResource(R.drawable.dot_none);  
                }  
            }
        }  
        
    }
    
    @Override
    public void onResume() {
    	super.onResume();
    	ActivityUtils.clearAll();
    }

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		Intent intent = new Intent();
		intent.setClass(getActivity(), NewsDetailActivity.class);
		startActivity(intent);
	}
	
 // 滑动图片数据适配器
    
    // 滑动页面更改事件监听器
    
	

	

}
