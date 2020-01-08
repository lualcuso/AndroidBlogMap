package com.example.androidapplicantcodingchallenge.models

import org.json.JSONObject

class BlogEntry(json: String) : JSONObject(json) {
    val title = this.optString("title")
    val description = this.optString("description")
    val author = this.optString("author")
    val date = this.optString("article_date")
    val image_url = this.optString("image")
    val link = this.optString("link")
}