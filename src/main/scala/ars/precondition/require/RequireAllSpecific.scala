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

import ars.precondition.MessageBuilder.NoNameParameter
import ars.precondition.implicits._

import scala.collection.Iterable

/** `requireXXX` methods to test specific property for all elements of iterable.
  *
  * @author Arsen Ibragimov (ars)
  * @since 0.0.4
  */
trait RequireAllSpecific extends RequireAll with RequireAny with RequireString {

  /**
    * Tests that iterable `value` does not contain `null` values, and
    * otherwise throws [[RequireCore.exception()]].
    *
    * @param value the iterable value (must be non-null)
    * @param name the name to include in the failure message (must be non-null)
    *
    * @tparam T the type of iterable elements
    */
  @inline def requireAllNotNull[T](value: Iterable[T], name: String = NoNameParameter): Unit = {
    requireAll(value, name)(requireNotNull)
  }
  //
  /**
    * Tests that iterable `value` does not contain blank (`null` or empty) strings, and
    * otherwise throws [[RequireCore.exception()]].
    *
    * @param value the iterable value (must be non-null)
    * @param name the name to include in the failure message (must be non-null)
    */
  @inline def requireAllNotBlank(value: Iterable[String], name: String = NoNameParameter): Unit = {
    requireAll(value, name)(requireNotBlank)
  }
}
