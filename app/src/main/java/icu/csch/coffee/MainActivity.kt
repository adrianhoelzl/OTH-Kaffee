package icu.csch.coffee

import android.graphics.Color
import android.media.Image
import android.nfc.NfcAdapter
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    var schwarz: ImageButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }




    // handling ACTION_TAG_DISCOVERED action from intent:
    override fun onResume() {
        super.onResume()
        if (intent.action == NfcAdapter.ACTION_TAG_DISCOVERED) {
            Toast.makeText(this, "NFC Tag\n" +
                    this.ByteArrayToHexString(intent.getByteArrayExtra(NfcAdapter.EXTRA_ID)),
                    Toast.LENGTH_LONG).show()
        }
    }
    // Converting byte[] to hex string:
    private fun ByteArrayToHexString(inarray: ByteArray?): String {
        var i: Int
        var j: Int
        var `in`: Int
        val hex = arrayOf("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F")
        var out = ""
        j = 0
        while (j < inarray!!.size) {
            `in` = inarray[j].toInt() and 0xff
            i = `in` shr 4 and 0x0f
            out += hex[i]
            i = `in` and 0x0f
            out += hex[i]
            ++j
        }
        return out
    }
}

private fun ImageButton.performClick(onTouchListener: View.OnTouchListener) {

}
