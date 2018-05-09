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

import ars.precondition.MessageBuilder.{NoNameParameter, mustBeCorrectEmail, mustBeCorrectUuid}
import ars.precondition.Predicates.{isCorrectEmail, isCorrectUuid}

/** `requireXXX` methods for formatted string values.
  *
  * @author Arsen Ibragimov (ars)
  * @since 0.0.4
  */
trait RequireStringFormat extends RequireCore {

  /**
    * Tests that string `email` is a correct email address,
    * and otherwise throws [[RequireCore.exception()]].
    *
    * @param email the testing email string (must be non-null)
    * @param name  the name to include in the failure message (must be non-null)
    *
    * @throws RuntimeException if test fails
    */
  @inline def requireEmail(email: String, name: String = NoNameParameter): Unit = {
    require(isCorrectEmail(email), mustBeCorrectEmail(name, email))
  }

  /**
    * Tests that string `uuid` is a correct universally unique identifier (UUID),
    * and otherwise throws [[RequireCore.exception()]].
    *
    * @param uuid the testing uuid string (must be non-null)
    * @param name the name to include in the failure message (must be non-null)
    *
    * @throws RuntimeException if test fails
    */
  @inline def requireUuid(uuid: String, name: String = NoNameParameter): Unit = {
    require(isCorrectUuid(uuid),  mustBeCorrectUuid(name, uuid))
  }
}
