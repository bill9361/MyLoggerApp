package org.bill.logger.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import org.bill.logger.R;
import org.bill.logger.databinding.ActivityLogin1Binding;
import org.bill.logger.logger.Logger;
import org.bill.logger.viewmodel.LoginViewModel;

/**
 * 作者：Bill
 * 时间：2018-06-08 10:00
 * 描述：用户登录
 */
public class Login1Activity extends AppCompatActivity implements LoginViewModel.ILoginViewModelListener
{
    private ActivityLogin1Binding mBinding;
  //  private LoginTask mLoginTask;
    private LoginViewModel mLoginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_login1);
        setSupportActionBar(mBinding.toolbar);

        //输入完之后监听软键盘的回车事件
        //返回true，保留软键盘。false，隐藏软键盘
        mBinding.edtPassword.setOnEditorActionListener(new TextView.OnEditorActionListener()
        {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event)
            {
                if (actionId == EditorInfo.IME_ACTION_GO)
                {
                    login();
                    return true;
                }
                return false;
            }
        });

        mBinding.btnLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                login();
            }
        });

        mLoginViewModel = new LoginViewModel();
        mLoginViewModel.setViewModelListener(this);
        mBinding.setLoginViewModel(mLoginViewModel);

    }

    /**
     * 登录
     */
    private void login()
    {
        String userName = mBinding.edtUsername.getText().toString().trim();
        String password = mBinding.edtPassword.getText().toString().trim();

        if (isUserNameVaild(userName) && isPasswordVaild(password))
        {
            if(mLoginViewModel != null) mLoginViewModel.login(userName,password);
        }
    }

    /**
     * 用户名验证，必须是手机号或者邮箱
     * @param userName
     * @return
     */
    private boolean isUserNameVaild(String userName)
    {
        if (TextUtils.isEmpty(userName))
        {
            showEditTextError(mBinding.edtUsername,getString(R.string.login1_error_username_empty));
            return false;
        }
        /*if (!isEmailVaild(userName) && !isPhoneNumber(userName))
        {
            showError(mBinding.edtUsername,getString(R.string.login1_error_username_format));
            return false;
        }*/
        return true;
    }

    /**
     * 密码验证，长度必须[6-16]
     * @param password
     * @return
     */
    private boolean isPasswordVaild(String password)
    {
        if (TextUtils.isEmpty(password))
        {
            showEditTextError(mBinding.edtPassword,getString(R.string.login1_error_password_empty));
            return false;
        }
        if (2 > password.length() || password.length() > 16)
        {
            showEditTextError(mBinding.edtPassword,getString(R.string.login1_error_password_length_range));
            return false;
        }

        return true;
    }

    /**
     * 显示错误信息
     * @param view
     * @param error
     */
    private void showEditTextError(EditText view, String error)
    {
        if (view != null )
        {
            view.setError(error);
            view.requestFocus();
        }
    }

    /*
    @Override
    public void onBackPressed()
    {
        if(mLoginViewModel != null) mLoginViewModel.cancelLoginTask();
        super.onBackPressed();
    }*/



    @Override
    public void startLoading()
    {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.loading_anim);
        //匀速加载
        animation.setInterpolator(new LinearInterpolator());
        mBinding.btnLogin.setText("");
        mBinding.loadingLayout.setVisibility(View.VISIBLE);
        mBinding.imageViewRefreshing.startAnimation(animation);

    }

    @Override
    public void stopLoading()
    {
        mBinding.imageViewRefreshing.clearAnimation();
        mBinding.btnLogin.setText(R.string.login1_btn_login);
        mBinding.loadingLayout.setVisibility(View.GONE);

    }

    /**
     * 登录成功回调
     */
    @Override
    public void loginSuccessed()
    {
        Intent intent = new Intent(this,UserInfoActivity.class);
        startActivity(intent);
    }

    /**
     * 登录失败回调
     * @param message
     */
    @Override
    public void loginFailed(String message)
    {

    }
}
