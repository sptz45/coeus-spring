/* - Coeus web framework -------------------------
 *
 * Licensed under the Apache License, Version 2.0.
 *
 * Author: Spiros Tzavellas
 */
package com.coeusweb.spring.interceptor

import org.springframework.web.context.request.{ WebRequestInterceptor, ServletWebRequest }
import org.springframework.web.servlet.HandlerInterceptor 
import org.springframework.ui.ModelMap
import com.coeusweb.interceptor.RequestInterceptor
import com.coeusweb.core.RequestContext

/**
 * Adapts Spring's {@link WebRequestInterceptor} and {@link HandlerInterceptor} to
 * Coeus {@link RequestInterceptor} trait.
 */
object RequestInterceptorAdapter {
  
  /**
   * Wrap the given @{code WebRequestInterceptor} in a {@code RequestInterceptor}. 
   */
  def apply(interceptor: WebRequestInterceptor): RequestInterceptor =
    new WebRequestInterceptorAdapter(interceptor)
  
  /**
   * Wrap the given @{code HandlerInterceptor} in a {@code RequestInterceptor}. 
   */
  def apply(interceptor: HandlerInterceptor): RequestInterceptor =
    new HandlerInterceptorAdapter(interceptor)

  private implicit def toException(t: Throwable) = t match {
    case e: Exception => e
    case _            => new Exception(t)
  }

  private class WebRequestInterceptorAdapter(interceptor: WebRequestInterceptor) extends RequestInterceptor {
    def preHandle(context: RequestContext): Boolean = {
      val req = new ServletWebRequest(context.request.servletRequest, context.response.servletResponse)
      interceptor.preHandle(req)
      continue
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

  private class HandlerInterceptorAdapter(interceptor: HandlerInterceptor) extends RequestInterceptor {
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
