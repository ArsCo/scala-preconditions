package ars.precondition

import org.scalatest.Suites

/** All tests for package `ars.precondition`.
  *
  * @author ars (Arsen Ibragimov)
  * @since 0.0.1
  */
class AllPackageTests extends Suites(
  new MessagesTest,
  new PredicatesTest,
  new RequireUtilsTest
)
