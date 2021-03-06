package cn.cetelem.des.object_support.task.factory.impl;

import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.stereotype.Service;

import cn.cetelem.des.object_support.task.factory.TaskFactory;
import cn.cetelem.des.object_support.task.resolver.TaskResolver;
import cn.cetelem.des.taskBean.TaskBean;
import cn.cetelem.des.utils.AfterTip;
import cn.cetelem.des.utils.BeforeTip;
import cn.cetelem.des.utils.Group;
import cn.cetelem.des.utils.Multiton;
import cn.cetelem.des.utils.TaskBeanScanUtil;

/**
 * 
 * @author flaki
 * @date 2016年6月1日
 * @type 任务工厂 通过继承ApplicationObjectSupport获取TaskBean
 * @version 1.0
 * @email wysznb@hotmail.com
 * 
 */
@Service
@Scope("singleton")
public class TaskSpringFactory extends ApplicationObjectSupport implements
		TaskFactory {

	/**
	 * 用于给组任务注册Bean替身
	 * 
	 * @throws Exception
	 */
	@PostConstruct
	public void regGroupTask() throws Exception {
		ConfigurableApplicationContext cac = (ConfigurableApplicationContext) getApplicationContext();
		DefaultListableBeanFactory bf = (DefaultListableBeanFactory) cac
				.getBeanFactory();
		Set<Class<?>> classes = TaskBeanScanUtil.getClassesWithAnnotation(
				"cn.cetelem.des", Group.class);
		for (Class<?> clazz : classes) {
			if (!clazz.isInterface()) {
				throw new RuntimeException(
						"annotation of Group can't be use not in a interface ");
			}
			BeanDefinitionBuilder bdb = BeanDefinitionBuilder
					.genericBeanDefinition(clazz.getAnnotation(Group.class)
							.type());
			String workers = clazz.getAnnotation(Group.class).value();
			bdb.addPropertyValue("workers", workers);
			if (clazz.getAnnotation(Multiton.class) != null) {
				bdb.setScope(BeanDefinition.SCOPE_PROTOTYPE);
			}
			if (clazz.getAnnotation(BeforeTip.class) != null) {
				bdb.addPropertyValue("beforeTip",
						clazz.getAnnotation(BeforeTip.class).value());
			} else {
				bdb.addPropertyValue("beforeTip", "开始调用" + workers);
			}
			if (clazz.getAnnotation(AfterTip.class) != null) {
				bdb.addPropertyValue("afterTip",
						clazz.getAnnotation(AfterTip.class).value());
			} else {
				bdb.addPropertyValue("afterTip", workers + "调用结束");
			}
			bf.registerBeanDefinition(low(clazz.getSimpleName()),
					bdb.getRawBeanDefinition());
		}

	}

	/**
	 * @param simpleName
	 * @return
	 */
	private String low(String simpleName) {
		char[] chars = simpleName.toCharArray();
		chars[0] |= 32;
		return new String(chars);
	}

	/**
	 * 像Spring请求TaskBean 其他类型使用会约束报错 用于执行任务提取
	 */
	public TaskBean getTaskBean(String taskName) {
		return getApplicationContext().getBean(taskName, TaskBean.class);
	}

	/**
	 * 检查对象bean是否单例
	 * 
	 * @param taskName
	 * @return
	 */
	public boolean checkPrototype(String taskName) {
		return getApplicationContext().isPrototype(taskName);

	}

	public TaskHandler getHandler() {
		return getApplicationContext()
				.getBean("taskHandler", TaskHandler.class);
	}

	

	@Override
	public TaskResolver getTaskResolver() {
		return getApplicationContext()
				.getBean("taskResolver", TaskResolver.class);
	}


}
