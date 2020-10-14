package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.util.concurrent.Future

private var counter = 0
private lateinit var loginButton: Button
private lateinit var loginEditText: EditText
private lateinit var passwordEditText: EditText
private lateinit var counterTextView: TextView
private lateinit var credentialsRepo: CredentialsRepo


class LoginFragment : Fragment() {

    private fun addPrefs(login: String, password: String) {
        credentialsRepo.insertCredentials(Credentials(1, login, password))
    }

    private fun getPref(): Future<Credentials?> {
        return credentialsRepo.getCredentials()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        credentialsRepo = CredentialsRepo(requireActivity().application)

        loginButton = view.findViewById(R.id.loginButton)
        loginEditText = view.findViewById(R.id.editTextLogin)
        passwordEditText = view.findViewById(R.id.editTextPassword)
        counterTextView = view.findViewById(R.id.counterTextView)

        counterTextView.text = counter.toString()
        getPref().get()?.let {
            loginEditText.setText(it.login)
            passwordEditText.setText(it.password)
        }

        loginButton.setOnClickListener {
            addPrefs(loginEditText.text.toString(), passwordEditText.text.toString())
        }
    }

    override fun onResume() {
        println("On resume: $counter")
        counterTextView.text = counter.toString()
        counter++

        super.onResume()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

/*    companion object {
        */
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment.
     *//*
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LoginFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }
    }*/
}


