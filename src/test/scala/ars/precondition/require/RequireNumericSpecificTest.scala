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

/** Tests for [[RequireNumericSpecific]].
  *
  * @author Arsen Ibragimov (ars)
  * @since 0.0.4
  */
class RequireNumericSpecificTest extends AbstractBaseTest with BeforeAndAfterEach {

  var requireNumericSpecific: RequireNumericSpecific = _

  val fieldName = "fieldName"

  override protected def beforeEach(): Unit = {
    requireNumericSpecific = new RequireNumericSpecific {}
  }

  "RequireNumericSpecific" must "be instance of RequireCore" in {
    assert(requireNumericSpecific.isInstanceOf[RequireNumericRange])
  }


  "requirePort()" must "throws IAE if `value` not in range [0, 0xffff]" in {
    val rn = requireNumericSpecific
    import rn._

    intercept[IllegalArgumentException] {
      requirePort(-100, fieldName)
    }

    intercept[IllegalArgumentException] {
      requirePort(-1)
    }

    intercept[IllegalArgumentException] {
      requirePort(-100L, fieldName)
    }

    intercept[IllegalArgumentException] {
      requirePort(-1L)
    }

    intercept[IllegalArgumentException] {
      requirePort(0xffff + 1)
    }

    intercept[IllegalArgumentException] {
      requirePort(0xffff + 100)
    }

    intercept[IllegalArgumentException] {
      requirePort(0xffffL + 1L)
    }

    intercept[IllegalArgumentException] {
      requirePort(0xffffL + 100L)
    }
  }

  it must "do nothing otherwise" in {
    val rn = requireNumericSpecific
    import rn._

    requirePort(0)
    requirePort(0, fieldName)

    requirePort(0L)
    requirePort(0L, fieldName)

    requirePort(20)
    requirePort(20, fieldName)

    requirePort(1500)
    requirePort(1500, fieldName)

    requirePort(1500L)
    requirePort(1500L, fieldName)

    requirePort(0xffff)
    requirePort(0xffff, fieldName)

    requirePort(0xffffL)
    requirePort(0xffffL, fieldName)
  }
}
