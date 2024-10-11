package com.blog.example.data

import com.blog.example.models.User

interface MongoRepository {

  suspend fun checkUserExistence(user: User): User?
  suspend fun checkUserId(id: String): Boolean
}