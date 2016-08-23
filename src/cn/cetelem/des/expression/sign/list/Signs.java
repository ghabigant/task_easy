package cn.cetelem.des.expression.sign.list;

import java.util.HashMap;
import java.util.Map;

import cn.cetelem.des.expression.sign.Sign;
import cn.cetelem.des.expression.sign.impl.Bound;
import cn.cetelem.des.expression.sign.impl.Judge;
import cn.cetelem.des.expression.sign.impl.Point;

/**
 * 
 * @author flaki
 * @date 2016年6月1日
 * @type 标记库
 * @version 1.0
 * @email wysznb@hotmail.com
 *
 */
public enum Signs {
	SIGNS;
	private static Map<String,Sign> datas ;
	static{
		init();
	}
	private Signs(){}
	
	private static void init() {
		datas = new HashMap<String, Sign>();
		for(Sign sign:Bound.values()){
			datas.put(sign.getFormat(), sign);
		}
		for(Sign sign:Judge.values()){
			datas.put(sign.getFormat(), sign);
		}
		for(Sign sign:Point.values()){
			datas.put(sign.getFormat(), sign);
		}
		
	}
	public Sign getSign(String format){
		return datas.get(format);
		
	}
	public Map<String, Sign> getSigns(){
		return datas;
	}
}
