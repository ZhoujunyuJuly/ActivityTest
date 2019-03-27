package com.example.fragment;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView mOne;
    TextView mTwo;
    TextView mThree;
    FrameLayout ly_content;


    private MyFragment fg1,fg2,fg3;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
        initView();


    }

    private void initView(){
        mOne = findViewById(R.id.tv_one);
        mTwo = findViewById(R.id.tv_two);
        mThree = findViewById(R.id.tv_three);
        ly_content = findViewById(R.id.fragmentlayout);

        mOne.setOnClickListener(this);
        mTwo.setOnClickListener(this);
        mThree.setOnClickListener(this);
    }

    private void setSelected(){
        mOne.setSelected(false);
        mTwo.setSelected(false);
        mThree.setSelected(false);
    }

    //隐藏所有Fragment
    private void hideAllFragment(FragmentTransaction fragmentTransaction){
        if(fg1 != null)fragmentTransaction.hide(fg1);
        if(fg2 != null)fragmentTransaction.hide(fg2);
        if(fg3 != null)fragmentTransaction.hide(fg3);
    }



    @Override
    public void onClick(View v){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        hideAllFragment(fragmentTransaction);

        switch (v.getId()){
            case R.id.tv_one:
                setSelected();
                mOne.setSelected(true);
                if(fg1 == null){
                    fg1 = new MyFragment("first");
                    fragmentTransaction.add(R.id.fragmentlayout,fg1);
                }else {
                    fragmentTransaction.show(fg1);
                }
                break;

            case R.id.tv_two:
                setSelected();
                mTwo.setSelected(true);
                if(fg2 == null){
                    fg2 = new MyFragment("second");
                    fragmentTransaction.add(R.id.fragmentlayout,fg2);
                }else {
                    fragmentTransaction.show(fg2);
                }
                break;

            case R.id.tv_three:
                setSelected();
                mThree.setSelected(true);
                if(fg3 == null){
                    fg3 = new MyFragment("third");
                    fragmentTransaction.add(R.id.fragmentlayout,fg3);
                }else {
                    fragmentTransaction.show(fg3);
                }
                break;
        }

        fragmentTransaction.commit();
    }

}
