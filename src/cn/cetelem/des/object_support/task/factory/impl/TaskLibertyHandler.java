package cn.cetelem.des.object_support.task.factory.impl;

import java.util.Map;

import cn.cetelem.des.expression.TaskChecker;
import cn.cetelem.des.object_support.task.factory.TaskFactory;
import cn.cetelem.des.object_support.task.pojo.BaseNode;
import cn.cetelem.des.pool.PooledObject;
import cn.cetelem.des.pool.TaskPooledObjectFactory;
import cn.cetelem.des.pool.impl.DefaultPooledObject;
import cn.cetelem.des.pool.impl.GenericKeyedObjectPool;
import cn.cetelem.des.pool.impl.GenericKeyedObjectPoolConfig;
import cn.cetelem.des.taskBean.TaskBean;
import cn.cetelem.des.thread.EngineThreadLocal;
import cn.cetelem.des.thread.NodesThreadLocal;

public class TaskLibertyHandler extends TaskHandler {

	public TaskLibertyHandler() {
		taskRunManager = new TaskRunManager();

	}

	void setTaskFactory(final TaskFactory taskFactory) {
		this.taskFactory = taskFactory;
		taskResolver = taskFactory.getTaskResolver();
		taskPooledObjectFactory = new TaskPooledObjectFactory() {
			@Override
			public TaskBean create(String key) throws Exception {
				TaskBean newBean = taskFactory.getTaskBean(key);
				return newBean;
			}

			@Override
			public PooledObject<TaskBean> wrap(TaskBean value) {
				return new DefaultPooledObject<TaskBean>(value);
			}
		};

	}

	public TaskLibertyHandler setMaxIdlePerKey(int maxIdlePerKey){
		taskResolver.setMaxIdlePerKey(maxIdlePerKey);
		return this;
	}
	
	public TaskLibertyHandler setMaxTotal(int maxTotal){
		taskResolver.setMaxTotal(maxTotal);
		return this;
	}
	
	public TaskLibertyHandler setMaxTotalPerKey(int maxTotalPerKey){
		taskResolver.setMaxTotalPerKey(maxTotalPerKey);
		return this;
	}
	
	public TaskLibertyHandler setMinIdlePerKey(int minIdlePerKey){
		taskResolver.setMinIdlePerKey(minIdlePerKey);
		return this;
	}
	
	public TaskLibertyHandler initPool() {
		taskPooledObjectFactory = new TaskPooledObjectFactory();
		taskPooledObjectFactory.setTaskFactory(taskFactory);
		GenericKeyedObjectPoolConfig config = new GenericKeyedObjectPoolConfig();
		config.setMaxIdlePerKey(taskResolver.getMaxIdlePerKey());
		config.setMaxTotal(taskResolver.getMaxTotal());
		config.setMaxTotalPerKey(taskResolver.getMaxTotalPerKey());
		config.setMinIdlePerKey(taskResolver.getMinIdlePerKey());
		TaskHandler.taskPool = new GenericKeyedObjectPool<String, TaskBean>(
				taskPooledObjectFactory, config);
		return this;
	}

	public TaskLibertyHandler setTaskChecker(String engineName)
			throws InstantiationException, IllegalAccessException,
			ClassNotFoundException {
		EngineThreadLocal engineLocal = new EngineThreadLocal();
		((TaskLibertyFactory) taskFactory).getTaskResolver().setEngine(
				engineName);
		engineLocal.setTaskResolver(((TaskLibertyFactory) taskFactory)
				.getTaskResolver());
		engineLocal.postInit();
		taskChecker = new TaskChecker();
		TaskChecker.setEngineLocal(engineLocal);
		return this;
	}

	/**
	 * 流程的总入口,单次调用 需要注入本类
	 * 
	 * @throws Exception
	 */
	public Object pushAll(Object context) throws Exception {
		pointLocal.set("start");
		nodes = new NodesThreadLocal() {
			@Override
			protected Map<String, BaseNode> initialValue() {
				return ((TaskLibertyFactory) taskFactory).TASKS;
			}
		};
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

}
