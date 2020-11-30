package icu.csch.coffee

sealed class BeveragePurchaseState {

    /**
     * Return the new state after a beverage was selected.
     */
    abstract fun handleBeverageSelection(beverageType: BeverageType): BeveragePurchaseState

    /**
     * Return the new state after an NFC tag was detected.
     */
    abstract fun handleNfcTagDetection(identifier: String): BeveragePurchaseState

    object Idle : BeveragePurchaseState() {
        override fun handleBeverageSelection(beverageType: BeverageType) =
            BeverageTypeSelected(beverageType)

        override fun handleNfcTagDetection(identifier: String) = NfcTagDetected(identifier)
    }

    class BeverageTypeSelected(val beverageType: BeverageType) : BeveragePurchaseState() {
        override fun handleBeverageSelection(beverageType: BeverageType) =
            BeverageTypeSelected(beverageType)

        override fun handleNfcTagDetection(identifier: String) = NfcTagDetected(identifier)
    }

    class NfcTagDetected(val identifier: String) : BeveragePurchaseState() {
        override fun handleBeverageSelection(beverageType: BeverageType) =
            InputComplete(beverageType, nfcTagIdentifier = identifier)

        override fun handleNfcTagDetection(identifier: String) = NfcTagDetected(identifier)
    }

    class InputComplete(
        val beverageType: BeverageType,
        val nfcTagIdentifier: String
    ) : BeveragePurchaseState() {
        override fun handleBeverageSelection(beverageType: BeverageType) =
            InputComplete(beverageType, nfcTagIdentifier)

        override fun handleNfcTagDetection(identifier: String) =
            InputComplete(beverageType, identifier)
    }

}
