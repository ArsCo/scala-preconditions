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

package ars.precondition.require.bound

import ars.precondition.require.RequireInternal

/** Number to bounded number converters.
  *
  * @param value the value (must be non-null)
  *
  * @tparam T the value type
  *
  * @author Arsen Ibragimov (ars)
  * @since 0.0.4
  */
class RichNumberBound[T: Numeric](val value: T) { // TODO Non optimal Can't use extends AnyVal for all numeric types

  RequireInternal.requireNonNull(value, "value")

  /** @return inclusive bound with the same value. */
  def inclusive : BoundedNumber[T] = Inclusive(value)

  /** @return exclusive bound with the same value. */
  def exclusive : BoundedNumber[T] = Exclusive(value)

  /** Same as call [[exclusive]]. It was added as syntax sugar only. */
  def < : BoundedNumber[T] = exclusive

  /** Same as call [[inclusive]]. It was added as syntax sugar only. */
  def <= : BoundedNumber[T] = inclusive
}
