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

/** Tests for [[BoundedNumber]].
  *
  * @author Arsen Ibragimov (ars)
  * @since 0.0.4
  */
class BoundedNumberTest extends AbstractBaseTest {
  "BoundedNumber" must "have Inclusive & Exclusive subtypes" in {

    Exclusive(10)

    assert(Inclusive(5).isInstanceOf[BoundedNumber[_]])
    assert(Exclusive(15).isInstanceOf[BoundedNumber[_]])
  }

  "Inclusive" must "have 1 numeric arg" in {
    Inclusive(5)
    Inclusive(5L)
    Inclusive(5.6f)
    Inclusive(5.66)
    Inclusive(BigInt(5.toString))
    Inclusive(BigDecimal(5.toString))
  }

  "Exclusive" must "have 1 numeric arg" in {
    Exclusive(5)
    Exclusive(5L)
    Exclusive(5.6f)
    Exclusive(5.66)
    Exclusive(BigInt(5.toString))
    Exclusive(BigDecimal(5.toString))
  }
}
