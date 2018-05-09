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
import scala.language.implicitConversions

/** `requireXXX` methods to test all elements of iterable.
  *
  * @author Arsen Ibragimov (ars)
  * @since 0.0.4
  */
trait RequireAll extends RequireCore {

  /**
    * Calls `require` for all elements of iterable `value`. The `require` must test the element and
    * throws [[RequireCore.exception()]] if it's not satisfy the requirements.
    *
    * @param value the iterable value (must be non-null)
    * @param name the name to include in the failure message (must be non-null)
    * @param require the test function (must be non-null)
    *
    * @tparam T the type of iterable elements
    *
    * @throws RuntimeException if test fails
    */
  def requireAll[T](value: Iterable[T], name: String = NoNameParameter)
                   (require: RequireElementFunction[T]): Unit = {
    RequireInternal.requireNonNull(value, "value")
    RequireInternal.requireNotBlank(name, "name")
    RequireInternal.requireNonNull(require, "require")

    value.zipWithIndex.foreach { case (element, index) => require(element, name, index) }
  }

  /**
    * Calls `predicate` for all elements of iterable `value`. It throws [[RequireCore.exception()]] if
    * there's an element that does not satisfy the requirements.
    *
    * @param value the iterable value (must be non-null)
    * @param name the name to include in the failure message (must be non-null)
    * @param predicate the predicate (must be non-null)
    *
    * @tparam T the type of iterable elements
    *
    * @throws RuntimeException if test fails
    */
  def requireAllPredicate[T](value: Iterable[T], name: String = NoNameParameter)
                            (predicate: RequirePredicate[T]): Unit = {
    requireAll(value, name)(predicate)
  }
}
