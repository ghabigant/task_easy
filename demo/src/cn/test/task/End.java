package cn.test.task;

import cn.cetelem.des.interceptor.TaskLogger;
import cn.cetelem.des.taskBean.TaskBean;
import cn.cetelem.des.utils.Multiton;
import cn.cetelem.des.utils.Task;


@Task
@Multiton
public class End implements TaskBean {
	private TaskLogger logger = TaskLogger.getLogger(End.class);


	@Override
	public Object invoke(Object context) throws Exception {
		logger.info("end");
		return context;
	}

}
