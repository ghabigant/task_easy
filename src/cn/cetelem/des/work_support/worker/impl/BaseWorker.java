/**
 * 
 */
package cn.cetelem.des.work_support.worker.impl;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.ReadWriteLock;

import cn.cetelem.des.work_support.worker.TaskWorker;

/**
 * @author flaki
 * @date 2016年6月20日
 * @type TODO
 * @version 1.0
 * @email wysznb@hotmail.com
 * 
 */

public abstract class BaseWorker implements TaskWorker {
	private Object context;
	private CyclicBarrier barrier;
	protected ReadWriteLock lock;
	
	/**
	 * @param lock the lock to set
	 */
	public void setLock(ReadWriteLock lock) {
		this.lock = lock;
	}

	public void setBarrier(CyclicBarrier barrier){
		this.barrier = barrier;
	}
	
	public void setContext(Object context){
		this.context = context;
	}
	/* 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		try {
			invoke(context);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				barrier.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (BrokenBarrierException e) {
				e.printStackTrace();
			}
		}
		
	}

	/* 
	 * @see cn.cetelem.des.taskBean.TaskBean#invoke(java.lang.Object)
	 */
	public abstract Object invoke(Object context) throws Exception ;

}
