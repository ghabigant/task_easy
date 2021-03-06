/**
 * 
 */
package cn.cetelem.des.thread;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import cn.cetelem.des.expression.engine.Engine;
import cn.cetelem.des.object_support.task.resolver.TaskResolver;

/**
 * @author flaki
 * @date 2016年6月2日
 * @type 引擎ThreadLocal
 * @version 1.0
 * @email wysznb@hotmail.com
 * 
 */
@Repository
public class EngineThreadLocal extends ThreadLocal<Engine> {
	private static Engine engine;
	
	Logger logger = Logger.getLogger(EngineThreadLocal.class);
	
	public TaskResolver getTaskResolver() {
		return taskResolver;
	}

	public void setTaskResolver(TaskResolver taskResolver) {
		this.taskResolver = taskResolver;
	}

	@Resource
	private TaskResolver taskResolver;

	/* 
	 * @see java.lang.ThreadLocal#initialValue()
	 */
	@Override
	protected Engine initialValue() {
		return EngineThreadLocal.engine;
	}
	
	/**
	 * 引擎配置
	 * 
	 * 请在Spring配置文件中配置
	 * 如未配置会默认使用:
	 * cn.cetelem.des.expression.engine.Impl.JsEngine
	 */
	@PostConstruct
	private void init() throws InstantiationException, IllegalAccessException,
			ClassNotFoundException {
		logger.info("start init engine.");
		if (!StringUtils.isEmpty(taskResolver.getEngine())) {
			EngineThreadLocal.engine = (Engine) Class.forName(taskResolver.getEngine())
					.newInstance();
		}else{
			EngineThreadLocal.engine = (Engine) Class.forName(TaskResolver.DEFAULT_ENGINE)
					.newInstance();
		}
	}
	
	public void postInit() throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		init();
	}
	

}
