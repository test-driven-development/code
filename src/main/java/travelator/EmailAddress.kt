package travelator

import java.util.*

class EmailAddress(val localPart: String, val domain: String) {
  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (other == null || javaClass != other.javaClass) return false
    val that = other as EmailAddress
    return localPart == that.localPart && domain == that.domain
  }

  override fun hashCode(): Int {
    return Objects.hash(localPart, domain)
  }

  override fun toString(): String {
    return "$localPart@$domain"
  }

  companion object {
    @JvmStatic
    fun parse(value: String): EmailAddress { // <2>
      val atIndex = value.lastIndexOf('@')
      require(!(atIndex < 1 || atIndex == value.length - 1)) {
        "EmailAddress must be two parts separated by @"
      }
      return EmailAddress(value.substring(0, atIndex), value.substring(atIndex + 1))
    }
  }
}
