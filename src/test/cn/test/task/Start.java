package cn.test.task;

import cn.cetelem.des.interceptor.TaskLogger;
import cn.cetelem.des.taskBean.TaskBean;
import cn.cetelem.des.utils.Singleton;
import cn.cetelem.des.utils.Task;

/**
 * 
 * @author flaki
 * @date 2016年7月8日
 * @type start节点
 * @version 1.0
 * @email wysznb@hotmail.com
 *
 */
@Task
@Singleton
public class Start implements TaskBean {
	private TaskLogger logger = TaskLogger.getLogger(Start.class);

	@Override
	public Object invoke(Object context) throws Exception {
		System.out.println("start");
		return context;
	}


}
