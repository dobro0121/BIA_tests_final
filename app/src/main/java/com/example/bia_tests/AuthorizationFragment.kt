package com.example.bia_tests

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation
import com.arefbhrn.maskededittext.MaskedEditText
import com.google.firebase.FirebaseApp


class AuthorizationFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_authorization, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val editTextPhone = view.findViewById<MaskedEditText>(R.id.editTextPhone)
        val button = view.findViewById<Button>(R.id.buttonNext)
        editTextPhone.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }

            @SuppressLint("ResourceAsColor")
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(p3 == 16)
                {
                    button.setTextColor(Color.WHITE)
                    button.setBackgroundResource(R.drawable.frame_black_fill)
                    button.isClickable = true
                } else {
                    button.setTextColor(Color.parseColor("#B8C4DB"))
                    button.setBackgroundResource(R.drawable.frame_gray_fill)
                    button.isClickable = false
                }
            }

            override fun afterTextChanged(p0: Editable?) { }
        })

        button.setOnClickListener {
            val bundle = Bundle()
            Log.d("phone-number", editTextPhone.getText().toString())
            bundle.putString("phoneNumber", editTextPhone.getText().toString())
            Navigation.findNavController(view).navigate(R.id.enterCodeFragment, bundle)
        }
    }

}