package com.abdi.prophetstoryapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.abdi.prophetstoryapp.data.KisahResponse
import com.abdi.prophetstoryapp.data.network.ApiClient
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class MainViewModel : ViewModel() {

    val kisahresponse = MutableLiveData<List<KisahResponse>>()
    var isLoading = MutableLiveData<Boolean>()
    var isError = MutableLiveData<Throwable>()


    fun getKisahNabi(responHandler: (List<KisahResponse>) -> Unit, errorHandler : (Throwable) -> Unit) {
        ApiClient.getApiService().getKisahNabi()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({
                responHandler(it)
            }, {
                errorHandler(it)
        })
    }

    fun getData() {
        isLoading.value = true
        getKisahNabi({
            isLoading.value = false
            kisahresponse.value = it
        },{
            isLoading.value = false
            isError.value = it
        })
    }
}