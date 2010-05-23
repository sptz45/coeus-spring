/* - Coeus web framework -------------------------
 *
 * Licensed under the Apache License, Version 2.0.
 *
 * Author: Spiros Tzavellas
 */
package com.coeusweb.spring.factory

import javax.servlet.ServletConfig
import com.coeusweb.core.ControllerRegistry
import com.coeusweb.config._


class SpringModule(sc: ServletConfig) extends ConfigBuilder(sc) with ControllerRegistrar {

  override val dispatcherConfig = new DispatcherConfig(sc) {
    override lazy val controllerFactory = new SpringControllerFactory(sc.getServletContext)
  }

  def register(registry: ControllerRegistry) {
    SpringRegistrar.registerControllers(registry, sc.getServletContext)
  }
}