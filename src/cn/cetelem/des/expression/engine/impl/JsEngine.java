package cn.cetelem.des.expression.engine.impl;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import cn.cetelem.des.expression.engine.Engine;

/**
 * 
 * @author flaki
 * @date 2016年6月1日
 * @type JS引擎 封装了ScriptEngineManager
 * @version 1.0
 * @email wysznb@hotmail.com
 *
 */
public class JsEngine implements Engine {
	private static ScriptEngine engine;
	static {
		ScriptEngineManager manager = new ScriptEngineManager();
		engine = manager.getEngineByName("javascript");
	}

	@Override
	public void put(String key, Object value) {
		engine.put(key, value);

	}

	@Override
	public Object get(String key) {
		return engine.get(key);
	}

	@Override
	public Object eval(String script) throws Exception {
		return engine.eval(script);
	}

}
