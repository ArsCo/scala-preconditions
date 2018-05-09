/*
 * Copyright 2018 Arsen Ibragimov (ars)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ars.precondition.require

import ars.precondition.MessageBuilder.{notBlank, notNull}
import ars.precondition.Predicates._
import ars.precondition.implicits._

/** Internal utility methods. __Not a public API.__
  *
  * @author Arsen Ibragimov (ars)
  * @since 0.0.4
  */
protected[precondition] object RequireInternal {

  private[precondition] def requireMessage(message: String): Unit = {
    if (!isNotBlank(message)) throw new RuntimeException(notBlank("message", message))
  }

  private[precondition] def requireException(exception: Option[RuntimeException]): Unit = {
    if (!isNotNull(exception)) throw new RuntimeException(notNull("exception"))
    if (exception.isDefined && !isNotNull(exception.get)) throw new RuntimeException(notNull("exception"))
  }

  private[precondition] def requireNonNull[T](value: T, name: String): Unit = {
    value match {
      case null => throw new RuntimeException(notNull(name))
      case _ =>
    }
  }

  private[precondition] def requireNotBlank(value: String, name: String): Unit = {
    if (!isNotBlank(value)) throw new RuntimeException(notBlank(name, value))
  }

  private[precondition] def requireNonNullMessage[T](message: T): Unit = {
    requireNonNull(message, "message")
  }


  private[precondition] def requireName(name: String): Unit = {
    requireNotBlank(name, "name")
  }

  private[precondition] def requireRestriction(restriction: String): Unit = {
    requireNotBlank(restriction, "restriction")
  }

  private[precondition] def requireSizeFrom(size: Int): Unit = {
    if (!isNumberFrom(size, 0.inclusive)) throw new RuntimeException(notBlank("size", size))
  }

  private[precondition] def requireSizeUntil(size: Int): Unit = {
    if (!isNumberFrom(size, 1.inclusive)) throw new RuntimeException(notBlank("size", size))
  }

  private[precondition] def requireRange(from: Int, until: Int): Unit = {
    require(from < until, s"Incorrect range [$from, $until). Expression `from` < `until` must be true.")
  }


}
