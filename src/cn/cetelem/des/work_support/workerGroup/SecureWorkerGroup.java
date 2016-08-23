/**
 * 
 */
package cn.cetelem.des.work_support.workerGroup;

import java.util.concurrent.Executors;


/**
 * @author flaki
 * @date 2016年7月6日
 * @type TODO
 * @version 1.0
 * @email wysznb@hotmail.com
 * 
 */
public class SecureWorkerGroup extends BaseWorkerGroup {
	@Override
	public Object invoke(Object context) throws Exception{
		exec = Executors.newFixedThreadPool(workAmount);
		run(context);
		exec.shutdown();
		return this.context;
	}

	
}
