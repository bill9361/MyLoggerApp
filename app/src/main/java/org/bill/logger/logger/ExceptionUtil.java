package org.bill.logger.logger;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

/**
 * 异常工具
 * 作者: mbFeng
 * 创建时间:2015年12月2日 下午3:09:44 
 */
public class ExceptionUtil
{
	
	private ExceptionUtil(){}
	
	/**
	 * 获取异常内容
	 * @param ex
	 * @return
	 */
	public static String getCrashContent(Throwable ex) 
	{
		if(ex != null)
		{
			//可以获取到 Cause By的内容
			Writer writer = new StringWriter();  
			if(writer != null)
			{
				PrintWriter printWriter = new PrintWriter(writer);  
				ex.printStackTrace(printWriter);  
				Throwable cause = ex.getCause();  
				while (cause != null) 
				{  
					cause.printStackTrace(printWriter);  
					cause = cause.getCause();  
				}  
				
				printWriter.close();  
				return writer.toString();
			}
		}
		
		return null;
	}

}
