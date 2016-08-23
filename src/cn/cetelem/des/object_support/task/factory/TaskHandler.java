package cn.cetelem.des.object_support.task.factory;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import cn.cetelem.des.expression.TaskChecker;
import cn.cetelem.des.object_support.task.factory.impl.TaskFactory;
import cn.cetelem.des.object_support.task.pojo.BaseNode;
import cn.cetelem.des.object_support.task.resolver.TaskResolver;
import cn.cetelem.des.pool.TaskPooledObjectFactory;
import cn.cetelem.des.pool.impl.GenericKeyedObjectPool;
import cn.cetelem.des.pool.impl.GenericKeyedObjectPoolConfig;
import cn.cetelem.des.taskBean.TaskBean;
import cn.cetelem.des.thread.NodesThreadLocal;
import cn.cetelem.des.thread.PointThreadLocal;

/**
 * 
 * @author flaki
 * @date 2016年6月1日
 * @type 任务控制器
 * @version 1.0
 * @email wysznb@hotmail.com
 *
 */
@Repository
public class TaskHandler {
	protected ThreadLocal<String> pointLocal = new PointThreadLocal();;
	protected ThreadLocal<Map<String, BaseNode>> nodes;
	@Resource(name = "taskSpringFactory")
	TaskFactory taskFactory;
	@Resource
	TaskChecker taskChecker;
	@Resource
	TaskRunManager taskRunManager;
	@Resource
	TaskResolver taskResolver;
	@Resource
	TaskPooledObjectFactory taskPooledObjectFactory;
	public volatile static GenericKeyedObjectPool<String, TaskBean> taskPool;
	/**
	 * 流程的总入口,单次调用 需要注入本类
	 * @throws Exception 
	 */
	public Object pushAll(Object context) throws Exception {
		pointLocal.set("start");
		nodes = new NodesThreadLocal();
		while (!"end".equals(pointLocal.get())) {
			String point = pointLocal.get();
			context = push(point, context);
			String nextMethod = nodes.get().get(point).getGotoMethod();
			String nextBean = nodes.get().get(point).getGotoBean();
			nodes.get().get(nextBean).setMethod(nextMethod);
			pointLocal.set(nextBean);

		}
		return push("end", context);
	}

	/**
	 * 任务分发流程 push(String string, Object context) 流程如下： 1.分析表达式
	 * 判断是否执行本任务，是否覆盖目标任务 2.执行本任务(true时) 3.覆盖目标任务(true时) 4.转移指针
	 * @throws Exception 
	 */
	protected Object push(String string, Object context) throws Exception {
		TaskBean taskBean = null;
		//在非单例的情况下才进入对象池
		if (!taskFactory.checkPrototype(string)) {
			taskBean = taskFactory.getTaskBean(string);
			BaseNode baseNode = nodes.get().get(string);
			String checkResult = taskChecker.check(baseNode, context);
			if ("false".equals(checkResult)) {
				return context;
			} else if ("true".equals(checkResult)) {
				return taskRunManager.push(baseNode, context, taskBean);
			} else {
				String[] jump = checkResult.split("=");
				String[] jumpgoto = jump[1].split("-");
				if (!"null".equals(jumpgoto[0])) {
					nodes.get().get(string).setGotoBean(jumpgoto[0]);
				}
				if (jumpgoto.length == 2) {
					nodes.get().get(string).setGotoMethod(jumpgoto[1]);
				}
				if (!"false".equals(jump[0])) {
					return taskRunManager.push(baseNode, context, taskBean);
				} else {
					return context;
				}
			}
		} else {
			try {
				taskBean = taskPool.borrowObject(string);
				BaseNode baseNode = nodes.get().get(string);
				String checkResult = taskChecker.check(baseNode, context);
				if ("false".equals(checkResult)) {
					return context;
				} else if ("true".equals(checkResult)) {
					return taskRunManager.push(baseNode, context, taskBean);
				} else {
					String[] jump = checkResult.split("=");
					String[] jumpgoto = jump[1].split("-");
					if (!"null".equals(jumpgoto[0])) {
						nodes.get().get(string).setGotoBean(jumpgoto[0]);
					}
					if (jumpgoto.length == 2) {
						nodes.get().get(string).setGotoMethod(jumpgoto[1]);
					}
					if (!"false".equals(jump[0])) {
						return taskRunManager.push(baseNode, context, taskBean);
					} else {
						return context;
					}
				}
			}finally {
				taskPool.returnObject(string, taskBean);
			}
		}

	}

	/**
	 * 初始化对象池
	 */
	@PostConstruct
	private void initTaskPool() {
		GenericKeyedObjectPoolConfig config = new GenericKeyedObjectPoolConfig();
		config.setMaxIdlePerKey(taskResolver.getMaxIdlePerKey());
		config.setMaxTotal(taskResolver.getMaxTotal());
		config.setMaxTotalPerKey(taskResolver.getMaxTotalPerKey());
		config.setMinIdlePerKey(taskResolver.getMinIdlePerKey());
		TaskHandler.taskPool = new GenericKeyedObjectPool<String, TaskBean>(
				taskPooledObjectFactory, config);
	}
}
