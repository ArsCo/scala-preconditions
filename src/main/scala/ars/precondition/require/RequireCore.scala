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

import ars.precondition.require.RequireInternal._
import scala.language.implicitConversions


/** `RequireXXX` core functionality. Each `RequireXXX` trait MUST extend this trait
  * and, after checking, delegate work to its methods.
  *
  * @author Arsen Ibragimov (ars)
  * @since 0.0.4
  */
trait RequireCore {

  /**
    * Creates a new instance of exception. Default implementation uses
    * [[RequireCore.DefaultExceptionCreator]] exception creator.
    *
    * @param message the message (must be non-blank)
    * @param cause the cause exception (must be non-null and not `Some(null)`)
    *
    * @throws RuntimeException if any argument is not valid
    *
    * @return new exception instance (non-null)
    */
  def exception(message: String, cause: Option[RuntimeException] = None): RuntimeException = {
    RequireCore.DefaultExceptionCreator(message, cause)
  }

  /**
    * Constructs the failure message. Default implementation invokes `toString()` method of `message`
    * and returns result.
    *
    * @param message the source message (must be non-null)
    *
    * @throws RuntimeException if `message` is null
    *
    * @return the failure message (non-null)
    */
  def failureMessage[T](message: => T): String = {
    val m = message // evaluate once
    requireNonNullMessage(m)
    m.toString
  }

  /**
    * Fails test. Must be invoked at every time when `requireXXX` method fails. Default implementation
    * invokes `exception()` to create new exception instance with message produced by `failureMessage()` method
    * and throws the result.
    *
    * @param message the `String` to include in the failure message (must be non-null)
    *
    * @throws RuntimeException at every invokation
    */
  def fail(message: => Any, throwable: Option[RuntimeException] = None): Unit =
    throw exception(failureMessage(message), throwable)

  /**
    * Invokes `requirement` to test an expression and calls [[fail]] if it returns `false`.
    *
    * @param requirement the expression to test
    * @param message a `String` to include in the failure message (must be non-null)
    *
    * @throws RuntimeException if `requirement` invokation returns `false` of any argument is not valid
    */
  def require[T](requirement: => Boolean, message: => T): Unit = {
    if (!requirement) fail(message)
  }

  /**
    * Wraps predicate [[RequirePredicate]] to element function [[RequireElementFunction]].
    *
    * @param predicate the predicate (must be non-null)
    *
    * @tparam T the type of element
    *
    * @return the new [[RequireElementFunction]] instance
    */
  implicit def pred2elem[T](predicate: RequirePredicate[T]): RequireElementFunction[T] = {
    (value: T, name: String, index: Int) =>
      require(predicate(value), s"Incorrect value of parameter `$name($index)`")
  }

  /**
    * Wraps predicate [[RequirePredicate]] to element function [[RequireFunction]].
    *
    * @param predicate the predicate (must be non-null)
    *
    * @tparam T the type of element
    *
    * @return the new [[RequireFunction]] instance
    */
  implicit def pred2func[T](predicate: RequirePredicate[T]): RequireFunction[T] = {
    (value: T, name: String) =>
      require(predicate(value), s"Incorrect value of parameter `$name`")
  }
}

object RequireCore {

  /**
    * Exception creator. Creates new instances of exceptions
    */
  type ExceptionCreator = (String, Option[RuntimeException]) => RuntimeException

  /**
    * Default implementation of exception creator.
    * Each call creates a new instance of [[IllegalArgumentException]].
    */
  val DefaultExceptionCreator: ExceptionCreator =
    (message: String, exception: Option[RuntimeException]) => {
      requireMessage(message)
      requireException(exception)

      exception.map(new IllegalArgumentException(message, _)).getOrElse(new IllegalArgumentException(message))
    }
}
