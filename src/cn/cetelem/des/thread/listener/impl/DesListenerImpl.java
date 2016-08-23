/**
 * 
 */
package cn.cetelem.des.thread.listener.impl;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.xml.ws.WebServiceContext;

import org.springframework.stereotype.Repository;

import cn.cetelem.des.interceptor.TaskLogger;
import cn.cetelem.des.thread.listener.DesListener;

/**
 * @author flaki
 * @date 2016年6月12日
 * @type DesListener
 * @version 1.0
 * @email wysznb@hotmail.com
 * 
 */
@Repository
public class DesListenerImpl implements DesListener {
	private static long OUT_TIME = 30000;
	private static long CLEAN_TIME = 60000;
	private volatile int i = 0;
	private TaskLogger logger = TaskLogger.getLogger(DesListenerImpl.class);
	@Resource
	private WebServiceContext webServiceContext;
	private final static ConcurrentHashMap<String, RequestConf> LINKED_THREAD = new ConcurrentHashMap<String, RequestConf>();

	public static void setOUT_TIME(long oUT_TIME) {
		OUT_TIME = oUT_TIME;
	}

	public static void setCLEAN_TIME(long cLEAN_TIME) {
		CLEAN_TIME = cLEAN_TIME;
	}

	@Override
	public boolean push(String appId, String ip) {
		if (LINKED_THREAD.containsKey(appId)) {
			return false;
		} else {
			synchronized (this) {
				if (LINKED_THREAD.containsKey(appId)) {
					return false;
				}
				LINKED_THREAD.put(appId, new RequestConf(new Date().getTime(),
						ip));
				Thread.currentThread().setName("通知");
				logger.info("new request from " + ip);
				i++;
			}
			return true;
		}
	}

	@Override
	public void pop(String appId) {
		LINKED_THREAD.remove(appId);
	}

	/*
	 * @see cn.cetelem.des.thread.listener.DesListener#getIp(java.lang.String)
	 */
	@Override
	public String getIp(String appId) {
		return LINKED_THREAD.get(appId).getIp();
	}

	public long getTime(String appId) {
		return LINKED_THREAD.get(appId).getIntime();
	}

	/*
	 * @see cn.cetelem.des.thread.listener.DesListener#getAll()
	 */
	@Override
	public Map<String, RequestConf> getAll() {
		return LINKED_THREAD;
	}

	public int getAllCount() {
		return i;
	}

	public static class RequestConf {
		private Long intime;
		private String ip;

		public Long getIntime() {
			return intime;
		}

		public void setIntime(Long intime) {
			this.intime = intime;
		}

		public String getIp() {
			return ip;
		}

		public void setIp(String ip) {
			this.ip = ip;
		}

		@Override
		public String toString() {
			return "RequestConf [intime=" + intime + ", ip=" + ip + "]";
		}

		public RequestConf(Long intime, String ip) {
			super();
			this.intime = intime;
			this.ip = ip;
		}

		public RequestConf() {
			super();
		}

	}

	public void cleanAll() {
		synchronized (DesListenerImpl.class) {
			long thisTime = new Date().getTime();
			for (String key : LINKED_THREAD.keySet()) {
				if (thisTime - getTime(key) > OUT_TIME) {
					pop(key);
				}
			}
		}
	}

	@PostConstruct
	public void initPopThread() {
		logger.info("初始化队列池");
		new Thread(){
			@Override
			public void run() {
				while(true){
					try {
						TimeUnit.MILLISECONDS.sleep(CLEAN_TIME);
					} catch (InterruptedException e) {
						initPopThread();
					}
					logger.info("开始清理队列池");
					cleanAll();
					logger.info("清理结束");
				}
			}
		}.start();
	}

}
