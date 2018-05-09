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

import ars.precondition.MessageBuilder.{NoNameParameter, negative, nonNegative, positive}

import scala.math.Numeric

/** `requireXXX` methods for [[Numeric]] values.
  *
  * @author Arsen Ibragimov (ars)
  * @since 0.0.4
  */
trait RequireNumeric extends RequireCore {

  /**
    * Tests that `value` is positive, and otherwise throws [[RequireCore.exception()]].
    *
    * @param value the value to test (must be non-null)
    * @param name the name to include in the failure message (must be non-null)
    *
    * @throws RuntimeException if test fails
    */
  def requirePositive[@specialized T: Numeric](value: T, name: String = NoNameParameter): Unit = {
    val n = implicitly[Numeric[T]]
    require(n.gt(value, n.zero), positive(name, value))
  }

  /**
    * Tests that `value` is negative, and otherwise throws [[RequireCore.exception()]].
    *
    * @param value the value to test (must be non-null)
    * @param name the name to include in the failure message (must be non-null)
    *
    * @throws RuntimeException if test fails
    */
  def requireNegative[@specialized T: Numeric](value: T, name: String = NoNameParameter): Unit = {
    val n = implicitly[Numeric[T]]
    require(n.lt(value, n.zero), negative(name, value))
  }

  /**
    * Tests that `value` is negative or zero, and otherwise throws [[RequireCore.exception()]].
    *
    * @param value the value to test (must be non-null)
    * @param name the name to include in the failure message (must be non-null)
    *
    * @throws RuntimeException if test fails
    */
  def requireNonPositive[@specialized T: Numeric](value: T, name: String = NoNameParameter): Unit = {
    val n = implicitly[Numeric[T]]
    require(n.lteq(value, n.zero), nonNegative(name, value))
  }

  /**
    * Tests that `value` is positive or zero, and otherwise throws [[RequireCore.exception()]].
    *
    * @param value the value to test (must be non-null)
    * @param name the name to include in the failure message (must be non-null)
    *
    * @throws RuntimeException if test fails
    */
  def requireNonNegative[@specialized T: Numeric](value: T, name: String = NoNameParameter): Unit = {
    val n = implicitly[Numeric[T]]
    require(n.gteq(value, n.zero), nonNegative(name, value))
  }
}
