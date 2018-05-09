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

/** Tests for [[RequireStringNumeric]].
  *
  * @author Arsen Ibragimov (ars)
  * @since 0.0.4
  */
class RequireStringNumericTest extends AbstractBaseTest with BeforeAndAfterEach {

  var requireStringNumeric: RequireStringNumeric = _

  val fieldName = "fieldName"

  override protected def beforeEach(): Unit = {
    requireStringNumeric = new RequireStringNumeric {}
  }

  "RequireStringNumeric" must "be instance of RequireCore" in {
    assert(requireStringNumeric.isInstanceOf[RequireCore])
  }

  "requireByte()" must "throws IAE if string value can't be converted to Byte" in {
    val r = requireStringNumeric
    import r._

    val n = "name"
    val v = "22"

    requireByte(v, n)
    requireByte(v)

    val bv = "f"
    intercept[IllegalArgumentException] {
      requireByte(bv, n)
    }
    intercept[IllegalArgumentException] {
      requireByte(bv)
    }

    val lv = "34444444444"
    intercept[IllegalArgumentException] {
      requireByte(lv, n)
    }
    intercept[IllegalArgumentException] {
      requireByte(lv)
    }
  }

  it must "validate args" in {
    val r = requireStringNumeric
    import r._

    intercept[RuntimeException] {
      requireByte(null, fieldName)
    }

    requireByte("22", fieldName) // lazy evaluation
    intercept[RuntimeException] {
      requireByte("3000", null)
    }
  }

  "requireShort()" must "test that string value can be converted to Short" in {
    val r = requireStringNumeric
    import r._

    val n = "name"
    val v = "260"

    requireShort("10", n)
    requireShort(v, n)
    requireShort(v)

    val bv = "f"
    intercept[IllegalArgumentException] {
      requireShort(bv, n)
    }
    intercept[IllegalArgumentException] {
      requireShort(bv)
    }

    val lv = "34444444444"
    intercept[IllegalArgumentException] {
      requireShort(lv, n)
    }
    intercept[IllegalArgumentException] {
      requireShort(lv)
    }
  }

  it must "validate args" in {
    val r = requireStringNumeric
    import r._

    intercept[RuntimeException] {
      requireShort(null, fieldName)
    }

    requireShort("22", fieldName) // lazy evaluation
    intercept[RuntimeException] {
      requireShort("34444444444", null)
    }
  }

  "requireInt()" must "test that string value can be converted to Int" in {
    val r = requireStringNumeric
    import r._

    val n = "name"
    val v = "4400000"

    requireInt("10", n)
    requireInt("3000", n)
    requireInt(v, n)
    requireInt(v)

    val bv = "f"
    intercept[IllegalArgumentException] {
      requireInt(bv, n)
    }
    intercept[IllegalArgumentException] {
      requireInt(bv)
    }

    val lv = "34444444444"
    intercept[IllegalArgumentException] {
      requireInt(lv, n)
    }
    intercept[IllegalArgumentException] {
      requireInt(lv)
    }
  }

  it must "validate args" in {
    val r = requireStringNumeric
    import r._

    intercept[RuntimeException] {
      requireInt(null, fieldName)
    }

    requireInt("2442", fieldName) // lazy evaluation
    intercept[RuntimeException] {
      requireInt("34444444444", null)
    }
  }

  "requireLong()" must "test that string value can be converted to Long" in {
    val r = requireStringNumeric
    import r._

    val n = "name"
    val v = "4400000000000"

    requireLong("10", n)
    requireLong("3000", n)
    requireLong("400000000", n)
    requireLong(v, n)
    requireLong(v)

    val bv = "f"
    intercept[IllegalArgumentException] {
      requireLong(bv, n)
    }
    intercept[IllegalArgumentException] {
      requireLong(bv)
    }

    val lv = "344444444455555555555554"
    intercept[IllegalArgumentException] {
      requireLong(lv, n)
    }
    intercept[IllegalArgumentException] {
      requireLong(lv)
    }
  }

  it must "validate args" in {
    val r = requireStringNumeric
    import r._

    intercept[RuntimeException] {
      requireLong(null, fieldName)
    }

    requireLong("2442", fieldName) // lazy evaluation
    intercept[RuntimeException] {
      requireLong("344444444455555555555554", null)
    }
  }

  "requireFloat()" must "test that string value can be converted to Float" in {
    val r = requireStringNumeric
    import r._

    val n = "name"
    val v = "44000000.434"

    requireFloat("10", n)
    requireFloat("3000", n)
    requireFloat("400000000", n)
    requireFloat("4400000000000", n)
    requireFloat(v, n)
    requireFloat(v)

    val bv = "f"
    intercept[IllegalArgumentException] {
      requireFloat(bv, n)
    }
    intercept[IllegalArgumentException] {
      requireFloat(bv)
    }

    val lv = "34444444445555555555555433333334433"
    requireFloat(lv, n)
    requireFloat(lv)


    val lv1 = "344444444455555555555554.443"
    requireFloat(lv1, n)
    requireFloat(lv1)
  }

  it must "validate args" in {
    val r = requireStringNumeric
    import r._

    intercept[RuntimeException] {
      requireFloat(null, fieldName)
    }

    requireFloat("2442", fieldName) // lazy evaluation
    intercept[RuntimeException] {
      requireFloat("d", null)
    }
  }

  "requireDouble()" must "test that string value can be converted to Double" in {
    val r = requireStringNumeric
    import r._

    val n = "name"
    val v = "44000000.434"

    requireDouble("10", n)
    requireDouble("3000", n)
    requireDouble("400000000", n)
    requireDouble("4400000000000", n)
    requireDouble(v, n)
    requireDouble(v)

    val bv = "f"
    intercept[IllegalArgumentException] {
      requireDouble(bv, n)
    }
    intercept[IllegalArgumentException] {
      requireDouble(bv)
    }

    val lv = "34444444445555555555555433333334433"
    requireDouble(lv, n)
    requireDouble(lv)


    val lv1 = "344444444455555555555554.443"
    requireDouble(lv1, n)
    requireDouble(lv1)
  }

  it must "validate args" in {
    val r = requireStringNumeric
    import r._

    intercept[RuntimeException] {
      requireDouble(null, fieldName)
    }

    requireDouble("2442", fieldName) // lazy evaluation
    intercept[RuntimeException] {
      requireDouble("d", null)
    }
  }

}
