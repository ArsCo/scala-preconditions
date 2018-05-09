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

import ars.precondition.implicits._

/** Tests for [[RequireNumericRange]].
  *
  * @author Arsen Ibragimov (ars)
  * @since 0.0.4
  */
class RequireNumericRangeTest extends AbstractBaseTest with BeforeAndAfterEach {

  var requireNumericRange: RequireNumericRange = _

  val fieldName = "fieldName"

  override protected def beforeEach(): Unit = {
    requireNumericRange = new RequireNumericRange {}
  }

  "RequireNumericRange" must "be instance of RequireCore" in {
    assert(requireNumericRange.isInstanceOf[RequireCore])
  }

  "requireNumber()" must "throws IAE if `value` isn't equals to expected" in {
    val rn = requireNumericRange
    import rn._

    intercept[IllegalArgumentException] {
      requireNumber(5, 6)
    }

    intercept[IllegalArgumentException] {
      requireNumber(5, 6, fieldName)
    }
  }

  it must "do nothing otherwise" in {
    val rn = requireNumericRange
    import rn._

    requireNumber(5, 5)
    requireNumber(5, 5, fieldName)
  }

  it must "validate args" in {
    val rn = requireNumericRange
    import rn._

    intercept[RuntimeException] {
      requireNumber(null, BigDecimal(10))
    }

    intercept[RuntimeException] {
      requireNumber(null, BigDecimal(10), fieldName)
    }

    intercept[RuntimeException] {
      requireNumber(BigDecimal(10), null)
    }

    intercept[RuntimeException] {
      requireNumber(BigDecimal(10), null, fieldName)
    }

    intercept[RuntimeException] {
      requireNumber(5, 5, null)
    }
  }

  "requireNumberFrom()" must "throws IAE if `value` less than expected (exclusive)" in {
    val rn = requireNumericRange
    import rn._

    intercept[IllegalArgumentException] {
      requireNumberFrom(-100, 5.exclusive)
    }

    intercept[IllegalArgumentException] {
      requireNumberFrom(0, 5.exclusive)
    }

    intercept[IllegalArgumentException] {
      requireNumberFrom(4, 5.exclusive)
    }

    intercept[IllegalArgumentException] {
      requireNumberFrom(5, 5.exclusive)
    }
  }

  it must "throws IAE if `value` less than expected (inclusive)" in {
    val rn = requireNumericRange
    import rn._

    intercept[IllegalArgumentException] {
      requireNumberFrom(-100, 5.inclusive)
    }

    intercept[IllegalArgumentException] {
      requireNumberFrom(0, 5.inclusive)
    }

    intercept[IllegalArgumentException] {
      requireNumberFrom(4, 5.inclusive)
    }
  }

  it must "do nothing otherwise (exclusive)" in {
    val rn = requireNumericRange
    import rn._

    requireNumberFrom(6, 5.exclusive)
    requireNumberFrom(10, 5.exclusive)
    requireNumberFrom(10000, 5.exclusive)

    requireNumberFrom(6, 5.exclusive, fieldName)
    requireNumberFrom(10, 5.exclusive, fieldName)
    requireNumberFrom(10000, 5.exclusive, fieldName)
  }

  it must "do nothing otherwise (inclusive)" in {
    val rn = requireNumericRange
    import rn._

    requireNumberFrom(5, 5.inclusive)
    requireNumberFrom(6, 5.inclusive)
    requireNumberFrom(10000, 5.inclusive)

    requireNumberFrom(5, 5.inclusive, fieldName)
    requireNumberFrom(6, 5.inclusive, fieldName)
    requireNumberFrom(10000, 5.inclusive, fieldName)
  }

  it must "validate args" in {
    val rn = requireNumericRange
    import rn._

    intercept[RuntimeException] {
      requireNumberFrom(null, BigDecimal(10).inclusive)
    }

    intercept[RuntimeException] {
      requireNumberFrom(null, BigDecimal(10).inclusive, fieldName)
    }

    intercept[RuntimeException] {
      requireNumberFrom(BigDecimal(10), null)
    }

    intercept[RuntimeException] {
      requireNumberFrom(BigDecimal(10), null, fieldName)
    }

    intercept[RuntimeException] {
      requireNumberFrom(BigDecimal(10), BigDecimal(10).inclusive, null)
    }
  }

  "requireNumberUntil()" must "throws IAE if `value` less than expected (exclusive)" in {
    val rn = requireNumericRange
    import rn._

    intercept[IllegalArgumentException] {
      requireNumberUntil(50, 50.exclusive)
    }

    intercept[IllegalArgumentException] {
      requireNumberUntil(100, 50.exclusive)
    }

    intercept[IllegalArgumentException] {
      requireNumberUntil(1000, 50.exclusive)
    }

    intercept[IllegalArgumentException] {
      requireNumberUntil(100000, 50.exclusive)
    }
  }

  "requireNumberUntil()" must "throws IAE if `value` less than expected (inclusive)" in {
    val rn = requireNumericRange
    import rn._

    intercept[IllegalArgumentException] {
      requireNumberUntil(51, 50.inclusive)
    }

    intercept[IllegalArgumentException] {
      requireNumberUntil(100, 50.inclusive)
    }

    intercept[IllegalArgumentException] {
      requireNumberUntil(1000, 50.inclusive)
    }

    intercept[IllegalArgumentException] {
      requireNumberUntil(100000, 50.inclusive)
    }
  }

  it must "do nothing otherwise (exclusive)" in {
    val rn = requireNumericRange
    import rn._

    requireNumberUntil(4, 5.exclusive)
    requireNumberUntil(0, 5.exclusive)
    requireNumberUntil(-100, 5.exclusive)

    requireNumberUntil(4, 5.exclusive, fieldName)
    requireNumberUntil(0, 5.exclusive, fieldName)
    requireNumberUntil(-100, 5.exclusive, fieldName)
  }

  it must "do nothing otherwise (inclusive)" in {
    val rn = requireNumericRange
    import rn._

    requireNumberUntil(5, 5.inclusive)
    requireNumberUntil(4, 5.inclusive)
    requireNumberUntil(0, 5.inclusive)
    requireNumberUntil(-100, 5.inclusive)

    requireNumberUntil(5, 5.inclusive, fieldName)
    requireNumberUntil(4, 5.inclusive, fieldName)
    requireNumberUntil(0, 5.inclusive, fieldName)
    requireNumberUntil(-100, 5.inclusive, fieldName)
  }

  it must "validate args" in {
    val rn = requireNumericRange
    import rn._

    intercept[RuntimeException] {
      requireNumberUntil(null, BigDecimal(10).inclusive)
    }

    intercept[RuntimeException] {
      requireNumberUntil(null, BigDecimal(10).inclusive, fieldName)
    }

    intercept[RuntimeException] {
      requireNumberUntil(BigDecimal(10), null)
    }

    intercept[RuntimeException] {
      requireNumberUntil(BigDecimal(10), null, fieldName)
    }

    intercept[RuntimeException] {
      requireNumberUntil(BigDecimal(10), BigDecimal(10).inclusive, null)
    }
  }

  "requireNumberInRange" must "throws IAE if `value` in not in range (inclusive, inclusive)" in {
    val rn = requireNumericRange
    import rn._

    intercept[IllegalArgumentException] {
      requireNumberInRange(-20, 9.inclusive, 50.inclusive)
    }

    intercept[IllegalArgumentException] {
      requireNumberInRange(0, 9.inclusive, 50.inclusive)
    }

    intercept[IllegalArgumentException] {
      requireNumberInRange(8, 9.inclusive, 50.inclusive)
    }

    intercept[IllegalArgumentException] {
      requireNumberInRange(51, 9.inclusive, 50.inclusive)
    }

    intercept[IllegalArgumentException] {
      requireNumberInRange(500, 9.inclusive, 50.inclusive)
    }

    intercept[IllegalArgumentException] {
      requireNumberInRange(-20, 9.inclusive, 50.inclusive, fieldName)
    }

    intercept[IllegalArgumentException] {
      requireNumberInRange(0, 9.inclusive, 50.inclusive, fieldName)
    }

    intercept[IllegalArgumentException] {
      requireNumberInRange(8, 9.inclusive, 50.inclusive, fieldName)
    }

    intercept[IllegalArgumentException] {
      requireNumberInRange(51, 9.inclusive, 50.inclusive, fieldName)
    }

    intercept[IllegalArgumentException] {
      requireNumberInRange(500, 9.inclusive, 50.inclusive, fieldName)
    }
  }

  it must "throws IAE if `value` in not in range (inclusive, exclusive)" in {
    val rn = requireNumericRange
    import rn._

    intercept[IllegalArgumentException] {
      requireNumberInRange(-20, 9.inclusive, 50.exclusive)
    }

    intercept[IllegalArgumentException] {
      requireNumberInRange(0, 9.inclusive, 50.exclusive)
    }

    intercept[IllegalArgumentException] {
      requireNumberInRange(8, 9.inclusive, 50.exclusive)
    }

    intercept[IllegalArgumentException] {
      requireNumberInRange(50, 9.inclusive, 50.exclusive)
    }

    intercept[IllegalArgumentException] {
      requireNumberInRange(51, 9.inclusive, 50.exclusive)
    }

    intercept[IllegalArgumentException] {
      requireNumberInRange(500, 9.inclusive, 50.inclusive)
    }

    intercept[IllegalArgumentException] {
      requireNumberInRange(-20, 9.inclusive, 50.exclusive, fieldName)
    }

    intercept[IllegalArgumentException] {
      requireNumberInRange(0, 9.inclusive, 50.exclusive, fieldName)
    }

    intercept[IllegalArgumentException] {
      requireNumberInRange(8, 9.inclusive, 50.exclusive, fieldName)
    }

    intercept[IllegalArgumentException] {
      requireNumberInRange(50, 9.inclusive, 50.exclusive, fieldName)
    }

    intercept[IllegalArgumentException] {
      requireNumberInRange(51, 9.inclusive, 50.exclusive, fieldName)
    }

    intercept[IllegalArgumentException] {
      requireNumberInRange(500, 9.inclusive, 50.inclusive, fieldName)
    }
  }

  it must "throws IAE if `value` in not in range (exclusive, inclusive)" in {
    val rn = requireNumericRange
    import rn._

    intercept[IllegalArgumentException] {
      requireNumberInRange(-20, 9.exclusive, 50.inclusive)
    }

    intercept[IllegalArgumentException] {
      requireNumberInRange(0, 9.exclusive, 50.inclusive)
    }

    intercept[IllegalArgumentException] {
      requireNumberInRange(8, 9.exclusive, 50.inclusive)
    }

    intercept[IllegalArgumentException] {
      requireNumberInRange(9, 9.exclusive, 50.inclusive)
    }

    intercept[IllegalArgumentException] {
      requireNumberInRange(51, 9.exclusive, 50.inclusive)
    }

    intercept[IllegalArgumentException] {
      requireNumberInRange(500, 9.exclusive, 50.inclusive)
    }

    intercept[IllegalArgumentException] {
      requireNumberInRange(-20, 9.exclusive, 50.inclusive, fieldName)
    }

    intercept[IllegalArgumentException] {
      requireNumberInRange(0, 9.exclusive, 50.inclusive, fieldName)
    }

    intercept[IllegalArgumentException] {
      requireNumberInRange(8, 9.exclusive, 50.inclusive, fieldName)
    }

    intercept[IllegalArgumentException] {
      requireNumberInRange(9, 9.exclusive, 50.inclusive, fieldName)
    }

    intercept[IllegalArgumentException] {
      requireNumberInRange(51, 9.exclusive, 50.inclusive, fieldName)
    }

    intercept[IllegalArgumentException] {
      requireNumberInRange(500, 9.exclusive, 50.inclusive, fieldName)
    }
  }

  it must "throws IAE if `value` in not in range (exclusive, exclusive)" in {
    val rn = requireNumericRange
    import rn._

    intercept[IllegalArgumentException] {
      requireNumberInRange(-20, 9.exclusive, 50.exclusive)
    }

    intercept[IllegalArgumentException] {
      requireNumberInRange(0, 9.exclusive, 50.exclusive)
    }

    intercept[IllegalArgumentException] {
      requireNumberInRange(8, 9.exclusive, 50.exclusive)
    }

    intercept[IllegalArgumentException] {
      requireNumberInRange(9, 9.exclusive, 50.exclusive)
    }

    intercept[IllegalArgumentException] {
      requireNumberInRange(50, 9.exclusive, 50.exclusive)
    }

    intercept[IllegalArgumentException] {
      requireNumberInRange(51, 9.exclusive, 50.exclusive)
    }

    intercept[IllegalArgumentException] {
      requireNumberInRange(500, 9.exclusive, 50.exclusive)
    }

    intercept[IllegalArgumentException] {
      requireNumberInRange(-20, 9.exclusive, 50.exclusive, fieldName)
    }

    intercept[IllegalArgumentException] {
      requireNumberInRange(0, 9.exclusive, 50.exclusive, fieldName)
    }

    intercept[IllegalArgumentException] {
      requireNumberInRange(8, 9.exclusive, 50.exclusive, fieldName)
    }

    intercept[IllegalArgumentException] {
      requireNumberInRange(9, 9.exclusive, 50.exclusive, fieldName)
    }

    intercept[IllegalArgumentException] {
      requireNumberInRange(50, 9.exclusive, 50.exclusive, fieldName)
    }

    intercept[IllegalArgumentException] {
      requireNumberInRange(51, 9.exclusive, 50.exclusive, fieldName)
    }

    intercept[IllegalArgumentException] {
      requireNumberInRange(500, 9.exclusive, 50.exclusive, fieldName)
    }
  }

  it must "do nothing otherwise (inclusive, inclusive)" in {
    val rn = requireNumericRange
    import rn._

    requireNumberInRange(9, 9.inclusive, 50.inclusive)
    requireNumberInRange(10, 9.inclusive, 50.inclusive)
    requireNumberInRange(25, 9.inclusive, 50.inclusive)
    requireNumberInRange(49, 9.inclusive, 50.inclusive)
    requireNumberInRange(50, 9.inclusive, 50.inclusive)

    requireNumberInRange(9, 9.inclusive, 50.inclusive, fieldName)
    requireNumberInRange(10, 9.inclusive, 50.inclusive, fieldName)
    requireNumberInRange(25, 9.inclusive, 50.inclusive, fieldName)
    requireNumberInRange(49, 9.inclusive, 50.inclusive, fieldName)
    requireNumberInRange(50, 9.inclusive, 50.inclusive, fieldName)
  }

  it must "do nothing otherwise (inclusive, exclusive)" in {
    val rn = requireNumericRange
    import rn._

    requireNumberInRange(9, 9.inclusive, 50.exclusive)
    requireNumberInRange(10, 9.inclusive, 50.exclusive)
    requireNumberInRange(25, 9.inclusive, 50.exclusive)
    requireNumberInRange(49, 9.inclusive, 50.exclusive)

    requireNumberInRange(9, 9.inclusive, 50.exclusive, fieldName)
    requireNumberInRange(10, 9.inclusive, 50.exclusive, fieldName)
    requireNumberInRange(25, 9.inclusive, 50.exclusive, fieldName)
    requireNumberInRange(49, 9.inclusive, 50.exclusive, fieldName)
  }

  it must "do nothing otherwise (exclusive, inclusive)" in {
    val rn = requireNumericRange
    import rn._

    requireNumberInRange(10, 9.exclusive, 50.inclusive)
    requireNumberInRange(25, 9.exclusive, 50.inclusive)
    requireNumberInRange(49, 9.exclusive, 50.inclusive)
    requireNumberInRange(50, 9.exclusive, 50.inclusive)

    requireNumberInRange(10, 9.exclusive, 50.inclusive, fieldName)
    requireNumberInRange(25, 9.exclusive, 50.inclusive, fieldName)
    requireNumberInRange(49, 9.exclusive, 50.inclusive, fieldName)
    requireNumberInRange(50, 9.exclusive, 50.inclusive, fieldName)
  }

  it must "do nothing otherwise (exclusive, exclusive)" in {
    val rn = requireNumericRange
    import rn._

    requireNumberInRange(10, 9.exclusive, 50.exclusive)
    requireNumberInRange(25, 9.exclusive, 50.exclusive)
    requireNumberInRange(49, 9.exclusive, 50.exclusive)

    requireNumberInRange(10, 9.exclusive, 50.exclusive, fieldName)
    requireNumberInRange(25, 9.exclusive, 50.exclusive, fieldName)
    requireNumberInRange(49, 9.exclusive, 50.exclusive, fieldName)
  }

  it must "validate args" in {
    val rn = requireNumericRange
    import rn._

    intercept[RuntimeException] {
      requireNumberInRange(null, BigDecimal(10).inclusive, BigDecimal(50).inclusive)
    }

    intercept[RuntimeException] {
      requireNumberInRange(null, BigDecimal(10).inclusive, BigDecimal(50).inclusive, fieldName)
    }

    intercept[RuntimeException] {
      requireNumberInRange(BigDecimal(10), null, BigDecimal(50).inclusive)
    }

    intercept[RuntimeException] {
      requireNumberInRange(BigDecimal(10), null, BigDecimal(50).inclusive, fieldName)
    }

    intercept[RuntimeException] {
      requireNumberInRange(BigDecimal(10), BigDecimal(10).inclusive, null)
    }

    intercept[RuntimeException] {
      requireNumberInRange(BigDecimal(10), BigDecimal(10).inclusive, null, fieldName)
    }

    intercept[RuntimeException] {
      requireNumberInRange(BigDecimal(10), BigDecimal(10).inclusive, BigDecimal(50).inclusive, null)
    }
  }
}