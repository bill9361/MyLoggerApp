package org.bill.logger.viewmodel;

import android.databinding.ObservableArrayMap;
import android.databinding.ObservableField;
import android.databinding.ObservableMap;
import android.util.Log;

import org.bill.logger.logger.Logger;
import org.bill.logger.model.pojo.User;

/**
 * 作者：Bill
 * 时间：2018-06-12 15:50
 * 描述：用户信息ViewModel
 */
public class UserInfoViewModel extends BaseViewModel<UserInfoViewModel.IUserInfoViewModelListener>
{
    //public final ObservableField<User> userOF = new ObservableField<>();
    public final ObservableMap<String,Object> userFiledMap = new ObservableArrayMap<>();

    private int index = 1;


    /**
     * 加载用户信息
     */
    public void loadUserInfo()
    {
        User user = new User();
        user.setUserName("mbfeng@bgb.com.cn");
        user.setAge((int)(Math.random()*100));

        if (index % 2 == 0) user.setHeadImageUrl("https://avatar.csdn.net/6/F/7/1_u011002668.jpg?1523959103");
        else user.setHeadImageUrl("http://img1.dzwww.com:8080/tupian_pl/20150813/16/7858995348613407436.jpg");
        user.setJob("AA");

        index++;

        userFiledMap.put(Keys.USER_NAME,user.getUserName());
        userFiledMap.put(Keys.AGE,user.getAge());
        userFiledMap.put(Keys.JOB,user.getJob());
        userFiledMap.put(Keys.HEAD_IMAGE_URL,user.getHeadImageUrl());

        Logger.i("User："+user);

        if (getViewModelListener() != null) getViewModelListener().loadUserInfoCompleted();

    }

    /**
     * 用户信息ViewModel监听器
     */
    public interface IUserInfoViewModelListener extends IBaseViewModelListener
    {
        //加载用户信息完成
        public void loadUserInfoCompleted();
    }

    public final class Keys
    {
        public static final String USER_NAME = "userName";
        public static final String AGE = "age";
        public static final String JOB = "job";
        public static final String HEAD_IMAGE_URL = "headImageUrl";
    }

}
