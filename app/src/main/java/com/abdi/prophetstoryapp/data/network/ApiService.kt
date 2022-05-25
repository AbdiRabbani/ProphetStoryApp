package com.abdi.prophetstoryapp.data.network

import com.abdi.prophetstoryapp.data.KisahResponse
import io.reactivex.rxjava3.core.Flowable
import retrofit2.http.GET

interface ApiService {

    @GET("kisahnabi")
    fun getKisahNabi() : Flowable<List<KisahResponse>>


}