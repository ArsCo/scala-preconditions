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

package ars.precondition.require.bound

import ars.precondition.AbstractBaseTest

/** Tests for [[RichNumberBound]].
  *
  * @author Arsen Ibragimov (ars)
  * @since 0.0.4
  */
class RichNumberBoundTest extends AbstractBaseTest {

  "RichNumberBound" must "takes 1 args of numeric type" in {
    new RichNumberBound(10)
    new RichNumberBound(7L)
    new RichNumberBound(7.5f)
    new RichNumberBound(7.53)
    new RichNumberBound(BigInt(1234.toString))
    new RichNumberBound(BigDecimal(1234.5678.toString))
  }

  it must "validate args" in {
    intercept[RuntimeException] {
      val b: BigDecimal = null
      new RichNumberBound(b)
    }
  }

  "inclusive" must "return Inclusive(value)" in {
    val r = new RichNumberBound(10)
    assert(r.inclusive == Inclusive(10))
  }

  "exclusive" must "return Exclusive(value)" in {
    val r = new RichNumberBound(10)
    assert(r.exclusive == Exclusive(10))
  }

  "<=" must "return the same as `inclusive`" in {
    val r = new RichNumberBound(10)
    assertResult(r.inclusive)(r.<=)
  }

  "<" must "return the same as `exclusive`" in {
    val r = new RichNumberBound(10)
    assertResult(r.exclusive)(r.<)
  }
}
