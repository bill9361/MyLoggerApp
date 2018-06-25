package org.bill.logger.viewmodel;

import android.text.TextUtils;

import org.bill.logger.R;
import org.bill.logger.app.App;
import org.bill.logger.logger.ExceptionUtil;
import org.bill.logger.logger.Logger;
import org.bill.logger.model.pojo.Result;
import org.bill.logger.model.pojo.User;
import org.bill.logger.model.service.UserService;

import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import okhttp3.Headers;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 作者：Bill
 * 时间：2018-06-8 15:00
 * 描述：用户信息ViewModel
 */
public class LoginViewModel extends BaseViewModel<LoginViewModel.ILoginViewModelListener>
{
     //private LoginTask mLoginTask;
     //默认请求超时时间是10秒
     private OkHttpClient okHttpClient = new OkHttpClient.Builder()
             .readTimeout(3,TimeUnit.MINUTES)
             .writeTimeout(3,TimeUnit.MINUTES)
             .connectTimeout(3, TimeUnit.MINUTES)
             .build();

    /**
     * http://www.dbis.cn:8081/matrix/sysUser/mobileRole.do
     * 登录
     */
    public void login(String userName,String password)
    {
        try {
            if (TextUtils.isEmpty(userName) && TextUtils.isEmpty(password))
            {
                Logger.i("用户名和密码不能为空");
            }

            if (getViewModelListener() != null) getViewModelListener().startLoading();
            Logger.i("登录：userName:"+userName+"\tpassword:"+password);
            //Retrofit直接在当前线程（主线程）发起异步请求，最后收到的请求结果是在主线程中
            Retrofit loginRetrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(App.appContext.getString(R.string.base_url))
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            UserService userService = loginRetrofit.create(UserService.class);
            //Call<Result<User>> callResult = userService.appLogin(userName,password);
            //Call<Result<User>> callResult = userService.appLoginGetParam(userName,password);
            Call<Result<User>> callResult = userService.appLoginPost(userName,password);

            //真正进行网络请求
            callResult.enqueue(new Callback<Result<User>>() {
                @Override
                public void onResponse(Call<Result<User>> call, Response<Result<User>> response)
                {
                    if (getViewModelListener() != null) getViewModelListener().stopLoading();
                    if (response != null)
                    {
                        /*Headers headers = response.headers();
                        if (headers != null)
                        {
                            Iterator<String> it = headers.names().iterator();
                            if (it != null)
                            {
                                while (it.hasNext())
                                {
                                    String headerName = it.next();
                                    String headerValue = headers.get(headerName);
                                    Logger.i(headerName+"="+headerValue);
                                }
                            }
                        }*/

                        String setCookie = response.headers().get("Set-Cookie");
                        Logger.i("Set-Cookie:"+setCookie);

                        Result<User> loginResult = response.body();
                        Logger.i("response:"+response);
                        Logger.i("response.body():"+loginResult);
                        if (loginResult != null)
                        {
                            if(loginResult.isSuccess())
                            {
                                Logger.i("登录成功");
                                if (getViewModelListener() != null) getViewModelListener().loginSuccessed();
                                return;
                            }
                            else
                            {
                                Logger.i("登录失败,"+loginResult.getMessage());
                                return;
                            }
                        }
                        else
                        {
                            Logger.i("登录失败,"+response);
                            return;
                        }
                    }
                    Logger.i("登录失败");
                }

                @Override
                public void onFailure(Call<Result<User>> call, Throwable t)
                {
                    Logger.i("登录异常"+ ExceptionUtil.getCrashContent(t));
                    if (getViewModelListener() != null) getViewModelListener().stopLoading();
                }
            });
        } catch (Exception e)
        {
            if (getViewModelListener() != null) getViewModelListener().stopLoading();
            Logger.i("登录异常"+ ExceptionUtil.getCrashContent(e));
            Logger.e2s(e);
        }
    }

    /**
     * 登录异步任务
     */
    /*private class LoginTask extends AsyncTask<Integer,Integer,Boolean>
    {
        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            if (getViewModelListener() != null) getViewModelListener().startLoading();
        }

        @Override
        protected Boolean doInBackground(Integer... integers)
        {
            //必须在doInBackground()里面判断isCancelled,并让Cancelled的任务执行完，程序才会执行下一个异步任务
            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean)
        {
            super.onPostExecute(aBoolean);
            if (getViewModelListener() != null) getViewModelListener().stopLoading();
        }

        @Override
        protected void onCancelled()
        {
            super.onCancelled();
        }
    }*/

    /**
     * 取消登录异步任务
     */
    /*public void cancelLoginTask()
    {
        if (mLoginTask != null && !mLoginTask.isCancelled())
        {
            //一般推荐使用 cancel(false)，使用 cancel(true) 无法保证后台线程一定会被销毁
            mLoginTask.cancel(false);
            mLoginTask = null;
            if (getViewModelListener() != null) getViewModelListener().stopLoading();
        }
    }*/


    /**
     * 登录ViewModel监听器
     */
    public interface ILoginViewModelListener extends IBaseViewModelListener
    {
        //登录成功
        public void loginSuccessed();
        //登录失败
        public void loginFailed(String message);
    }
}
