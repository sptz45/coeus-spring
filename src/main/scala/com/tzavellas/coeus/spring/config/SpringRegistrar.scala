/* - Coeus web framework -------------------------
 *
 * Licensed under the Apache License, Version 2.0.
 *
 * Author: Spiros Tzavellas
 */
package com.tzavellas.coeus.spring.config

import javax.servlet.ServletContext
import org.springframework.web.context.support.WebApplicationContextUtils
import org.springframework.web.context.WebApplicationContext
import com.tzavellas.coeus.FrameworkException
import com.tzavellas.coeus.mvc.controller.Controller
import com.tzavellas.coeus.core.config.ControllerRegistry

/**
 * Register all the coeus `Controller` beans found in a Spring `WebApplicationContext`.
 *
 * @see WebApplicationContext
 */
private object SpringRegistrar {

  /**
   * Register any coeus Controllers defined as Spring beans in the specified
   * {@code WebApplicationContext}.
   *
   * @param registry       used to register the controllers
   * @param servletContext used to locate the Spring application context
   *
   * @throws FrameworkException if an error is detected in the way the Coeus
   *         controllers are configured (e.g. when are not singleton scoped)
   */
  def registerControllers(registry: ControllerRegistry, sc: ServletContext) {
    val ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(sc)
    for (name <- controllerNames(ctx)) {
      assertHasSingletonScope(ctx, name)
      registry.controllers += ctx.getBean(name, classOf[Controller])
    }
  }

  private def controllerNames(ctx: WebApplicationContext) = {
    ctx.getBeanNamesForType(classOf[Controller])
  }

  private def assertHasSingletonScope(ctx: WebApplicationContext, name: String) {
    if (!ctx.isSingleton(name)) {
      throw new FrameworkException(
        "Controller bean with name '"+name+"' is not configured with " +
        "'singleton' scope. All Controllers must have 'singleton' scope.", null)
    }
  }
}
