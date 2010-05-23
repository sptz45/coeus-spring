/* - Coeus web framework -------------------------
 *
 * Licensed under the Apache License, Version 2.0.
 *
 * Author: Spiros Tzavellas
 */
package com.coeusweb.spring.i18n.msg

import java.util.Locale
import org.springframework.context.{ MessageSource, NoSuchMessageException }
import com.coeusweb.i18n.msg.{ MessageBundle, MessageNotFoundException }

/**
 * Adapts a Spring {@link MessageSource} to a Coeus {@link MessageBundle}.
 */
class MessageSourceBundle(messages: MessageSource) extends MessageBundle {

  def apply(locale: Locale, code: String, args: Any*): String = {
    try {
      messages.getMessage(code, box(args), locale)
    } catch {
      case e: NoSuchMessageException => throw new MessageNotFoundException(code, locale, e) 
    }
  }

  def get(locale: Locale, code: String, args: Any*): Option[String] = {
    try {
      Some(messages.getMessage(code, box(args), locale))
    } catch {
      case e: NoSuchMessageException => None 
    }
  }

  private def box(args: Seq[Any]): Array[AnyRef] = {
    val boxed = new Array[AnyRef](args.length)
    var i = 0
    while (i < boxed.length) {
      boxed(i) = args(i).asInstanceOf[AnyRef]
      i += 1
    }
    boxed
  }
}