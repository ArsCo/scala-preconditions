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

import ars.precondition.require.RequireInternal._
import ars.precondition.require.bound.{BoundedNumber, Exclusive, Inclusive}

/** Message factory methods.
  *
  * @author Arsen Ibragimov (ars)
  * @since 0.0.1
  */
object MessageBuilder {

  val NoNameParameter = "<no name>"

  def mustBe[T](name: String, restriction: String, value: T): String = {
    requireName(name)
    requireRestriction(restriction)
    s"The parameter '$name' must be $restriction: '$value'."
  }

  def mustBe(name: String, restriction: String): String = {
    requireName(name)
    requireRestriction(restriction)
    s"The parameter '$name' must be $restriction."
  }

  def mustMatch[T](name: String, restriction: String, value: T): String = {
    requireName(name)
    requireRestriction(restriction)
    s"The parameter '$name' must match $restriction: '$value'."
  }

  def mustContain[T](name: String, restriction: String): String = {
    requireName(name)
    requireRestriction(restriction)
    s"The parameter '$name' must contain $restriction."
  }

  def mustNotContain[T](name: String, restriction: String, value: T): String = {
    requireName(name)
    requireRestriction(restriction)
    s"The parameter '$name' must not contain $restriction: '$value'."
  }

  def sizeMustBe[T](name: String, restriction: String, value: T): String = {
    requireName(name)
    requireRestriction(restriction)
    s"The size (length) of the parameter '$name' must be $restriction: '$value'."
  }

  def mustSatisfy[T](name: String, restriction: String, value: T): String = {
    requireName(name)
    requireRestriction(restriction)
    s"The parameter '$name' must satisfy restriction $restriction: '$value'."
  }

  def notNull(name: String): String = mustBe(name, "not null")
  def notBlank[T](name: String, value: T): String = mustBe(name, "not null or empty", value)

  def positive[T](name: String, value: T): String = mustBe(name, "positive", value)
  def negative[T](name: String, value: T): String = mustBe(name, "negative", value)

  def nonNegative[T](name: String, value: T): String = mustBe(name, "non-negative", value)
  def nonPositive[T](name: String, value: T): String = mustBe(name, "non-positive", value)

  def regExpr[T](name: String, value: T): String = mustMatch(name, "the regular expression", value)

  def numberOfType[T](name: String, value: String, typeClass: Class[T]): String =
    mustBe(name, s"number of type '${typeClass.getName}'", value)

  def sizeMustBeEqual[T](name: String, value: T, size: Int): String =
    sizeMustBe(name, s"equals '$size'", value)

  def sizeMustBeInRange[T](name: String, value: T, from: Int, until: Int): String =
    sizeMustBe(name, s"in range from '$from' to '$until'", value)

  def sizeMustBeGtEq[T](name: String, value: T, from: Int): String =
    sizeMustBe(name, s"great than or equal '$from'", value)

  def sizeMustBeLt[T](name: String, value: T, until: Int): String =
    sizeMustBe(name, s"less than '$until'", value)

  def numberMustBeEqual[T](name: String, value: T, number: T): String = mustBe(name, s"equal $number", value)

  def numberMustBeGt[T](name: String, value: T, leftBound: BoundedNumber[T]): String = {
    val operator = leftBound match {
      case Exclusive(_) => "<"
      case Inclusive(_) => "≤"
      case _ => throw new IllegalStateException("Unknown bound type")
    }

    mustSatisfy(name, s"${leftBound.value} $operator $value", value)
  }

  def numberMustBeLt[T](name: String, value: T, rightBound: BoundedNumber[T]): String = {
    val operator = rightBound match {
      case Exclusive(_) => ">"
      case Inclusive(_) => "≥"
      case _ => throw new IllegalStateException("Unknown bound type")
    }

    mustSatisfy(name, s"${rightBound.value} $operator $value", value)
  }

  def mustContainOnlyOneOf[T](name: String, value: Iterable[T], allowDups: Boolean): String = {
    val valueString = iterableToPrettyString(value)
    val duplicatesString = dupsString(allowDups)

    mustContain(name, s"one and only one of $valueString ($duplicatesString)")
  }

  def mustContainAtLeastOneOf[T](name: String, value: Iterable[T], allowDups: Boolean): String = {
    val valueString = iterableToPrettyString(value)
    val duplicatesString = dupsString(allowDups)

    mustContain(name, s"at least one of $valueString ($duplicatesString)")
  }

  def mustBeCorrectEmail(name: String, value: String): String = mustBe(name, "correct email", value)

  def mustBeCorrectUuid(name: String, value: String): String = mustBe(name, "correct UUID", value)

  def nonUniqueMessage[T, F](name: String, fieldName: String, nonUnique: (F, Iterable[T])): String = {
    val (key, vals) = nonUnique
    val num = vals.size
    val s = vals.map(v => s"'$v'").mkString(", ")

    s"Each element of `$name` must have unique value of `$fieldName` but there're $num non-unique for '$key': $s."
  }

  private[precondition] def iterableToPrettyString[T](value: Iterable[T]): String = {
    val values = value.toSeq.map(_.toString).mkString(", ")
    s"[$values]"
  }

  private[precondition] def dupsString(allowDups: Boolean): String = s"with${ if (allowDups) "" else "out" } duplicates"
}
