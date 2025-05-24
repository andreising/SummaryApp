package com.andreising.summaryapp.presentation.fragments.choose_lvl

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.andreising.summaryapp.R
import com.andreising.summaryapp.databinding.FragmentChooseLvlBinding
import dev.androidbroadcast.vbpd.viewBinding

class ChooseLvlFragment : Fragment(R.layout.fragment_choose_lvl) {

    private val binding: FragmentChooseLvlBinding by viewBinding(FragmentChooseLvlBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_choose_lvl, container, false)
    }

}