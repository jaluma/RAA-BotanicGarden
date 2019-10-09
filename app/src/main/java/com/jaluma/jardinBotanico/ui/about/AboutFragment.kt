package com.jaluma.jardinBotanico.ui.about

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.jaluma.jardinBotanico.R

class AboutFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val layout = inflater.inflate(R.layout.fragment_about, container, false)

        val btn = layout.findViewById<Button>(R.id.github)

        btn.setOnClickListener { v -> goToGithub(v) }
        return layout
    }

    private fun goToGithub(view: View) {
        goToUrl("http://github.com/jaluma")
    }

    private fun goToUrl(url: String) {
        val uriUrl = Uri.parse(url)
        val launchBrowser = Intent(Intent.ACTION_VIEW, uriUrl)
        startActivity(launchBrowser)
    }
}