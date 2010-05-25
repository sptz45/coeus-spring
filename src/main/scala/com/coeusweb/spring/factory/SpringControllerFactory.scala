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
import com.coeusweb.core.factory.ControllerFactory
import com.coeusweb.core.convention.Conventions

/**
 * A {@code ControllerFactory} that creates {@code Controller} instances from beans
 * defined in a {@code WebApplicationContext}. 
 * 
 * @see SpringRegistrar
 */
class SpringControllerFactory(servletContext: ServletContext) extends ControllerFactory {
  
  val context = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext)

  def controllerRegistered[C <: Controller](controllerClass: Class[C]) { }

  def createController[C <: Controller](klass: Class[C]): C = {
    context.getBean(classToBeanName(klass)).asInstanceOf[C]
  }
  
  /**
   * Translate the class name to a bean name to be used for creating a controller instance
   * from a Spring {@code ApplicationContext}.
   * 
   * <p>This method uses the class simple name with the first character converted to lower
   * case.</p>
   * 
   * @param c the class to use for deriving a controller bean name
   */
  protected def classToBeanName(c: Class[_]) = Conventions.classToAttributeName(c)
}