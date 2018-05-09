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

import ars.precondition.MessageBuilder.{NoNameParameter, notBlank, regExpr}
import ars.precondition.Predicates.{isMatchPattern, isNotBlank}

import scala.language.implicitConversions
import scala.util.matching.Regex

/** `requireXXX` methods for [[String]] values.
  *
  * @author Arsen Ibragimov (ars)
  * @since 0.0.4
  */
trait RequireString extends RequireCore {

  /**
    * Tests that `value` isn't `null` or empty (after `trim`), and otherwise throws [[RequireCore.exception()]].
    *
    * @param value the value to test (must be non-null)
    * @param name the name to include in the failure message (must be non-null)
    *
    * @throws RuntimeException if test fails
    */
  def requireNotBlank(value: String, name: String = NoNameParameter): Unit = {
    require(isNotBlank(value), notBlank(name, value))
  }

  /**
    * Tests that `value` matches regular expression pattern, and otherwise throws [[RequireCore.exception()]].
    *
    * @param value the value to test (must be non-null)
    * @param pattern the pattern to test (must be non-null)
    * @param name the name to include in the failure message (must be non-null)
    *
    * @throws RuntimeException if test fails
    */
  def requirePattern(value: String, pattern: Regex, name: String = NoNameParameter): Unit = {
    require(isMatchPattern(value, pattern), regExpr(name, value))
  }
}
