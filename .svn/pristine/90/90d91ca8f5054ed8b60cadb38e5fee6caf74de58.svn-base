package cn.cetelem.des.expression.engine.impl;

import java.util.HashMap;
import java.util.Map;

import cn.cetelem.des.expression.engine.Engine;

/**
 * 
 * @author flaki
 * @date 2016年6月1日
 * @type 简单的引擎实例,未完成,可供参考
 * @version 1.0
 * @email wysznb@hotmail.com
 *
 */
public class SimpleEngine implements Engine {
	private final Map<String,Object> data = new HashMap<String, Object>();
	
	@Override
	public void put(String key, Object value) {
		data.put(key, value);
	}

	@Override
	public Object get(String key) {
		return data.get(key);
	}

	@Override
	public Object eval(String script) throws Exception {
		return script;
		
	}
	

}
