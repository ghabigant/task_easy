package cn.cetelem.des.object_support.task.pojo;

import java.util.HashMap;
import java.util.Map;

import cn.cetelem.des.taskBean.TaskBean;

public class TaskEntitys {
	private Map<String, TaskEntity> taskMap = new HashMap<String, TaskEntity>();

	public TaskBean getTask(String beanName) {
		if (taskMap.get(beanName) == null) {
			return null;
		} else if (taskMap.get(beanName).isSingleton()) {
			return taskMap.get(beanName).getBean();
		} else {
			try {
				return taskMap.get(beanName).getClazz().newInstance();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public void put(String beanName, Class<TaskBean> clazz, boolean singleton) {
		TaskEntity taskEntity = new TaskEntity();
		taskEntity.setBeanName(beanName);
		taskEntity.setClazz(clazz);
		taskEntity.setSingleton(singleton);
		if(singleton){
			try {
				taskEntity.setBean(clazz.newInstance());
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		taskMap.put(beanName, taskEntity);
	
	}
	
	public boolean checkPrototype(String taskName){
		return taskMap.get(taskName).singleton;
	}

	public static class TaskEntity {
		private String beanName;
		private Class<TaskBean> clazz;
		private TaskBean bean;
		private boolean singleton;

		String getBeanName() {
			return beanName;
		}

		void setBeanName(String beanName) {
			this.beanName = beanName;
		}

		Class<TaskBean> getClazz() {
			return clazz;
		}

		void setClazz(Class<TaskBean> clazz) {
			this.clazz = clazz;
		}

		TaskBean getBean() {
			return bean;
		}

		void setBean(TaskBean bean) {
			this.bean = bean;
		}

		boolean isSingleton() {
			return singleton;
		}

		void setSingleton(boolean singleton) {
			this.singleton = singleton;
		}

	}

}