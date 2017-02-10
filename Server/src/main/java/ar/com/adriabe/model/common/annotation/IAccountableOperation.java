package ar.com.adriabe.model.common.annotation;

import ar.com.adriabe.model.constant.ACCOUNTABLE_DOCUMENT;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Mildo on 6/10/14.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface IAccountableOperation {
    String desc() default "Definir descripcion de operación";
    ACCOUNTABLE_DOCUMENT type() default ACCOUNTABLE_DOCUMENT.EMPTY;

}
