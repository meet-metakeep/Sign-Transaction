# MetaKeep Transaction Signing App

Android app demonstrating how to use the MetaKeep SDK for signing EVM transactions.

## Setup

1. **Clone the repository**
2. **Configure MetaKeep Domain**: Update the domain ID in `MainActivity.kt`:
   ```kotlin
   sdk = MetaKeep("YOUR_DOMAIN_ID", AppContext(this))
   ```
3. **Update manifest placeholders** in `app/build.gradle`:
   ```gradle
   manifestPlaceholders = [
       metakeepDomain: "YOUR_DOMAIN_ID.auth.metakeep.xyz", 
       metakeepScheme: "com.domain.app"
   ]
   ```

## Dependencies

The app uses:
- `xyz.metakeep.sdk:lib:2.0.3` - MetaKeep SDK
- Android SDK 33+
- Kotlin 1.8.22

## Usage

The app automatically attempts to sign a transaction when launched. To customize:

1. **Modify the transaction** in `MainActivity.kt`:
   ```kotlin
   val txnString = """
   {
       "type": 2,
       "to": "0x...",
       "value": "0x...",
       // ... other fields
   }
   """
   ```

2. **Handle the response** in the callback:
   ```kotlin
   sdk.signTransaction(
       JsonRequest(txnString),
       "Description",
       Callback(
           onSuccess = { response -> /* Handle success */ },
           onFailure = { error -> /* Handle error */ }
       )
   )
   ```

## MetaKeep SDK Features

- **Authentication**: Handles user authentication through MetaKeep wallet
- **Transaction Signing**: Signs EVM transactions with user approval
- **Callback Handling**: Provides success/failure responses

## Notes

- Requires MetaKeep wallet to be installed
- User must approve transactions in the wallet
- Results are logged to console for debugging
