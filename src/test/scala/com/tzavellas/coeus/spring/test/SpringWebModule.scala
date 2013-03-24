/* - Coeus web framework -------------------------
 *
 * Licensed under the Apache License, Version 2.0.
 *
 * Author: Spiros Tzavellas
 */
package com.tzavellas.coeus.spring
package test

import javax.servlet.ServletConfig
import com.tzavellas.coeus.core.config._
import config.SpringSupport

class SpringWebModule(sc: ServletConfig) extends WebModule(sc)
                                            with SpringSupport
