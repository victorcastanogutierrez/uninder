package com.uninder.uninder.getMatches

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.uninder.uninder.R
import kotlinx.android.synthetic.main.get_matches.*
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.uninder.uninder.model.Person
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking
import org.jetbrains.anko.custom.async
import org.jetbrains.anko.doAsync
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors
import javax.xml.transform.Result
import kotlin.coroutines.experimental.suspendCoroutine


class MatchesFragment : Fragment(), GetMatchesView {


    private var presenter: GetMatchesPresenter = GetMatchesPresenterImpl(this)


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.get_matches, container, false)
    }

    override fun onStart() {
        super.onStart()
        presenter.loadMatches()
    }

    override fun onMatchesDataLoaded(data:MutableList<String>) {
        val output = mutableMapOf<String, String>()

        data.forEach({
            Log.d("Busca", "busca iumagen para $it")
            Person.findPersonImage(it, { uri:Uri ->
                Log.d("Encuentra", "Encuentra iumagen para $it")
                output[it] = uri.toString()
            })
        })

        Log.d("Acaba", "Acaba la carga")



        //matches_list.layoutManager = LinearLayoutManager(this.activity)
        //matches_list.adapter = MatchListAdapter(presenter.getMatches(), { email -> sendMail(email) },{
        //    view,url -> putCircularImage(view,url)
        //})
    }

    override fun putCircularImage( view: ImageView,url: String) {
        Glide.with(this.context!!).load(url).apply(RequestOptions.circleCropTransform()).into(view)
    }

    override fun sendMail(email: String) {

        val intent = Intent(Intent.ACTION_SENDTO)
        intent.data = Uri.parse("mailto:")
        intent.putExtra(Intent.EXTRA_EMAIL, mutableListOf<String>(email).toTypedArray())
        intent.putExtra(Intent.EXTRA_SUBJECT, "uninder")
        startActivity(intent)
    }

}