package cn.test.task;

import cn.cetelem.des.interceptor.TaskLogger;
import cn.cetelem.des.taskBean.TaskBean;
import cn.cetelem.des.utils.Multiton;
import cn.cetelem.des.utils.Task;

/**
 * 
 * @author flaki
 * @date 2016年7月8日
 * @type End
 * @version 1.0
 * @email wysznb@hotmail.com
 *
 */
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
