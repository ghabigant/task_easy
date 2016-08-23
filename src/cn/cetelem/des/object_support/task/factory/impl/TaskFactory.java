package cn.cetelem.des.object_support.task.factory.impl;

import cn.cetelem.des.object_support.task.resolver.TaskResolver;
import cn.cetelem.des.taskBean.TaskBean;

public interface TaskFactory {

	boolean checkPrototype(String string);

	TaskBean getTaskBean(String string);

	TaskResolver getTaskResolver();
	

}
