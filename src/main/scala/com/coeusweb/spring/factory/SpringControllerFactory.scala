/* - Coeus web framework -------------------------
 *
 * Licensed under the Apache License, Version 2.0.
 *
 * Author: Spiros Tzavellas
 */
package com.coeusweb.spring.factory

import javax.servlet.ServletContext
import org.springframework.web.context.support.WebApplicationContextUtils
import com.coeusweb.mvc.controller.Controller
import com.coeusweb.mvc.util.Conventions
import com.coeusweb.core.factory.ControllerFactory

/**
 * A <code>ControllerFactory</code> that creates <code>Controller</code>
 * instances from beans defined in a <code>WebApplicationContext</code>.
 * 
 * @param sc the <code>ServletContext</code> to be used for discovering the
 *           <code>WebApplicationContext</code>.
 * 
 * @see WebApplicationContext
 */
class SpringControllerFactory(sc: ServletContext) extends ControllerFactory {
  
  val context = WebApplicationContextUtils.getRequiredWebApplicationContext(sc)

  def controllerRegistered[C <: Controller](controllerClass: Class[C]) { }

  def createController[C <: Controller](klass: Class[C]): C = {
    context.getBean(classToBeanName(klass)).asInstanceOf[C]
  }
  
  /**
   * Derive the bean name of a controller from the controller's class.
   * 
   * <p>This method will return the simple name of the class with the first
   * character converted to lower case.</p>
   * 
   * @param c the class to use for deriving a controller bean name
   */
  protected def classToBeanName(c: Class[_]) = Conventions.classToAttributeName(c)
}