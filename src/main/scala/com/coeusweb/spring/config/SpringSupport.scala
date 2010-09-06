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
import factory._

/**
 * A trait that performs any Spring related setup in a <code>WebModule</code>.
 *
 * <p>This trait performs two tasks:<p>
 * <ol>
 * <li>sets the <code>controllerFactory</code> of the <code>WebModule</code>
 * to <code>SpringControllerFactory</code></li>
 * <li>registers all the <code>Controller</code> classes found in the
 * <code>WebApplicationContext</code> in the module's controller registry.</li>
 * </ol>
 */
trait SpringSupport {

  this: WebModule =>

  controllerFactory = new SpringControllerFactory(servletConfig.getServletContext)
  SpringRegistrar.registerControllers(this, servletConfig.getServletContext)


  /**
   * Retrieve a bean from <code>WebApplicationContext</code>.
   * 
   * @param name the name of the bean.
   * @param T    the type of the bean.
   * @return     the bean with the specified name.
   * @throws     NoSuchBeanDefinitionException when a bean with the specified
   *             name cannot be found.
   */
  def bean[T](name: String)(implicit m: Manifest[T]): T = {
    val context = WebApplicationContextUtils.getRequiredWebApplicationContext(servletConfig.getServletContext)
    context.getBean(name).asInstanceOf[T]
  }
}