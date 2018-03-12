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

  def onlyOneOf[T](value: Iterable[T], seq: Iterable[T], allowDups: Boolean = false): Boolean = {
    val valueSet = value.toSet
    val seqSet = seq.toSet
    val intersection = seqSet.intersect(valueSet)

    val isContains = intersection.size == 1
    if (!isContains) return false

    val isDupsCorrect = if (allowDups) true else value.count(_ == intersection.head) == 1
    isDupsCorrect
  }

  def atLeastOneOf[T](value: Iterable[T], seq: Iterable[T], allowDups: Boolean = false): Boolean = {
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

}
