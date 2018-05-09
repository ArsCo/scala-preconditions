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

import ars.precondition.MessageBuilder.{NoNameParameter, notBlank}
import ars.precondition.Predicates.isNotBlank

import scala.collection.Iterable

/** `requireXXX` methods for [[Iterable]] values.
  *
  * @author Arsen Ibragimov (ars)
  * @since 0.0.4
  */
trait RequireIterable extends RequireCore {

  /**
    * Tests that sequence `value` isn't `null` or empty, and otherwise throws [[RequireCore.exception()]].
    *
    * @param value the iterable to test
    * @param name the name to include in the failure message
    *
    * @tparam T the type of iterable elements
    *
    * @throws RuntimeException if test fails
    */
  def requireNotBlank[T](value: Iterable[T], name: String): Unit = {
    require(isNotBlank(value), notBlank(name, value))
  }

  /**
    * Tests that sequence `value` isn't `null` or empty, and otherwise throws [[RequireCore.exception()]].
    *
    * @param value the iterable to test
    *
    * @tparam T the type of iterable elements
    *
    * @throws RuntimeException if test fails
    */
  def requireNotBlank[T](value: Iterable[T]): Unit = {
    requireNotBlank[T](value, NoNameParameter)
  }
}
