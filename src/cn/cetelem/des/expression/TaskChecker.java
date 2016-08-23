package cn.cetelem.des.expression;

import javax.annotation.Resource;
import javax.script.ScriptException;

import org.springframework.stereotype.Repository;

import sun.org.mozilla.javascript.internal.NativeJavaObject;
import sun.org.mozilla.javascript.internal.NativeObject;
import cn.cetelem.des.expression.engine.Engine;
import cn.cetelem.des.object_support.task.pojo.BaseNode;
import cn.cetelem.des.object_support.task.resolver.TaskResolver;
import cn.cetelem.des.thread.EngineThreadLocal;

/**
 * 
 * @author flaki
 * @date 2016年6月1日
 * @type 表达式解析器
 * @version 1.0
 *
 */
@Repository
public class TaskChecker {

	@Resource
	private TaskResolver taskResolver;


	/**
	 * 表达式引擎,在初始化获得
	 */
	private static ThreadLocal<Engine> engineLocal = new EngineThreadLocal();

	public static ThreadLocal<Engine> getEngineLocal() {
		return engineLocal;
	}

	public static void setEngineLocal(ThreadLocal<Engine> engineLocal) {
		TaskChecker.engineLocal = engineLocal;
	}

	/**
	 * 
	 * @param baseNode
	 *            节点信息
	 * @param context
	 *            传输对象
	 * @return 结果 比如：check(baseNode,simpleBean) 其中
	 *         baseNode的expression属性为"bean.name == '张三'"
	 *         simpleBean的name属性为'张三'(bean是隐含对象和参数对应) 则返回 'false'。
	 *         expression为javascript语句 如果expression为单表达式则一定需要是返回boolean样式的字符串
	 *         如果expression不为单表达式,解析器会读取另外2个表达式： decision：'false'和'true'
	 *         gotoPath: 字符串，代表目标Task 注意： 只有decision 为'false'
	 *         或者单表达式为'false'时才不会走本任务流程 也就是说如果结果为null也会执行任务
	 *         goto只有获取实际TaskName时才有效
	 * 
	 */
	public String check(BaseNode baseNode, Object context) {

		try {
			Engine engine = engineLocal.get();
			String el = baseNode.getExpression();
			if (el == null || "".equals(el)) {
				return "true";
			}
			engine.put("bean", context);
			// 执行表达式
			Object result = engine.eval(el);
			if (result instanceof Boolean) {
				if ((Boolean) result) {
					return "true";
				} else {
					return "false";
				}
			} else {
				String gotoDecision = handler(engine.get("gotoPath"));
				String decision = handler(engine.get("decision"));
				String longDecision = (decision == null ? "null" : decision)
						+ "=" + (gotoDecision == null ? "null" : gotoDecision);
				return longDecision;
			}
			
		} catch (ScriptException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "true";
	}

	/**
	 * 处理引擎返回结果
	 * 
	 * 注意 此方法只兼容jdk6和jdk7 如果有用到低版本或更高版本的需求 可以通过邮寄告诉我,我会提供一个适应版本的工具包
	 */
	private String handler(Object object) {
		if (object instanceof String) {
			return (String) object;
		} else if (object instanceof NativeJavaObject) {
			return (String) ((NativeJavaObject) object)
					.getDefaultValue(String.class);
		} else if (object instanceof NativeObject) {
			return (String) ((NativeObject) object)
					.getDefaultValue(String.class);
		} else if (object == null) {
			return null;
		}
		return object.toString();
	}

}
