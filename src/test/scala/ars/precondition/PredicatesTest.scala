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

    assert(onlyOneOf(value, seq))
    assert(onlyOneOf(value, seq, allowDups = false))
    assert(onlyOneOf(valueDups, seq, allowDups = true))
  }

  it must "return `false` otherwise" in {

    assert(
      !onlyOneOf(
        Seq(),
        Seq()
      )
    )

    assert(
      !onlyOneOf(
        Seq("A"),
        Seq()
      )
    )

    assert(
      !onlyOneOf(
        Seq(),
        Seq("F")
      )
    )

    assert(
      !onlyOneOf(
        Seq("A", "F", "A"),
        Seq("A")
      )
    )

    assert(
      !onlyOneOf(
        Seq(),
        Seq(),
        allowDups = true
      )
    )

    assert(
      !onlyOneOf(
        Seq("A"),
        Seq(),
        allowDups = true
      )
    )

    assert(
      !onlyOneOf(
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

    assert(Predicates.atLeastOneOf(value, seq))
    assert(Predicates.atLeastOneOf(value, seq, allowDups = false))
    assert(Predicates.atLeastOneOf(valueDups, seq, allowDups = true))

    assert(Predicates.atLeastOneOf(value2, seq2))
    assert(Predicates.atLeastOneOf(value2, seq2, allowDups = false))
    assert(Predicates.atLeastOneOf(valueDups2, seq2, allowDups = true))
  }

  it must "return `false` otherwise" in {
    assert(
      !Predicates.atLeastOneOf(
        Seq(),
        Seq()
      )
    )

    assert(
      !Predicates.atLeastOneOf(
        Seq("A"),
        Seq()
      )
    )

    assert(
      !Predicates.atLeastOneOf(
        Seq(),
        Seq("F")
      )
    )

    assert(
      !Predicates.atLeastOneOf(
        Seq(),
        Seq(),
        allowDups = true
      )
    )

    assert(
      !Predicates.atLeastOneOf(
        Seq("A"),
        Seq(),
        allowDups = true
      )
    )

    assert(
      !onlyOneOf(
        Seq(),
        Seq("F"),
        allowDups = true
      )
    )
  }

}
