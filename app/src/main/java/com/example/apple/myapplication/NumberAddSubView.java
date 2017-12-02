package com.example.apple.myapplication;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.widget.TintTypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.apple.myapplication.R;



public class NumberAddSubView extends LinearLayout implements View.OnClickListener {

    private ImageView btn_sub;
    private ImageView btn_add;
    private TextView tv_count;
    private int value = 1;
    private int minValue = 1;
    private int maxValue = 10;
    //取方框中的值
    public int getValue(){
        String constru=tv_count.getText().toString().trim();
        if(constru!=null){
            value=Integer.valueOf(constru);
        }
        return value;

    }

    //设定方框中的值
    public void setValue(int value) {
        this.value = value;
    }
    //getter and setter for minValue and maxValue

    public int getMinValue() {
        return minValue;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }


    public NumberAddSubView(Context context) {
        super(context);
    }

    public NumberAddSubView(Context context,  AttributeSet attrs) {
        super(context, attrs);
    }


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public NumberAddSubView(Context context,  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View.inflate(context,R.layout.add_sub_layout,this);
        btn_add=findViewById(R.id.btn_add);
        btn_sub=findViewById(R.id.btn_sub);
        tv_count=findViewById(R.id.tv_count);

        getValue();

        //设置点击事件
        btn_sub.setOnClickListener(this);
        btn_add.setOnClickListener(this);
        if (attrs != null) {
            //取出属性
              TintTypedArray tintTypedArray = TintTypedArray.obtainStyledAttributes(context, attrs, R.styleable.NumberAddSubView);
              int value = tintTypedArray.getInt(R.styleable.NumberAddSubView_value, 0);
             if (value > 0) {
                 setValue(value);
             }
              int minValue = tintTypedArray.getInt(R.styleable.NumberAddSubView_minValue, 0);
             if (value > 0) {
                 setMinValue(minValue);
             }
                  int maxValue = tintTypedArray.getInt(R.styleable.NumberAddSubView_maxValue, 0);
             if (value > 0) {
                 setMaxValue(maxValue);
             }
             Drawable addDrawable = tintTypedArray.getDrawable(R.styleable.NumberAddSubView_numberAddBackground);
             if (addDrawable != null) {
                 btn_add.setImageDrawable(addDrawable);
             }
             Drawable subDrawable = tintTypedArray.getDrawable(R.styleable.NumberAddSubView_numberSubBackground);
             if (subDrawable != null) {
                 btn_sub.setImageDrawable(subDrawable);
             }
        }
    }


    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.btn_add) {
            addNumber();

            if (onNumberChangeListener != null) {
                onNumberChangeListener.addNumber(v, value);
            }
        }
           else {
               subNumber();
               if (onNumberChangeListener != null) {
                   onNumberChangeListener.addNumber(v, value);
               }
           }
       }

       public void addNumber(){
           if(value<maxValue){
               value=value+1;

           }
           setValue(value);

        }

    public void subNumber(){
        if(value>minValue){
            value=value-1;

        }
        setValue(value);

    }
    public interface OnNumberChangeListener {
        //当按钮被点击的时候回调
        void addNumber(View view, int value);
        void subNumner(View view, int value);
    }
    private OnNumberChangeListener onNumberChangeListener;

    public void setOnNumberChangeListener(OnNumberChangeListener onNumberChangeListener){
    this.onNumberChangeListener=onNumberChangeListener;
    }
}


}
