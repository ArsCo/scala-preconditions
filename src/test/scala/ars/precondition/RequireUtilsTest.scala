package ars.precondition

import ars.precondition.RequireUtils._

/** Tests for [[RequireUtils]].
  *
  * @author ars (Arsen Ibragimov)
  * @since 0.0.3
  */
class RequireUtilsTest extends AbstractBaseTest {

  val fieldName = "fieldName"
  val message = "message"

  "prefix" must "be empty by default" in {
    assert(prefix == "")
  }

  "postfix" must "be empty by default" in {
    assert(postfix == "")
  }

  "require" must "by default throw IAE if requirement is false" in {
    val e = intercept[IllegalArgumentException] {
      require(false, message)
    }
    assert(e.getMessage.contains(message))
  }

  "require" must "by dafault returns immediately if reauirement is true" in {
    require(true, message)
  }

  "requireNotNull" must "tests that value is not null and throws IAE otherwise" in {
    requireNotNull("4444", fieldName)
    requireNotNull("5555")

    intercept[IllegalArgumentException] {
      requireNotNull(null, fieldName)
    }

    intercept[IllegalArgumentException] {
      requireNotNull(null, null)
    }

    intercept[IllegalArgumentException] {
      requireNotNull(null)
    }
  }

  "requireNotBlank" must "tests that value is not null or trimmed empty string and throws IAE otherwise" in {
    requireNotBlank("4444", fieldName)
    requireNotBlank("5555")

    intercept[IllegalArgumentException] {
      val value: String = null
      requireNotBlank(value, fieldName)
    }

    intercept[IllegalArgumentException] {
      requireNotBlank("", fieldName)
    }

    intercept[IllegalArgumentException] {
      requireNotBlank("  ", fieldName)
    }

    intercept[IllegalArgumentException] {
      val value: String = null
      requireNotBlank(value)
    }

    intercept[IllegalArgumentException] {
      requireNotBlank("   ")
    }
  }

  "requireNotBlank" must "tests that value is not null or empty list and throws IAE otherwise" in {
    requireNotBlank(List("111"), fieldName)
    requireNotBlank(List("555"))

    intercept[IllegalArgumentException] {
      val value: List[String] = null
      requireNotBlank(value, fieldName)
    }

    intercept[IllegalArgumentException] {
      requireNotBlank(Nil, fieldName)
    }

    intercept[IllegalArgumentException] {
      val value: String = null
      requireNotBlank(value)
    }

    intercept[IllegalArgumentException] {
      requireNotBlank(Nil)
    }
  }

  "requirePositive(Int)" must "tests that value is positive" in {
    val value: Int = 5
    requirePositive(value, message)
    requirePositive(value)

    intercept[IllegalArgumentException] {
      requirePositive(0, message)
    }

    intercept[IllegalArgumentException] {
      requirePositive(-1, message)
    }

    intercept[IllegalArgumentException] {
      requirePositive(0)
    }

    intercept[IllegalArgumentException] {
      requirePositive(-1)
    }
  }

  "requirePositive(Long)" must "tests that value is positive" in {
    val value: Long = 5L
    requirePositive(value, message)
    requirePositive(value)

    intercept[IllegalArgumentException] {
      requirePositive(0L, message)
    }

    intercept[IllegalArgumentException] {
      requirePositive(-1L, message)
    }

    intercept[IllegalArgumentException] {
      requirePositive(0L)
    }

    intercept[IllegalArgumentException] {
      requirePositive(-1L)
    }
  }

  "requirePositive(Float)" must "tests that value is positive" in {
    val value: Float = 5.0F
    requirePositive(value, message)
    requirePositive(value)

    intercept[IllegalArgumentException] {
      requirePositive(0F, message)
    }

    intercept[IllegalArgumentException] {
      requirePositive(-1F, message)
    }

    intercept[IllegalArgumentException] {
      requirePositive(0F)
    }

    intercept[IllegalArgumentException] {
      requirePositive(-1F)
    }
  }

  "requirePositive(Double)" must "tests that value is positive" in {
    val value: Double = 5.0
    requirePositive(value, message)
    requirePositive(value)

    intercept[IllegalArgumentException] {
      requirePositive(0.0, message)
    }

    intercept[IllegalArgumentException] {
      requirePositive(-1.5, message)
    }

    intercept[IllegalArgumentException] {
      requirePositive(0.0)
    }

    intercept[IllegalArgumentException] {
      requirePositive(-1.5)
    }
  }

  "requirePositive(BigInt)" must "tests that value is positive" in {
    val value: BigInt = 50000
    requirePositive(value, message)
    requirePositive(value)

    intercept[IllegalArgumentException] {
      requirePositive(BigInt(0), message)
    }

    intercept[IllegalArgumentException] {
      requirePositive(BigInt(-1), message)
    }

    intercept[IllegalArgumentException] {
      requirePositive(BigInt(0))
    }

    intercept[IllegalArgumentException] {
      requirePositive(BigInt(-1))
    }
  }

  "requirePositive(BigDecimal)" must "tests that value is positive" in {
    val value: BigDecimal = 50000
    requirePositive(value, message)
    requirePositive(value)

    intercept[IllegalArgumentException] {
      requirePositive(BigDecimal(0.0), message)
    }

    intercept[IllegalArgumentException] {
      requirePositive(BigDecimal(-1.6), message)
    }

    intercept[IllegalArgumentException] {
      requirePositive(BigDecimal(0.0))
    }

    intercept[IllegalArgumentException] {
      requirePositive(BigDecimal(-1.4))
    }
  }

  "requireNonNegative(Int)" must "tests that value is positive or zero" in {
    requireNonNegative(5, message)
    requireNonNegative(5)
    requireNonNegative(0, message)
    requireNonNegative(0)

    intercept[IllegalArgumentException] {
      requireNonNegative(-1, message)
    }

    intercept[IllegalArgumentException] {
      requireNonNegative(-1)
    }
  }

  "requireNonNegative(Long)" must "tests that value is positive or zero" in {
    requireNonNegative(5L, message)
    requireNonNegative(5L)
    requireNonNegative(0L, message)
    requireNonNegative(0L)

    intercept[IllegalArgumentException] {
      requireNonNegative(-1L, message)
    }

    intercept[IllegalArgumentException] {
      requireNonNegative(-1L)
    }
  }

  "requireNonNegative(Float)" must "tests that value is positive or zero" in {
    requireNonNegative(5.5F, message)
    requireNonNegative(5.3F)
    requireNonNegative(0.0F, message)
    requireNonNegative(0.0F)

    intercept[IllegalArgumentException] {
      requireNonNegative(-1.34F, message)
    }

    intercept[IllegalArgumentException] {
      requireNonNegative(-1.22F)
    }
  }

  "requireNonNegative(Double)" must "tests that value is positive or zero" in {
    requireNonNegative(5.5, message)
    requireNonNegative(5.3)
    requireNonNegative(0.0, message)
    requireNonNegative(0.0)

    intercept[IllegalArgumentException] {
      requireNonNegative(-1.34, message)
    }

    intercept[IllegalArgumentException] {
      requireNonNegative(-1.22)
    }
  }

  "requireNonNegative(BigInt)" must "tests that value is positive or zero" in {
    requireNonNegative(BigInt(5), message)
    requireNonNegative(BigInt(5))
    requireNonNegative(BigInt(0), message)
    requireNonNegative(BigInt(0))

    intercept[IllegalArgumentException] {
      requireNonNegative(BigInt(-1), message)
    }

    intercept[IllegalArgumentException] {
      requireNonNegative(BigInt(-1))
    }
  }

  "requireNonNegative(BigDecimal)" must "tests that value is positive or zero" in {
    requireNonNegative(BigDecimal(5.5), message)
    requireNonNegative(BigDecimal(5.3))
    requireNonNegative(BigDecimal(0.0), message)
    requireNonNegative(BigDecimal(0.0))

    intercept[IllegalArgumentException] {
      requireNonNegative(BigDecimal(-1.34), message)
    }

    intercept[IllegalArgumentException] {
      requireNonNegative(BigDecimal(-1.22))
    }
  }


  "requireNotContainsNull" must "tests that no null values in sequence" in {
    requireAllNotNull(Seq(), "seq")
    requireAllNotNull(Seq("5", "6", "7"), "seq")

    intercept[IllegalArgumentException] {
      requireAllNotNull(Seq(null), "seq")
    }

    intercept[IllegalArgumentException] {
      requireAllNotNull(Seq("5", null), "seq")
    }

    intercept[IllegalArgumentException] {
      requireAllNotNull(Seq(null, "5"), "seq")
    }

    intercept[IllegalArgumentException] {
      requireAllNotNull(Seq(null, "5", null), "seq")
    }

    intercept[IllegalArgumentException] {
      requireAllNotNull(Seq("5", null, "6", null), "seq")
    }

    intercept[IllegalArgumentException] {
      requireAllNotNull(Seq("5", "6", null), "seq")
    }
  }

}
