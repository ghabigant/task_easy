/**
 * 
 */
package cn.cetelem.des.thread;

import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.cetelem.des.object_support.task.factory.TaskHoldManager;
import cn.cetelem.des.object_support.task.pojo.BaseNode;

/**
 * @author flaki
 * @date 2016年6月2日
 * @type 节点ThreadLocal
 * @version 1.0
 * @email wysznb@hotmail.com
 * 
 */
@Repository
public class NodesThreadLocal extends ThreadLocal<Map<String, BaseNode>> {
	

	/* 
	 * @see java.lang.ThreadLocal#initialValue()
	 */
	@Override
	protected Map<String, BaseNode> initialValue() {
		return TaskHoldManager.TASKS;
	}
	
	
	

}
