/**
 * 
 */
package cn.cetelem.des.work_support.worker.annotation;

import cn.cetelem.des.interceptor.TaskLogger;

/**
 * @author flaki
 * @date 2016年7月6日
 * @type TODO
 * @version 1.0
 * @email wysznb@hotmail.com
 * 
 */
public class EfficientLogWorkerGroup4Annotation extends EfficientWorkerGroup4Annotation {
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

	/**
	 * @return the beforeTip
	 */
	public String getBeforeTip() {
		return beforeTip;
	}

	/**
	 * @param beforeTip the beforeTip to set
	 */
	public void setBeforeTip(String beforeTip) {
		this.beforeTip = beforeTip;
	}

	/**
	 * @return the afterTip
	 */
	public String getAfterTip() {
		return afterTip;
	}

	/**
	 * @param afterTip the afterTip to set
	 */
	public void setAfterTip(String afterTip) {
		this.afterTip = afterTip;
	}
	
}
