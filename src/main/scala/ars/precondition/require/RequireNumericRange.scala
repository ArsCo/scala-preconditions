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

import ars.precondition.MessageBuilder._
import ars.precondition.Predicates._
import ars.precondition.require.bound.BoundedNumber

import scala.language.implicitConversions
import scala.math.Numeric


/** `requireXXX` methods to test numeric ranges.
  *
  * @author Arsen Ibragimov (ars)
  * @since 0.0.1
  */
trait RequireNumericRange extends RequireCore {

  /**
    * Test that `value` is equal to `number`, and otherwise throws [[RequireCore.exception()]].
    *
    * @param value the value (must be non-null)
    * @param number the required number (must be non-null)
    * @param name the name to include in the failure message (must be non-null)
    *
    * @tparam T the type of iterable elements
    *
    * @throws RuntimeException if test fails
    */
  @inline def requireNumber[@specialized T: Numeric](value: T, number: T, name: String = NoNameParameter): Unit = {
    RequireInternal.requireNonNull(value, "value")
    RequireInternal.requireNonNull(number, "number")
    RequireInternal.requireNonNull(name, "name")

    require(isEqualNumber(value, number), numberMustBeEqual(name, value, number))
  }


  /**
    * Tests that `value` is from `leftBound` until maximum value of type `T` (inclusive),
    * and otherwise throws [[RequireCore.exception()]].
    *
    * @param value the value (must be non-null)
    * @param leftBound the left bound (must be non-null)
    * @param name the name to include in the failure message (must be non-null)
    *
    * @tparam T the type of value
    *
    * @throws RuntimeException if test fails
    */
  @inline def requireNumberFrom[@specialized T: Numeric]
                               (value: T, leftBound: BoundedNumber[T], name: String = NoNameParameter): Unit = {
    RequireInternal.requireNonNull(value, "value")
    RequireInternal.requireNonNull(leftBound, "leftBound")
    RequireInternal.requireNonNull(name, "name")

    require(
      isNumberFrom(value, leftBound),
      numberMustBeGt(name, value, leftBound)
    )
  }

  /**
    * Tests that `value` is from minimum value of type `T` (inclusive) until `rightBound`,
    * and otherwise throws [[RequireCore.exception()]].
    *
    * @param value the value (must be non-null)
    * @param rightBound the right bound (must be non-null)
    * @param name the name to include in the failure message (must be non-null)
    *
    * @tparam T the type of value
    *
    * @throws RuntimeException if test fails
    */
  @inline def requireNumberUntil[@specialized T: Numeric]
                                (value: T, rightBound: BoundedNumber[T], name: String = NoNameParameter): Unit = {

    RequireInternal.requireNonNull(value, "value")
    RequireInternal.requireNonNull(rightBound, "rightBound")
    RequireInternal.requireNonNull(name, "name")

    require(
      isNumberUntil(value, rightBound),
      numberMustBeLt(name, value, rightBound)
    )
  }

  /**
    * Tests that `value` is from `leftBound` until `rightBound`, and otherwise throws [[RequireCore.exception()]].
    *
    * @param value the value (must be non-null)
    * @param leftBound the left bound (must be non-null)
    * @param rightBound the right bound (must be non-null)
    *
    * @tparam T the type of value
    *
    * @throws RuntimeException if test fails
    */
  @inline def requireNumberInRange[@specialized T: Numeric]
                                  (value: T, leftBound: BoundedNumber[T],
                                   rightBound: BoundedNumber[T],  name: String = NoNameParameter): Unit = {

    // Don't validate that range is not empty because this type of validation is different for integral and float types
    requireNumberFrom(value, leftBound, name)
    requireNumberUntil(value, rightBound, name)
  }
}
