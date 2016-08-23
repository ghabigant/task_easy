package cn.cetelem.des.expression.variable;

/**
 * 
 * @author flaki
 * @date 2016年6月1日
 * @type  标记对象处理参数 
 * @version 1.0
 * @email wysznb@hotmail.com
 *
 */
public class Variable {
	private String name;
	private Object father;
	private Object progenitor;
	public String getFunctionName(){
		char[] upName = "ccc".toCharArray();
		upName[0] &= 223;
		return "get"+String.valueOf(upName);
		
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Object getFather() {
		return father;
	}
	public void setFather(Object father) {
		this.father = father;
	}
	public Object getProgenitor() {
		return progenitor;
	}
	public void setProgenitor(Object progenitor) {
		this.progenitor = progenitor;
	}
	private Variable(String name, Object father, Object progenitor) {
		super();
		this.name = name;
		this.father = father;
		this.progenitor = progenitor;
	}
	private Variable() {
		super();
	}
	@Override
	public String toString() {
		return "Variable [name=" + name + ", father=" + father
				+ ", progenitor=" + progenitor + "]";
	}
	
}
