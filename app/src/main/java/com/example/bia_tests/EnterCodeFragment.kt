package com.example.bia_tests

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.Navigation
import com.arefbhrn.maskededittext.MaskedEditText
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit

class EnterCodeFragment : Fragment() {

    private var forceResendingToken: PhoneAuthProvider.ForceResendingToken? = null
    private var mCallbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks? = null
    private var mVerificationId: String? = null
    private lateinit var mAuth: FirebaseAuth
    private val TAG = "MAIN_TAG"
    private lateinit var view_: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_enter_code, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view_ = view
        val textViewEnterNumber = view.findViewById<TextView>(R.id.textViewEnterNumber)
        val phoneNumber = arguments?.getString("phoneNumber")
        val phoneNumberVerify = phoneNumber?.trim()
        mAuth= FirebaseAuth.getInstance()
        textViewEnterNumber.text = "Код был отправлен на номер телефона\n" + phoneNumber

        val editTextCode = view.findViewById<MaskedEditText>(R.id.editTextCode)
        val buttonNextCode = view.findViewById<Button>(R.id.buttonNextCode)
        editTextCode.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }

            @SuppressLint("ResourceAsColor")
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(p3 == 11)
                {
                    buttonNextCode.setTextColor(Color.WHITE)
                    buttonNextCode.setBackgroundResource(R.drawable.frame_black_fill)
                    buttonNextCode.isClickable = true
                } else {
                    buttonNextCode.setTextColor(Color.parseColor("#B8C4DB"))
                    buttonNextCode.setBackgroundResource(R.drawable.frame_gray_fill)
                    buttonNextCode.isClickable = false
                }
            }

            override fun afterTextChanged(p0: Editable?) { }
        })

        mCallbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(phoneAuthCredential: PhoneAuthCredential) {
                signInWithPhoneAuthCredential(phoneAuthCredential)
                Navigation.findNavController(view).navigate(R.id.tasksFragment)
            }

            override fun onVerificationFailed(e: FirebaseException) {
                Toast.makeText(requireContext(), "${e.message}", Toast.LENGTH_SHORT).show()
            }

            override fun onCodeSent(verificationId: String, token: PhoneAuthProvider.ForceResendingToken) {
                Log.d(TAG, "onCodeSent: $verificationId")
                mVerificationId = verificationId
                forceResendingToken = token
            }

        }

        // начало верификации
        if (phoneNumberVerify != null) {
            startPhoneNumberVerification(phoneNumberVerify)
        } else {
            Toast.makeText(requireContext(), "Введите номер телефона!", Toast.LENGTH_SHORT).show()
        }

        buttonNextCode.setOnClickListener {
            val editTextCode = view.findViewById<MaskedEditText>(R.id.editTextCode)
            val code = editTextCode.text.toString().replace(" ","")
            Log.d("code", code.replace(" ", ""))
            verifyPhoneNumberWithCode(mVerificationId, code)

        }

        // Вернуться назад
        val buttonBack = view.findViewById<ImageButton>(R.id.imageButtonBack)
        buttonBack.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.authorizationFragment)
        }

        // Обратный отсчет времени для повторной отправки кода
        val textViewCountTime = view.findViewById<TextView>(R.id.textViewNewCode)
        object : CountDownTimer(59000, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                textViewCountTime.text = "Выслать повторный код (" + millisUntilFinished / 1000 + ")"
                textViewCountTime.isClickable = false
            }

            override fun onFinish() {
                textViewCountTime.text = "Выслать повторный код"
                textViewCountTime.setTextColor(Color.RED)
                textViewCountTime.paintFlags = Paint.UNDERLINE_TEXT_FLAG
                textViewCountTime.isClickable = true
            }
        }.start()

        // Повторная отправка кода
        textViewCountTime.setOnClickListener {
            if (textViewCountTime.text.equals("Выслать повторный код")) {
                val phoneNumberVerify = phoneNumber?.trim()
                if (phoneNumberVerify != null) {
                    resendVerificationCode(phoneNumberVerify, forceResendingToken)
                } else {
                    Toast.makeText(requireContext(), "Введите номер телефона!", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }

    }
    
    private fun startPhoneNumberVerification(phone: String) {
        val options = mCallbacks?.let {
            PhoneAuthOptions.newBuilder(mAuth)
                .setPhoneNumber(phone)
                .setTimeout(60L, TimeUnit.SECONDS)
                .setActivity(requireActivity())
                .setCallbacks(it).build()
        }

        if (options != null) {
            PhoneAuthProvider.verifyPhoneNumber(options)
        }
    }
    
    private fun resendVerificationCode(phone: String, token: PhoneAuthProvider.ForceResendingToken?) {
        val options = mCallbacks?.let {
            PhoneAuthOptions.newBuilder(mAuth)
                .setPhoneNumber(phone)
                .setTimeout(60L, TimeUnit.SECONDS)
                .setActivity(requireActivity())
                .setCallbacks(it)
                .setForceResendingToken(token!!).build()
        }

        if (options != null) {
            PhoneAuthProvider.verifyPhoneNumber(options)
        }
    }
    
    private fun verifyPhoneNumberWithCode(verificationId: String?, code: String) {
        val credential = verificationId?.let { PhoneAuthProvider.getCredential(it, code) }
        signInWithPhoneAuthCredential(credential)
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential?) {
        mAuth.signInWithCredential(credential!!)
            .addOnSuccessListener {
                val phone = mAuth.currentUser?.phoneNumber
                Toast.makeText(requireContext(), "Аутентификация $phone прошла успешно!", Toast.LENGTH_SHORT).show()
                Navigation.findNavController(view_).navigate(R.id.tasksFragment)
            }.addOnFailureListener { e ->
                Toast.makeText(requireContext(), "Введен неверный код из SMS!", Toast.LENGTH_SHORT).show()
            }
    }

}