/* - Coeus web framework -------------------------
 *
 * Licensed under the Apache License, Version 2.0.
 *
 * Author: Spiros Tzavellas
 */
package com.tzavellas.coeus.spring.config

import org.junit.Test
import org.junit.Assert._
import javax.servlet.ServletContextEvent
import org.springframework.mock.web.{ MockServletConfig, MockServletContext }
import org.springframework.web.context.ContextLoaderListener
import com.tzavellas.coeus.FrameworkException

import com.tzavellas.coeus.spring.test._

class SpringRegistrarTest {
  
  @Test(expected=classOf[FrameworkException])
  def raise_error_when_a_controller_is_not_configured_with_singleton_scope() {
    init("/errors-context.xml")
  }
  
  @Test
  def register_the_controllers_from_spring_context() {
    val controllers = init("/web-context.xml").controllers.result
    assertTrue(controllers.exists(_.getClass == classOf[BlogController]))
    assertTrue(controllers.exists(_.getClass == classOf[PostController]))
  }
  
  private def init(configLocation: String) = {
    val servletContext = new MockServletContext
    servletContext.addInitParameter("contextConfigLocation", configLocation)
    val servletConfig = new MockServletConfig(servletContext, "test-servlet")
    (new ContextLoaderListener).contextInitialized(new ServletContextEvent(servletContext))
    new SpringWebModule(servletConfig)
  }
}
