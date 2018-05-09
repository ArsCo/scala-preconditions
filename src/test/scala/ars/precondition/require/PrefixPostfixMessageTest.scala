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
import org.scalatest.BeforeAndAfterEach

/** Tests for [[PrefixPostfixMessage]].
  *
  * @author Arsen Ibragimov (ars)
  * @since 0.0.4
  */
class PrefixPostfixMessageTest extends AbstractBaseTest with BeforeAndAfterEach {

  var prefixPostfixMessage: PrefixPostfixMessage = _

  val fieldName = "fieldName"

  override protected def beforeEach(): Unit = {
    prefixPostfixMessage = new PrefixPostfixMessage {}
  }

  "PrefixPostfixMessage" must "be instance of RequireCore" in {
    assert(prefixPostfixMessage.isInstanceOf[RequireCore])
  }

  it must "wrap message with prefix and postfix" in {
    class TestPrefixPostfixMessage extends PrefixPostfixMessage {
      override def prefix: String = "PREFIX_"
      override def postfix: String = "_POSTFIX"
    }

    val t = new TestPrefixPostfixMessage
    assertResult("PREFIX_MESSAGE_POSTFIX")(t.failureMessage("MESSAGE"))
  }

  "prefix" must "be empty by default" in {
    assertResult("")(prefixPostfixMessage.prefix)
  }

  "postfix" must "be empty by default" in {
    assertResult("")(prefixPostfixMessage.postfix)
  }



}
