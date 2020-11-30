package icu.csch.coffee

import android.nfc.NfcAdapter
import android.os.Bundle
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.appcompat.app.AppCompatActivity
import icu.csch.coffee.BeveragePurchaseState.Idle
import icu.csch.coffee.BeveragePurchaseState.InputComplete
import icu.csch.coffee.BeverageType.BLACK_COFFEE
import icu.csch.coffee.BeverageType.COFFEE_WITH_MILK
import icu.csch.coffee.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var state: BeveragePurchaseState = Idle
        set(value) {
            field = value
            onStateChange(value)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setUpButtons()
        setContentView(binding.root)
    }

    private fun setUpButtons() {
        binding.milch.setOnClickListener {
            println("'Coffee with milk' selected")
            onBeverageSelected(COFFEE_WITH_MILK)
        }

        binding.schwarz.setOnClickListener {
            println("'Black coffee' selected")
            onBeverageSelected(BLACK_COFFEE)
        }
    }

    // handling ACTION_TAG_DISCOVERED action from intent:
    override fun onResume() {
        super.onResume()
        if (intent.action == NfcAdapter.ACTION_TAG_DISCOVERED) {
            val byteArray = intent.getByteArrayExtra(NfcAdapter.EXTRA_ID)
            val hexString = byteArray?.toHexString() ?: ""
            Toast.makeText(this, "NFC Tag\n$hexString", LENGTH_LONG).show()
            onNfcTagDetected(hexString)
        }
    }

    /**
     * Handle the selection of a beverage.
     */
    private fun onBeverageSelected(beverageType: BeverageType) {
        state = state.handleBeverageSelection(beverageType)
    }

    /**
     * Handle the detection of an NFC tag.
     */
    private fun onNfcTagDetected(nfcTagIdentifier: String) {
        state = state.handleNfcTagDetection(nfcTagIdentifier)
    }

    /**
     * Handle the change of the state.
     */
    private fun onStateChange(newState: BeveragePurchaseState) {
        if (newState is InputComplete) {
            performPurchase(
                newState.beverageType,
                newState.nfcTagIdentifier
            )
        }
    }

    private fun performPurchase(beverageType: BeverageType, nfcTagIdentifier: String) {
        // TODO
        state = Idle
    }


}
