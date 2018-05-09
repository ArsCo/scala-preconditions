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

import java.util.regex.Pattern

import ars.precondition.AbstractBaseTest
import org.scalatest.BeforeAndAfterEach

/** Tests for [[RequireString]].
  *
  * @author Arsen Ibragimov (ars)
  * @since 0.0.4
  */
class RequireStringTest extends AbstractBaseTest with BeforeAndAfterEach {

  var requireString: RequireString = _

  val fieldName = "fieldName"

  override protected def beforeEach(): Unit = {
    requireString = new RequireString {}
  }

  "RequireString" must "be instance of RequireCore" in {
    assert(requireString.isInstanceOf[RequireCore])
  }

  "requireNotBlank()" must "tests that value is not null or trimmed empty string and throws IAE otherwise" in {
    val rs = requireString
    import rs._

    requireNotBlank("4444", fieldName)
    requireNotBlank("5555")

    intercept[IllegalArgumentException] {
      val value: String = null
      requireNotBlank(value, fieldName)
    }

    intercept[IllegalArgumentException] {
      requireNotBlank("", fieldName)
    }

    intercept[IllegalArgumentException] {
      requireNotBlank("  ", fieldName)
    }

    intercept[IllegalArgumentException] {
      val value: String = null
      requireNotBlank(value)
    }

    intercept[IllegalArgumentException] {
      requireNotBlank("   ")
    }
  }

  "requirePattern()" must "test that the value matches the pattern" in {
    val rs = requireString
    import rs._

    val n = "name"
    val v = "aaaa@bbb.rr"
    val p = "^a+@b+\\.r+$".r
    requirePattern(v, p, n)
    requirePattern(v, p)

    val bv = "sadfsdf"
    intercept[IllegalArgumentException] {
      requirePattern(bv, p, n)
    }
    intercept[IllegalArgumentException] {
      requirePattern(bv, p)
    }
  }

  it must "be usable with java Pattern (implicit conversion)" in {
    val rs = requireString
    import rs._

    import ars.precondition.implicits._

    val n = "name"
    val v = "aaaa@bbb.rr"
    val p = Pattern.compile("^a+@b+\\.r+$")
    requirePattern(v, p, n)
    requirePattern(v, p)

    val bv = "sadfsdf"
    intercept[IllegalArgumentException] {
      requirePattern(bv, p, n)
    }
    intercept[IllegalArgumentException] {
      requirePattern(bv, p)
    }
  }

}
