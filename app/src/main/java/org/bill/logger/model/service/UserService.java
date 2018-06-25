package org.bill.logger.model.service;


import org.bill.logger.model.pojo.Result;
import org.bill.logger.model.pojo.User;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * 作者：Bill
 * 时间：2018-06-14 15:11
 * 描述：用户Service
 */
public interface UserService
{
    /**
     * app登录接口GET请求路径方式
     */
    @GET("login/appLogin/{userName}/{password}.do")
    Call<Result<User>> appLogin(@Path("userName") String userName, @Path("password") String password);

    /**
     * app登录接口GET请求参数方式
     */
    @GET("login/appLoginGetParam.do")
    Call<Result<User>> appLoginGetParam(@Query("userName") String userName, @Query("password") String password);

    /**
     * app登录接口POST请求
     * FormUrlEncoded表单的方式传递键值对
     */
    @POST("login/appLoginPost.do")
    @FormUrlEncoded
    Call<Result<User>> appLoginPost(@Field("userName") String userName, @Field("password") String password);


    /**
     * 获取用户信息
     */
    @POST("login/getUserInfo.do")
    @FormUrlEncoded
    Call<Result<User>> getUserInfo(@Field("userId") String userId);

}
