package ar.com.adriabe.model.common.annotation;

import ar.com.adriabe.model.constant.ACTION_TYPE;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Mildo on 6/10/14.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ILogableOperation {
    String desc() default "Definir descripcion de operación";
    ACTION_TYPE type() default ACTION_TYPE.READ;
}
