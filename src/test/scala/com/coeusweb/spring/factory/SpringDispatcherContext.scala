/* - Coeus web framework -------------------------
 *
 * Licensed under the Apache License, Version 2.0.
 *
 * Author: Spiros Tzavellas
 */
package com.coeusweb.spring.factory

import javax.servlet.ServletConfig
import com.coeusweb.config._

class SpringDispatcherContext(sc: ServletConfig) extends WebModule(sc) {
  
  SpringRegistrar.registerControllers(this, sc.getServletContext)

  override val dispatcherConfig = new DispatcherConfig(sc) {
    override lazy val controllerFactory = new SpringControllerFactory(sc.getServletContext)
  }
}
