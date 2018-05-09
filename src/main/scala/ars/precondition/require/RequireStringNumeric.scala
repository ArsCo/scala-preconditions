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

import ars.precondition.MessageBuilder.{NoNameParameter, numberOfType}
import ars.precondition.Predicates._

/** `requireXXX` methods for numeric string values.
  *
  * @author Arsen Ibragimov (ars)
  * @since 0.0.4
  */
trait RequireStringNumeric extends RequireCore {

  /**
    * Tests that `value` is a `Byte` number string, and otherwise throws `IllegalArgumentException`.
    *
    * @param value the value to test
    * @param name the name to include in the failure message
    */
  def requireByte(value: String, name: String = NoNameParameter): Unit = {
    require(isByteNumber(value), numberOfType(name, value, classOf[Byte]))
  }

  /**
    * Tests that `value` is a `Short` number string, and otherwise throws `IllegalArgumentException`.
    *
    * @param value the value to test
    * @param name the name to include in the failure message
    */
  def requireShort(value: String, name: String = NoNameParameter): Unit = {
    require(isShortNumber(value), numberOfType(name, value, classOf[Short]))
  }

  /**
    * Tests that `value` is an `Int` number string, and otherwise throws `IllegalArgumentException`.
    *
    * @param value the value to test
    * @param name the name to include in the failure message
    */
  def requireInt(value: String, name: String = NoNameParameter): Unit = {
    require(isIntNumber(value), numberOfType(name, value, classOf[Int]))
  }

  /**
    * Tests that `value` is a `Long` number string, and otherwise throws `IllegalArgumentException`.
    *
    * @param value the value to test
    * @param name the name to include in the failure message
    */
  def requireLong(value: String, name: String = NoNameParameter): Unit = {
    require(isLongNumber(value), numberOfType(name, value, classOf[Long]))
  }

  /**
    * Tests that `value` is a `Float` number string, and otherwise throws `IllegalArgumentException`.
    *
    * @param value the value to test
    * @param name the name to include in the failure message
    */
  def requireFloat(value: String, name: String = NoNameParameter): Unit = {
    require(isFloatNumber(value), numberOfType(name, value, classOf[Float]))
  }

  /**
    * Tests that `value` is a `Double` number string, and otherwise throws `IllegalArgumentException`.
    *
    * @param value the value to test
    * @param name the name to include in the failure message
    */
  def requireDouble(value: String, name: String = NoNameParameter): Unit = {
    require(isDoubleNumber(value), numberOfType(name, value, classOf[Double]))
  }
}
