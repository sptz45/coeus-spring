package com.coeusweb.spring.factory

import org.junit.Test
import org.junit.Assert._
import javax.servlet.ServletContextEvent
import org.springframework.mock.web.{ MockServletConfig, MockServletContext }
import org.springframework.web.context.ContextLoaderListener

import com.coeusweb.spring.test._

class SpringRegistrarTest {
  
  @Test(expected=classOf[ConfigurationException])
  def detect_singleton_controllers() {
    init("/errors-context.xml")
  }
  
  @Test
  def register_the_controllers_from_spring_context() {
    val controllers = init("/web-context.xml").controllers
    assertTrue(controllers.contains(classOf[BlogController]))
    assertTrue(controllers.contains(classOf[PostController]))
  }
  
  private def init(configLocation: String) = {
    val servletContext = new MockServletContext
    servletContext.addInitParameter("contextConfigLocation", configLocation)
    val servletConfig = new MockServletConfig(servletContext, "test-servlet")
    (new ContextLoaderListener).contextInitialized(new ServletContextEvent(servletContext))
    new SpringDispatcherContext(servletConfig)
  }
}