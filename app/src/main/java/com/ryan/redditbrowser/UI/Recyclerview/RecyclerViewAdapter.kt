package com.ryan.redditbrowser.UI.Recyclerview

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.ryan.redditbrowser.R
import com.ryan.redditbrowser.Retrofit.Data.Children
import com.ryan.redditbrowser.UI.CustomTabHelper
import com.ryan.redditbrowser.UI.WebView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.recyclerview_item.view.*


class RecyclerViewAdapter(var children: MutableList<Children>, val context: Context) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    private var customTabHelper: CustomTabHelper = CustomTabHelper()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerViewAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.recyclerview_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return children.size
    }

    override fun onBindViewHolder(holder: RecyclerViewAdapter.ViewHolder, position: Int) {
        holder.post.text = children.get(position).data.title
        holder.subreddit.text = "r/" + children.get(position).data.subreddit
        if (children.get(position).data.preview != null) {
            Picasso.get().load(
                children.get(position).data.preview.images.get(0).source.url.replace(
                    "&amp;",
                    "&"
                )
            )
                .resize(600, 600)
                .centerInside()
                .into(holder.image)
        } else {
            holder.image.visibility = View.GONE;
        }
        var link: String = "https://www.reddit.com" + children.get(position).data.permalink
        holder.itemView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                val builder = CustomTabsIntent.Builder()
                builder.setToolbarColor(ContextCompat.getColor(context, R.color.colorPrimary))
                builder.addDefaultShareMenuItem()
                val anotherCustomTab = CustomTabsIntent.Builder().build()
                val requestCode = 100
                val intent = anotherCustomTab.intent
                intent.setData(Uri.parse(link))
                val pendingIntent = PendingIntent.getActivity(
                    context,
                    requestCode,
                    intent,
                    PendingIntent.FLAG_UPDATE_CURRENT
                )
                builder.addMenuItem(children.get(position).data.title, pendingIntent)
                builder.setShowTitle(true)
                builder.setStartAnimations(context, android.R.anim.fade_in, android.R.anim.fade_out)
                builder.setExitAnimations(context, android.R.anim.fade_in, android.R.anim.fade_out)
                val customTabsIntent = builder.build()
                val packageName = customTabHelper.getPackageNameToUse(context, link)
                if (packageName == null) {
                    val intentOpenUri = Intent(context, WebView::class.java)
                    intentOpenUri.putExtra("URL", link)
                    context.startActivity(intentOpenUri)
                } else {
                    customTabsIntent.intent.setPackage(packageName)
                    customTabsIntent.launchUrl(context, Uri.parse(link))
                }
            }

        })
    }

    fun swap(newChildren: MutableList<Children>) {
        children.clear()
        children.addAll(newChildren)
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var post = view.post
        var subreddit = view.subbreddit
        var image = view.image
    }
}