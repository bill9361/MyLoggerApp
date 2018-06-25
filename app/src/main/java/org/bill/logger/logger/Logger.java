package org.bill.logger.logger;

import android.text.TextUtils;
import android.util.Log;

/**
 * 日志记录器
 * 作者: mbFeng
 * 创建时间:2015年11月30日 上午11:27:30
 */
public class Logger
{
	//是否是开发模式
	public static final boolean IS_DEVELOP_MODE = true;
	public static final String TAG = "fmb";
	private Logger(){}
	/**
	 * 日志打印但不收集
	 * @param log 日志信息
	 */
	public static void i(String log)
	{
		if(IS_DEVELOP_MODE)
		{
			String tag = getTag();
			if(!TextUtils.isEmpty(log)) logi(tag, log);
		}
	}
	
	/**
	 * 日志打印和收集
	 * @param log 日志信息
	 */
	public static void i2s(String log)
	{
		String tag = getTag();
		if(IS_DEVELOP_MODE)
		{
			if(!TextUtils.isEmpty(log)) Log.i(tag, log);
		}
	//	else LoggerThreadPoolMger.getInstance().debug(new LoggerRunnable(tag, log, null, AndroidDebugLogModel.TYPE_DEBUG));
	}
	/**
	 * 需要处理的异常日志打印和收集
	 * @param e
	 */
	public static void e2s(Throwable e)
	{
		String tag = getTag();
		if(IS_DEVELOP_MODE)
		{
			if(e != null) Log.e(tag, ExceptionUtil.getCrashContent(e));
		}
		else
		{
			//LoggerThreadPoolMger.getInstance().submitExceptionEmail((Exception) e);
		}
	}
	/**
	 * 需要处理的全局异常日志打印和收集
	 * @param e
	 */
	public static void globalE2S(Throwable e)
	{
		String tag = getTag();
		if(IS_DEVELOP_MODE)
		{
			if(e != null) Log.e(tag, ExceptionUtil.getCrashContent(e));
		}
		else 
		{
			//LoggerThreadPoolMger.getInstance().debug(new LoggerRunnable(tag, null, e, AndroidDebugLogModel.TYPE_GLOBAL_EXCEPTION));
		}
	}
	/**
	 * 获取Tag
	 * @return
	 */
	private static String getTag()
	{
		StringBuilder tagBuilder = new StringBuilder(TAG);
		StackTraceElement callMethodStackTraceElement = getStackTraceElement();
		if(callMethodStackTraceElement != null)
		{
			tagBuilder
					.append("-")
					.append(callMethodStackTraceElement.getClassName())
					.append("(")
					.append(callMethodStackTraceElement.getLineNumber())
					.append(")");
		}
		return tagBuilder.toString();
	}
	
	/**
	 * 获取当前方法的调用者的调用者的调用栈
	 */
	private static StackTraceElement getStackTraceElement()
	{
		try
		{
			return Thread.currentThread().getStackTrace()[5];
		} catch (Exception e)
		{
			//LoggerThreadPoolMger.getInstance().submitExceptionEmail(e);
		}
		
		return null;
	}
	/**
	 * 重组输出日志
	 * @param tag
	 * @param log
	 * @throws Exception
	 */
	private static void logi(String tag,String log)
	{
		try
		{
			if(!TextUtils.isEmpty(log))
			{
				byte[] bytes = log.getBytes("utf-8");
				if(bytes != null)
				{
					int maxLength = 3*1024;
					//倍数
					int index = bytes.length/maxLength;
					if(index > 0)
					{
						//超出部分
						int overLength = bytes.length%maxLength;
						if(overLength > 0) index += 1;
						int caledLength = 0;
						// 叠加每个字符的长度
						for (int i = 0; i < index; i++)
						{
							int strLength = 0;
							StringBuilder newMaxStrBuilder = new StringBuilder();
							for (int j = caledLength; j < log.length(); j++)
							{
								String temp = log.substring(j, j + 1);
								strLength += temp.getBytes("utf-8").length;
								if(strLength <= maxLength) 
								{
									caledLength++;
									newMaxStrBuilder.append(temp);
								}
								else break;
							}
							Log.i(tag, newMaxStrBuilder.toString());
						}
					}
					else Log.i(tag,log);
				}
			}
		} catch (Exception e)
		{
			e2s(e);
		}
	}
	
}





