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

package ars.precondition.implicits

import ars.precondition.AbstractBaseTest
import ars.precondition.require.bound.RichNumberBound

/** Tests for `implicits` package object.
  *
  * @author Arsen Ibragimov (ars)
  * @since 0.0.3
  */
class implicitsTest extends AbstractBaseTest {

  "num2richBound" must "converts Numeric to RichNumberBound" in {

    val i = num2richBound(5)
    assert(i.isInstanceOf[RichNumberBound[_]])
    assertResult(5)(i.value)

    val l = num2richBound(5L)
    assert(l.isInstanceOf[RichNumberBound[_]])
    assertResult(5L)(l.value)

    val f = num2richBound(5.3f)
    assert(f.isInstanceOf[RichNumberBound[_]])
    assertResult(5.3f)(f.value)

    val d = num2richBound(5.45)
    assert(d.isInstanceOf[RichNumberBound[_]])
    assertResult(5.45)(d.value)

    val biv = BigInt(54.toString)
    val bi = num2richBound(biv)
    assert(bi.isInstanceOf[RichNumberBound[_]])
    assertResult(biv)(bi.value)

    val bdv = BigDecimal(5.56.toString)
    val bd = num2richBound(bdv)
    assert(bd.isInstanceOf[RichNumberBound[_]])
    assertResult(bdv)(bd.value)
  }
}
