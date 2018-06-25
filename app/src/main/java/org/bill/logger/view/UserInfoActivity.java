package org.bill.logger.view;

import android.databinding.DataBindingUtil;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import org.bill.logger.R;
import org.bill.logger.databinding.ActivityUserInfoBinding;
import org.bill.logger.logger.ExceptionUtil;
import org.bill.logger.logger.Logger;
import org.bill.logger.viewmodel.UserInfoViewModel;

/**
 * 作者：Bill
 * 时间：2018-06-12 16:02
 * 描述：用户信息Activity
 */
public class UserInfoActivity extends AppCompatActivity implements UserInfoViewModel.IUserInfoViewModelListener
{
    private ActivityUserInfoBinding mBinding;
    private UserInfoViewModel mUserInfoViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_user_info);
        mUserInfoViewModel = new UserInfoViewModel();
        //设置监听
        mUserInfoViewModel.setViewModelListener(this);
        mBinding.setUserInfoViewModel(mUserInfoViewModel);
        mUserInfoViewModel.loadUserInfo();
        //设置下拉出现的小圆圈是否缩放，
        //mBinding.swipeRefreshLayout.setProgressViewOffset(true,50,200);
        //设置下拉圆圈的大小
        mBinding.swipeRefreshLayout.setSize(SwipeRefreshLayout.DEFAULT);
        // 设定下拉圆圈的背景
        //mBinding.swipeRefreshLayout.setProgressBackgroundColor(android.R.color.white);
        // 设置下拉圆圈上的颜色，可设置多个（是个可变数组）
        mBinding.swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);

        mBinding.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Logger.i("swipeRefreshLayout.onRefresh()");

                mUserInfoViewModel.loadUserInfo();
            }
        });
    }

    /**
     * 加载用户信息完成
     */
    @Override
    public void loadUserInfoCompleted()
    {
        mBinding.setMap(mUserInfoViewModel.userFiledMap);


        Glide
                .with(this)
                .load(mUserInfoViewModel.userFiledMap.get(UserInfoViewModel.Keys.HEAD_IMAGE_URL).toString())
               /* .listener(new RequestListener() {
                    @Override
                    public boolean onException(Exception e, Object model, Target target, boolean isFirstResource)
                    {
                        Logger.i("RequestListener.onException()");
                        Logger.e2s(e);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Object resource, Object model, Target target, boolean isFromMemoryCache, boolean isFirstResource)
                    {
                       // Logger.i("model:"+model+" isFirstResource: "+isFirstResource);
                        return false;
                    }
                })*/
              //  .thumbnail(0.1f)
                //开启图片的淡入淡出效果
               // .crossFade(3000)
                .placeholder(R.drawable.default_image)
                .into(mBinding.headImg);


        Logger.i("loadUserInfoCompleted()");
        Logger.i("图片URL:"+mUserInfoViewModel.userFiledMap.get(UserInfoViewModel.Keys.HEAD_IMAGE_URL).toString());
        stopLoading();

    }

    @Override
    public void startLoading()
    {
    }

    @Override
    public void stopLoading()
    {
        mBinding.swipeRefreshLayout.setRefreshing(false);
    }
}
