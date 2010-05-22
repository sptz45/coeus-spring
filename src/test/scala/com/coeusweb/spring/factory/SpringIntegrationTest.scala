/* - Coeus web framework -------------------------
 *
 * Licensed under the Apache License, Version 2.0.
 *
 * Author: Spiros Tzavellas
 */
package com.coeusweb.spring
package factory

import org.junit.Test
import org.junit.Assert._
import javax.servlet.ServletContextEvent
import org.springframework.mock.web.MockServletConfig
import org.springframework.web.context.ContextLoaderListener
import com.coeusweb.core.ControllerRegistry

class SpringIntegrationTest {
  
  val servletConfig = new MockServletConfig("test-servlet")
  (new ContextLoaderListener).contextInitialized(new ServletContextEvent(servletConfig.getServletContext))
  
  val module = new SpringModule(servletConfig)
  val config = module.dispatcherConfig

  @Test
  def register_and_create_controllers() {
    
    module.register(new ControllerRegistry(config))
    
    val blog = config.controllerFactory.createController(classOf[test.BlogController])
    assertNotNull(blog.index)
    assertNotSame(blog, config.controllerFactory.createController(classOf[test.BlogController]))
    
    val post = config.controllerFactory.createController(classOf[test.PostController])
    assertNotNull(post.index)
  }
}
