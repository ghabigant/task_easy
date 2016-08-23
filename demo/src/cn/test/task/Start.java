package cn.test.task;

import cn.cetelem.des.interceptor.TaskLogger;
import cn.cetelem.des.taskBean.TaskBean;
import cn.cetelem.des.utils.Singleton;
import cn.cetelem.des.utils.Task;

@Task
@Singleton
public class Start implements TaskBean {
	private TaskLogger logger = TaskLogger.getLogger(Start.class);

	@Override
	public Object invoke(Object context) throws Exception {
		logger.info("start");
		return context;
	}


}
