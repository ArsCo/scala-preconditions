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
import ars.precondition.Predicates.{isSize, isSizeFrom, isSizeInRange, isSizeUntil}

import scala.collection.Iterable

/** `requireXXX` methods to test [[Iterable]] size.
  *
  * @author Arsen Ibragimov (ars)
  * @since 0.0.4
  */
trait RequireSize extends RequireCore {
  /**
    * Tests that size of iterable `value` equals `size`, and otherwise throws [[RequireCore.exception()]].
    *
    * @param value the iterable value (must be non-null)
    * @param size the expected size (must be positive)
    * @param name the name to include in the failure message (must be non-null)
    *
    * @tparam T the type of iterable elements
    *
    * @throws RuntimeException if test fails
    */
  @inline def requireSize[T](value: Iterable[T], size: Int, name: String = NoNameParameter): Unit = {
    RequireInternal.requireSizeFrom(size)
    require(isSize(value, size), sizeMustBeEqual(name, value, size))
  }

  /**
    * Tests that size of iterable `value` is from `from` inclusive until [[Int.MaxValue]] inclusive,
    * and otherwise throws [[RequireCore.exception()]].
    *
    * @param value the iterable value (must be non-null)
    * @param from the minimum size value (inclusive)
    * @param name the name to include in the failure message (must be non-null)
    *
    * @tparam T the type of iterable elements
    *
    * @throws RuntimeException if test fails
    */
  @inline def requireSizeFrom[T](value: Iterable[T], from: Int, name: String = NoNameParameter): Unit = {
    RequireInternal.requireSizeFrom(from)
    require(isSizeFrom(value, from), sizeMustBeGtEq(name, value, from))
  }

  /**
    * Tests that size of iterable `value` is from `0` inclusive until `until` exclusive,
    * and otherwise throws [[RequireCore.exception()]].
    *
    * @param value the iterable value (must be non-null)
    * @param until the maximum size value (exclusive)
    * @param name the name to include in the failure message (must be non-null)
    *
    * @tparam T the type of iterable elements
    *
    * @throws RuntimeException if test fails
    */
  @inline def requireSizeUntil[T](value: Iterable[T], until: Int, name: String = NoNameParameter): Unit = {
    RequireInternal.requireSizeUntil(until)
    require(isSizeUntil(value, until), sizeMustBeLt(name, value, until))
  }

  /**
    * Tests that size of iterable `value` is from `from` inclusive until `until` exclusive,
    * and otherwise throws [[RequireCore.exception()]].
    *
    * @param value the iterable value  (must be non-null)
    * @param from the minimum size value (inclusive)
    * @param until the maximum size value (exclusive)
    * @param name the name to include in the failure message (must be non-null)
    *
    * @tparam T the type of iterable elements
    *
    * @throws RuntimeException if test fails
    */
  @inline def requireSizeInRange[T](value: Iterable[T], from: Int, until: Int, name: String = NoNameParameter): Unit = {
    RequireInternal.requireSizeFrom(from)
    RequireInternal.requireRange(from, until)
    require(isSizeInRange(value, from, until), sizeMustBeInRange(name, value, from, until))
  }
}
