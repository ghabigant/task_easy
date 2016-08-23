package cn.cetelem.des.expression.engine;

/**
 * 
 * @author flaki
 * @date 2016年6月1日
 * @type 表达式引擎接口，用于自定义引擎
 * @version 1.0
 * @email wysznb@hotmail.com
 *
 */
public interface Engine {
	public void put(String key, Object value);
	public Object get(String key);
	public Object eval(String script) throws Exception;

}
