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

/** Tests for [[RequireSize]].
  *
  * @author Arsen Ibragimov (ars)
  * @since 0.0.4
  */
class RequireSizeTest extends AbstractBaseTest with BeforeAndAfterEach {

  var requireSizeImpl: RequireSize = _

  val fieldName = "fieldName"

  override protected def beforeEach(): Unit = {
    requireSizeImpl = new RequireSize {}
  }

  "RequireSize" must "be instance of RequireCore" in {
    assert(requireSizeImpl.isInstanceOf[RequireCore])
  }

  "requireSize" must "test that size of iterable is equals to required" in {
    val rs = requireSizeImpl
    import rs._

    val s = Seq(1, 2, 3)

    requireSize(s, 3)
    requireSize(s, 3, fieldName)

    requireSize(Seq(), 0)
    requireSize(Seq(), 0, fieldName)

    intercept[IllegalArgumentException] {
      requireSize(s, 1, fieldName)
    }

    intercept[IllegalArgumentException] {
      requireSize(s, 5, fieldName)
    }
  }

  it must "validate args" in {
    val rs = requireSizeImpl
    import rs._

    val s = Seq(1, 2, 3)

    intercept[RuntimeException] {
      requireSize(s, -1)
    }

    intercept[RuntimeException] {
      requireSize(s, -1, fieldName)
    }

    requireSize(s, 3, null) // lazy evaluation

    intercept[RuntimeException] {
      requireSize(s, 2, null)
    }
  }

  "requireSizeInRange" must "tests that size of iterable in [from, until)" in {
    val rs = requireSizeImpl
    import rs._

    val s = Seq(1, 2, 3)

    requireSizeInRange(s, 0, 5)
    requireSizeInRange(s, 0, 5, fieldName)
    requireSizeInRange(s, 3, 4, fieldName)

    intercept[IllegalArgumentException] {
      requireSizeInRange(s, 1, 3, fieldName)
    }

    intercept[IllegalArgumentException] {
      requireSizeInRange(s, 1, 2, fieldName)
    }

    intercept[IllegalArgumentException] {
      requireSizeInRange(s, 4, 5, fieldName)
    }
  }

  it must "validate args" in {
    val rs = requireSizeImpl
    import rs._

    val s = Seq(1, 2, 3)

    intercept[RuntimeException] {
      requireSizeInRange(s, -1, 5, fieldName)
    }

    intercept[RuntimeException] {
      requireSizeInRange(s, 3, 1, fieldName)
    }

    intercept[RuntimeException] {
      requireSizeInRange(s, -1, 5, fieldName)
    }

    intercept[RuntimeException] {
      requireSizeInRange(s, 3, 3, fieldName)
    }

    requireSizeInRange(s, 2, 5, null) // lazy evaluation

    intercept[RuntimeException] {
      requireSizeInRange(s, 4, 5, null)
    }
  }

  "requireSizeFrom" must "tests that size of iterable >= from" in {
    val rs = requireSizeImpl
    import rs._

    val s = Seq(1, 2, 3)

    requireSizeFrom(s, 0)
    requireSizeFrom(s, 0, fieldName)

    requireSizeFrom(s, 1)
    requireSizeFrom(s, 1, fieldName)

    requireSizeFrom(s, 3)
    requireSizeFrom(s, 3, fieldName)

    intercept[IllegalArgumentException] {
      requireSizeFrom(s, 4)
    }
    intercept[IllegalArgumentException] {
      requireSizeFrom(s, 4, fieldName)
    }
  }

  it must "validate args" in {
    val rs = requireSizeImpl
    import rs._

    val s = Seq(1, 2, 3)

    intercept[RuntimeException] {
      requireSizeFrom(s, -5, fieldName)
    }

    intercept[RuntimeException] {
      requireSizeFrom(s, -1, fieldName)
    }

    requireSizeFrom(s, 2, null) // lazy evaluation
    intercept[RuntimeException] {
      requireSizeFrom(s, 5, null)
    }
  }

  "requireSizeUntil" must "tests that size of iterable < until" in {
    val rs = requireSizeImpl
    import rs._

    val s = Seq(1, 2, 3)

    requireSizeUntil(s, 5)
    requireSizeUntil(s, 5, fieldName)

    requireSizeUntil(s, 4)
    requireSizeUntil(s, 4, fieldName)

    intercept[IllegalArgumentException] {
      requireSizeUntil(s, 3)
    }
    intercept[IllegalArgumentException] {
      requireSizeUntil(s, 3, fieldName)
    }

    intercept[IllegalArgumentException] {
      requireSizeUntil(s, 2)
    }
    intercept[IllegalArgumentException] {
      requireSizeUntil(s, 2, fieldName)
    }
  }

  it must "validate args" in {
    val rs = requireSizeImpl
    import rs._

    val s = Seq(1, 2, 3)

    intercept[RuntimeException] {
      requireSizeUntil(s, -5, fieldName)
    }

    intercept[RuntimeException] {
      requireSizeUntil(s, 0, fieldName)
    }

    requireSizeUntil(s, 5, null) // lazy evaluation
    intercept[RuntimeException] {
      requireSizeUntil(s, 2, null)
    }
  }
}
