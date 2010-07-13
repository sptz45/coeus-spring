/* - Coeus web framework -------------------------
 *
 * Licensed under the Apache License, Version 2.0.
 *
 * Author: Spiros Tzavellas
 */
package com.coeusweb.spring.mvc;

import java.lang.annotation.*;
import org.springframework.context.annotation.Scope;

/**
 * A Spring meta-annotation that can be used for annotating Coeus controllers.
 *
 * <p>The usage of this annotation is not necessary for using Coeus with Spring but it is
 * recommended.</p>
 *
 * <p>This annotation is annotated with the Spring's {@literal @Controller} annotation so controllers
 * annotated with this annotation can be automatically discovered in Spring's classpath scanning. Also
 * this annotation is annotated with the {@literal @Scope("prototype")} annotation so the controllers
 * will also get configured in the prototype scope.</p>
 */
@org.springframework.stereotype.Controller
@Scope("prototype")
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Inherited
public @interface Controller { }
