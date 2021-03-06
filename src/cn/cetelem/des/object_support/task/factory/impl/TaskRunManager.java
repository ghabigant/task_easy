package cn.cetelem.des.object_support.task.factory.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import cn.cetelem.des.object_support.task.pojo.BaseNode;
import cn.cetelem.des.taskBean.TaskBean;

/**
 * 
 * @author flaki
 * @date 2016年6月1日
 * @type 任务执行器
 * @version 1.0
 * @email wysznb@hotmail.com
 *
 */
@Service
public class TaskRunManager {
	
	/**
	 * 如果method有有效值则调用显示方法
	 * 否则调用默认方法(必要时须强转类型)
	 * @throws Exception 
	 * 
	 */
	public Object push(BaseNode baseNode, Object context, TaskBean taskBean) throws Exception {
		if (StringUtils.isEmpty(baseNode.getMethod())) {
			return taskBean.invoke(context);
		}else{
			try {
				String method = baseNode.getMethod();
				Class<?> inType = Class.forName(baseNode.getInType());
				Class<?> outType = Class.forName(baseNode.getOutType());
				Method m = taskBean.getClass().getMethod(method, inType);
				return outType.cast(m.invoke(taskBean, context));
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		return context;
	}

}
