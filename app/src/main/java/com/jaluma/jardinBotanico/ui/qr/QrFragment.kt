package com.jaluma.jardinBotanico.ui.qr

import android.Manifest.permission.CAMERA
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.zxing.Result
import me.dm7.barcodescanner.zxing.ZXingScannerView


class QrFragment : Fragment(), ZXingScannerView.ResultHandler {
    private val REQUEST_CAMERA: Int = 5
    lateinit var scannerView: ZXingScannerView
    private val googleSearch = "http://www.google.com/images?q="

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (!checkPermission()) {
            requestPermissions(arrayOf(CAMERA), REQUEST_CAMERA)
        }

        scannerView = ZXingScannerView(activity)
        return scannerView
    }

    private fun checkPermission(): Boolean {
        return ContextCompat.checkSelfPermission(context as Context, CAMERA) == PackageManager.PERMISSION_GRANTED
    }

    override fun handleResult(result: Result?) {
        val myResult = result?.text

        val builder = AlertDialog.Builder(context)
        builder.setTitle("Escaneo de flores:")
        builder.setPositiveButton("Buscar") { _, _ ->
            val uri = googleSearch + myResult?.replace(" ", "+")

            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
            startActivity(browserIntent)
        }
        builder.setMessage(result?.text)
        val alert1 = builder.create()
        alert1.show()

        Handler().postDelayed({ scannerView.resumeCameraPreview(this) }, 2000)
    }

    override fun onResume() {
        super.onResume()
        scannerView.setResultHandler(this)
        scannerView.startCamera()
    }

    override fun onPause() {
        super.onPause()
        scannerView.stopCamera()
    }

}

