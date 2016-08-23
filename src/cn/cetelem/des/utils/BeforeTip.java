/**
 * 
 */
package cn.cetelem.des.utils;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author flaki
 * @date 2016年7月7日
 * @type TODO
 * @version 1.0
 * @email wysznb@hotmail.com
 * 
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface BeforeTip {
	String value() default "";

}
