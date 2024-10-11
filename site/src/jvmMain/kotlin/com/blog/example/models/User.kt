package com.blog.example.models

import kotlinx.serialization.Serializable
import org.bson.codecs.ObjectIdGenerator

@Serializable
actual data class User(
  actual val id: String = ObjectIdGenerator().generate().toString(),
  actual val username: String = "",
  actual val password: String = ""
)

@Serializable
actual data class UserWithoutPassword(
  actual val id: String = ObjectIdGenerator().generate().toString(),
  actual val username: String = ""
)