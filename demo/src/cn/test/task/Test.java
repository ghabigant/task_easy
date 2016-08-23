package cn.test.task;

import cn.cetelem.des.object_support.task.factory.TaskLibertyFactory;
import cn.cetelem.des.object_support.task.factory.TaskLibertyHandler;


public class Test {
	public static void main(String[] args) throws Exception {
//		TaskLibertyFactory taskFactory = TaskLibertyFactory.getLibertyFactory();
//		taskFactory.scanAndInit("cn.test", "classpath:taskContext.xml");
//		TaskLibertyHandler taskHandler = taskFactory.getHandler();
//		taskHandler.setMaxIdlePerKey(10);
//		taskHandler.setMinIdlePerKey(5);
//		taskHandler.setMaxTotal(50);
//		taskHandler.setMaxTotalPerKey(5);
//		taskHandler.initPool();
//		taskHandler.setTaskChecker(null);
//		taskHandler.pushAll(new Object());
		
		
		TaskLibertyHandler taskHandler = TaskLibertyFactory.getLibertyFactory()
				.scanAndInit("cn.test", "classpath:taskContext.xml")
				.getHandler()
				.setMaxIdlePerKey(10)
				.setMinIdlePerKey(5)
				.setMaxTotal(50)
				.setMaxTotalPerKey(5)
				.initPool()
				.setTaskChecker("");
		taskHandler.pushAll(new Object());
	}
}
