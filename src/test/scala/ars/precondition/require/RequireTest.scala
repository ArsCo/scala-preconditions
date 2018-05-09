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

import ars.precondition.AbstractBaseTest

/** Tests for [[Require]].
  *
  * @author Arsen Ibragimov (ars)
  * @since 0.0.4
  */
class RequireTest extends AbstractBaseTest {
  "Require" must "have default instance extending all Require* traits" in {
    val d = Require.Default

    assert(d.isInstanceOf[RequireCore])

    assert(d.isInstanceOf[RequireAll])
    assert(d.isInstanceOf[RequireAllSpecific])

    assert(d.isInstanceOf[RequireAny])
    assert(d.isInstanceOf[RequireIterable])


    assert(d.isInstanceOf[RequireNumeric])
    assert(d.isInstanceOf[RequireNumericRange])

    assert(d.isInstanceOf[RequireOptional])
    assert(d.isInstanceOf[RequireSize])

    assert(d.isInstanceOf[RequireString])
    assert(d.isInstanceOf[RequireStringFormat])
    assert(d.isInstanceOf[RequireStringNumeric])
  }

  "README example" must "be correct" in {
    import ars.precondition.require.Require.Default._  // requireXXX methods
    import ars.precondition.implicits._                // implicit conversions

    case class MyValue()

    def myCustomCheck(value: MyValue): Boolean = true

    def foo(name: String, age: Int, email: Option[String], code: String, custom: MyValue) = {
      requireNotBlank(name, "name")
      requireNonNegative(age, "age")
      optional(email, "email")(requireEmail)
      requirePattern(code, "[A-Z]{4,7}".r, "code")
      require(myCustomCheck(custom), "The argument `custom` is not valid.")
      // . . .
    }
  }
}

