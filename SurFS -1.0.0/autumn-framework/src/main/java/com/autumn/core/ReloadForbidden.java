package com.autumn.core;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>Title: 类加载框架</p>
 *
 * <p>Description: 添加@ReloadForbidden注释，不执行重载</p>
 *
 * <p>Copyright: Autumn Copyright (c) 2011</p>
 *
 * <p>Company: Autumn </p>
 *
 * @author 刘社朋
 * @version 2.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ReloadForbidden {
}
