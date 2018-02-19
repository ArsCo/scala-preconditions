package ars.precondition

/** Tests for [[Messages]].
  *
  * @author ars (Arsen Ibragimov)
  * @since 0.0.3
  */
class MessagesTest extends AbstractBaseTest {

  "NoNameParameter" must "be '<no name>' string" in {
    assertResult("<no name>") { Messages.NoNameParameter }
  }

  "mustBe(name, restriction, value)" must "have correct string value" in {
    val n = "param name"
    val r = "not null or null or ..."
    val v = "123fgh"
    assertResult(s"The parameter '$n' must be $r: '$v'.") {
      Messages.mustBe(n, r, v)
    }
  }


}
