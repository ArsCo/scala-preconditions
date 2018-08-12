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

/** Tests for [[RequireUnique]].
  *
  * @author Arsen Ibragimov (ars)
  * @since 0.1.2
  */
class RequireUniqueTest extends AbstractBaseTest with BeforeAndAfterEach {

  var requireUnique: RequireUnique = _

  val fieldName = "fieldName"
  val name = "name"

  val uniqueTestValues = Seq(
    TestClass("f1", 1),
    TestClass("f2", 2)
  )

  val nonUniqueTestValues1 = Seq(
    TestClass("f1", 1),
    TestClass("f2", 2),
    TestClass("f2", 3)
  )

  val nonUniqueTestValues2 = Seq(
    TestClass("f1", 1),
    TestClass("f2", 2),
    TestClass("f3", 2)
  )

  override protected def beforeEach(): Unit = {
    requireUnique = new RequireUnique {}
  }

  "RequireUnique" must "be instance of RequireCore" in {
    assert(requireUnique.isInstanceOf[RequireCore])
  }

  "requireUniqueField()" must "validate args" in {
    requireUnique.requireUniqueField(uniqueTestValues)(_.field1)
    requireUnique.requireUniqueField(uniqueTestValues, name)(_.field1)
    requireUnique.requireUniqueField(uniqueTestValues, name, fieldName)(_.field1)

    intercept[RuntimeException] {
      requireUnique.requireUniqueField[TestClass, String](null, name, fieldName)(_.field1)
    }

    intercept[RuntimeException] {
      requireUnique.requireUniqueField(uniqueTestValues, null, fieldName)(_.field1)
    }

    intercept[RuntimeException] {
      requireUnique.requireUniqueField(uniqueTestValues, "   ", fieldName)(_.field1)
    }

    intercept[RuntimeException] {
      requireUnique.requireUniqueField(uniqueTestValues, name, "  ")(_.field1)
    }

    intercept[RuntimeException] {
      requireUnique.requireUniqueField(uniqueTestValues, name, fieldName)(null)
    }
  }

  it must "throw IAE if there are 2 more elements with the same field value" in {
    intercept[IllegalArgumentException] {
      requireUnique.requireUniqueField(nonUniqueTestValues1, name, fieldName)(_.field1)
    }

    intercept[IllegalArgumentException] {
      requireUnique.requireUniqueField(nonUniqueTestValues2, name, fieldName)(_.field2)
    }
  }

  it must "do nothing if each element of iterable has unique value of field" in {
    requireUnique.requireUniqueField[TestClass, String](uniqueTestValues, name, fieldName)(_.field1)
  }
}

case class TestClass(field1: String, field2: Int)
