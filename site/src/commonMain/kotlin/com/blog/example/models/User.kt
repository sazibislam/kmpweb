package com.blog.example.models

import kotlinx.serialization.SerialName

expect class User {
  val id: String
  val username: String
  val password: String
}

expect class UserWithoutPassword {
  val id: String
  val username: String
}
