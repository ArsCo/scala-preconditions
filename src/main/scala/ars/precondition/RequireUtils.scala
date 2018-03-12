package ars.precondition

import java.util.UUID

import ars.precondition.Messages._
import ars.precondition.Predicates.BoundTypes.{Exclusive, Inclusive}
import ars.precondition.Predicates._

import scala.collection.Iterable
import scala.math.Numeric
import scala.util.matching.Regex

/** Validation utility methods.
  *
  * @author ars (Arsen Ibragimov)
  * @since 0.0.1
  */
object RequireUtils {

  /**
    * @return the prefix of any `requireXXX` method failure message string
    */
  @inline def prefix: String = ""

  /**
    * @return the postfix of any `requireXXX` method failure message string
    */
  @inline def postfix: String = ""

  /**
    * Builds the failure message of any `requireXXX` method. Default implementation concatenates
    * prefix returned by [[RequireUtils#prefix]] method followed by `message` followed by postfix
    * returned by [[RequireUtils#postfix]] method.
    *
    * Override this implementation if you want to intercept or change rules of building failure messages.
    *
    * @param message the source message
    *
    * @return the failure message
    */
  @inline def failureMessage(message: => Any): String = prefix + message + postfix

  /**
    * Fails test. Must be invoked at every time when `requireXXX` method fails. Default implementation
    * throws an `IllegalArgumentException` with message built by [[RequireUtils#failureMessage]] method.
    *
    * Override this implementation if you want another failure behaviour of all `requireXXX` methods of this objects.
    *
    * For example if you want to throw `MyApiException` use this code:
    * {{{
    *   override def fail(message: => Any) = {
    *     throw new MyApiException(failureMessage(message))
    *   }
    * }}}
    *
    * @param message a `String` to include in the failure message
    */
  @inline def fail(message: => Any): Unit = {
    throw new IllegalArgumentException(failureMessage(message))
  }

  /**
    * Tests an expression, invoking [[RequireUtils#fail]] if false.
    *
    *  @param requirement the expression to test
    *  @param message a `String` to include in the failure message
    */
  @inline def require(requirement: Boolean, message: => Any): Unit = {
    if (!requirement) fail(message)
  }

  /**
    * Tests that `value` isn't `null`, and otherwise throws `IllegalArgumentException`.
    *
    * @param value the value to test
    * @param name the name to include in the failure message
    */
  @inline def requireNotNull(value: AnyRef, name: String = NoNameParameter): Unit = {
    require(isNotNull(value), notNull(name))
  }

  /**
    * Tests that `value` isn't `null` or empty (after `trim`), and otherwise throws `IllegalArgumentException`.
    *
    * @param value the value to test
    * @param name the name to include in the failure message
    */
  @inline def requireNotBlank(value: String, name: String = NoNameParameter): Unit = {
    require(isNotBlank(value), notBlank(value, name))
  }

  /**
    * Tests that sequence `value` isn't `null` or empty, and otherwise throws `IllegalArgumentException`.
    *
    * @param value the iterable to test
    * @param name the name to include in the failure message
    *
    * @tparam T the type of iterable elements
    */
  @inline def requireNotBlank[T](value: Iterable[T], name: String): Unit = {
    require(isNotBlank(value), notBlank(value, name))
  }

  /**
    * Tests that sequence `value` isn't `null` or empty, and otherwise throws `IllegalArgumentException`.
    *
    * @param value the iterable to test
    *
    * @tparam T the type of iterable elements
    */
  @inline def requireNotBlank[T](value: Iterable[T]): Unit = {
    requireNotBlank[T](value, NoNameParameter)
  }

  /**
    * Tests that `value` is positive, and otherwise throws `IllegalArgumentException`.
    *
    * @param value the value to test
    * @param name the name to include in the failure message
    */
  @inline def requirePositive[@specialized T: Numeric](value: T, name: String = NoNameParameter): Unit = {
    val n = implicitly[Numeric[T]]
    require(n.gt(value, n.zero), positive(value, name))
  }

  /**
    * Tests that `value` is positive or zero, and otherwise throws `IllegalArgumentException`.
    *
    * @param value the value to test
    * @param name the name to include in the failure message
    */
  @inline def requireNonNegative[@specialized T: Numeric](value: T, name: String = NoNameParameter): Unit = {
    val n = implicitly[Numeric[T]]
    require(n.gteq(value, n.zero), nonNegative(value, name))
  }

  /**
    * Tests that `value` is negative, and otherwise throws `IllegalArgumentException`.
    *
    * @param value the value to test
    * @param name the name to include in the failure message
    */
  @inline def requireNegative[@specialized T: Numeric](value: T, name: String = NoNameParameter): Unit = {
    val n = implicitly[Numeric[T]]
    require(n.lt(value, n.zero), negative(value, name))
  }

  /**
    * Tests that `value` is negative or zero, and otherwise throws `IllegalArgumentException`.
    *
    * @param value the value to test
    * @param name the name to include in the failure message
    */
  @inline def requireNonPositive[@specialized T: Numeric](value: T, name: String = NoNameParameter): Unit = {
    val n = implicitly[Numeric[T]]
    require(n.lteq(value, n.zero), nonNegative(value, name))
  }

//  /**
//    * Tests that `value` matches regular expression pattern, and otherwise throws `IllegalArgumentException`.
//    *
//    * @param value the value to test
//    * @param pattern the pattern to test
//    * @param name the name to include in the failure message
//    */
//  @inline def requirePattern(value: String, pattern: Pattern, name: String = NoNameParameter): Unit = {
//    require(isMatchPattern(value, pattern), regExpr(value, name))
//  }

  /**
    * Tests that `value` matches regular expression pattern, and otherwise throws `IllegalArgumentException`.
    *
    * @param value the value to test
    * @param pattern the pattern to test
    * @param name the name to include in the failure message
    */
  @inline def requirePattern(value: String, pattern: Regex, name: String = NoNameParameter): Unit = {
    require(isMatchPattern(value, pattern), regExpr(value, name))
  }

  /**
    * Tests that `value` is a `Byte` number string, and otherwise throws `IllegalArgumentException`.
    *
    * @param value the value to test
    * @param name the name to include in the failure message
    */
  @inline def requireByte(value: String, name: String = NoNameParameter): Unit = {
    require(isByteNumber(value), numberOfType(value, classOf[Byte], name))
  }

  /**
    * Tests that `value` is a `Short` number string, and otherwise throws `IllegalArgumentException`.
    *
    * @param value the value to test
    * @param name the name to include in the failure message
    */
  @inline def requireShort(value: String, name: String = NoNameParameter): Unit = {
    require(isShortNumber(value), numberOfType(value, classOf[Short], name))
  }

  /**
    * Tests that `value` is an `Int` number string, and otherwise throws `IllegalArgumentException`.
    *
    * @param value the value to test
    * @param name the name to include in the failure message
    */
  @inline def requireInt(value: String, name: String = NoNameParameter): Unit = {
    require(isIntNumber(value), numberOfType(value, classOf[Int], name))
  }

  /**
    * Tests that `value` is a `Long` number string, and otherwise throws `IllegalArgumentException`.
    *
    * @param value the value to test
    * @param name the name to include in the failure message
    */
  @inline def requireLong(value: String, name: String = NoNameParameter): Unit = {
    require(isLongNumber(value), numberOfType(value, classOf[Long], name))
  }

  /**
    * Tests that `value` is a `Float` number string, and otherwise throws `IllegalArgumentException`.
    *
    * @param value the value to test
    * @param name the name to include in the failure message
    */
  @inline def requireFloat(value: String, name: String = NoNameParameter): Unit = {
    require(isFloatNumber(value), numberOfType(value, classOf[Float], name))
  }

  /**
    * Tests that `value` is a `Double` number string, and otherwise throws `IllegalArgumentException`.
    *
    * @param value the value to test
    * @param name the name to include in the failure message
    */
  @inline def requireDouble(value: String, name: String = NoNameParameter): Unit = {
    require(isDoubleNumber(value), numberOfType(value, classOf[Double], name))
  }

  /**
    * Takes the element value, the name and the index as parameters and invokes
    * [[RequireUtils#fail]] if requirements isn't satisfied.
    *
    * @tparam T the type of element
    */
  type RequireElementFunction[T] = (T, String, Int) => Unit

  /**
    * Takes the value and the name as parameters and invokes
    * [[RequireUtils#fail]] if requirements isn't satisfied.
    *
    * @tparam T the type of element
    */
  type RequireFunction[T] = (T, String) => Unit

  /**
    * Translates [[RequireFunction]] (for example [[[RequireUtils.requireNotBlank(value:String*]]]
    * or [[RequireUtils#requireNotNull]]) to [[RequireElementFunction]] to use with
    * [[RequireUtils#requireAll]] method.
    *
    * Default implementation concatenates name and index in string `name(index)`.
    *
    * @param f the function to translate
    *
    * @tparam T the type of iterable elements
    *
    * @return the translated function
    */
  @inline def toRequireElementFunction[T](f: RequireFunction[T]): RequireElementFunction[T] = {
    (element: T, name: String, index: Int) => f(element, s"$name($index)")
  }

  /**
    * Tests that all elements of iterable `value` satisfy the `predicate`, and
    * otherwise throws `IllegalArgumentException`.
    *
    * @param value the iterable value
    * @param name the name to include in the failure message
    * @param require the function
    *
    * @tparam T the type of iterable elements
    */
  @inline def requireAll[T](value: Iterable[T], name: String = NoNameParameter)
                           (require: RequireElementFunction[T]): Unit = {
    var i = 0
    value.foreach { element =>
      require(element, name, i)
      i += 1
    }
  }

  /**
    * Tests that iterable `value` does not contain `null` values, and
    * otherwise throws `IllegalArgumentException`.
    *
    * @param value the iterable value
    * @param name the name to include in the failure message
    *
    * @tparam T the type of iterable elements
    */
  @inline def requireAllNotNull[T <: AnyRef](value: Iterable[T], name: String = NoNameParameter): Unit = {
    requireAll(value, name)(toRequireElementFunction(requireNotNull))
  }

  /**
    * Tests that iterable `value` does not contain blank (`null` or empty) strings, and
    * otherwise throws `IllegalArgumentException`.
    *
    * @param value the iterable value
    * @param name the name to include in the failure message
    */
  @inline def requireAllNotBlank(value: Iterable[String], name: String = NoNameParameter): Unit = {
    requireAll(value, name)(toRequireElementFunction(requireNotBlank))
  }

  /**
    * Tests that size of iterable `value` equals expected `size`, and otherwise throws `IllegalArgumentException`.
    *
    * @param value the iterable value
    * @param size the expected size
    * @param name the name to include in the failure message
    *
    * @tparam T the type of iterable elements
    */
  @inline def requireSize[T](value: Iterable[T], size: Int, name: String = NoNameParameter): Unit = {
    require(isSize(value, size), sizeMustBeEqual(value, name, size))
  }

  /**
    * Tests that size of iterable `value` is from `from` inclusive until `until` exclusive,
    * and otherwise throws `IllegalArgumentException`.
    *
    * @param value the iterable value
    * @param from the minimum size value (inclusive)
    * @param until the maximum size value (exclusive)
    * @param name the name to include in the failure message
    *
    * @tparam T the type of iterable elements
    */
  @inline def requireSizeInRange[T](value: Iterable[T], from: Int, until: Int, name: String = NoNameParameter): Unit = {
    require(isSizeInRange(value, from, until), sizeMustBeInRange(value, name, from, until))
  }

  /**
    * Tests that size of iterable `value` is from `from` inclusive until [[Int.MaxValue]],
    * and otherwise throws `IllegalArgumentException`.
    *
    * @param value the iterable value
    * @param from the minimum size value (inclusive)
    * @param name the name to include in the failure message
    *
    * @tparam T the type of iterable elements
    */
  @inline def requireSizeFrom[T](value: Iterable[T], from: Int, name: String = NoNameParameter): Unit = {
    require(isSizeFrom(value, from), sizeMustBeGtEq(value, name, from))
  }

  /**
    * Tests that size of iterable `value` is from `0` inclusive until `until` exclusive,
    * and otherwise throws `IllegalArgumentException`.
    *
    * @param value the iterable value
    * @param until the maximum size value (exclusive)
    * @param name the name to include in the failure message
    *
    * @tparam T the type of iterable elements
    */
  @inline def requireSizeUntil[T](value: Iterable[T], until: Int, name: String = NoNameParameter): Unit = {
    require(isSizeUntil(value, until), sizeMustBeLt(value, name, until))
  }

  /**
    * Test that `value` is equal to `number`, and otherwise throws `IllegalArgumentException`.
    *
    * @param value the value
    * @param number the required number
    *
    * @tparam T the type of iterable elements
    */
  @inline def requireNumber[@specialized T: Numeric](value: T, name: String = NoNameParameter)(number: T): Unit = {
    require(isNumber(value, number), numberMustBeEqual(value, name, number))
  }

  /**
    * Tests that `value` is from `leftBound` with bound type `leftBoundType`
    * until maximum value of type `T` (inclusive).
    *
    * @param value the value
    * @param leftBound the left bound
    * @param leftBoundType the left bound type
    *
    * @tparam T the type of value
    */
  @inline def requireNumberFrom[@specialized T: Numeric]
                               (value: T, name: String = NoNameParameter)
                               (leftBound: T, leftBoundType: BoundTypes.BoundType = Inclusive): Unit = {
    requireNotNull(leftBoundType, "leftBoundType")
    require(isNumberFrom(value)(leftBound, leftBoundType), numberMustBeGt(value, name)(leftBound, leftBoundType))
  }

  /**
    * Tests that `value` is from minimum value of type `T` (inclusive)
    * until `rightBound` with bound type `rightBoundType`.
    *
    * @param value the value
    * @param rightBound the right bound
    * @param rightBoundType the right bound type
    *
    * @tparam T the type of value
    */
  @inline def requireNumberUntil[@specialized T: Numeric]
                                (value: T, name: String = NoNameParameter)
                                (rightBound: T, rightBoundType: BoundTypes.BoundType = Exclusive): Unit = {
    requireNotNull(rightBoundType, "rightBoundType")
    require(isNumberUntil(value)(rightBound, rightBoundType), numberMustBeLt(value, name)(rightBound, rightBoundType))
  }

  /**
    * Tests that `value` is from `leftBound` (exclusive) until maximum value of type `T` (inclusive).
    *
    * @param value the value
    * @param leftBound the left bound
    *
    * @tparam T the type of value
    */
  @inline def requireNumberFromExclusive[@specialized T: Numeric]
                                        (value: T, name: String = NoNameParameter)(leftBound: T): Unit = {
    requireNumberFrom(value, name)(leftBound, Exclusive)
  }

  /**
    * Tests that `value` is from `leftBound` with bound type `leftBoundType`
    * until `rightBound` with bound type `rightBoundType`.
    *
    * @param value the value
    * @param leftBound the left bound
    * @param leftBoundType the left bound type
    * @param rightBound the right bound
    * @param rightBoundType the right bound type
    *
    * @tparam T the type of value
    */
  @inline def requireNumberInRange[@specialized T: Numeric]
                                  (value: T, name: String = NoNameParameter)
                                  (leftBound: T, leftBoundType: BoundTypes.BoundType = Inclusive)
                                  (rightBound: T, rightBoundType: BoundTypes.BoundType = Exclusive): Unit = {
    requireNumberFrom(value, name)(leftBound, leftBoundType)
    requireNumberUntil(value, name)(rightBound, rightBoundType)
  }

  /**
    * Tests that `value` is from `leftBound` (exclusive) until `rightBound` (exclusive).
    *
    * @param value the value
    * @param leftBound the left bound
    * @param rightBound the right bound
    *
    * @tparam T the type of value
    */
  @inline def requireNumberInterval[@specialized T: Numeric]
                                   (value: T, name: String = NoNameParameter)
                                   (leftBound: T, rightBound: T): Unit = {
    requireNumberInRange(value, name)(leftBound, Exclusive)(rightBound, Exclusive)
  }

  /**
    * Tests that `value` is from `leftBound` (inclusive) until `rightBound` (inclusive).
    *
    * @param value the value
    * @param leftBound the left bound
    * @param rightBound the right bound
    *
    * @tparam T the type of value
    */
  @inline def requireNumberSegment[@specialized T: Numeric]
                                  (value: T, name: String = NoNameParameter)
                                  (leftBound: T, rightBound: T): Unit = {
    requireNumberInRange(value, name)(leftBound, Inclusive)(rightBound, Inclusive)
  }

  /**
    * Invokes `require` function if `value` is Some(_) and do nothing otherwise.
    *
    * @param value the value
    * @param require the function
    *
    * @tparam T the type of value
    */
  @inline def optional[T](value: Option[T])(require: T => Unit): Unit = {
    value.foreach(require)
  }


  /**
    * Tests that one and only one of elems of `seq` is contained in `value`
    *
    * @param value the value
    * @param name the name to include in the failure message
    * @param seq the sequence of values
    * @param allowDups is allow duplicates
    *
    * @tparam T the type of value
    */
  @inline def requireOnlyOneOf[T](value: Iterable[T], name: String = NoNameParameter)
                                 (seq: Iterable[T], allowDups: Boolean = false): Unit = {
    require(
      isOnlyOneOf(value, seq, allowDups),
      mustContainOnlyOneOf(seq, name, allowDups)
    )
  }

  /**
    * Tests that at least one of elems of `seq` is contained in `value`
    *
    * @param value the value
    * @param name the name to include in the failure message
    * @param seq the sequence of values
    * @param allowDups is allow duplicates
    *
    * @tparam T the type of value
    */
  @inline def requireAtLeastOneOf[T](value: Iterable[T], name: String = NoNameParameter)
                                    (seq: Iterable[T], allowDups: Boolean = false): Unit = {
    require(
      isAtLeastOneOf(value, seq, allowDups),
      mustContainAtLeastOneOf(seq, name, allowDups)
    )
  }

  /**
    * Tests that string `email` is a correct email address.
    *
    * @param email the testing email string
    * @param name the name to include in the failure message
    */
  @inline def requireEmail(email: String, name: String = NoNameParameter): Unit = {
    require(isCorrectEmail(email), mustBeCorrectEmail(email, name))
  }

  /**
    * Tests that string `uuid` is a correct universally unique identifier (UUID).
    *
    * @param uuid the testing uuid string
    * @param name the name to include in the failure message
    */
  @inline def requireUuid(uuid: String, name: String = NoNameParameter): Unit = {
    require(isCorrectUuid(uuid),  mustBeCorrectUuid(uuid, name))
  }


}
