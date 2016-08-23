/**
 * 
 */
package cn.cetelem.des.work_support.worker;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.ReadWriteLock;

import cn.cetelem.des.taskBean.TaskBean;

/**
 * @author flaki
 * @date 2016年6月20日
 * @type 并发等待任务接口
 * @version 1.0
 * @email wysznb@hotmail.com
 * 
 */
public interface TaskWorker extends Runnable,TaskBean{
	public void setContext(Object context);
	public void setBarrier(CyclicBarrier barrier);
	public void setLock(ReadWriteLock lock);
}
