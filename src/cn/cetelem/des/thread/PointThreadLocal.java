/**
 * 
 */
package cn.cetelem.des.thread;

import org.springframework.stereotype.Repository;

/**
 * @author flaki
 * @date 2016年6月2日
 * @type 指针ThreadLocal
 * @version 1.0
 * @email wysznb@hotmail.com
 * 
 */
@Repository
public class PointThreadLocal extends ThreadLocal<String> {
	

	/* 
	 * @see java.lang.ThreadLocal#initialValue()
	 */
	@Override
	protected String initialValue() {
		return "start";
	}
	
	
	

}
