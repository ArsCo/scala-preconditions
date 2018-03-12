package ars.precondition

import ars.precondition.Predicates.BoundTypes

/** Message factory methods.
  *
  * @author ars (Arsen Ibragimov)
  * @since 0.0.1
  */
object Messages {

  val NoNameParameter = "<no name>"

  private[precondition] def mustBe[T](name: String, restriction: String, value: T) = {
    s"The parameter '$name' must be $restriction: '$value'."
  }

  private def mustBe(name: String, restriction: String) = {
    s"The parameter '$name' must be $restriction."
  }

  private def mustMatch[T](name: String, restriction: String, value: T) = {
    s"The parameter '$name' must match $restriction: '$value'."
  }

  private def mustNotContains[T](name: String, restriction: String) = {
    s"The parameter '$name' must not contains $restriction."
  }

  private def mustNotContains[T](name: String, restriction: String, value: T) = {
    s"The parameter '$name' must not contains $restriction: '$value'."
  }

  private def sizeMustBe[T](name: String, restriction: String, value: T) = {
    s"The length of the parameter '$name' must be $restriction: '$value'."
  }

  private def mustSatisfy[T](name: String, restriction: String, value: T) = {
    s"The parameter '$name' must satisfy restriction $restriction: '$value'."
  }

  def notNull(name: String): String = mustBe(name, "not null")
  def notBlank[T](value: T, name: String): String = mustBe(name, "not null or empty", value)

  def positive[T](value: T, name: String): String = mustBe(name, "positive", value)
  def negative[T](value: T, name: String): String = mustBe(name, "negative", value)

  def nonNegative[T](value: T, name: String): String = mustBe(name, "non-negative", value)
  def nonPositive[T](value: T, name: String): String = mustBe(name, "non-positive", value)

  def regExpr[T](value: T, name: String): String = mustMatch(name, "the regular expression", value)

  def numberOfType[T](value: String, typeClass: Class[T], name: String): String =
    mustBe(name, s"number of type '${typeClass.getName}'", value)

  def sizeMustBeEqual[T](value: T, name: String, size: Int): String =
    sizeMustBe(name, s"equals '$size'", value)

  def sizeMustBeInRange[T](value: T, name: String, from: Int, until: Int): String =
    sizeMustBe(name, s"in range from '$from' to '$until'", value)

  def sizeMustBeGtEq[T](value: T, name: String, from: Int): String =
    sizeMustBe(name, s"great than or equal '$from'", value)

  def sizeMustBeLt[T](value: T, name: String, until: Int): String =
    sizeMustBe(name, s"less than '$until'", value)

  def numberMustBeEqual[T](value: T, name: String, number: T): String = mustBe(name, s"equal $number", value)

  def numberMustBeGt[T](value: T, name: String)(leftBound: T, leftBoundType: BoundTypes.BoundType): String = {
    val operator = leftBoundType match {
      case BoundTypes.Exclusive => "<"
      case BoundTypes.Inclusive => "<="
    }

    mustSatisfy(name, s"$leftBound $operator $value", value)
  }

  def numberMustBeLt[T](value: T, name: String)(rightBound: T, rightBoundType: BoundTypes.BoundType): String = {
    val operator = rightBoundType match {
      case BoundTypes.Exclusive => ">"
      case BoundTypes.Inclusive => ">="
    }

    mustSatisfy(name, s"$rightBound $operator $value", value)
  }

  def mustContainOnlyOneOf[T](seq: Iterable[T], name: String, allowDups: Boolean): String = {
    s"The parameter '$name' must contains one and only one of $seq (${dupsString(allowDups)})"
  }

  def mustContainAtLeastOneOf[T](seq: Iterable[T], name: String, allowDups: Boolean): String = {
    s"The parameter '$name' must contains at least one of $seq (${dupsString(allowDups)})"
  }

  private def dupsString(allowDups: Boolean): String = s"with${if (!allowDups) "" else "out"}"
}
