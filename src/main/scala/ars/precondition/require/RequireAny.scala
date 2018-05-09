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

/** `requireXXX` methods for [[Any]] values.
  *
  * @author Arsen Ibragimov (ars)
  * @since 0.0.1
  */
trait RequireAny extends RequireCore {

  /**
    * Tests that `value` isn't `null`, and otherwise throws [[RequireCore.exception()]].
    *
    * @param value the value to test
    * @param name the name to include in the failure message (must be non-null)
    */
  def requireNotNull(value: Any, name: String = NoNameParameter): Unit = {
    require(isNotNull(value), notNull(name))
  }
}























