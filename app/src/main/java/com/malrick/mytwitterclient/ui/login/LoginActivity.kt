package com.malrick.mytwitterclient.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.malrick.mytwitterclient.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_login.*
import android.widget.Toast
import com.malrick.mytwitterclient.R
import com.malrick.mytwitterclient.ui.oauth.OAuthActivity


class LoginActivity : AppCompatActivity(), LoginMvpView  {

    private val presenter = LoginPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        presenter.attachView(this)

        // Écoute du bouton de login
        loginButton.setOnClickListener { presenter.performLogin() }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        // Si le code retourné par l'activité de login correspond au code de succès (CODE_OAUTH)
        // Alors on redirige vers la fonction ok, sinon, on redirige vers la fonction de failure
        if(requestCode == LoginPresenter.CODE_OAUTH)
            if(resultCode == RESULT_OK)
                presenter.onResultOk()
            else
                presenter.onResultCanceled()
    }

    override fun showOauthActivity(requestCode: Int) {
        // Lancement de l'activité OAuth en attendant la réponse "requestCode"
        startActivityForResult(Intent(this, OAuthActivity::class.java), requestCode)
    }

    override fun showLoginError() {
        // Toast d'erreur
        Toast.makeText(this, getString(R.string.error), Toast.LENGTH_LONG).show()
    }

    override fun showLoginCanceled() {
        // Toast de cancel
        Toast.makeText(this, getString(R.string.cancellation), Toast.LENGTH_LONG).show()
    }


    override fun moveOn() {
        // Redirection en cas de login success
        startActivity(Intent(this, MainActivity::class.java))

        // Destruction de l'activité
        finish()
    }

}