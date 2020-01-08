package com.example.androidapplicantcodingchallenge

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.androidapplicantcodingchallenge.models.BlogEntry
import java.io.InputStream
import java.net.URL


class EntryAdapter(private val context: Context,
                    private val dataSource: ArrayList<BlogEntry>) : BaseAdapter() {

    private val inflater: LayoutInflater
            = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return dataSource.size
    }

    //2
    override fun getItem(position: Int): Any {
        return dataSource[position]
    }

    //3
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    //4
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        // Get view for row item
        val rowView = inflater.inflate(R.layout.list_item_entry, parent, false)

        val blogEntry = getItem(position) as BlogEntry
        val titleTextView = rowView.findViewById(R.id.titleLabel) as TextView
        val descriptionTextView = rowView.findViewById(R.id.descriptionLabel) as TextView
        val authorView = rowView.findViewById(R.id.authorLabel) as TextView
        val dateView = rowView.findViewById(R.id.dateLabel) as TextView
        val profileImage = rowView.findViewById(R.id.profileImage) as ImageView

        titleTextView.text = blogEntry.title
        descriptionTextView.text = blogEntry.description
        authorView.text = blogEntry.author
        dateView.text = blogEntry.date

        DownloadImageTask(profileImage).execute("https://bv-content.beenverified.com/fit-in/60x/filters:autojpg()/${blogEntry.image_url}")

        return rowView
    }

    fun setImage (imageView: ImageView) {

    }

    inner class DownloadImageTask(bmImage: ImageView) : AsyncTask<String?, Void?, Bitmap?>() {
        private var bmImage: ImageView
        override fun doInBackground(vararg urls: String?): Bitmap? {
            val urldisplay = urls[0]
            var bmp: Bitmap? = null
            try {
                val `in`: InputStream = URL(urldisplay).openStream()
                bmp = BitmapFactory.decodeStream(`in`)
            } catch (e: Exception) {
                Log.e("Error", e.message)
                e.printStackTrace()
            }
            return bmp
        }

        override fun onPostExecute(result: Bitmap?) {
            bmImage.setImageBitmap(result)
            setImage(bmImage)
        }

        init {
            this.bmImage = bmImage
        }
    }
}

