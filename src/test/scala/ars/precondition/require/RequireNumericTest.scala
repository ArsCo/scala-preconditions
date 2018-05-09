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

/** Tests for [[RequireNumeric]].
  *
  * @author Arsen Ibragimov (ars)
  * @since 0.0.4
  */
class RequireNumericTest extends AbstractBaseTest with BeforeAndAfterEach {

  var requireNumeric: RequireNumeric = _

  val fieldName = "fieldName"

  override protected def beforeEach(): Unit = {
    requireNumeric = new RequireNumeric {}
  }

  "RequireNumeric" must "be instance of RequireCore" in {
    assert(requireNumeric.isInstanceOf[RequireCore])
  }
  
  "requirePositive(Int)" must "tests that value is positive" in {
    val rn = requireNumeric
    import rn._

    val value: Int = 5
    requirePositive(value, fieldName)
    requirePositive(value)

    intercept[IllegalArgumentException] {
      requirePositive(0, fieldName)
    }

    intercept[IllegalArgumentException] {
      requirePositive(-1, fieldName)
    }

    intercept[IllegalArgumentException] {
      requirePositive(0)
    }

    intercept[IllegalArgumentException] {
      requirePositive(-1)
    }
  }

  "requirePositive(Long)" must "tests that value is positive" in {
    val rn = requireNumeric
    import rn._

    val value: Long = 5L
    requirePositive(value, fieldName)
    requirePositive(value)

    intercept[IllegalArgumentException] {
      requirePositive(0L, fieldName)
    }

    intercept[IllegalArgumentException] {
      requirePositive(-1L, fieldName)
    }

    intercept[IllegalArgumentException] {
      requirePositive(0L)
    }

    intercept[IllegalArgumentException] {
      requirePositive(-1L)
    }
  }

  "requirePositive(Float)" must "tests that value is positive" in {
    val rn = requireNumeric
    import rn._
    
    val value: Float = 5.0F
    requirePositive(value, fieldName)
    requirePositive(value)

    intercept[IllegalArgumentException] {
      requirePositive(0F, fieldName)
    }

    intercept[IllegalArgumentException] {
      requirePositive(-1F, fieldName)
    }

    intercept[IllegalArgumentException] {
      requirePositive(0F)
    }

    intercept[IllegalArgumentException] {
      requirePositive(-1F)
    }
  }

  "requirePositive(Double)" must "tests that value is positive" in {
    val rn = requireNumeric
    import rn._
    
    val value: Double = 5.0
    requirePositive(value, fieldName)
    requirePositive(value)

    intercept[IllegalArgumentException] {
      requirePositive(0.0, fieldName)
    }

    intercept[IllegalArgumentException] {
      requirePositive(-1.5, fieldName)
    }

    intercept[IllegalArgumentException] {
      requirePositive(0.0)
    }

    intercept[IllegalArgumentException] {
      requirePositive(-1.5)
    }
  }

  "requirePositive(BigInt)" must "tests that value is positive" in {
    val rn = requireNumeric
    import rn._
    
    val value: BigInt = 50000
    requirePositive(value, fieldName)
    requirePositive(value)

    intercept[IllegalArgumentException] {
      requirePositive(BigInt(0), fieldName)
    }

    intercept[IllegalArgumentException] {
      requirePositive(BigInt(-1), fieldName)
    }

    intercept[IllegalArgumentException] {
      requirePositive(BigInt(0))
    }

    intercept[IllegalArgumentException] {
      requirePositive(BigInt(-1))
    }
  }

  "requirePositive(BigDecimal)" must "tests that value is positive" in {
    val rn = requireNumeric
    import rn._
    
    val value: BigDecimal = 50000
    requirePositive(value, fieldName)
    requirePositive(value)

    intercept[IllegalArgumentException] {
      requirePositive(BigDecimal(0.0), fieldName)
    }

    intercept[IllegalArgumentException] {
      requirePositive(BigDecimal(-1.6), fieldName)
    }

    intercept[IllegalArgumentException] {
      requirePositive(BigDecimal(0.0))
    }

    intercept[IllegalArgumentException] {
      requirePositive(BigDecimal(-1.4))
    }
  }

  "requirePositive" must "validate args" in {
    val rn = requireNumeric
    import rn._

    requirePositive(5, null) // lazy evaluation

    intercept[RuntimeException] {
      requirePositive(-5, null)
    }
  }

  "requireNegative(Int)" must "tests that value is negative" in {
    val rn = requireNumeric
    import rn._

    requireNegative(-5, fieldName)
    requireNegative(-5)

    intercept[IllegalArgumentException] {
      requireNegative(0, fieldName)
    }

    intercept[IllegalArgumentException] {
      requireNegative(0)
    }

    intercept[IllegalArgumentException] {
      requireNegative(1, fieldName)
    }

    intercept[IllegalArgumentException] {
      requireNegative(1)
    }
  }

  "requireNegative(Long)" must "tests that value is negative" in {
    val rn = requireNumeric
    import rn._

    requireNegative(-5L, fieldName)
    requireNegative(-5L)

    intercept[IllegalArgumentException] {
      requireNegative(0L, fieldName)
    }

    intercept[IllegalArgumentException] {
      requireNegative(0L)
    }

    intercept[IllegalArgumentException] {
      requireNegative(1L, fieldName)
    }

    intercept[IllegalArgumentException] {
      requireNegative(1L)
    }
  }

  "requireNegative(Float)" must "tests that value is negative" in {
    val rn = requireNumeric
    import rn._

    requireNegative(-5.5F, fieldName)
    requireNegative(-5.3F)

    intercept[IllegalArgumentException] {
      requireNegative(0.0F, fieldName)
    }

    intercept[IllegalArgumentException] {
      requireNegative(0.0F)
    }

    intercept[IllegalArgumentException] {
      requireNegative(1.34F, fieldName)
    }

    intercept[IllegalArgumentException] {
      requireNegative(1.22F)
    }
  }

  "requireNegative(Double)" must "tests that value is negative" in {
    val rn = requireNumeric
    import rn._

    requireNegative(-5.5, fieldName)
    requireNegative(-5.3)

    intercept[IllegalArgumentException] {
      requireNegative(0.0, fieldName)
    }

    intercept[IllegalArgumentException] {
      requireNegative(0.0)
    }

    intercept[IllegalArgumentException] {
      requireNegative(1.34, fieldName)
    }

    intercept[IllegalArgumentException] {
      requireNegative(1.22)
    }
  }

  "requireNegative(BigInt)" must "tests that value is negative" in {
    val rn = requireNumeric
    import rn._

    requireNegative(BigInt(-5), fieldName)
    requireNegative(BigInt(-5))

    intercept[IllegalArgumentException] {
      requireNegative(BigInt(0), fieldName)
    }

    intercept[IllegalArgumentException] {
      requireNegative(BigInt(0))
    }

    intercept[IllegalArgumentException] {
      requireNegative(BigInt(1), fieldName)
    }

    intercept[IllegalArgumentException] {
      requireNegative(BigInt(1))
    }
  }

  "requireNegative(BigDecimal)" must "tests that value is negative" in {
    val rn = requireNumeric
    import rn._

    requireNegative(BigDecimal(-5.5), fieldName)
    requireNegative(BigDecimal(-5.3))

    intercept[IllegalArgumentException] {
      requireNegative(BigDecimal(0.0), fieldName)
    }

    intercept[IllegalArgumentException] {
      requireNegative(BigDecimal(0.0))
    }

    intercept[IllegalArgumentException] {
      requireNegative(BigDecimal(1.34), fieldName)
    }

    intercept[IllegalArgumentException] {
      requireNegative(BigDecimal(1.22))
    }
  }

  "requireNegative" must "validate args" in {
    val rn = requireNumeric
    import rn._

    requireNegative(-5, null) // lazy evaluation

    intercept[RuntimeException] {
      requireNegative(5, null)
    }
  }

  "requireNonPositive(Int)" must "tests that value is negative or zero" in {
    val rn = requireNumeric
    import rn._

    requireNonPositive(-5, fieldName)
    requireNonPositive(-5)
    requireNonPositive(0, fieldName)
    requireNonPositive(0)

    intercept[IllegalArgumentException] {
      requireNonPositive(1, fieldName)
    }

    intercept[IllegalArgumentException] {
      requireNonPositive(1)
    }
  }

  "requireNonPositive(Long)" must "tests that value is negative or zero" in {
    val rn = requireNumeric
    import rn._

    requireNonPositive(-5L, fieldName)
    requireNonPositive(-5L)
    requireNonPositive(0L, fieldName)
    requireNonPositive(0L)

    intercept[IllegalArgumentException] {
      requireNonPositive(1L, fieldName)
    }

    intercept[IllegalArgumentException] {
      requireNonPositive(1L)
    }
  }

  "requireNonPositive(Float)" must "tests that value is negative or zero" in {
    val rn = requireNumeric
    import rn._

    requireNonPositive(-5.5F, fieldName)
    requireNonPositive(-5.3F)
    requireNonPositive(0.0F, fieldName)
    requireNonPositive(0.0F)

    intercept[IllegalArgumentException] {
      requireNonPositive(1.34F, fieldName)
    }

    intercept[IllegalArgumentException] {
      requireNonPositive(1.22F)
    }
  }

  "requireNonPositive(Double)" must "tests that value is negative or zero" in {
    val rn = requireNumeric
    import rn._

    requireNonPositive(-5.5, fieldName)
    requireNonPositive(-5.3)
    requireNonPositive(0.0, fieldName)
    requireNonPositive(0.0)

    intercept[IllegalArgumentException] {
      requireNonPositive(1.34, fieldName)
    }

    intercept[IllegalArgumentException] {
      requireNonPositive(1.22)
    }
  }

  "requireNonPositive(BigInt)" must "tests that value is negative or zero" in {
    val rn = requireNumeric
    import rn._

    requireNonPositive(BigInt(-5), fieldName)
    requireNonPositive(BigInt(-5))
    requireNonPositive(BigInt(0), fieldName)
    requireNonPositive(BigInt(0))

    intercept[IllegalArgumentException] {
      requireNonPositive(BigInt(1), fieldName)
    }

    intercept[IllegalArgumentException] {
      requireNonPositive(BigInt(1))
    }
  }

  "requireNonPositive(BigDecimal)" must "tests that value is negative or zero" in {
    val rn = requireNumeric
    import rn._

    requireNonPositive(BigDecimal(-5.5), fieldName)
    requireNonPositive(BigDecimal(-5.3))
    requireNonPositive(BigDecimal(0.0), fieldName)
    requireNonPositive(BigDecimal(0.0))

    intercept[IllegalArgumentException] {
      requireNonPositive(BigDecimal(1.34), fieldName)
    }

    intercept[IllegalArgumentException] {
      requireNonPositive(BigDecimal(1.22))
    }
  }

  "requireNonPositive" must "validate args" in {
    val rn = requireNumeric
    import rn._

    requireNonPositive(-5, null) // lazy evaluation

    intercept[RuntimeException] {
      requireNonPositive(5, null)
    }
  }

  "requireNonNegative(Int)" must "tests that value is positive or zero" in {
    val rn = requireNumeric
    import rn._

    requireNonNegative(5, fieldName)
    requireNonNegative(5)
    requireNonNegative(0, fieldName)
    requireNonNegative(0)

    intercept[IllegalArgumentException] {
      requireNonNegative(-1, fieldName)
    }

    intercept[IllegalArgumentException] {
      requireNonNegative(-1)
    }
  }

  "requireNonNegative(Long)" must "tests that value is positive or zero" in {
    val rn = requireNumeric
    import rn._

    requireNonNegative(5L, fieldName)
    requireNonNegative(5L)
    requireNonNegative(0L, fieldName)
    requireNonNegative(0L)

    intercept[IllegalArgumentException] {
      requireNonNegative(-1L, fieldName)
    }

    intercept[IllegalArgumentException] {
      requireNonNegative(-1L)
    }
  }

  "requireNonNegative(Float)" must "tests that value is positive or zero" in {
    val rn = requireNumeric
    import rn._

    requireNonNegative(5.5F, fieldName)
    requireNonNegative(5.3F)
    requireNonNegative(0.0F, fieldName)
    requireNonNegative(0.0F)

    intercept[IllegalArgumentException] {
      requireNonNegative(-1.34F, fieldName)
    }

    intercept[IllegalArgumentException] {
      requireNonNegative(-1.22F)
    }
  }

  "requireNonNegative(Double)" must "tests that value is positive or zero" in {
    val rn = requireNumeric
    import rn._

    requireNonNegative(5.5, fieldName)
    requireNonNegative(5.3)
    requireNonNegative(0.0, fieldName)
    requireNonNegative(0.0)

    intercept[IllegalArgumentException] {
      requireNonNegative(-1.34, fieldName)
    }

    intercept[IllegalArgumentException] {
      requireNonNegative(-1.22)
    }
  }

  "requireNonNegative(BigInt)" must "tests that value is positive or zero" in {
    val rn = requireNumeric
    import rn._

    requireNonNegative(BigInt(5), fieldName)
    requireNonNegative(BigInt(5))
    requireNonNegative(BigInt(0), fieldName)
    requireNonNegative(BigInt(0))

    intercept[IllegalArgumentException] {
      requireNonNegative(BigInt(-1), fieldName)
    }

    intercept[IllegalArgumentException] {
      requireNonNegative(BigInt(-1))
    }
  }

  "requireNonNegative(BigDecimal)" must "tests that value is positive or zero" in {
    val rn = requireNumeric
    import rn._

    requireNonNegative(BigDecimal(5.5), fieldName)
    requireNonNegative(BigDecimal(5.3))
    requireNonNegative(BigDecimal(0.0), fieldName)
    requireNonNegative(BigDecimal(0.0))

    intercept[IllegalArgumentException] {
      requireNonNegative(BigDecimal(-1.34), fieldName)
    }

    intercept[IllegalArgumentException] {
      requireNonNegative(BigDecimal(-1.22))
    }
  }

  "requireNonNegative" must "validate args" in {
    val rn = requireNumeric
    import rn._

    requireNonNegative(5, null) // lazy evaluation

    intercept[RuntimeException] {
      requireNonNegative(-5, null)
    }
  }
}
