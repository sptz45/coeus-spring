/* - Coeus web framework -------------------------
 *
 * Licensed under the Apache License, Version 2.0.
 *
 * Author: Spiros Tzavellas
 */
package com.coeusweb.spring.test

import com.coeusweb.mvc._
import com.coeusweb.spring.mvc.Controller
import org.springframework.stereotype.Component

@Controller
class BlogController extends AbstractController {
  @Get def index = "index"
}

@Controller
class PostController extends AbstractController {
  @Get def index = "index"
}

@Component
class SingletonController extends AbstractController {
  @Get def index = "index"
}