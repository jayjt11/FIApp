package com.expleo.expleodemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.fiserv.dps.mobile.sdk.bridge.controller.Bridge
import com.fiserv.dps.mobile.sdk.bridge.model.Zelle

class MainActivity : AppCompatActivity() {

    private val zelle = Zelle(
        institutionId = "some_id",
        ssoKey = "sso_key",
        parameters = mapOf(
            "param1" to "1234",
            "param2" to "something",
            "param3" to "abc123"
        )
    )

    private val bridge: Bridge by lazy {
        Bridge(
            activity = this,
            config = zelle
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // optionally: set the contact pre-caching flag (default: false)
        zelle.preCacheContacts = true

        val view = bridge.view()
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_holder, view)
            commit()
        }
    }

    fun doSomething(view: View) {
        val popup = bridge.popup()
        popup.show(supportFragmentManager, popup.tag)
    }
}
