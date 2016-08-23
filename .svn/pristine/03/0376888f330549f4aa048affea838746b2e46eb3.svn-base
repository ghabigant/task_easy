/**
 * 
 */
package cn.cetelem.des.utils;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import cn.cetelem.des.work_support.worker.annotation.EfficientLogWorkerGroup4Annotation;

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
@Task
public @interface Group {
	String value() default "";
	Class<?> type() default EfficientLogWorkerGroup4Annotation.class;
}
