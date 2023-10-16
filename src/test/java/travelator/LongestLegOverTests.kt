package travelator

import java.time.Duration
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime
import java.util.List
import java.util.concurrent.ThreadLocalRandom
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class LongestLegOverTests {
  private val legs =
      List.of(
          leg("one hour", Duration.ofHours(1)),
          leg("one day", Duration.ofDays(1)),
          leg("two hours", Duration.ofHours(2)),
      )
  private val oneDay = Duration.ofDays(1)

  @Test
  fun `is absent when no legs`() {
    Assertions.assertNull(longestLegOver(emptyList(), Duration.ZERO))
  }

  @Test
  fun `is absent when no legs long enough`() {
    Assertions.assertNull(longestLegOver(legs, oneDay))
  }

  @Test
  fun `is longest leg when one match`() {
    Assertions.assertEquals(
        "one day",
        longestLegOver(legs, oneDay.minusMillis(1))!!.description,
    )
  }

  @Test
  fun `is longest leg when more than one match`() {
    Assertions.assertEquals(
        "one day",
        longestLegOver(legs, Duration.ofMinutes(59))?.description,
    )
  }

  companion object {
    private fun leg(description: String, duration: Duration): Leg {
      val start =
          ZonedDateTime.ofInstant(
              Instant.ofEpochSecond(
                  ThreadLocalRandom.current().nextInt().toLong(),
              ),
              ZoneId.of("UTC"),
          )
      return Leg(description, start, start.plus(duration))
    }
  }
}
