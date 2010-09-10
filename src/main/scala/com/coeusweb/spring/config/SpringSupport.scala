/* - Coeus web framework -------------------------
 *
 * Licensed under the Apache License, Version 2.0.
 *
 * Author: Spiros Tzavellas
 */
package com.coeusweb.spring
package config

import org.springframework.web.context.support.WebApplicationContextUtils
import com.coeusweb.core.config.WebModule

/**
 * A trait that performs any Spring related setup in a {@code WebModule}.
 *
 * <p>This trait registers all the {@code Controller} classes found in the
 * {@code WebApplicationContext} in the module's controller registry. The 
 * controllers must be defined as <em>Singleton</em> Spring beans.</p>
 * 
 * @see WebApplicationContextUtils
 */
trait SpringSupport {

  this: WebModule =>

  SpringRegistrar.registerControllers(this, servletContext)


  /**
   * Retrieve a bean from <code>WebApplicationContext</code>.
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