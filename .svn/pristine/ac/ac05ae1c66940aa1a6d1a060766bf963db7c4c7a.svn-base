package cn.cetelem.des.object_support.task.factory;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import cn.cetelem.des.object_support.task.pojo.BaseNode;
import cn.cetelem.des.object_support.task.resolver.TaskResolver;

/**
 * 
 * @author flaki
 * @date 2016年6月1日
 * @type 流程持久容器
 * @version 1.0
 * @email wysznb@hotmail.com
 *
 */
@Repository
public class TaskHoldManager {
	@Resource
	private TaskResolver taskResolver;
	
	public static Map<String, BaseNode> TASKS;
	public static Object lock = new Object();
	private Logger log = Logger.getLogger(TaskHoldManager.class);
	/**
	 * 会在服务器启动时调用
	 */
	@PostConstruct
	public void init(){
		if (TASKS == null) {
			synchronized (lock) {
				if(TASKS == null) {
					try {
						TASKS = taskResolver.getNodes();
					} catch (Exception e) {
						log.info("tasks init failed, please check the xml of task");
						e.printStackTrace();
					}
					log.info(TASKS.size()+" nodes have inited!");
					
				}
			}
		}
	}

}
