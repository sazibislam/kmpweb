package com.blog.example.models

import kotlinx.serialization.Serializable

@Serializable
actual data class User(
  actual val _id: String = org.bson.codecs.ObjectIdGenerator().generate().toString(),
  actual val username: String = "",
  actual val password: String = ""
)

@Serializable
actual data class UserWithoutPassword(
  actual val _id: String = org.bson.codecs.ObjectIdGenerator().generate().toString(),
  actual val username: String = ""
)