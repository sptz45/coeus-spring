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
import com.coeusweb.core.ControllerRegistry

import com.coeusweb.spring.test._

class SpringIntegrationTest {

  @Test
  def register_and_create_controllers() {
    val module = init("/web-context.xml")
    module.register(new ControllerRegistry(module.dispatcherConfig))
    
    val factory = module.dispatcherConfig.controllerFactory
    
    val blog = factory.createController(classOf[BlogController])
    assertNotNull(blog.index)
    assertNotSame(blog, factory.createController(classOf[BlogController]))
    
    val post = factory.createController(classOf[PostController])
    assertNotNull(post.index)
  }
  
  @Test(expected=classOf[ConfigurationException])
  def detect_singleton_controllers() {
    val module = init("/errors-context.xml")
    module.register(new ControllerRegistry(module.dispatcherConfig))
  }
  
  
  private def init(configLocation: String): SpringModule = {
    val servletContext = new MockServletContext
    servletContext.addInitParameter("contextConfigLocation", configLocation)
    val servletConfig = new MockServletConfig(servletContext, "test-servlet")
    (new ContextLoaderListener).contextInitialized(new ServletContextEvent(servletContext))
    new SpringModule(servletConfig)
  }
}
