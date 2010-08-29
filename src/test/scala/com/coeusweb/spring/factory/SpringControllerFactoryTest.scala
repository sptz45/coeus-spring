/* - Coeus web framework -------------------------
 *
 * Licensed under the Apache License, Version 2.0.
 *
 * Author: Spiros Tzavellas
 */
package com.coeusweb.spring.factory


import org.junit.Test
import org.junit.Assert._
import javax.servlet.ServletContextEvent
import org.springframework.mock.web.{ MockServletConfig, MockServletContext }
import org.springframework.web.context.ContextLoaderListener

import com.coeusweb.spring.test._

class SpringControllerFactoryTest {

  @Test
  def create_controllers_from_spring_app_context() {
    val config = init("/web-context.xml")
    val factory = config.controllerFactory
    
    val blog = factory.createController(classOf[BlogController])
    assertNotNull(blog.index)
    assertNotSame(blog, factory.createController(classOf[BlogController]))
    
    val post = factory.createController(classOf[PostController])
    assertNotNull(post.index)
  }
  
  private def init(configLocation: String) = {
    val servletContext = new MockServletContext
    servletContext.addInitParameter("contextConfigLocation", configLocation)
    val servletConfig = new MockServletConfig(servletContext, "test-servlet")
    (new ContextLoaderListener).contextInitialized(new ServletContextEvent(servletContext))
    new SpringWebModule(servletConfig)
  }
}
