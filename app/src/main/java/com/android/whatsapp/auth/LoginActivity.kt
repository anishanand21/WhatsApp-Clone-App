package com.android.whatsapp.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import com.android.whatsapp.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var phoneNumber: String
    private lateinit var countryCode: String
    private val CREDENTIAL_PICKER_REQUEST = 1  // Set to an unused request code

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //requestHint()

        phoneNumberEt.addTextChangedListener {
            nextBtn.isEnabled = !(it.isNullOrEmpty() || it.length < 10)
        }

        nextBtn.setOnClickListener {
            checkNumber()
        }
    }

//    // Construct a request for phone numbers and show the picker
//    private fun requestHint() {
//        val hintRequest = HintRequest.Builder()
//            .setPhoneNumberIdentifierSupported(true)
//            .build()
//        val credentialsClient = Credentials.getClient(this)
//        val intent = credentialsClient.getHintPickerIntent(hintRequest)
//        startIntentSenderForResult(
//            intent.intentSender,
//            CREDENTIAL_PICKER_REQUEST,
//            null, 0, 0,0
//        )
//    }
//
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        when(requestCode) {
//            CREDENTIAL_PICKER_REQUEST ->
//                if(resultCode == Activity.RESULT_OK && data != null) {
//                    val credential = data.getParcelableExtra<Credential>(Credential.EXTRA_KEY)
//                }
//        }
//    }

    private fun checkNumber() {
        countryCode = ccp.selectedCountryCodeWithPlus
        phoneNumber = countryCode + phoneNumberEt.text.toString()

        notifyUser()
    }

    private fun notifyUser() {
        MaterialAlertDialogBuilder(this).apply {
            setMessage("We will be verifying the phone number:$phoneNumber\n" +
                    "Is this OK, or would you like to edit the number?")
            setPositiveButton("Ok") { _,_ ->
                showOtpActivity()
            }
            setNegativeButton("Edit") {dialog, which ->
                dialog.dismiss()
            }
            setCancelable(false)
            create()
            show()
        }
    }

    private fun showOtpActivity() {
        startActivity(Intent(this, OtpActivity::class.java).putExtra(PHONE_NUMBER,phoneNumber))
        finish()
    }
}