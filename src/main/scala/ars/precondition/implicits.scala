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

package ars.precondition

import java.util.regex.Pattern

import ars.precondition.require.{RequireAll, RequireAny, RequireElementFunction, RequireFunction}
import ars.precondition.require.bound.RichNumberBound

import scala.math.Numeric
import scala.util.matching.Regex


/** Package for implicit conversions.
  *
  * @author Arsen Ibragimov (ars)
  * @since 0.0.4
  */
object implicits {

  @inline implicit def num2richBound[T : Numeric](value: T): RichNumberBound[T] = new RichNumberBound[T](value)

  /**
    * Converts Java [[Pattern]] to Scala [[Regex]].
    *
    * @param pattern the pattern (must be non-null)
    *
    * @return the new instance of [[Regex]]
    */
  @inline implicit def pattern2regexp(pattern: Pattern): Regex = pattern.pattern().r

  /**
    * Converts function of type `(T, String) => Unit` into [[RequireFunction]].
    *
    * @param func the function (must be non-null)
    *
    * @tparam T the type of value
    *
    * @return the new instance of [[RequireFunction]]
    */
  @inline implicit def func2recFunc[T](func: (T, String) => Unit): RequireFunction[T] = func

  /**
    * Wraps [[RequireFunction]] (for example [[[RequireUtils.requireNotBlank(value:String*]]]
    * or [[RequireAny#requireNotNull]]) to [[RequireElementFunction]] to use with
    * [[RequireAll#requireAll]] method.
    *
    * Default implementation concatenates name and index in string `name(index)`.
    *
    * @param func the function to translate
    *
    * @tparam T the type of iterable elements
    *
    * @return the new instance of [[RequireElementFunction]]
    */
  @inline implicit class ImplicitRequireElementFunction[T](func: RequireFunction[T]) extends RequireElementFunction[T] {
    override def apply(v1: T, v2: String, v3: Int): Unit = func(v1, v2)
  }







}

