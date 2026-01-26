package com.mrsanglier.tsumegohero.domain.authentication.delegate

interface LogoutCleanDelegate {
    suspend fun logoutClean()
}

class LogoutCleanDelegateImpl(
) : LogoutCleanDelegate {
    override suspend fun logoutClean() {
    }
}