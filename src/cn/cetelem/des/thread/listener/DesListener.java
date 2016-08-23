/**
 * 
 */
package cn.cetelem.des.thread.listener;

import java.util.Map;

import cn.cetelem.des.thread.listener.impl.DesListenerImpl.RequestConf;

/**
 * @author flaki
 * @date 2016年6月12日
 * @type TODO
 * @version 1.0
 * @email wysznb@hotmail.com
 * 
 */
public interface DesListener {
	public boolean push(String appId, String ip);
	public void pop(String appId);
	public String getIp(String appId);
	public Map<String,RequestConf> getAll();

}
