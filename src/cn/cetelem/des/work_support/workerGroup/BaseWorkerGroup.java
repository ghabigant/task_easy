/**
 * 
 */
package cn.cetelem.des.work_support.workerGroup;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import cn.cetelem.des.object_support.task.factory.TaskHandler;
import cn.cetelem.des.taskBean.TaskBean;
import cn.cetelem.des.utils.Worker;
import cn.cetelem.des.work_support.worker.TaskWorker;

/**
 * @author flaki
 * @date 2016年7月6日
 * @type 等待并发基础类
 * @version 1.0
 * @email wysznb@hotmail.com
 * 
 */
public abstract class BaseWorkerGroup implements TaskBean {
	protected Map<String, TaskWorker> workStore = new HashMap<String, TaskWorker>();
	protected int workAmount;
	protected ExecutorService exec;
	protected CyclicBarrier barrier;
	protected volatile Object context;
	protected final ReadWriteLock lock = new ReentrantReadWriteLock();

	@Override
	public abstract Object invoke(Object context) throws Exception;

	protected void run(Object context) throws InterruptedException,
			BrokenBarrierException {
		try {
			this.context = context;
			for (String taskName : workStore.keySet()) {
				workStore.get(taskName).setContext(this.context);
				exec.submit(workStore.get(taskName));
			}
			barrier.await();
		} finally {
			barrier.reset();
		}

	}

	@PostConstruct
	public void init() throws Exception {
		Field[] fields = this.getClass().getDeclaredFields();
		for (Field field : fields) {
			Worker worker = field.getAnnotation(Worker.class);
			if (worker != null) {
				String WorkerName = null;
				if ("".equals(worker.value())) {
					WorkerName = field.getName();

				} else {
					WorkerName = worker.value();
				}
				workStore.put(WorkerName, (TaskWorker) TaskHandler.taskPool
						.borrowObject(WorkerName));
			}
		}
		workAmount = workStore.size();
		barrier = new CyclicBarrier(workAmount + 1);
		for (String taskName : workStore.keySet()) {
			workStore.get(taskName).setBarrier(barrier);
			workStore.get(taskName).setLock(lock);
		}
		initBeExtend();
	}

	/**
	 * 
	 */
	protected void initBeExtend() {
	}

	protected void initBeExtendEnd() {
	}

	@PreDestroy
	public void destroy() {
		for (String taskName : workStore.keySet()) {
			TaskHandler.taskPool
					.returnObject(taskName, workStore.get(taskName));
		}
		initBeExtendEnd();
	}
}
