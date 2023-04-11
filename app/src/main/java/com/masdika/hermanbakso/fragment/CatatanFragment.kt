package com.masdika.hermanbakso.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.masdika.hermanbakso.databinding.FragmentCatatanBinding

private var _binding: FragmentCatatanBinding? = null
private val binding get() = _binding!!

class CatatanFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCatatanBinding.inflate(inflater, container, false)
        return binding.root
    }
}