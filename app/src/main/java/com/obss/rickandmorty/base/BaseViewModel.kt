package com.obss.rickandmorty.base

import androidx.lifecycle.ViewModel
import com.obss.rickandmorty.model.ErrorPopUpMessage
import com.obss.rickandmorty.network.INetworkResponseHandling
import com.obss.rickandmorty.utils.SingleLiveEvent

open class BaseViewModel : ViewModel(), INetworkResponseHandling {
    val errorMessage: SingleLiveEvent<ErrorPopUpMessage> = SingleLiveEvent()

    val loading: SingleLiveEvent<Boolean> = SingleLiveEvent()

    //region interface
    override fun loading(switch: Boolean) {
        loading.postValue(switch)
    }

    override fun onGenericErrorTaken(message: String) {
        errorMessage.postValue(ErrorPopUpMessage("error", message))
    }

    override fun onErrorPopUp(header: String, body: String) {
        errorMessage.postValue(ErrorPopUpMessage(header, body))
    }
    //endregion

}