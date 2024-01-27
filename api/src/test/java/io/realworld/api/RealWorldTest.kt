package io.realworld.api

import io.realworld.api.models.entities.UserCreds
import io.realworld.api.models.requests.SignupRequest
import org.junit.Assert.assertNotNull
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.random.Random

class RealWorldTest {
    private val realworldclient = RealWorldClient()

    @Test
    fun `GET Articles`() {
        runBlocking {
            val articles = realworldclient.publicApi.getArticles()
            assertNotNull(articles.body()?.articles)
        }
    }

    @Test
    fun `GET Articles by Authors`() {
        runBlocking {
            val articles = realworldclient.publicApi.getArticles(author = "Ping Sokołowski")
            assertNotNull(articles.body()?.articles)
        }
    }

    @Test
    fun `GET Articles by Tag`() {
        runBlocking {
            val articles = realworldclient.publicApi.getArticles(tag = "facilis")
            assertNotNull(articles.body()?.articles)
        }

    }
    @Test
    fun `POST user - New User`(){
        runBlocking {
            val userCreds = UserCreds(
                    "testmail${Random.nextInt(999,9999)}@real.com",
                    "pass${Random.nextInt(99999,999999)}",
                    "rand_user_${Random.nextInt( 99,  999)}"
            )
            runBlocking {
                val test= realworldclient.publicApi.signupUsers(SignupRequest(userCreds))
                assertEquals(userCreds.username,test.body()?.user?.username)
            }
        }
    }
}