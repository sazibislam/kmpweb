package com.blog.example.api

import com.blog.example.data.MongoDB
import com.blog.example.models.User
import com.blog.example.models.UserWithoutPassword
import com.varabyte.kobweb.api.Api
import com.varabyte.kobweb.api.ApiContext
import com.varabyte.kobweb.api.data.getValue
import com.varabyte.kobweb.api.http.setBodyText
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.nio.charset.StandardCharsets

@Api(routeOverride = "userCheck")
suspend fun userCheck(context: ApiContext) {

  return try {
    val userRequest = context.req.body?.decodeToString()?.let {
      Json.decodeFromString<User>(it)
    }

    val user = userRequest?.let {
      context.data.getValue<MongoDB>().checkUserExistence(
        User(username = it.username, password = hasPassword(it.password))
      )
    }

    if (user != null) {
      context.res.setBodyText(
        Json.encodeToString<UserWithoutPassword>(
          UserWithoutPassword(
            _id = user._id,
            username = user.username
          )
        )
      )
    } else {
      context.res.setBodyText(Json.encodeToString(Exception("User not found")))
    }
  } catch (e: Exception) {
    context.res.setBodyText(Json.encodeToString(Exception(e.message)))
  }
}

/*
* Check if the password is hashed
* */
private fun hasPassword(password: String): String {

  val messageDigest = java.security.MessageDigest.getInstance("SHA-256")
  val hashedBytes = messageDigest.digest(password.toByteArray(StandardCharsets.UTF_8))

  val hexString = StringBuffer()

  for (byte in hashedBytes) {

    hexString.append(String.format("%02x", byte)) //two characters for each byte
  }
  return hexString.toString()
}