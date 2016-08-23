/**
 * 
 */
package cn.cetelem.des.work_support.workerGroup;

import cn.cetelem.des.interceptor.TaskLogger;

/**
 * @author flaki
 * @date 2016年7月6日
 * @type TODO
 * @version 1.0
 * @email wysznb@hotmail.com
 * 
 */
public class EfficientLogWorkerGroup extends EfficientWorkerGroup {
	protected TaskLogger logger = TaskLogger.getLogger(this.getClass());
	protected String beforeTip;
	protected String afterTip;

	@Override
	public Object invoke(Object context) throws Exception {
		if (beforeTip != null) {
			logger.info(beforeTip);
		}
		run(context);
		if (afterTip != null) {
			logger.info(afterTip);
		}
		return this.context;

	}
}
