package cn.cetelem.des.object_support.task.factory;

import java.util.Map;
import java.util.Set;

import cn.cetelem.des.object_support.task.factory.impl.TaskFactory;
import cn.cetelem.des.object_support.task.pojo.BaseNode;
import cn.cetelem.des.object_support.task.pojo.TaskEntitys;
import cn.cetelem.des.object_support.task.resolver.TaskResolver;
import cn.cetelem.des.taskBean.TaskBean;
import cn.cetelem.des.utils.Group;
import cn.cetelem.des.utils.Multiton;
import cn.cetelem.des.utils.Task;
import cn.cetelem.des.utils.TaskBeanScanUtil;
import cn.cetelem.des.work_support.worker.annotation.BaseWorkerGroup4Annotation;

public class TaskLibertyFactory implements TaskFactory {
	TaskEntitys taskEntitys = new TaskEntitys();

	public Map<String, BaseNode> TASKS;

	private TaskResolver taskResolver = new TaskResolver();

	public TaskResolver getTaskResolver() {
		return taskResolver;
	}

	public void setTaskResolver(TaskResolver taskResolver) {
		this.taskResolver = taskResolver;
	}

	@SuppressWarnings("unchecked")
	public void regGroupTask(String scanPackage) throws Exception {
		Set<Class<?>> classes = TaskBeanScanUtil.getClassesWithAnnotation(
				scanPackage, Group.class);
		for (Class<?> clazz : classes) {
			if (!clazz.isInterface()) {
				throw new RuntimeException(
						"annotation of Group can't be use not in a interface ");
			}
			taskEntitys.put(low(clazz.getSimpleName()), (Class<TaskBean>) clazz
					.getAnnotation(Group.class).type(), clazz
					.getAnnotation(Multiton.class) != null);
			String workers = clazz.getAnnotation(Group.class).value();
			((BaseWorkerGroup4Annotation) taskEntitys.getTask(low(clazz
					.getSimpleName()))).setWorkers(workers);

		}
		
		classes = TaskBeanScanUtil.getClassesWithAnnotation(
				scanPackage, Task.class);
		for (Class<?> clazz : classes) {
			if (clazz.isInterface()) {
				throw new RuntimeException(
						"annotation of Group can't be use not in a interface ");
			}
			taskEntitys.put(low(clazz.getSimpleName()), (Class<TaskBean>) clazz, clazz
					.getAnnotation(Multiton.class) != null);

		}

	}

	private String low(String simpleName) {
		char[] chars = simpleName.toCharArray();
		chars[0] |= 32;
		return new String(chars);
	}

	public TaskLibertyFactory scanAndInit(String scanPackage, String url)
			throws Exception {
		regGroupTask(scanPackage);
		taskResolver.setUrl(url);
		TASKS = taskResolver.getNodes();
		return this;
	}

	/**
	 * 请求TaskBean 其他类型使用会约束报错 用于执行任务提取
	 */
	public TaskBean getTaskBean(String taskName) {
		return taskEntitys.getTask(taskName);
	}

	/**
	 * 检查对象bean是否单例
	 * 
	 * @param taskName
	 * @return
	 */
	public boolean checkPrototype(String taskName) {
		return taskEntitys.checkPrototype(taskName);
	}

	public TaskLibertyHandler getHandler() {
		TaskLibertyHandler handler = new TaskLibertyHandler();
		handler.setTaskFactory(this);
		return handler;
	}
	public static void main(String[] args) throws Exception {
		TaskLibertyFactory taskFactory = TaskLibertyFactory.getLibertyFactory();
		taskFactory.scanAndInit("cn.test", "classpath:taskContext.xml");
		TaskLibertyHandler taskHandler = taskFactory.getHandler();
		taskHandler.setMaxIdlePerKey(10);
		taskHandler.setMinIdlePerKey(5);
		taskHandler.setMaxTotal(50);
		taskHandler.setMaxTotalPerKey(5);
		taskHandler.initPool();
		taskHandler.setTaskChecker(null);
		taskHandler.pushAll(new Object());
	}

	public static TaskLibertyFactory getLibertyFactory() {
		return new TaskLibertyFactory();
	}
}
