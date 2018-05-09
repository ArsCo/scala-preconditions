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

/** Package object for `ars.precondition.require`
  *
  * @author Arsen Ibragimov (ars)
  * @since 0.0.1
  */
package object require {

  /**
    * Predicate (function from type `T` to [[Boolean]])
    *
    * @tparam T the type
    */
  type RequirePredicate[T] = T => Boolean

  /**
    * Takes the value and the name as parameters and invokes
    * [[ars.precondition.require.RequireCore#fail]] if requirements isn't satisfied.
    *
    * @tparam T the type of element
    */
  type RequireFunction[T] = (T, String) => Unit

  /**
    * Takes the element value, the name and the index as parameters and invokes
    * [[RequireCore#fail]] if requirements isn't satisfied.
    *
    * @tparam T the type of element
    */
  type RequireElementFunction[T] = (T, String, Int) => Unit
}
