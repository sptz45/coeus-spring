/* - Coeus web framework -------------------------
 *
 * Licensed under the Apache License, Version 2.0.
 *
 * Author: Spiros Tzavellas
 */
package com.tzavellas.coeus.spring
package config

import org.springframework.web.context.support.WebApplicationContextUtils
import com.tzavellas.coeus.core.config.WebModule

/**
 * A trait that performs any Spring related setup in a `WebModule`.
 *
 * This trait registers all the `Controller` classes found in the
 * `WebApplicationContext` in the module's controller registry. The 
 * controllers must be defined as '''Singleton''' Spring beans.
 * 
 * @see WebApplicationContextUtils
 */
trait SpringSupport {

  this: WebModule =>

  SpringRegistrar.registerControllers(this, servletContext)


  /**
   * Retrieve a bean from `WebApplicationContext`.
   * 
   * @param name the name of the bean.
   * @param T    the type of the bean.
   * @return     the bean with the specified name.
   * @throws     NoSuchBeanDefinitionException when a bean with the specified
   *             name cannot be found.
   */
  def bean[T](name: String): T = _context.getBean(name).asInstanceOf[T]
  
  private def _context = {
    WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext)
  }
}