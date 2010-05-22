/* - Coeus web framework -------------------------
 *
 * Licensed under the Apache License, Version 2.0.
 *
 * Author: Spiros Tzavellas
 */
package com.coeusweb.spring.factory

import javax.servlet.ServletContext
import org.springframework.web.context.support.WebApplicationContextUtils
import com.coeusweb.Controller
import com.coeusweb.core.ControllerRegistry

object SpringRegistrar {

  def registerControllers(registry: ControllerRegistry, servletContext: ServletContext) {
    val context = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext)
    val controllerBeanNames = context.getBeanNamesForType(classOf[Controller])
    for (name <- controllerBeanNames) {
      if (!context.isPrototype(name)) {
        throw new ConfigurationException(
          "Controller with name '"+name+"' is not configured with 'prototype' scope. All Controllers must have 'prototype' scope.")
      }
      registry.register(context.getType(name).asInstanceOf[Class[Controller]])
    }
  }
}