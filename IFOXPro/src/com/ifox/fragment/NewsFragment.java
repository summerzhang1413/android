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
	private ArrayList<HashMap<String, Object>> listItems;   //������֡�ͼƬ��Ϣ  
    private SimpleAdapter listItemAdapter;                  //������     
//	private DisplayMetrics dm;

	// ����ͼƬ�ļ���
	private ArrayList<View> imagePageViews = null;
	private ViewGroup main = null;
	private ViewPager viewPager = null;
	// ��ǰViewPager����
	private int pageIndex = 0; 
	
	// ����Բ��ͼƬ��View
	private ViewGroup imageCircleView = null;
	private ImageView[] imageCircleViews = null; 
	
	// ��������
	private TextView tvSlideTitle = null;
	
	// ����������
	private SlideImageLayout slideLayout = null;
	// ���ݽ�����
	private NewsXmlParser parser = null; 

	LayoutInflater inflater;
	private float rawX ;
	private float rawY ;
	private float x ;
	private float y ;
	
	// ����ͼƬ�ļ���

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
		
		// ����ͼƬ����
		imagePageViews = new ArrayList<View>();
		//LayoutInflater inflater = getLayoutInflater();  
		main = (ViewGroup)inflater.inflate(R.layout.news_fragment, null);
		viewPager = (ViewPager) main.findViewById(R.id.image_slide_page);  //�õ�ViewPager
		//ȡ����Ļ���� ������viewpager����
//		dm = new DisplayMetrics();
//		getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
//		LayoutParams para = (LayoutParams)viewPager.getLayoutParams() ;
//		para.width = dm.widthPixels ;
//		para.height = dm.heightPixels/3 ;
//		viewPager.setLayoutParams(para) ;
		
		
		scroll=(com.ifox.util.ScrollViewExtend)main.findViewById(R.id.it_is_scroll);
		//scroll.setOnTouchListener(new ImageOnTouchListener()) ;
		// Բ��ͼƬ����
		parser = new NewsXmlParser();
		int length = parser.getSlideImages().length;
		imageCircleViews = new ImageView[length];//ʵ����Բ��ͼƬ 
		imageCircleView = (ViewGroup) main.findViewById(R.id.layout_circle_images);
		slideLayout = new SlideImageLayout(getActivity());
		slideLayout.setCircleImageLayout(length);
		
		for(int i = 0;i < length;i++){
			imagePageViews.add(slideLayout.getSlideImageLayout(parser.getSlideImages()[i]));//�õ�����ͼƬ
			imageCircleViews[i] = slideLayout.getCircleImageLayout(i);
			imageCircleView.addView(slideLayout.getLinearLayout(imageCircleViews[i], 10, 10));
		}
		
		// ����Ĭ�ϵĻ�������
		tvSlideTitle = (TextView) main.findViewById(R.id.tvSlideTitle);
		tvSlideTitle.setText(parser.getSlideTitles()[0]);
		
		//getActivity().setContentView(main);
		//inflater.inflate(R.layout.main, main)  ;
		
		// ����ViewPager
        viewPager.setAdapter(new SlideImageAdapter());  
        viewPager.setCurrentItem(0) ;
        viewPager.setOnPageChangeListener(new ImagePageChangeListener());
	}
	//���������б�����
	private void initListview(){
		listItems = new ArrayList<HashMap<String, Object>>();  
        for(int i=1;i<10;i++)     
       {     
           HashMap<String, Object> map = new HashMap<String, Object>();     
//           map.put("ItemImage", R.drawable.image03);
           map.put("ItemTitle", i+".����˶�Ƴ����۰�Fonepad 7��");
           map.put("ItemContent", "���������棬ME175���֮ǰ������Fonepad 7��ȥ��ǰ��˫���ȣ������˴�����������500���������ͷ��120��ǰ������ͷ��Ȼ������ME175����7990��̨�ң�Լ�������1650Ԫ���ҡ�");
           listItems.add(map);     
       }     
       //������������Item�Ͷ�̬�����Ӧ��Ԫ��     
        listItemAdapter = new SimpleAdapter(getActivity(),listItems,//����Դ      
                R.layout.news_list_item,//ListItem��XML����ʵ��     
                //��̬������ImageItem��Ӧ������             
                new String[] {"ItemTitle","ItemContent"},      
                //ImageItem��XML�ļ������һ��ImageView,����TextView ID     
                new int[] {R.id.newsTitle,R.id.newsContent}     
            ); 
	}
 // ����ͼƬ����������
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
    
    // ����ҳ������¼�������
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
	
 // ����ͼƬ����������
    
    // ����ҳ������¼�������
    
	

	

}