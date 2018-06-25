package org.bill.logger.viewmodel;

/**
 * 作者：Bill
 * 时间：2018-06-13 11:45
 * 描述：BaseViewModel
 */
public class BaseViewModel<T extends IBaseViewModelListener>
{
    //ViewModel监听器
    private T viewModelListener;

    /**
     * 获取ViewModel监听器
     * @return
     */
    public T getViewModelListener()
    {
        return viewModelListener;
    }

    /**
     * 设置ViewModel监听器
     * @param viewModelListener
     */
    public void setViewModelListener(T viewModelListener)
    {
        this.viewModelListener = viewModelListener;
    }
}
