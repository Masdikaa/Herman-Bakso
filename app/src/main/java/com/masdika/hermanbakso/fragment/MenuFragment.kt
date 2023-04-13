package com.masdika.hermanbakso.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.masdika.hermanbakso.R
import com.masdika.hermanbakso.adapter.MenuAdapter
import com.masdika.hermanbakso.databinding.FragmentMenuBinding
import com.masdika.hermanbakso.model.MenuMakananData

private var _binding: FragmentMenuBinding? = null
private val binding get() = _binding!!

class MenuFragment : Fragment() {

    //Menu Makanan RV
    private lateinit var adapter: MenuAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var menuArrayList: ArrayList<MenuMakananData>

    lateinit var menuImage: Array<Int>
    lateinit var menuTitle: Array<String>
    lateinit var menuPrice: Array<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initiating data from recycle view
        dataInitialize()
    }

    private fun dataInitialize() {
        menuTitle = arrayOf(
            getString(R.string.bakso_tahu),
            getString(R.string.bakso_tuna),
            getString(R.string.bakso_pedas),
            getString(R.string.bakso_pedas_jumbo),
            getString(R.string.bakso_lava_kematian),
            getString(R.string.bakso_urat),
            getString(R.string.mie_ayam_bakso),
            getString(R.string.mie_ayam_bakso_ceker),
            getString(R.string.mie_ayam_babi)
        )
        menuPrice = arrayOf(
            getString(R.string.bakso_tahu_price),
            getString(R.string.bakso_tuna_price),
            getString(R.string.bakso_pedas_price),
            getString(R.string.bakso_pedas_jumbo_price),
            getString(R.string.bakso_lava_kematian_price),
            getString(R.string.bakso_urat_price),
            getString(R.string.mie_ayam_bakso_price),
            getString(R.string.mie_ayam_bakso_ceker_price),
            getString(R.string.mie_ayam_babi_price),
        )
        menuImage = arrayOf(
            R.drawable.bakso_tahu,
            R.drawable.bakso_tuna,
            R.drawable.bakso_pedas,
            R.drawable.bakso_pedas_jumbo,
            R.drawable.bakso_lava,
            R.drawable.bakso_urat,
            R.drawable.mie_ayam_bakso,
            R.drawable.mie_ayam_bakso_ceker,
            R.drawable.mie_babi
        )
        recyclerView = binding.recyclerViewMenu
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)
        menuArrayList = arrayListOf()
        getData()
    }

    private fun getData() {
        for (i in menuImage.indices) {
            val dataMenuMakanan = MenuMakananData(menuTitle[i], menuPrice[i], menuImage[i])
            menuArrayList.add(dataMenuMakanan)
        }
        adapter = MenuAdapter(menuArrayList)
        recyclerView.adapter = adapter
        adapter.setOnItemClickListener(object : MenuAdapter.onItemClickListener {
            override fun onItemClick(position: Int) {
                Toast.makeText(context, "Clicked : $position", Toast.LENGTH_SHORT).show()
            }
        })
    }
}