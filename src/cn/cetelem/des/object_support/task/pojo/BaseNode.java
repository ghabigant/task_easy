package cn.cetelem.des.object_support.task.pojo;

/**
 * 
 * @author flaki
 * @date 2016年6月1日
 * @type 任务标记单元
 * @version 1.0
 * @email wysznb@hotmail.com
 *
 */
public class BaseNode {
	protected String name;
	private String expression;
	private String inType;
	private String outType;
	private String gotoBean;
	private String gotoMethod;
	private String method;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getExpression() {
		return expression;
	}
	public void setExpression(String expression) {
		this.expression = expression;
	}
	public String getGotoBean() {
		return gotoBean;
	}
	public void setGotoBean(String gotoBean) {
		this.gotoBean = gotoBean;
	}
	public String getGotoMethod() {
		return gotoMethod;
	}
	public void setGotoMethod(String gotoMethod) {
		this.gotoMethod = gotoMethod;
	}
	public String getInType() {
		return inType;
	}
	public void setInType(String inType) {
		this.inType = inType;
	}
	public String getOutType() {
		return outType;
	}
	public void setOutType(String outType) {
		this.outType = outType;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	
	
	
}
