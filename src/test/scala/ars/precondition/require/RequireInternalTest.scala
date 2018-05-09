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
import ars.precondition.require.RequireInternal._

/** Tests for [[RequireInternal]].
  *
  * @author Arsen Ibragimov (ars)
  * @since 0.0.4
  */
class RequireInternalTest extends AbstractBaseTest {
  "requireMessage" must "throws RuntimeException if `message` is blank" in {
    intercept[RuntimeException] {
      requireMessage(null)
    }

    intercept[RuntimeException] {
      requireMessage("")
    }

    intercept[RuntimeException] {
      requireMessage("  ")
    }
  }

  it must "do nothing if message is not blank" in {
    requireMessage("asdfaf")
  }

  "requireException" must "throws RuntimeException if `exception` is null or `Some(null)`" in {
    intercept[RuntimeException] {
      requireException(null)
    }
    intercept[RuntimeException] {
      requireException(Some(null))
    }
  }

  it must "do nothing otherwise" in {
    requireException(None)
    requireException(Some(new RuntimeException))
  }

  "requireNonNullMessage" must "throws RuntimeException if `message` is null" in {
    intercept[RuntimeException] {
      requireNonNullMessage(null)
    }
  }

  it must "do nothing otherwise" in {
    requireNonNullMessage(5)
    requireNonNullMessage("sadfsaf")
    requireNonNullMessage("")
    requireNonNullMessage(" ")
  }

  "requireName" must "throws RuntimeException if `name` is blank" in {
    intercept[RuntimeException] {
      requireName(null)
    }

    intercept[RuntimeException] {
      requireName("")
    }

    intercept[RuntimeException] {
      requireName("   ")
    }
  }

  it must "do nothing otherwise" in {
    requireName("sdafsdf")
  }

  "requireRestriction" must "throws RuntimeException if `restriction` is blank" in {
    intercept[RuntimeException] {
      requireRestriction(null)
    }

    intercept[RuntimeException] {
      requireRestriction("")
    }

    intercept[RuntimeException] {
      requireRestriction("   ")
    }
  }

  it must "do nothing otherwise" in {
    requireRestriction("sdafsdf")
  }

  "requireSizeFrom" must "throws RuntimeException if `size` < 0" in {
    intercept[RuntimeException] {
      requireSizeFrom(-4)
    }

    intercept[RuntimeException] {
      requireSizeFrom(-1)
    }
  }

  it must "do nothing otherwise" in {
    requireSizeFrom(0)
    requireSizeFrom(1)
    requireSizeFrom(100)
  }

  "requireSizeUntil" must "throws RuntimeException if `size` < 1" in {
    intercept[RuntimeException] {
      requireSizeUntil(-4)
    }

    intercept[RuntimeException] {
      requireSizeUntil(-1)
    }

    intercept[RuntimeException] {
      requireSizeUntil(0)
    }
  }

  it must "do nothing otherwise" in {
    requireSizeUntil(1)
    requireSizeUntil(2)
    requireSizeUntil(100)
  }

  "requireRange" must "throws RuntimeException if range [from, until) is empty" in {
    intercept[RuntimeException] {
      requireRange(-10, -10)
    }
    intercept[RuntimeException] {
      requireRange(0, 0)
    }
    intercept[RuntimeException] {
      requireRange(5, 5)
    }
    intercept[RuntimeException] {
      requireRange(5, -5)
    }
    intercept[RuntimeException] {
      requireRange(5, 4)
    }
    intercept[RuntimeException] {
      requireRange(-5, -6)
    }
  }

  it must "do nothing otherwise" in {
    requireRange(-10, -9)
    requireRange(-10, 2)
    requireRange(0, 1)
    requireRange(5, 8)
  }
}
