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
import com.coeusweb.core.util.Strings


class SpringControllerFactory(servletContext: ServletContext) extends ControllerFactory {
  
  val context = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext)

  def registerClass[C <: Controller](controllerClass: Class[C]) { }

  def createController[C <: Controller](klass: Class[C]): C = {
    context.getBean(classToBeanName(klass)).asInstanceOf[C]
  }
  
  def classToBeanName(c: Class[_]) = Strings.firstCharToLower(c.getSimpleName)
}