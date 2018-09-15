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

import scala.math.Numeric

/** `requireXXX` methods to test specific numeric ranges.
  *
  * @author Arsen Ibragimov (ars)
  * @since 0.0.1
  */
trait RequireNumericSpecific extends RequireNumericRange {

  val MinPortValue = 0
  val MaxPortValue = 0xffff

  /**
    * Test that `value` is correct port value, and otherwise throws [[RequireCore.exception()]].
    *
    * @param value the value (must be non-null)
    * @param name the name to include in the failure message (must be non-null)
    *
    * @tparam T the type of value
    *
    * @throws RuntimeException if test fails
    */
  def requirePort[@specialized(Int, Long) T: Numeric](value: T, name: String = NoNameParameter): Unit = {
    val imp = implicitly[Numeric[T]]
    val min = imp.fromInt(MinPortValue)
    val max = imp.fromInt(MaxPortValue)
    RequireInternal.requireNonNull(value, "value")
    RequireInternal.requireNonNull(name, "name")
    requireNumberInRange(value, min.inclusive, max.inclusive)
  }
}
