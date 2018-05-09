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

import java.util.regex.Pattern


/** Tests for [[RequireUtils]].
  *
  * @author Arsen Ibragimov (ars)
  * @since 0.0.3
  */
class RequireUtilsTest extends AbstractBaseTest {

//  val fieldName = "fieldName"
//  val message = "message"
//
//  "prefix" must "be empty by default" in {
//    assert(prefix == "")
//  }
//
//  "postfix" must "be empty by default" in {
//    assert(postfix == "")
//  }
//
//
//
//  "require()" must "by default throw IAE if requirement is false" in {
//    val e = intercept[IllegalArgumentException] {
//      require(false, message)
//    }
//    assert(e.getMessage.contains(message))
//  }
//
//  it must "by dafault returns immediately if reauirement is true" in {
//    require(true, message)
//  }
//
//  "requireNotNull()" must "tests that value is not null and throws IAE otherwise" in {
//    requireNotNull("4444", fieldName)
//    requireNotNull("5555")
//
//    intercept[IllegalArgumentException] {
//      requireNotNull(null, fieldName)
//    }
//
//    intercept[IllegalArgumentException] {
//      requireNotNull(null, null)
//    }
//
//    intercept[IllegalArgumentException] {
//      requireNotNull(null)
//    }
//  }
//




//

//

//

//

//
//  "requireAll" must "apply function to every Iterable element" in {
//    val n = "name"
//    val s = Seq(1, 4, 5, 7)
//    requireAll(s, n)((v: Int, n: String) => require(v > 0, s"Bad value `$n`"))
//
//
//  }
//
//
//  "requireNotContainsNull" must "tests that no null values in sequence" in {
//    requireAllNotNull(Seq(), "seq")
//    requireAllNotNull(Seq("5", "6", "7"), "seq")
//
//    intercept[IllegalArgumentException] {
//      requireAllNotNull(Seq(null), "seq")
//    }
//
//    intercept[IllegalArgumentException] {
//      requireAllNotNull(Seq("5", null), "seq")
//    }
//
//    intercept[IllegalArgumentException] {
//      requireAllNotNull(Seq(null, "5"), "seq")
//    }
//
//    intercept[IllegalArgumentException] {
//      requireAllNotNull(Seq(null, "5", null), "seq")
//    }
//
//    intercept[IllegalArgumentException] {
//      requireAllNotNull(Seq("5", null, "6", null), "seq")
//    }
//
//    intercept[IllegalArgumentException] {
//      requireAllNotNull(Seq("5", "6", null), "seq")
//    }
//  }

}
