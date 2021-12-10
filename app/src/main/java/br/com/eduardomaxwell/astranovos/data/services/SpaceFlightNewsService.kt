package br.com.eduardomaxwell.astranovos.data.services

import br.com.eduardomaxwell.astranovos.data.model.Post
import retrofit2.http.GET

interface SpaceFlightNewsService {
    @GET("articles")
    suspend fun listPosts(): List<Post>
}