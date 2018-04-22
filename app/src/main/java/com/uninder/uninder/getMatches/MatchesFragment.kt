package com.uninder.uninder.getMatches

import android.app.Dialog
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
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.uninder.uninder.model.Person
import org.jetbrains.anko.support.v4.indeterminateProgressDialog


class MatchesFragment : Fragment(), GetMatchesView {

    private var presenter: GetMatchesPresenter = GetMatchesPresenterImpl(this)

    private lateinit var indeterminateDialog: Dialog

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.get_matches, container, false)
    }

    override fun onStart() {
        super.onStart()
        presenter.loadMatches()
    }

    override fun onMatchesDataLoaded(data:MutableList<String>) {
        presenter.loadMatchesImages(data, { setUpAdapter(it) })

    }

    private fun setUpAdapter(data:MutableMap<String, String>) {
        matches_list.layoutManager = LinearLayoutManager(this.activity)
        matches_list.adapter = MatchListAdapter(data, { email -> sendMail(email) },{
            view,url -> putCircularImage(view,url)
        })
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

    override fun showIndeterminateLoading() {
        this.indeterminateDialog = indeterminateProgressDialog(getString(R.string.loadingContent))
        this.indeterminateDialog.show()
    }

    override fun hideIndeterminateLoading() {
        indeterminateDialog.dismiss()
    }

}