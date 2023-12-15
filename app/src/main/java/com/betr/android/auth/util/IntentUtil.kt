package com.betr.android.auth.util

import androidx.activity.result.IntentSenderRequest
import com.google.android.gms.auth.api.identity.BeginSignInResult

fun BeginSignInResult.buildSenderRequest(): IntentSenderRequest {
    return IntentSenderRequest
        .Builder(this.pendingIntent.intentSender)
        .build()
}