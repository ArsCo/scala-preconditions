package ars.precondition

import java.util.regex.Pattern

import scala.collection.Iterable
import scala.math.Numeric
import scala.util.matching.Regex

/** Predicates.
  *
  * @author ars (Arsen Ibragimov)
  * @since 0.0.1
  */
object Predicates {

  private final val EmailPattern = "^(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])$".r
  private final val UuidPattern = "^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$".r

  private def isNumberString(value: String, convert: String => AnyVal) = {
    try { convert(value); true }
    catch { case _: NumberFormatException => false }
  }

  object BoundTypes extends Enumeration {
    type BoundType = Value

    val Inclusive = Value
    val Exclusive = Value
  }

  def isNotNull[T <: AnyRef](value: T): Boolean = value != null
  def isNotBlank(value: String): Boolean = isNotNull(value) && value.trim.nonEmpty
  def isNotBlank[T](value: Iterable[T]): Boolean = isNotNull(value) && value.nonEmpty
  def isMatchPattern(value: String, pattern: Pattern): Boolean = pattern.matcher(value).matches()

  def isMatchPattern(value: String, regex: Regex): Boolean = {
    value match {
      case regex() => true
      case _ => false
    }
  }

  def isByteNumber(value: String): Boolean = isNumberString(value, _.toByte)
  def isShortNumber(value: String): Boolean = isNumberString(value, _.toShort)
  def isIntNumber(value: String): Boolean = isNumberString(value, _.toInt)
  def isLongNumber(value: String): Boolean = isNumberString(value, _.toLong)
  def isFloatNumber(value: String): Boolean = isNumberString(value, _.toFloat)
  def isDoubleNumber(value: String): Boolean = isNumberString(value, _.toDouble)

  def isSize[T](value: Iterable[T], size: Int): Boolean = value.size == size
  def isSizeFrom[T](value: Iterable[T], from: Int): Boolean = from <= value.size
  def isSizeUntil[T](value: Iterable[T], until: Int): Boolean = value.size < until
  def isSizeInRange[T](value: Iterable[T], from: Int, until: Int): Boolean = {
    isSizeFrom(value, from) && isSizeUntil(value, until)
  }


  def isNumber[@specialized T: Numeric](value: T, number: T): Boolean = {
    val n = implicitly[Numeric[T]]
    n.eq(value, number)
  }

  def isNumberFrom[@specialized T: Numeric](value: T)
                  (leftBound: T, leftBoundType: BoundTypes.BoundType = BoundTypes.Inclusive): Boolean = {
    val n = implicitly[Numeric[T]]
    leftBoundType match {
      case BoundTypes.Exclusive => n.lt(leftBound, value)
      case BoundTypes.Inclusive => n.lteq(leftBound,value)
    }
  }

  def isNumberUntil[@specialized T: Numeric](value: T)
                   (rightBound: T, rightBoundType: BoundTypes.BoundType = BoundTypes.Exclusive): Boolean = {
    val n = implicitly[Numeric[T]]
    rightBoundType match {
      case BoundTypes.Exclusive => n.lt(value, rightBound)
      case BoundTypes.Inclusive => n.lteq(value, rightBound)
    }
  }

  def isNumberInBounds[@specialized T: Numeric](value: T)
                      (leftBound: T, leftBoundType: BoundTypes.BoundType = BoundTypes.Inclusive)
                      (rightBound: T, rightBoundType: BoundTypes.BoundType = BoundTypes.Exclusive): Boolean = {
    isNumberFrom(value)(leftBound, leftBoundType) && isNumberUntil(value)(rightBound, rightBoundType)
  }

  def isOnlyOneOf[T](value: Iterable[T], seq: Iterable[T], allowDups: Boolean = false): Boolean = {
    val valueSet = value.toSet
    val seqSet = seq.toSet
    val intersection = seqSet.intersect(valueSet)

    val isContains = intersection.size == 1
    if (!isContains) return false

    val isDupsCorrect = if (allowDups) true else value.count(_ == intersection.head) == 1
    isDupsCorrect
  }

  def isAtLeastOneOf[T](value: Iterable[T], seq: Iterable[T], allowDups: Boolean = false): Boolean = {
    val valueSet = value.toSet
    val seqSet = seq.toSet
    val intersection = seqSet.intersect(valueSet)
    val isContains = intersection.nonEmpty
    if (!isContains) return false

    val isDupsCorrect =
      if (allowDups) true
      else intersection.map(i => value.count(_ == i)).forall(_ == 1)

    isDupsCorrect
  }

  def isCorrectEmail(email: String): Boolean = isMatchPattern(email, EmailPattern)
  def isCorrectUuid(guid: String): Boolean = isMatchPattern(guid, UuidPattern)

}
