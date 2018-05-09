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
import ars.precondition.MessageBuilder.NoNameParameter
import ars.precondition.require._
import org.scalatest.BeforeAndAfterEach

/** Tests for [[RequireOptional]].
  *
  * @author Arsen Ibragimov (ars)
  * @since 0.0.4
  */
class RequireOptionalTest extends AbstractBaseTest with BeforeAndAfterEach {

  var requireOptional: RequireOptional = _

  val fieldName = "fieldName"

  override protected def beforeEach(): Unit = {
    requireOptional = new RequireOptional {}
  }

  "RequireOptional" must "be instance of RequireCore" in {
    assert(requireOptional.isInstanceOf[RequireCore])
  }

  "optional()" must "invoke require if `value` is Some(_)" in {
    val ro = requireOptional
    import ro._

    var v = 0
    optional(Some("my string")) { case (s, n) =>
      assertResult("my string")(s)
      v = 10
    }
    assert(v == 10)
  }

  it must "have correct name in predicate" in {
    val ro = requireOptional
    import ro._

    optional(Some("my string"), "my name") { case (s, n) =>
      assertResult("my string")(s)
      assertResult("my name")(n)
    }

    optional(Some("my string")) { case (s, n) =>
      assertResult("my string")(s)
      assertResult(NoNameParameter)(n)
    }
  }

  it must "be usable with RequireFunction" in {
    val ro = requireOptional
    import ro._

    val rs: RequireFunction[String] = (value: String, name: String) => require(value != null, "Bad value")

    optional(Some("my string"), "my name")(rs)
  }

  it must "do nothing otherwise" in {
    val ro = requireOptional
    import ro._

    var v = 0
    optional[String](None) { case (s, n) =>
      assertResult("not used")(s)
      v = 10
    }
    assert(v == 0)
  }

  it must "validate args" in {
    val ro = requireOptional
    import ro._

    intercept[RuntimeException] {
      optional[String](null) { case (s, n) => s + "1234" }
    }

    optional(None)(null)
    intercept[RuntimeException] {
      optional(Some(10))(null)
    }
  }

  "optionalPredicate()" must "be usable with RequirePredicate" in {
    val ro = requireOptional
    import ro._

    optionalPredicate(Some("my string"))(_.nonEmpty)
    optionalPredicate(Some("my string"), "name")(_.nonEmpty)

    intercept[IllegalArgumentException] {
      optionalPredicate(Some("my string"))(_.isEmpty)
    }

    intercept[IllegalArgumentException] {
      optionalPredicate(Some("my string"), "name")(_.isEmpty)
    }
  }

}
