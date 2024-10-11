package com.blog.example.util

import com.blog.example.models.User
import com.blog.example.models.UserWithoutPassword
import com.varabyte.kobweb.browser.api
import kotlinx.browser.window
import kotlinx.serialization.encodeToString
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

suspend fun checkUserExistence(user: User) : UserWithoutPassword? {
    return try {

      val result = window.api.tryPost(
        apiPath = "userCheck",
        body = Json.encodeToString(user).encodeToByteArray()
      )
      result?.decodeToString()?.let { Json.decodeFromString<UserWithoutPassword>(it) }
    } catch (e: Exception){
      println(e.message)
      null
    }
}