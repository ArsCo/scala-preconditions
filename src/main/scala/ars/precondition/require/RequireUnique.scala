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

import ars.precondition.MessageBuilder
import ars.precondition.MessageBuilder.NoNameParameter

/** `requireXXX` methods for test uniqueness in collection.
  *
  * @author Arsen Ibragimov (ars)
  * @since 0.1.2
  */
trait RequireUnique extends RequireCore {

  /**
    * Test that all elements of iterable `value` has unique value of `field` extracted by `extractor`.
    *
    * @param value the testing iterable (must be non-null)
    * @param name the name to include in the failure message (must be non-blank)
    * @param field the field name to include in the failure message (must be non-blank)
    * @param extractor the extractor function to extract field value from instance of [[T]] (must be non-null)
    *
    * @tparam T the iterable element type
    * @tparam F the testing field type
    */
  def requireUniqueField[T, F](value: Iterable[T], name: String = NoNameParameter, field: String = NoNameParameter)
                              (extractor: FieldExtractor[T, F]): Unit = {
    RequireInternal.requireNonNull(value, "value")
    RequireInternal.requireName(name)
    RequireInternal.requireNotBlank(field, "field")
    RequireInternal.requireNonNull(extractor, "extractor")

    val nonUnique = firstNonUnique(value)(extractor)
    if (nonUnique.isDefined) {
      fail(MessageBuilder.nonUniqueMessage(name, field, nonUnique.get))
    }
  }

  private def firstNonUnique[T, F](values: Iterable[T])
                                  (extractor: FieldExtractor[T, F]): Option[(F, Iterable[T])] = {
    values.groupBy(extractor).find { case (_, seq) => seq.size > 1 }
  }
}
