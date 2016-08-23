package cn.cetelem.des.interceptor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Repository;

import cn.cetelem.des.object_support.task.pojo.BaseNode;

/**
 * 
 * @author flaki
 * @date 2016年6月1日
 * @type 任务处理AOP 用于记录任务指向
 * @version 1.0
 * @email wysznb@hotmail.com
 *
 */
@Repository
@Aspect
public class CheckerInterceptor {
	private TaskLogger logger = TaskLogger.getLogger(NodeInterceptor.class);
	
	@Pointcut("execution(* cn.cetelem.des.expression.TaskChecker.check(..))")
	private void anyMethod(){}
	
	@Around("anyMethod()")
	public String around(ProceedingJoinPoint pjp) throws Throwable{
		String gotoBean = ((BaseNode)pjp.getArgs()[0]).getGotoBean();
		String result =(String) pjp.proceed();
		String logInfo = "检测表达式："+((BaseNode)pjp.getArgs()[0]).getExpression()+"||"+"结果：" +result;
		if(gotoBean == null){
			if("true".equals(result)){
				logInfo +="||决定：执行结束任务,流程结束";
			}else if("false".equals(result)){
				logInfo +="||决定：不执行结束任务,流程结束";
			}
			logger.info(logInfo);
			return result;
		}
		if("true".equals(result)){
			logInfo +="||决定：执行本任务并跳转至"+gotoBean;
		}else if("false".equals(result)){
			logInfo +="||决定：不执行本任务并跳转至"+gotoBean;
		}else{
			logInfo +="||多重决定";
		}
		logger.info(logInfo);
		return result;
	}

}
