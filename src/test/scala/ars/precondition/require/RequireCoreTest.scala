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
import ars.precondition.require.RequireCore._
import org.scalatest.BeforeAndAfterEach

/** Tests for [[RequireCore]].
  *
  * @author Arsen Ibragimov (ars)
  * @since 0.0.4
  */
class RequireCoreTest extends AbstractBaseTest with BeforeAndAfterEach {

  var requireCore: RequireCore = _

  override protected def beforeEach(): Unit = {
    requireCore = new RequireCore {}
  }

  "DefaultExceptionCreator" must "create instance of IAE" in {
    val mes = "Message value"
    val exc = new RuntimeException

    val e = DefaultExceptionCreator(mes, None)
    assert(e.isInstanceOf[IllegalArgumentException])
    assertResult(mes)(e.getMessage)
    assertResult(null)(e.getCause)

    val e1 = DefaultExceptionCreator(mes, Some(exc))
    assert(e1.isInstanceOf[IllegalArgumentException])
    assertResult(mes)(e1.getMessage)
    assertResult(exc)(e1.getCause)
  }

  it must "validate args in" in {
    val mes = "Message value"

    intercept[RuntimeException] {
      DefaultExceptionCreator(null, None)
    }

    intercept[RuntimeException] {
      DefaultExceptionCreator(mes, null)
    }

    intercept[RuntimeException] {
      DefaultExceptionCreator(mes, Some(null))
    }
  }

  "exception()" must "create instance of IAE" in {
    val mes = "Message value"
    val exc = new RuntimeException

    val e = requireCore.exception(mes)
    assert(e.isInstanceOf[IllegalArgumentException])
    assertResult(mes)(e.getMessage)
    assertResult(null)(e.getCause)

    val e0 = requireCore.exception(mes, None)
    assert(e0.isInstanceOf[IllegalArgumentException])
    assertResult(mes)(e0.getMessage)
    assertResult(null)(e0.getCause)

    val e1 = requireCore.exception(mes, Some(exc))
    assert(e1.isInstanceOf[IllegalArgumentException])
    assertResult(mes)(e1.getMessage)
    assertResult(exc)(e1.getCause)
  }

  it must "validate args in" in {
    val rc = requireCore
    import rc._

    val mes = "Message value"

    intercept[RuntimeException] {
      exception(null, None)
    }

    intercept[RuntimeException] {
      exception(mes, null)
    }

    intercept[RuntimeException] {
      exception(mes, Some(null))
    }
  }

  "failureMessage()" must "return toString of message" in {
    val rc = requireCore
    import rc._

    assertResult("5") {
      failureMessage(5)
    }

    assertResult("striiiiing") {
      failureMessage("striiiiing")
    }
  }

  it must "validate args in" in {
    val rc = requireCore
    import rc._

    intercept[RuntimeException] {
      failureMessage(null)
    }
  }

  "fail()" must "throws exception() with failureMessage() message" in {

    intercept[IllegalArgumentException] {
      requireCore.fail("striiiiing")
    }

    val ex = new IllegalStateException()

    intercept[IllegalArgumentException] {
      requireCore.fail("striiiiing", Some(ex))
    }

    try {
      requireCore.fail("striiiiing")
    } catch {
      case e: IllegalArgumentException =>
        assert(e != null)
        assertResult(requireCore.failureMessage("striiiiing"))(e.getMessage)
        assertResult(null)(e.getCause)
      case _: Throwable => fail()
    }

    try {
      requireCore.fail("striiiiing", Some(ex))
    } catch {
      case e: IllegalArgumentException =>
        assert(e != null)
        assertResult(requireCore.failureMessage("striiiiing"))(e.getMessage)
        assertResult(ex)(e.getCause)
      case _: Throwable => fail()
    }
  }

  it must "validate args in" in {

    val mes = "Message value"

    intercept[RuntimeException] {
      requireCore.fail(null, None)
    }

    intercept[RuntimeException] {
      requireCore.fail(mes, null)
    }

    intercept[RuntimeException] {
      requireCore.fail(mes, Some(null))
    }
  }

  "require()" must "throw exception() with failureMessage() message if `requirement` evaluates to `false`" in {
    intercept[IllegalArgumentException] {
      requireCore.require(false, "striiiiing")
    }

    try {
      requireCore.require(false, "striiiiing")
    } catch {
      case e: IllegalArgumentException =>
        assert(e != null)
        assertResult(requireCore.failureMessage("striiiiing"))(e.getMessage)
        assertResult(null)(e.getCause)
      case _: Throwable => fail()
    }
  }

  it must "do nothing if `requirement` evaluates to `true`" in {
    requireCore.require(true, "striiiiing")
  }

  it must "validate args" in {
    requireCore.require(true, null) // lazy `message` evaluation
    intercept[RuntimeException] {
      requireCore.require(false, null)
    }
  }
}
