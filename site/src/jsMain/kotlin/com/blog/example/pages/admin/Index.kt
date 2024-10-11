package com.blog.example.pages.admin

import androidx.compose.runtime.Composable
import com.blog.example.util.isUserLoggedIn
import com.varabyte.kobweb.core.Page

@Page
@Composable
fun HomeScreen() {

  isUserLoggedIn {
    HomePage()
  }
}

@Composable
fun HomePage() {

  println("Admin home page")
}

