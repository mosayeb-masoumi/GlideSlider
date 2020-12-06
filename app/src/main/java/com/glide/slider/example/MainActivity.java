package com.glide.slider.example;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.request.RequestOptions;
import com.glide.slider.library.animations.DescriptionAnimation;
import com.glide.slider.library.SliderLayout;
import com.glide.slider.library.indicators.PagerIndicator;
import com.glide.slider.library.slidertypes.BaseSliderView;
import com.glide.slider.library.slidertypes.TextSliderView;
import com.glide.slider.library.tricks.ViewPagerEx;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements BaseSliderView.OnSliderClickListener,
        ViewPagerEx.OnPageChangeListener {

    private SliderLayout mDemoSlider;

    TextView txt;

    ArrayList<String> listName;
    ArrayList<String> listUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDemoSlider = findViewById(R.id.slider);

        txt = findViewById(R.id.txt);

        listUrl = new ArrayList<>();
        listName = new ArrayList<>();


        listUrl.add("https://images.ctfassets.net/hrltx12pl8hq/VZW7M82mrxByGHjvze4wu/b8d827530fa4f4619748010ada62765d/shutterstock_741805882_C.jpg?fit=fill&w=800&h=450");
        listName.add("JPG - Github");

        listUrl.add("https://ryder-daviesvets.co.uk/wp-content/uploads/2016/03/beautiful-sky-tree-wallpaper.jpg");
        listName.add("PNG - Android Studio");

        listUrl.add("https://2.bp.blogspot.com/-B2AIu6n6kTE/XJkQCZVyogI/AAAAAAAAC74/hQbH-HbF0KsLBjAgCdzFguG6aK56KxmGACLcBGAs/s1600/nature%2Bwallpaper%2B4.jpg");
        listName.add("GIF - Disney");

        listUrl.add("https://filedn.com/ltOdFv1aqz1YIFhf4gTY8D7/ingus-info/BLOGS/Photography-stocks3/stock-photography-slider.jpg");
        listName.add("WEBP - Mountain");

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.centerCrop();

        for (int i = 0; i < listUrl.size(); i++) {
            TextSliderView sliderView = new TextSliderView(this);
            // initialize SliderLayout
            sliderView
                    .image(listUrl.get(i))
                    .description(listName.get(i))
                    .setRequestOption(requestOptions)
                    .setProgressBarVisible(false)
                    .setOnSliderClickListener(this);

            //add your extra information
            sliderView.bundle(new Bundle());
            sliderView.getBundle().putString("extra", listName.get(i));
            mDemoSlider.addSlider(sliderView);
            mDemoSlider.setIndicatorVisibility(PagerIndicator.IndicatorVisibility.Visible);


        }

        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.CubeIn);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(3000);
        mDemoSlider.addOnPageChangeListener(this);
        mDemoSlider.stopCyclingWhenTouch(false);
    }

    @Override
    protected void onStop() {
        // To prevent a memory leak on rotation, make sure to call stopAutoCycle() on the slider before activity or fragment is destroyed
        mDemoSlider.stopAutoCycle();
        super.onStop();
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
        Toast.makeText(this, slider.getBundle().getString("extra") + "", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        Log.i("TAG", "onPageScrolled: ");
    }

    @Override
    public void onPageSelected(int position) {
        Log.i("TAG", "onPageScrolled: ");
        for (int i = 0; i < listName.size(); i++) {
            if (position == i) {
             txt.setText(listName.get(i));
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        Log.i("TAG", "onPageScrolled: ");
    }
}