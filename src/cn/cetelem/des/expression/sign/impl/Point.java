package cn.cetelem.des.expression.sign.impl;

import cn.cetelem.des.expression.sign.Sign;

	/**
	 * 
	 * @author flaki
	 * @date 2016年6月1日
	 * @type 指针符号
	 * @version 1.0
	 * @email wysznb@hotmail.com
	 *
	 */
public enum Point implements Sign {
	GOTO(16L,"跳转","goto","跳转"),
	NEXT(17L,"下一步","next","跳转");
	private final Long signId;
	private final String name;
	private final String format;
	private final String key;
	private Point(Long signId, String name, String format, String key) {
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
