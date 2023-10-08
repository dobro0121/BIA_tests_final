package com.example.bia_tests

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import androidx.fragment.app.findFragment
import androidx.navigation.Navigation

class ProfileSickFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile_sick, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buttonAddSick = view.findViewById<ImageButton>(R.id.imageButtonAddSick)
        buttonAddSick.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.newSickFragment)
        }

        val buttonBack = view.findViewById<ImageButton>(R.id.imageButtonBackSick)
        buttonBack.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.profileFragment)
        }
    }

}