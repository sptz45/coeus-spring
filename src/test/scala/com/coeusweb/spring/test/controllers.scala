/* - Coeus web framework -------------------------
 *
 * Licensed under the Apache License, Version 2.0.
 *
 * Author: Spiros Tzavellas
 */
package com.coeusweb.spring.test

import com.coeusweb.mvc._
import org.springframework.stereotype.{ Component, Controller }
import org.springframework.context.annotation.Scope

@Controller
class BlogController extends AbstractController {
  @Get def index = "index"
}

@Controller
class PostController extends AbstractController {
  @Get def index = "index"
}

@Component
@Scope("prototype")
class PrototypeController extends AbstractController {
  @Get def index = "index"
}