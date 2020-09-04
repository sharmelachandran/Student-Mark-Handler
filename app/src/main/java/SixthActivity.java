package com.example.demo;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
public class SixthActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    PagerAdapter pagerAdapter;

    TabItem tabecs,tabsop,tabcn,tabdsp,tabsem;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sixth);
        tabLayout=(TabLayout)findViewById(R.id.tablayout);
        tabecs=findViewById(R.id.tb1);
        tabsop=findViewById(R.id.tb2);
        tabcn=findViewById(R.id.tb3);
        tabdsp=findViewById(R.id.tb4);
        tabsem=findViewById(R.id.tb5);
        viewPager=findViewById(R.id.viewpager);

        pagerAdapter=new PageAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);
        tabLayout.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if(tab.getPosition()==1){
                }else if(tab.getPosition()==2){
                }
                else{
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        btn=(Button)findViewById(R.id.button8);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Result.class);
                startActivity(i);
            }
        });
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }
}
