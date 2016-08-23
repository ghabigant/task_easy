/**
 * 
 */
package cn.cetelem.des.interceptor;

import org.apache.log4j.Logger;

/**
 * @author flaki
 * @date 2016年6月2日
 * @type Task日志器
 * @version 1.0
 * @email wysznb@hotmail.com
 * 
 */
public class TaskLogger {
	Logger logger ;
	
	private TaskLogger(Logger logger){
		this.logger = logger;
	}

	public void info(Object message) {
		logger.info(Thread.currentThread().getName()+"||##"+message+"        ######## "+logger.getName());
	}
	
	public void warn(Object message) {
		logger.warn(Thread.currentThread().getName()+"||##"+message+"        ######## "+logger.getName());
	}
	
	public void debug(Object message) {
		logger.debug(Thread.currentThread().getName()+"||##"+message+"        ######## "+logger.getName());
	}
	
	public void error(Object message) {
		logger.error(Thread.currentThread().getName()+"||##"+message+"        ######## "+logger.getName());
	}
	
	public void error(Object message,Throwable t) {
		logger.error(Thread.currentThread().getName()+"||##"+message+"        ######## "+logger.getName(),t);
	}
	
	public static TaskLogger getLogger(Class<?> clazz){
		return new TaskLogger(Logger.getLogger(clazz));
		
	}
	

}
