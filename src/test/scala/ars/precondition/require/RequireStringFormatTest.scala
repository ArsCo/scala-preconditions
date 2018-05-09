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

/** Tests for [[RequireStringFormat]].
  *
  * @author Arsen Ibragimov (ars)
  * @since 0.0.4
  */
class RequireStringFormatTest extends AbstractBaseTest with BeforeAndAfterEach {

  var requireStringFormat: RequireStringFormat = _

  val fieldName = "fieldName"

  override protected def beforeEach(): Unit = {
    requireStringFormat = new RequireStringFormat {}
  }

  "RequireStringFormat" must "be instance of RequireCore" in {
    assert(requireStringFormat.isInstanceOf[RequireCore])
  }

  "requireEmail" must "throws IAF if `email` is incorrect (non-international format)" in {
    val r = requireStringFormat
    import r._

    intercept[IllegalArgumentException] {
      requireEmail("abcd234235ddfgw.tyyy.com", fieldName)
    }

    intercept[IllegalArgumentException] {
      requireEmail("ABC234235@фываfgw.tyyy.com", fieldName)
    }

    intercept[IllegalArgumentException] {
      requireEmail("abcd234235@фываfgw.tyyy.com", fieldName)
    }
    intercept[IllegalArgumentException] {
      requireEmail("", fieldName)
    }
    intercept[IllegalArgumentException] {
      requireEmail("abcdef", fieldName)
    }
  }

  it must "do nothing otherwise" in {
    val r = requireStringFormat
    import r._

    requireEmail("abcdef.sdf@ddfgw.ru")
    requireEmail("abcdef.sdf@ddfgw.ru", fieldName)
    requireEmail("abcdef.sdf@ddfgw.tyyy.com", fieldName)
    requireEmail("abcd234235@ddfgw.tyyy.com", fieldName)
  }

  it must "validate args" in {
    val r = requireStringFormat
    import r._

    intercept[RuntimeException] {
      requireEmail(null, fieldName)
    }

    requireEmail("abcdef.sdf@ddfgw.ru", fieldName) // lazy evaluation
    intercept[RuntimeException] {
      requireEmail("abcd234235@фываfgw.tyyy.com", null)
    }
  }

  "requireUuid" must "throws IAF if `uuid` is incorrect" in {
    val r = requireStringFormat
    import r._

    intercept[IllegalArgumentException] {
      requireUuid("837ab3836ab4-9273-38c3-274abc742932", fieldName)
    }
    intercept[IllegalArgumentException] {
      requireUuid("837ab38h-6ab4-9273-38c3-274abc742932", fieldName)
    }
    intercept[IllegalArgumentException] {
      requireUuid("837ab383-6ab4-9273-38c3-274abc742932d", fieldName)
    }
    intercept[IllegalArgumentException] {
      requireUuid("837ab383-6ab4-927z-38c3-274abc742932", fieldName)
    }
  }

  it must "do nothing otherwise" in {
    val r = requireStringFormat
    import r._

    requireUuid("837ab383-6ab4-9273-38c3-274abc742932")
    requireUuid("837ab383-6ab4-9273-38c3-274abc742932", fieldName)
  }

  it must "validate args" in {
    val r = requireStringFormat
    import r._

    intercept[RuntimeException] {
      requireUuid(null, fieldName)
    }

    requireUuid("837ab383-6ab4-9273-38c3-274abc742932", fieldName) // lazy evaluation
    intercept[RuntimeException] {
      requireUuid("837ab383-6ab4-9273-38c3-274abc742932d", null)
    }
  }
}
