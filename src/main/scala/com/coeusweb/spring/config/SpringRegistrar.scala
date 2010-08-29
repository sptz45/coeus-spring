/* - Coeus web framework -------------------------
 *
 * Licensed under the Apache License, Version 2.0.
 *
 * Author: Spiros Tzavellas
 */
package com.coeusweb.spring.config

import javax.servlet.ServletContext
import org.springframework.web.context.support.WebApplicationContextUtils
import org.springframework.web.context.WebApplicationContext
import com.coeusweb.Controller
import com.coeusweb.core.FrameworkException
import com.coeusweb.config.ControllerRegistry

/**
 * Register all the Coeus <code>Controller</code> classes found in a Spring
 * <code>WebApplicationContext</code>.
 *
 * @see WebApplicationContext
 */
private object SpringRegistrar {

  /**
   * Register any Coeus Controllers defined as Spring beans in the specified
   * {@code WebApplicationContext}.
   *
   * @param registry       used to register the controllers
   * @param servletContext used to locate the Spring application context
   *
   * @throws FrameworkException if an error is detected in the way the Coeus
   *         controllers are configured (e.g. when are not prototype scoped)
   */
  def registerControllers(registry: ControllerRegistry, sc: ServletContext) {
    val ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(sc)
    for (name <- controllerNames(ctx)) {
      assertHasPrototypeScope(ctx, name)
      registry.controllers += ctx.getType(name).asInstanceOf[Class[Controller]]
    }
  }

  private def controllerNames(ctx: WebApplicationContext) = {
    ctx.getBeanNamesForType(classOf[Controller])
  }

  private def assertHasPrototypeScope(ctx: WebApplicationContext, name: String) {
    if (!ctx.isPrototype(name)) {
      throw new FrameworkException(
        "Controller bean with name '"+name+"' is not configured with " +
        "'prototype' scope. All Controllers must have 'prototype' scope.")
    }
  }
}
