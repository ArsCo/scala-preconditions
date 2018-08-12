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

import org.scalatest.Suites

/** All tests for package `ars.precondition.require`.
  *
  * @author Arsen Ibragimov (ars)
  * @since 0.0.1
  */
class AllPackageTests extends Suites(
  new RequireInternalTest,

  new RequireTest,

  new PrefixPostfixMessageTest,

  new RequireAllSpecificTest,
  new RequireAllTest,
  new RequireAnyTest,
  new RequireCoreTest,
  new RequireInternalTest,
  new RequireIterableTest,
  new RequireUniqueTest,
  new RequireNumericRangeTest,
  new RequireNumericTest,
  new RequireOptionalTest,
  new RequireSizeTest,
  new RequireStringFormatTest,
  new RequireStringNumericTest,
  new RequireStringTest
)
