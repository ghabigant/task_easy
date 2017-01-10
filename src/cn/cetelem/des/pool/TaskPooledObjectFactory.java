/**
 * 
 */
package cn.cetelem.des.pool;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import cn.cetelem.des.object_support.task.factory.TaskFactory;
import cn.cetelem.des.pool.impl.DefaultPooledObject;
import cn.cetelem.des.taskBean.TaskBean;

/**
 * @author flaki
 * @date 2016年6月2日
 * @type 任务对象池工厂
 * @version 1.0
 * @email wysznb@hotmail.com
 * 
 * 用来生成对象池的工厂，单例模式交给Spring管理即可
 * 举例：
 * <pre style="border:solid thin; padding: 1ex;"
 * >
 * <code style="color:#0C0">//创建对象池</code>
 * <code style="color:#999">@Resouce</code>
 * TaskPooledObjectFactory <code style="color:#00C">taskPooledObjectFactory</code>;
 * GenericKeyedObjectPoolConfig <code style="color:#00C">config</code> = new GenericKeyedObjectPoolConfig();
 * <code style="color:#0C0">//注意：以下在配置文件配置</code>
 * <code style="color:#00C">config</code>.setMaxIdlePerKey(<code style="color:#00C">10</code>);<code style="color:#0C0">//最大资源数</code>
 * <code style="color:#00C">config</code>.setMaxTotal(<code style="color:#00C">500</code>);<code style="color:#0C0">//最大对象数</code>
 * <code style="color:#00C">config</code>.setMaxTotalPerKey(<code style="color:#00C">20</code>);<code style="color:#0C0">//单Key最大对象数</code>
 * <code style="color:#00C">config</code>.setMinIdlePerKey(<code style="color:#00C">5</code>);<code style="color:#0C0">//最小资源数</code>
 * GenericKeyedObjectPool <code style="color:#00C">taskPool</code> = new GenericKeyedObjectPool<String, TaskBean>(
 * <code style="color:#00C">taskPooledObjectFactory, <code style="color:#00C">config</code>);
 * 
 * <br/>
 * <code style="color:#0C0">//.......</code>
 * <code style="color:#0C0">//使用对象池</code>
 * Object obj = <code style="color:#00C">null</code>;
 * Object key = <code style="color:#C00">"Key"</code>;
 * <code style="color:#00C">try</code> {
 *     obj = pool.borrowObject(key);
 *     <code style="color:#0C0">//获取对象</code>
 * } <code style="color:#00C">catch</code>(Exception e) {
 *     <code style="color:#0C0">//检查对象</code>
 *     pool.invalidateObject(key, obj);
 *     <code style="color:#0C0">//千万别返回对象值2次</code>
 *     obj = <code style="color:#00C">null</code>;
 * } <code style="color:#00C">finally</code> {
 *     <code style="color:#0C0">//确保在结束块中交还给对象池</code>
 *     <code style="color:#00C">if</code>(<code style="color:#00C">null</code> != obj) {
 *         pool.returnObject(key, obj);
 *     }
 * }</pre>
 * 
 */

@Service
@Scope("singleton")
public class TaskPooledObjectFactory extends BaseKeyedPooledObjectFactory<String, TaskBean> {
	
	@Resource
	private TaskFactory taskFactory;
	
	public TaskFactory getTaskFactory() {
		return taskFactory;
	}

	public void setTaskFactory(TaskFactory taskFactory) {
		this.taskFactory = taskFactory;
	}

	@Override
	public TaskBean create(String key) throws Exception {
		TaskBean newBean =taskFactory.getTaskBean(key);
		return newBean;
	}

	@Override
	public PooledObject<TaskBean> wrap(TaskBean value) {
		return new DefaultPooledObject<TaskBean>(value);
	}

}
