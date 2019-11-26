package com.example.androidapplicantcodingchallenge.ui.blog

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.androidapplicantcodingchallenge.R

class BlogFragment : Fragment() {

    private lateinit var blogViewModel: BlogViewModel

    private lateinit var listView: ListView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        blogViewModel =
            ViewModelProviders.of(this).get(BlogViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_blog, container, false)

        listView = root.findViewById(R.id.blog_list)

        return root
    }
}