package com.domain.app

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import xyz.metakeep.sdk.AppContext
import xyz.metakeep.sdk.Callback
import xyz.metakeep.sdk.JsonRequest
import xyz.metakeep.sdk.JsonResponse
import xyz.metakeep.sdk.MetaKeep

class MainActivity : AppCompatActivity() {
	private lateinit var sdk: MetaKeep

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		// Initialize MetaKeep SDK
		sdk = MetaKeep("a071e2fe-54b8-41f2-8082-137e2073085e", AppContext(this))

		// Transaction to sign
		val txnString = """
		{
			"type": 2,
			"to": "0x97706df14a769e28ec897dac5ba7bcfa5aa9c444",
			"value": "0x2710",
			"nonce": "0x1",
			"data": "0x0123456789",
			"chainId": "0x13881",
			"gas": "0x17",
			"maxFeePerGas": "0x3e8",
			"maxPriorityFeePerGas": "0x3e7"
		}
		""".trimIndent()

		// Sign the transaction
		sdk.signTransaction(
			JsonRequest(txnString),
			"Sign EVM transaction",
			Callback(
				onSuccess = { response: JsonResponse ->
					Log.d("MetaKeep", "Transaction signed: ${response.toString()}")
				},
				onFailure = { error: JsonResponse ->
					Log.e("MetaKeep", "Signing failed: ${error.toString()}")
				},
			),
		)
	}
}
