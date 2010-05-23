/* - Coeus web framework -------------------------
 *
 * Licensed under the Apache License, Version 2.0.
 *
 * Author: Spiros Tzavellas
 */
package com.coeusweb.spring.factory

import com.coeusweb.core.FrameworkException

/**
 * Thrown when there is a problem in the way the Coeus controllers are configured
 * inside a Spring ApplicationContext.
 */
class ConfigurationException(message: String) extends FrameworkException(message)