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

/** Tests for [[RequireAllSpecific]].
  *
  * @author Arsen Ibragimov (ars)
  * @since 0.0.4
  */
class RequireAllSpecificTest extends AbstractBaseTest with BeforeAndAfterEach {

  var requireAllSpec: RequireAllSpecific = _

  val fieldName = "fieldName"

  override protected def beforeEach(): Unit = {
    requireAllSpec = new RequireAllSpecific {}
  }

  "RequireAllSpecific" must "be instance of RequireCore" in {
    assert(requireAllSpec.isInstanceOf[RequireCore])
  }

  "requireAllNotNull()" must "throw IAE if there's a null element in `value`" in {
    val rs = requireAllSpec
    import rs._

    val s = Seq(1, 2, null, 4)

    intercept[IllegalArgumentException] {
      requireAllNotNull(s)
    }

    intercept[IllegalArgumentException] {
      requireAllNotNull(s, fieldName)
    }
  }

  it must "do nothing otherwise" in {
    val rs = requireAllSpec
    import rs._

    val s = Seq(1, 2, 3, 4)
    requireAllNotNull(s)
    requireAllNotNull(s, fieldName)
  }

  it must "validate args" in {
    val rs = requireAllSpec
    import rs._

    intercept[RuntimeException] {
      requireAllNotNull(null)
    }

    intercept[RuntimeException] {
      requireAllNotNull(null, fieldName)
    }

    intercept[RuntimeException] {
      requireAllNotNull(Seq(1, 2), null)
    }
  }

  "requireAllNotBlank()" must "throw IAE if there's a blank string element in `value`" in {
    val rs = requireAllSpec
    import rs._

    val s = Seq("123", null, "456")

    intercept[IllegalArgumentException] {
      requireAllNotBlank(s)
    }

    intercept[IllegalArgumentException] {
      requireAllNotBlank(s, fieldName)
    }

    val s1 = Seq("123", "", "456")

    intercept[IllegalArgumentException] {
      requireAllNotBlank(s1)
    }

    intercept[IllegalArgumentException] {
      requireAllNotBlank(s1, fieldName)
    }
  }

  it must "do nothing otherwise" in {
    val rs = requireAllSpec
    import rs._

    val s = Seq("123", "456")
    requireAllNotBlank(s)
    requireAllNotBlank(s, fieldName)
  }

  it must "validate args" in {
    val rs = requireAllSpec
    import rs._

    intercept[RuntimeException] {
      requireAllNotBlank(null)
    }

    intercept[RuntimeException] {
      requireAllNotBlank(null, fieldName)
    }

    intercept[RuntimeException] {
      requireAllNotBlank(Seq("123", "456"), null)
    }
  }
}
