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
import ars.precondition.MessageBuilder.{NoNameParameter, notNull}
import ars.precondition.Predicates.isNotNull
import org.scalatest.BeforeAndAfterEach

/** Tests for [[RequireAll]].
  *
  * @author Arsen Ibragimov (ars)
  * @since 0.0.4
  */
class RequireAllTest extends AbstractBaseTest with BeforeAndAfterEach {

  var requireAllImpl: RequireAll = _

  val fieldName = "fieldName"

  override protected def beforeEach(): Unit = {
    requireAllImpl = new RequireAll {}
  }

  "RequireAll" must "be instance of RequireCore" in {
    assert(requireAllImpl.isInstanceOf[RequireCore])
  }

  "requireAll()" must "throw IAE if there's an element of `value` that don't satisfy the `require`" in {
    val rs = requireAllImpl
    import rs._

    val s = Seq(1, 2, 5, 7)
    intercept[IllegalArgumentException] {
      requireAll(s, fieldName) { case (v, name, index) =>
        require(v < 6, "message")
      }
    }

    intercept[IllegalArgumentException] {
      requireAll(s) { case (v, name, index) =>
        require(v < 6, "message")
      }
    }
  }

  it must "call RequireElementFunction for each element otherwise" in {
    val rs = requireAllImpl
    import rs._

    val s = Seq(1, 2, 5, 7)

    var ns = Seq[Int]()
    var c = 0
    var i = Seq[Int]()

    requireAll(s, fieldName) { case (v, name, index) =>
      c = c + 1
      ns +:= v
      i +:= index
    }

    assert(c == s.size)
    assert(s == ns.reverse)
    assert(s.indices == i.reverse)

    ns = Seq[Int]()
    c = 0
    i = Seq[Int]()

    requireAll(s) { case (v, name, index) =>
      c = c + 1
      ns +:= v
      i +:= index
    }

    assert(c == s.size)
    assert(s == ns.reverse)
    assert(s.indices == i.reverse)
  }

  it must "validate args" in {
    val rs = requireAllImpl
    import rs._

    intercept[RuntimeException] {
      requireAll[Int](null, fieldName) { case (v, name, index) => }
    }

    intercept[RuntimeException] {
      requireAll(Seq(1, 2, 3), null) { case (v, name, index) => }
    }

    intercept[RuntimeException] {
      requireAll(Seq(1, 2, 3), fieldName)(null)
    }
  }

  it must "be used with implicit conversion from RequireFunction" in {
    val rs = requireAllImpl
    import rs._

    import ars.precondition.implicits._

    val s = Seq(1, 2, 5, 7)

    def requireNotNull(value: Int, name: String = NoNameParameter): Unit = {
      require(isNotNull(value), notNull(name))
    }

    requireAll(s, fieldName)(requireNotNull)
  }

  "requireAllPredicate" must "throw IAE if there's an element of `value` that don't satisfy the `predicate`" in {
    val rs = requireAllImpl
    import rs._

    val s = Seq(1, 2, 5, 7)
    intercept[IllegalArgumentException] {
      requireAllPredicate(s, fieldName)(_ < 6)
    }

    intercept[IllegalArgumentException] {
      requireAllPredicate(s)(_ < 6)
    }
  }

  it must "call RequireElementFunction for each element otherwise" in {
    val rs = requireAllImpl
    import rs._

    val s = Seq(1, 2, 5, 7)

    var ns = Seq[Int]()
    var c = 0

    requireAllPredicate(s, fieldName) { v =>
      c = c + 1
      ns +:= v
      true
    }

    assert(c == s.size)
    assert(s == ns.reverse)

    ns = Seq[Int]()
    c = 0

    requireAllPredicate(s, fieldName) { v =>
      c = c + 1
      ns +:= v
      true
    }

    assert(c == s.size)
    assert(s == ns.reverse)
  }

  it must "validate args" in {
    val rs = requireAllImpl
    import rs._

    intercept[RuntimeException] {
      requireAllPredicate[Int](null, fieldName) { v => true }
    }

    intercept[RuntimeException] {
      requireAllPredicate(Seq(1, 2, 3), null) { v => true }
    }

    intercept[RuntimeException] {
      requireAllPredicate(Seq(1, 2, 3), fieldName)(null)
    }
  }

}
