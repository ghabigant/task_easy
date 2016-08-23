package cn.cetelem.des.expression.sign.impl;

import cn.cetelem.des.expression.sign.Sign;

/**
 * 
 * @author flaki
 * @date 2016年6月1日
 * @type 逻辑符号
 * @version 1.0
 * @email wysznb@hotmail.com
 *
 */
public enum Judge implements Sign  {
	OR(0L,"或","or","或运算"),
	AND(1L,"与","and","与运算"),
	NOT(2L,"非","not","非运算"),
	IF(3L,"如果","if","判断先行词"),
	ELSE(4L,"就","else","判断其他条件"),
	ELSE_IF(5L,"就","else if","判断其他条件"),
	GT(6L,"大于","gt","判断其他条件"),
	LT(7L,"小于","lt","判断其他条件"),
	EQ(8L,"等于","eq","判断其他条件"),
	GTAE(9L,"大于","gtae","判断其他条件"),
	LTAE(10L,"小于","ltae","判断其他条件");
	
	private final Long signId;
	private final String name;
	private final String format;
	private final String key;
	private Judge(Long signId, String name, String format, String key) {
		this.signId = signId;
		this.name = name;
		this.format = format;
		this.key = key;
	}
	public Long getSignId() {
		return signId;
	}
	public String getName() {
		return name;
	}
	public String getFormat() {
		return format;
	}
	public String getKey() {
		return key;
	}
	
	
	
	
	

}
