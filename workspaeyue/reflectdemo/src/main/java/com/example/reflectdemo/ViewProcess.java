package com.example.reflectdemo;

import android.app.Activity;

import java.lang.reflect.Field;

public class ViewProcess {
    /**
     * 绑定Activity
     */
    public static void injectViews(Activity activity)
    {
        //获取activity的class
        Class<? extends Activity> object = activity.getClass();
        //通过Class获取activity的所有属性
        Field[] fields = object.getDeclaredFields();
        for (Field field:fields)
        {
            //获取字段的注解的参数 这就是控件的id

        }
    }
}
