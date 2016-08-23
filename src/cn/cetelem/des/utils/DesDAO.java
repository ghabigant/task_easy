package cn.cetelem.des.utils;

import org.springframework.stereotype.Repository;


@Repository
public @interface DesDAO {
	String value() default "";

}
