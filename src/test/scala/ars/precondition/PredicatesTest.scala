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

import ars.precondition.Predicates._

/** Tests for [[Predicates]].
  *
  * @author Arsen Ibragimov (ars)
  * @since 0.0.3
  */
class PredicatesTest extends AbstractBaseTest {

  "onlyOneOf" must "return `true` if one and only one elem of `seq` contains in `value`" in {
    val seq = Set("A", "B", "C", "D")
    val value = Seq("A", "F", "K", "N")
    val valueDups = Seq("A", "F", "K", "A")

    assert(isOnlyOneOf(value, seq))
    assert(isOnlyOneOf(value, seq, allowDups = false))
    assert(isOnlyOneOf(valueDups, seq, allowDups = true))
  }

  it must "return `false` otherwise" in {

    assert(
      !isOnlyOneOf(
        Seq(),
        Seq()
      )
    )

    assert(
      !isOnlyOneOf(
        Seq("A"),
        Seq()
      )
    )

    assert(
      !isOnlyOneOf(
        Seq(),
        Seq("F")
      )
    )

    assert(
      !isOnlyOneOf(
        Seq("A", "F", "A"),
        Seq("A")
      )
    )

    assert(
      !isOnlyOneOf(
        Seq(),
        Seq(),
        allowDups = true
      )
    )

    assert(
      !isOnlyOneOf(
        Seq("A"),
        Seq(),
        allowDups = true
      )
    )

    assert(
      !isOnlyOneOf(
        Seq(),
        Seq("F"),
        allowDups = true
      )
    )
  }

  "atLeastOneOf" must "return `true` if at least one elem of `seq` contains in `value`" in {
    val seq = Set("A", "B", "C", "D")
    val value = Seq("A", "F", "K", "N")
    val valueDups = Seq("A", "F", "K", "A")

    val seq2 = Set("A", "B", "C", "D")
    val value2 = Seq("A", "F", "K", "B")
    val valueDups2 = Seq("A", "F", "K", "A", "B", "B")

    assert(Predicates.isAtLeastOneOf(value, seq))
    assert(Predicates.isAtLeastOneOf(value, seq, allowDups = false))
    assert(Predicates.isAtLeastOneOf(valueDups, seq, allowDups = true))

    assert(Predicates.isAtLeastOneOf(value2, seq2))
    assert(Predicates.isAtLeastOneOf(value2, seq2, allowDups = false))
    assert(Predicates.isAtLeastOneOf(valueDups2, seq2, allowDups = true))
  }

  it must "return `false` otherwise" in {
    assert(
      !Predicates.isAtLeastOneOf(
        Seq(),
        Seq()
      )
    )

    assert(
      !Predicates.isAtLeastOneOf(
        Seq("A"),
        Seq()
      )
    )

    assert(
      !Predicates.isAtLeastOneOf(
        Seq(),
        Seq("F")
      )
    )

    assert(
      !Predicates.isAtLeastOneOf(
        Seq(),
        Seq(),
        allowDups = true
      )
    )

    assert(
      !Predicates.isAtLeastOneOf(
        Seq("A"),
        Seq(),
        allowDups = true
      )
    )

    assert(
      !isOnlyOneOf(
        Seq(),
        Seq("F"),
        allowDups = true
      )
    )
  }

  "isCorrectEmail" must "return `true` if email is correct" in {
    assert(isCorrectEmail("abcdef.sdf@ddfgw.ru"))
    assert(isCorrectEmail("abcdef.sdf@ddfgw.tyyy.com"))
    assert(isCorrectEmail("abcd234235@ddfgw.tyyy.com"))
  }

  it must "return `false` otherwise" in {
    assert(!isCorrectEmail("abcd234235ddfgw.tyyy.com"))
    assert(!isCorrectEmail("ABC234235@фываfgw.tyyy.com"))
    assert(!isCorrectEmail("abcd234235@фываfgw.tyyy.com"))
    assert(!isCorrectEmail(""))
  }

  "isCorrectUuid" must "validate guid format" in {
    assert(isCorrectUuid("837ab383-6ab4-9273-38c3-274abc742932"))
  }

  it must "return `false` otherwise" in {
    assert(!isCorrectUuid("837ab3836ab4-9273-38c3-274abc742932"))
    assert(!isCorrectUuid("837ab38h-6ab4-9273-38c3-274abc742932"))
    assert(!isCorrectUuid("837ab383-6ab4-9273-38c3-274abc742932d"))
    assert(!isCorrectUuid(""))
  }
}
