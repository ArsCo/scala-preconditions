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
import org.scalatest.BeforeAndAfterEach

/** Tests for [[RequireAny]].
  *
  * @author Arsen Ibragimov (ars)
  * @since 0.0.4
  */
class RequireAnyTest extends AbstractBaseTest with BeforeAndAfterEach {

  var requireAny: RequireAny = _

  val fieldName = "fieldName"

  override protected def beforeEach(): Unit = {
    requireAny = new RequireAny {}
  }

  "RequireAny" must "be instance of RequireCore" in {
    assert(requireAny.isInstanceOf[RequireCore])
  }

  "requireNotNull" must "tests that value is not null and throw IAE otherwise" in {
    val rs = requireAny
    import rs._

    intercept[IllegalArgumentException] {
      requireNotNull(null, fieldName)
    }

    intercept[IllegalArgumentException] {
      requireNotNull(null)
    }
  }

  it must "do nothing otherwise" in {
    val rs = requireAny
    import rs._

    requireNotNull(10)
    requireNotNull(10, fieldName)

    requireNotNull("STR")
    requireNotNull("STR", fieldName)
  }

}
