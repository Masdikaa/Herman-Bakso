package com.masdika.hermanbakso.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.masdika.hermanbakso.databinding.FragmentRiwayatBinding

private var _binding: FragmentRiwayatBinding? = null
private val binding get() = _binding!!

class RiwayatFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRiwayatBinding.inflate(inflater, container, false)
        return binding.root
    }

}