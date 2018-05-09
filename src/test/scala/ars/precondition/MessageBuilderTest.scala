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

package ars.precondition

import ars.precondition.require.bound._

//import ars.precondition.Predicates.BoundTypes

/** Tests for [[MessageBuilder]].
  *
  * @author Arsen Ibragimov (ars)
  * @since 0.0.3
  */
class MessageBuilderTest extends AbstractBaseTest {

  "NoNameParameter" must "be '<no name>' string" in {
    assertResult("<no name>") { MessageBuilder.NoNameParameter }
  }

  "mustBe()" must "produce correct string value" in {
    val n = "param name"
    val r = "not null or null or ..."
    val v = "123fgh"

    assertResult(s"The parameter '$n' must be $r: '$v'.") {
      MessageBuilder.mustBe(n, r, v)
    }

    assertResult(s"The parameter '$n' must be $r.") {
      MessageBuilder.mustBe(n, r)
    }
  }

  "mustMatch()" must "produce correct string value" in {
    val n = "param name"
    val r = "not null or null or ..."
    val v = "123fgh"

    assertResult(s"The parameter '$n' must match $r: '$v'.") {
      MessageBuilder.mustMatch(n, r, v)
    }
  }

  "mustContain()" must "produce correct string value" in {
    val n = "param name"
    val r = "not null or null or ..."

    assertResult(s"The parameter '$n' must contain $r.") {
      MessageBuilder.mustContain(n, r)
    }
  }

  "mustNotContain()" must "produce correct string value" in {
    val n = "param name"
    val r = "not null or null or ..."
    val v = "123fgh"

    assertResult(s"The parameter '$n' must not contain $r: '$v'.") {
      MessageBuilder.mustNotContain(n, r, v)
    }
  }

  "sizeMustBe()" must "produce correct string value" in {
    val n = "param name"
    val r = "not null or null or ..."
    val v = "123fgh"

    assertResult(s"The size (length) of the parameter '$n' must be $r: '$v'.") {
      MessageBuilder.sizeMustBe(n, r, v)
    }
  }

  "mustSatisfy()" must "produce correct string value" in {
    val n = "param name"
    val r = "not null or null or ..."
    val v = "123fgh"

    assertResult(s"The parameter '$n' must satisfy restriction $r: '$v'.") {
      MessageBuilder.mustSatisfy(n, r, v)
    }
  }

  "notNull()" must "produce correct string value" in {
    val n = "param name"

    assertResult(MessageBuilder.mustBe(n, "not null")) {
      MessageBuilder.notNull(n)
    }
  }

  "notBlank()" must "produce correct string value" in {
    val n = "param name"
    val v = "123fgh"

    assertResult(MessageBuilder.mustBe(n, "not null or empty", v)) {
      MessageBuilder.notBlank(n, v)
    }
  }

  "positive()" must "produce correct string value" in {
    val n = "param name"
    val v = "123fgh"

    assertResult(MessageBuilder.mustBe(n, "positive", v)) {
      MessageBuilder.positive(n, v)
    }
  }

  "negative()" must "produce correct string value" in {
    val n = "param name"
    val v = "123fgh"

    assertResult(MessageBuilder.mustBe(n, "negative", v)) {
      MessageBuilder.negative(n, v)
    }
  }

  "nonNegative()" must "produce correct string value" in {
    val n = "param name"
    val v = "123fgh"

    assertResult(MessageBuilder.mustBe(n, "non-negative", v)) {
      MessageBuilder.nonNegative(n, v)
    }
  }

  "nonPositive()" must "produce correct string value" in {
    val n = "param name"
    val v = "123fgh"

    assertResult(MessageBuilder.mustBe(n, "non-positive", v)) {
      MessageBuilder.nonPositive(n, v)
    }
  }

  "regExpr()" must "produce correct string value" in {
    val n = "param name"
    val v = "123fgh"

    assertResult(MessageBuilder.mustMatch(n, "the regular expression", v)) {
      MessageBuilder.regExpr(n, v)
    }
  }

  "numberOfType()" must "produce correct string value" in {
    val n = "param name"
    val v = "123fgh"
    val c = "".getClass

    assertResult(MessageBuilder.mustBe(n, s"number of type '${c.getName}'", v)) {
      MessageBuilder.numberOfType(n, v, c)
    }
  }

  "sizeMustBeEqual()" must "produce correct string value" in {
    val n = "param name"
    val v = "123fgh"
    val s = 10

    assertResult(MessageBuilder.sizeMustBe(n, s"equals '$s'", v)) {
      MessageBuilder.sizeMustBeEqual(n, v, s)
    }
  }

  "sizeMustBeInRange()" must "produce correct string value" in {
    val n = "param name"
    val v = "123fgh"
    val f = 10
    val u = 30

    assertResult(MessageBuilder.sizeMustBe(n, s"in range from '$f' to '$u'", v)) {
      MessageBuilder.sizeMustBeInRange(n, v, f, u)
    }
  }

  "sizeMustBeGtEq()" must "produce correct string value" in {
    val n = "param name"
    val v = "123fgh"
    val f = 10

    assertResult(MessageBuilder.sizeMustBe(n, s"great than or equal '$f'", v)) {
      MessageBuilder.sizeMustBeGtEq(n, v, f)
    }
  }

  "sizeMustBeLt()" must "produce correct string value" in {
    val n = "param name"
    val v = "123fgh"
    val u = 10

    assertResult(MessageBuilder.sizeMustBe(n, s"less than '$u'", v)) {
      MessageBuilder.sizeMustBeLt(n, v, u)
    }
  }

  "numberMustBeEqual()" must "produce correct string value" in {
    val n = "param name"
    val v = "123fgh"
    val num = 10

    assertResult(MessageBuilder.mustBe(n, s"equal $num", v)) {
      MessageBuilder.numberMustBeEqual(n, v, num)
    }
  }

  "numberMustBeGt()" must "produce correct string value" in {
    val n = "param name"
    val v = 33
    val l = 10

    assertResult(MessageBuilder.mustSatisfy(n, s"$l < $v", v)) {
      MessageBuilder.numberMustBeGt(n, v, Exclusive(l))
    }

    assertResult(MessageBuilder.mustSatisfy(n, s"$l ≤ $v", v)) {
      MessageBuilder.numberMustBeGt(n, v, Inclusive(l))
    }
  }

  "numberMustBeLt()" must "produce correct string value" in {
    val n = "param name"
    val v = 33
    val l = 10

    assertResult(MessageBuilder.mustSatisfy(n, s"$l > $v", v)) {
      MessageBuilder.numberMustBeLt(n, v, Exclusive(l))
    }

    assertResult(MessageBuilder.mustSatisfy(n, s"$l ≥ $v", v)) {
      MessageBuilder.numberMustBeLt(n, v, Inclusive(l))
    }
  }

  "mustContainOnlyOneOf()" must "produce correct string value" in {
    val n = "param name"
    val v = Seq(33, 44,55)
    val pv = "[33, 44, 55]"

    assertResult(MessageBuilder.mustContain(n, s"one and only one of $pv (with duplicates)")) {
      MessageBuilder.mustContainOnlyOneOf(n, v, true)
    }

    assertResult(MessageBuilder.mustContain(n, s"one and only one of $pv (without duplicates)")) {
      MessageBuilder.mustContainOnlyOneOf(n, v, false)
    }
  }

  "mustContainAtLeastOneOf()" must "produce correct string value" in {
    val n = "param name"
    val v = Seq(33, 44,55)
    val pv = "[33, 44, 55]"

    assertResult(MessageBuilder.mustContain(n, s"at least one of $pv (with duplicates)")) {
      MessageBuilder.mustContainAtLeastOneOf(n, v, true)
    }

    assertResult(MessageBuilder.mustContain(n, s"at least one of $pv (without duplicates)")) {
      MessageBuilder.mustContainAtLeastOneOf(n, v, false)
    }
  }

  "mustBeCorrectEmail()" must "produce correct string value" in {
    val n = "param name"
    val v = "aaaaadfffff"

    assertResult(MessageBuilder.mustBe(n, "correct email", v)) {
      MessageBuilder.mustBeCorrectEmail(n, v)
    }
  }

  "mustBeCorrectUuid()" must "produce correct string value" in {
    val n = "param name"
    val v = "aaaaadfffff"

    assertResult(MessageBuilder.mustBe(n, "correct UUID", v)) {
      MessageBuilder.mustBeCorrectUuid(n, v)
    }
  }

  "iterableToPrettyString()" must "produce correct string value for Iterable[T]" in {
    assertResult("[33, 44, 55]") {
      MessageBuilder.iterableToPrettyString(Seq(33, 44,55))
    }
  }

  "dupsString" must "produce correct dups string" in {
    assertResult("with duplicates") {
      MessageBuilder.dupsString(true)
    }

    assertResult("without duplicates") {
      MessageBuilder.dupsString(false)
    }
  }
}
