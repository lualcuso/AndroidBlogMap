package com.example.androidapplicantcodingchallenge.ui.blog

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BlogViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is blog Fragment"
    }
    val text: LiveData<String> = _text
}