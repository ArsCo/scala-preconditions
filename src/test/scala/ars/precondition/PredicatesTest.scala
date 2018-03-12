package ars.precondition

import ars.precondition.Predicates._

/** Tests for [[Predicates]].
  *
  * @author ars (Arsen Ibragimov)
  * @since 0.0.3
  */
class PredicatesTest extends AbstractBaseTest {

  "onlyOneOf" must "return `true` if one and only one elem of `seq` contains in `value`" in {
    val seq = Set("A", "B", "C", "D")
    val value = Seq("A", "F", "K", "N")
    val valueDups = Seq("A", "F", "K", "A")

    assert(isOnlyOneOf(value, seq))
    assert(isOnlyOneOf(value, seq, allowDups = false))
    assert(isOnlyOneOf(valueDups, seq, allowDups = true))
  }

  it must "return `false` otherwise" in {

    assert(
      !isOnlyOneOf(
        Seq(),
        Seq()
      )
    )

    assert(
      !isOnlyOneOf(
        Seq("A"),
        Seq()
      )
    )

    assert(
      !isOnlyOneOf(
        Seq(),
        Seq("F")
      )
    )

    assert(
      !isOnlyOneOf(
        Seq("A", "F", "A"),
        Seq("A")
      )
    )

    assert(
      !isOnlyOneOf(
        Seq(),
        Seq(),
        allowDups = true
      )
    )

    assert(
      !isOnlyOneOf(
        Seq("A"),
        Seq(),
        allowDups = true
      )
    )

    assert(
      !isOnlyOneOf(
        Seq(),
        Seq("F"),
        allowDups = true
      )
    )
  }

  "atLeastOneOf" must "return `true` if at least one elem of `seq` contains in `value`" in {
    val seq = Set("A", "B", "C", "D")
    val value = Seq("A", "F", "K", "N")
    val valueDups = Seq("A", "F", "K", "A")

    val seq2 = Set("A", "B", "C", "D")
    val value2 = Seq("A", "F", "K", "B")
    val valueDups2 = Seq("A", "F", "K", "A", "B", "B")

    assert(Predicates.isAtLeastOneOf(value, seq))
    assert(Predicates.isAtLeastOneOf(value, seq, allowDups = false))
    assert(Predicates.isAtLeastOneOf(valueDups, seq, allowDups = true))

    assert(Predicates.isAtLeastOneOf(value2, seq2))
    assert(Predicates.isAtLeastOneOf(value2, seq2, allowDups = false))
    assert(Predicates.isAtLeastOneOf(valueDups2, seq2, allowDups = true))
  }

  it must "return `false` otherwise" in {
    assert(
      !Predicates.isAtLeastOneOf(
        Seq(),
        Seq()
      )
    )

    assert(
      !Predicates.isAtLeastOneOf(
        Seq("A"),
        Seq()
      )
    )

    assert(
      !Predicates.isAtLeastOneOf(
        Seq(),
        Seq("F")
      )
    )

    assert(
      !Predicates.isAtLeastOneOf(
        Seq(),
        Seq(),
        allowDups = true
      )
    )

    assert(
      !Predicates.isAtLeastOneOf(
        Seq("A"),
        Seq(),
        allowDups = true
      )
    )

    assert(
      !isOnlyOneOf(
        Seq(),
        Seq("F"),
        allowDups = true
      )
    )
  }

}
