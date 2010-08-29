/* - Coeus web framework -------------------------
 *
 * Licensed under the Apache License, Version 2.0.
 *
 * Author: Spiros Tzavellas
 */
package com.coeusweb.spring
package test

import javax.servlet.ServletConfig
import com.coeusweb.config._

class SpringWebModule(sc: ServletConfig) extends WebModule(sc)
                                            with config.SpringSupport
