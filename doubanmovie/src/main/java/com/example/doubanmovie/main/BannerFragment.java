//package com.example.doubanmovie.main;
//
//import android.content.Context;
//import android.net.Uri;
//import android.os.Bundle;
//import android.support.v4.app.Fragment;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Toast;
//
//import com.daimajia.slider.library.Animations.DescriptionAnimation;
//import com.daimajia.slider.library.Indicators.PagerIndicator;
//import com.daimajia.slider.library.SliderLayout;
//import com.daimajia.slider.library.SliderTypes.BaseSliderView;
//import com.daimajia.slider.library.SliderTypes.TextSliderView;
//import com.example.doubanmovie.R;
//import com.example.doubanmovie.model.SubjectsBean;
//
//import java.util.ArrayList;
//import java.util.List;
//
//



//固定广告栏使用独立fragment


//public class BannerFragment extends Fragment {
//
//
//    private SliderLayout mSliderLayout;
//    private PagerIndicator mIndicator;
//
//    public BannerFragment() {
//        // Required empty public constructor
//    }
//
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        View view = inflater.inflate(R.layout.ad_banner, container, false);
//        mSliderLayout = view.findViewById(R.id.ad_banner_id);
//        mIndicator = view.findViewById(R.id.indicator_id);
//
//        //获取图片地址
//        String[] imgURL;
//        String[] movieName;
//        imgURL = ((MainActivity) getActivity()).getmBannerImgURL();
//        movieName = ((MainActivity)getActivity()).getmMovieName();
//
//        Log.d("zjy", "onCreateView: img URL is " +imgURL);
//
//        for (int i = 0; i < 5; i++) {
//            TextSliderView bannerview = new TextSliderView(getActivity());
//            //bannerview.image(imgURL[0]).description("无");
//
//            //bannerview.image("https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2551352209.jpg").description("null")
//            //.setScaleType(BaseSliderView.ScaleType.CenterCrop);
//
//
//            //imgURL[0]="https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2551352209.jp";
//            bannerview.image(imgURL[i]).description("推荐电影： " + movieName[i])
//                    .setScaleType(BaseSliderView.ScaleType.CenterCrop);
//
//
//            mSliderLayout.addSlider(bannerview);
//        }
//
//
//
//        mSliderLayout.setCustomAnimation(new DescriptionAnimation());
//        mSliderLayout.setPresetTransformer(SliderLayout.Transformer.Accordion);
//        mSliderLayout.setDuration(200);
//        mSliderLayout.setCustomIndicator(mIndicator);
//
//        return view;
//
//    }
//
//
//
//
//}
