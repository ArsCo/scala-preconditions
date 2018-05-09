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

/** `requireXXX` methods for optional test.
  *
  * @author Arsen Ibragimov (ars)
  * @since 0.0.4
  */
trait RequireOptional extends RequireCore {

  /**
    * Invokes `require` function if `value` is Some(_) and do nothing otherwise.
    *
    * @param value the value (must be non-null)
    * @param require the function (must be non-null)
    *
    * @tparam T the type of value
    */
  @inline def optional[T](value: Option[T], name: String = NoNameParameter)
                         (require: RequireFunction[T]): Unit = {
    RequireInternal.requireNonNull(value, "value")
    value.foreach(require(_, name))
  }

  @inline def optionalPredicate[T](value: Option[T], name: String = NoNameParameter)
                                  (predicate: RequirePredicate[T]): Unit = {
    optional(value, name)(predicate)
  }
}
