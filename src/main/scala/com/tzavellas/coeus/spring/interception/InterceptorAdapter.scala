/* - Coeus web framework -------------------------
 *
 * Licensed under the Apache License, Version 2.0.
 *
 * Author: Spiros Tzavellas
 */
package com.tzavellas.coeus.spring.interception

import org.springframework.web.context.request.{ WebRequestInterceptor, ServletWebRequest }
import org.springframework.web.servlet.HandlerInterceptor 
import org.springframework.ui.ModelMap
import com.tzavellas.coeus.core.interception.Interceptor
import com.tzavellas.coeus.core.RequestContext

/**
 * Adapts Spring's `WebRequestInterceptor` and `HandlerInterceptor` to
 * coeus `Interceptor` trait.
 */
object InterceptorAdapter {
  
  /**
   * Wrap the given `WebRequestInterceptor` in a `RequestInterceptor`. 
   */
  def apply(interceptor: WebRequestInterceptor): Interceptor =
    new WebRequestInterceptorAdapter(interceptor)
  
  /**
   * Wrap the given `HandlerInterceptor` in a `RequestInterceptor`. 
   */
  def apply(interceptor: HandlerInterceptor): Interceptor =
    new HandlerInterceptorAdapter(interceptor)

  private class WebRequestInterceptorAdapter(interceptor: WebRequestInterceptor) extends Interceptor {
    def preHandle(context: RequestContext): Boolean = {
      val req = new ServletWebRequest(context.request.servletRequest, context.response.servletResponse)
      interceptor.preHandle(req)
      true
    }
    def postHandle(context: RequestContext) {
      val req = new ServletWebRequest(context.request.servletRequest, context.response.servletResponse)
      interceptor.postHandle(req, new ModelMap)
    }
    def afterRender(context: RequestContext) {
      val req = new ServletWebRequest(context.request.servletRequest, context.response.servletResponse)
      interceptor.afterCompletion(req, context.error)
    }
  }

  private class HandlerInterceptorAdapter(interceptor: HandlerInterceptor) extends Interceptor {
    def preHandle(context: RequestContext): Boolean = {
      import context._
      interceptor.preHandle(request.servletRequest, response.servletResponse, handler)
    }
    def postHandle(context: RequestContext) {
      import context._
      interceptor.postHandle(request.servletRequest, response.servletResponse, handler, null)
    }
    def afterRender(context: RequestContext) {
      import context._
      interceptor.afterCompletion(request.servletRequest, response.servletResponse, handler, error)
    }
  }
}
