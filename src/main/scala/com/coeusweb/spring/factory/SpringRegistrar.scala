/* - Coeus web framework -------------------------
 *
 * Licensed under the Apache License, Version 2.0.
 *
 * Author: Spiros Tzavellas
 */
package com.coeusweb.spring.factory

import javax.servlet.ServletContext
import org.springframework.web.context.support.WebApplicationContextUtils
import org.springframework.web.context.WebApplicationContext
import com.coeusweb.Controller
import com.coeusweb.config.ControllerRegistry

/**
 * Register all the Coeus {@code Controller} classes found in a Spring {@code WebApplicationContext}.
 *
 * @see SpringControllerFactory
 * @see WebApplicationContext
 */
object SpringRegistrar {

  /**
   * Register any Coeus Controllers defined as Spring beans in the {@code WebApplicationContext}.
   *
   * <p>This method uses {@link WebApplicationContextUtils#getRequiredWebApplicationContext}
   * to find the Spring {@code WebApplicationContext} in the specified {@code ServletContext}.</p>
   *
   * @param registry used to register the controllers
   * @param servletContext used to locate the Spring application context
   *
   * @throws ConfigurationException if an error is detected in the way the Coeus controllers are
   *         configured (e.g. when are not prototype scoped)
   */
  def registerControllers(registry: ControllerRegistry, servletContext: ServletContext) {
    val context = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext)
    for (name <- controllerNames(context)) {
      assertIsPrototype(context, name)
      registry.register(context.getType(name).asInstanceOf[Class[Controller]])
    }
  }

  private def controllerNames(context: WebApplicationContext) = {
    context.getBeanNamesForType(classOf[Controller])
  }

  private def assertIsPrototype(context: WebApplicationContext, controllerName: String) {
    if (!context.isPrototype(controllerName)) {
      throw new ConfigurationException(
        "Controller bean with name '"+controllerName+"' is not configured with 'prototype' scope. All Controllers must have 'prototype' scope.")
    }
  }
}
