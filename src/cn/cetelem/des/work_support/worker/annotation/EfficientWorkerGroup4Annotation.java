/**
 * 
 */
package cn.cetelem.des.work_support.worker.annotation;

import java.util.concurrent.Executors;

/**
 * @author flaki
 * @date 2016年7月6日
 * @type TODO
 * @version 1.0
 * @email wysznb@hotmail.com
 * 
 */
public class EfficientWorkerGroup4Annotation extends BaseWorkerGroup4Annotation {

	@Override
	public Object invoke(Object context) throws Exception {
		run(context);
		return this.context;

	}
	@Override
	protected void initBeExtend() {
		exec = Executors.newFixedThreadPool(workAmount);
	}
	@Override
	protected void initBeExtendEnd() {
		exec.shutdown();
	}

}
