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

/** Tests for [[RequireIterable]].
  *
  * @author Arsen Ibragimov (ars)
  * @since 0.0.4
  */
class RequireIterableTest extends AbstractBaseTest with BeforeAndAfterEach {

  var requireIterable: RequireIterable = _

  val fieldName = "fieldName"

  override protected def beforeEach(): Unit = {
    requireIterable = new RequireIterable {}
  }

  "RequireIterable" must "be instance of RequireCore" in {
    assert(requireIterable.isInstanceOf[RequireCore])
  }

  "requireNotBlank" must "tests that value is not null or empty list and throws IAE otherwise" in {
    val rs = requireIterable
    import rs._

    requireNotBlank(Seq("111"), fieldName)
    requireNotBlank(Seq("555"))

    intercept[IllegalArgumentException] {
      val value: List[String] = null
      requireNotBlank(value, fieldName)
    }

    intercept[IllegalArgumentException] {
      requireNotBlank(Nil, fieldName)
    }

    intercept[IllegalArgumentException] {
      val value: String = null
      requireNotBlank(value)
    }

    intercept[IllegalArgumentException] {
      requireNotBlank(Nil)
    }
  }

  "requireNotBlank" must "validate args" in {
    val rs = requireIterable
    import rs._

    requireNotBlank(Seq("111"), null) // lazy evaluation

    intercept[RuntimeException] {
      requireNotBlank(Seq(), null)
    }

    intercept[RuntimeException] {
      requireNotBlank(null, null)
    }
  }

}
