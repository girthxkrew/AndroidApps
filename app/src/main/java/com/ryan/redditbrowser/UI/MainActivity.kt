package com.ryan.redditbrowser.UI

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ryan.redditbrowser.R
import com.ryan.redditbrowser.Retrofit.Data.RedditResponse
import com.ryan.redditbrowser.Retrofit.RedditAPI
import com.ryan.redditbrowser.UI.Recyclerview.RecyclerViewAdapter
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val redditAPI by lazy { RedditAPI.create() }

    private lateinit var searchView: SearchView

    private var dataAdapter: RecyclerViewAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        recyclerview.layoutManager = layoutManager
        recyclerview.visibility = View.INVISIBLE
        constraintLayout.setBackgroundResource(R.drawable.main_screen)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        val searchItem = menu!!.findItem(R.id.action_search)
        searchView = searchItem.actionView as SearchView
        searchView.setSubmitButtonEnabled(true)
        searchView.setIconifiedByDefault(false);
        searchView
        searchView.setQueryHint("Search for a subreddit")
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean {
                return true
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                search(query)
                searchView.clearFocus()
                return true
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

    private fun search(searchString: String) {
        if (!isConnected()) {
            if (recyclerview.isVisible) {
                recyclerview.visibility = View.INVISIBLE
            }
            constraintLayout.setBackgroundResource(R.drawable.nointernetsaccess)
        } else {
            var disposableSingleObserver = object : DisposableSingleObserver<RedditResponse>() {
                override fun onSuccess(t: RedditResponse) {
                    if (!t.data.children.isEmpty()) {
                        recyclerview.visibility = View.VISIBLE
                        constraintLayout.setBackgroundResource(0)
                        if (dataAdapter == null) {
                            dataAdapter = RecyclerViewAdapter(t.data.children, this@MainActivity)
                            recyclerview.adapter = dataAdapter
                        } else {
                            dataAdapter!!.swap(t.data.children)
                        }
                    } else {
                        if (recyclerview.isVisible) {
                            recyclerview.visibility = View.INVISIBLE
                        }
                        constraintLayout.setBackgroundResource(R.drawable.nosubbreddits)
                    }
                }

                override fun onError(e: Throwable) {
                    if (recyclerview.isVisible) {
                        recyclerview.visibility = View.INVISIBLE
                    }
                    constraintLayout.setBackgroundResource(R.drawable.nosubbreddits)
                }

            }
            var searchQuery: Single<RedditResponse> = redditAPI.getData(searchString)
            searchQuery.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(disposableSingleObserver)
        }
    }

    fun isConnected(): Boolean {
        val cm = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true
        return isConnected
    }
}
