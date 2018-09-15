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

/** Default implementation containing all `requireXXX` methods.
  *
  * @author Arsen Ibragimov (ars)
  * @since 0.0.4
  */
class Require extends RequireCore
  with RequireAll with RequireAllSpecific
  with RequireAny
  with RequireIterable with RequireUnique
  with RequireNumeric with RequireNumericRange with RequireNumericSpecific
  with RequireOptional with RequireSize
  with RequireString with RequireStringFormat with RequireStringNumeric

object Require {

  /**
    * Default instance, containing all `requireXXX` methods.
    *
    * To use import its methods:
    * {{{
    *   import ars.precondition.require.Require.Default._
    * }}}
    */
  final val Default = new Require
}
