package com.example.androidapplicantcodingchallenge.models

data class Entry(val uuid: String) {
    var title: String = ""
    var description: String = "No Description Available"
    var author: String = ""
    var image: String = ""
    var article_date: String = ""
    var link: String = ""
}