package com.obss.rickandmorty.network.helper

import androidx.annotation.MainThread
import com.obss.rickandmorty.network.INetworkResponseHandling
import java.lang.Exception

abstract class RequestHelper<T> {

    suspend fun loadRequest(
        iNetworkResponseHandling: INetworkResponseHandling,
        isNeedProgressBar: Boolean
    ): DataHolder<T>? {
        if (isNeedProgressBar) iNetworkResponseHandling.loading(true)
        return try {
            val response = createNetworkRequest()
            if (isNeedProgressBar) iNetworkResponseHandling.loading(false)
            DataHolder.Success(response)
        } catch (e: Exception) {
            iNetworkResponseHandling.onErrorPopUp("Error", "An error occurred on network layer")
            DataHolder.Error("An error occurred on network layer")
        }
    }

    @MainThread
    protected abstract suspend fun createNetworkRequest(): T
}