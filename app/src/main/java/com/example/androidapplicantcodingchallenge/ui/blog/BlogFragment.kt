package com.example.androidapplicantcodingchallenge.ui.blog

import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.example.androidapplicantcodingchallenge.EntryAdapter
import com.example.androidapplicantcodingchallenge.EntryDetailWebView
import com.example.androidapplicantcodingchallenge.R
import com.example.androidapplicantcodingchallenge.models.BlogEntry
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL


class BlogFragment : Fragment() {
    private lateinit var listView: ListView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_blog, container, false)
        listView = root.findViewById(R.id.blog_list)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        HTTPAsyncTask().execute()
    }

    fun setList(newBlogEntry: String) {
        val answer = JSONObject(newBlogEntry)
        val articlesJSON = answer.getJSONArray("articles");
        val articlesObjects = ArrayList<BlogEntry>(10)
        for (i in 0 until articlesJSON!!.length() - 1) {
            articlesObjects.add(BlogEntry(articlesJSON[i].toString()))
        }

        val arrayAdapter = EntryAdapter(context!!, articlesObjects)
        listView.adapter = arrayAdapter

        listView.setOnItemClickListener { _, _, position, _ ->
            val selectedRecipe = articlesObjects[position]
            val detailIntent = EntryDetailWebView.newIntent(context!!, selectedRecipe)
            startActivity(detailIntent)
        }
    }

    fun  sendGetRequest(): String {
        val mURL = URL("https://www.beenverified.com/articles/index.android.json" )
        var requestResult = ""

        with(mURL.openConnection() as HttpURLConnection) {
            requestMethod = "GET"

            BufferedReader(InputStreamReader(inputStream)).use {
                val response = StringBuffer()

                var inputLine = it.readLine()
                while (inputLine != null) {
                    response.append(inputLine)
                    inputLine = it.readLine()
                }
                it.close()
                requestResult = response.toString()
            }
        }

        return requestResult
    }

    inner class HTTPAsyncTask : AsyncTask<String, Void, String>() {
        override fun doInBackground(vararg urls: String?): String {
            val resultHere = sendGetRequest()
            return resultHere
        }
        override fun onPostExecute(result: String?) {
            setList(result!!)
        }
    }
}

