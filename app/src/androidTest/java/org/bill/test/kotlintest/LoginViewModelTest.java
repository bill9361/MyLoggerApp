package org.bill.test.kotlintest;


import org.bill.logger.logger.Logger;
import org.bill.logger.viewmodel.LoginViewModel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * 作者：Bill
 * 时间：2018-06-19 13:50
 * 描述：LoginViewModel单元测试
 */
//androidTest测试目录是android 自己本身的主要是测UI测试,test是非包含android.*的测试
//@RunWith(AndroidJUnit4.class)
public class LoginViewModelTest
{
    private LoginViewModel loginViewModel;

    @Before
    public void setup()
    {
        if(loginViewModel == null) loginViewModel = new LoginViewModel();
    }

    @Test
    public void testLogin() throws Exception
    {
        Logger.i("testLogin()");
        loginViewModel.login("root","888888");
    }

    @Test
    public void testa() throws Exception
    {
        Logger.i("testa()");
    }

    @After
    public void end()
    {
    }
}