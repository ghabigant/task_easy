package cn.cetelem.des.interceptor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Repository;


/**
 * 
 * @author flaki
 * @date 2016年6月1日
 * @type 任务性能分析AOP
 * @version 1.0
 * @email wysznb@hotmail.com
 *
 */
@Repository
@Aspect
public class NodeInterceptor {
	private TaskLogger logger = TaskLogger.getLogger(NodeInterceptor.class);
	@Pointcut("execution(* cn.cetelem.des.task..*.*(..))")
	private void anyMethod(){}
	
	@Around("anyMethod()")
	public Object around(ProceedingJoinPoint pjp) throws Throwable{
		String taskName = pjp.getTarget().getClass().getSimpleName();
		logger.info("###################################");
		logger.info("[TASK:"+taskName+"]:任务开始");
		long ss = System.currentTimeMillis();
		Object ob = pjp.proceed();
		long se = System.currentTimeMillis();
		logger.info("[TASK:"+taskName+"]:任务执行用时："+String.format("%.2f",(se-ss)/1000.0)+"s");
		logger.info("###################################");
		return ob;
		
	}

}
