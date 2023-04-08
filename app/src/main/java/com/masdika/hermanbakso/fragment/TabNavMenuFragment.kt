package com.masdika.hermanbakso.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.masdika.hermanbakso.databinding.FragmentTabNavMenuBinding

private var _binding: FragmentTabNavMenuBinding? = null
private val binding get() = _binding!!

class TabNavMenuFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTabNavMenuBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

}