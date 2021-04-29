package com.example.praticalcity.api

import retrofit2.Call
import retrofit2.http.*

interface EndPoints {

    @GET ("/problemas/api/utilizadores")
    fun getUtilizadores(): Call<List<Utilizador>>

    @GET ("/problemas/api/utilizador/{id}")
    fun getUtilizadorById(@Path("id") id:Int): Call<Utilizador>

    @GET("/problemas/api/situacoes/")
    fun getSituacoes(): Call<List<Situacao>>

    @GET("/problemas/api/situacoes/{id}")
    fun getSituacaoById(@Path("id")id: Int): Call<Situacao>

    @FormUrlEncoded
    @POST("/problemas/api/utilizador_login")
    fun login(@Field("username") username: String?, @Field("password") password: String?): Call<OutputLogin>

}