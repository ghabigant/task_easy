package cn.cetelem.des.expression.sign.impl;

import cn.cetelem.des.expression.sign.Sign;

/**
 * 
 * @author flaki
 * @date 2016年6月1日
 * @type 边界符号
 * @version 1.0
 * @email wysznb@hotmail.com
 *
 */
public enum Bound implements Sign {
	F_BRACE(11L,"前大括号","{","边界"),
	L_BRACE(12L,"后大括号","}","边界"),
	F_PAR(13L,"前小括号","(","边界"),
	L_PAR(14L,"后小括号",")","边界"),
	Blank(15L,"空格"," ","边界");
	private final Long signId;
	private final String name;
	private final String format;
	private final String key;
	private Bound(Long signId, String name, String format, String key) {
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
