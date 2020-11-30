package icu.csch.coffee

private val HEX_DIGITS =
    arrayOf("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F")

// Converting byte[] to hex string:
fun ByteArray.toHexString() = fold("") { acc, component ->
    val byte = component.toInt() and 0xff

    val moreSignificantHalfByte = byte shr 4 and 0x0F
    val leftComponent = HEX_DIGITS[moreSignificantHalfByte]

    val lessSignificantHalfByte = byte and 0x0F
    val rightComponent = HEX_DIGITS[lessSignificantHalfByte]

    acc + leftComponent + rightComponent
}
