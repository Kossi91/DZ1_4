package com.example.dz1_4.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import com.example.dz1_4.databinding.FragmentMainBinding
import com.example.dz1_4.ui.adapters.FirstAdapter
import com.example.dz1_4.ui.adapters.SecondAdapter
import com.example.dz1_4.ui.model.MainModel
import com.example.dz1_4.ui.repository.ModelRepository

class MainFragment : Fragment() {

    private var binding: FragmentMainBinding? = null
    private var adapterFirst: FirstAdapter? = null
    private var adapterSecond: SecondAdapter? = null
    private val repository = ModelRepository()
    private var mainList: ArrayList<MainModel>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
    }

    private fun initialize() {
        mainList = repository.getListModelData()
        adapterFirst = FirstAdapter(mainList as ArrayList<MainModel>, this::onClickFirstListener)
        adapterSecond = SecondAdapter(mainList as ArrayList<MainModel>, this::onClickSecondListener)
        val concatAdapter = ConcatAdapter(adapterFirst, adapterSecond)
        binding?.rvMain?.adapter = concatAdapter
    }

    private fun onClickFirstListener(mainModel: MainModel) {
        findNavController().navigate(
            MainFragmentDirections.actionMainFragmentToDetailFragment(
                mainModel.name
            )
        )
    }

    private fun onClickSecondListener(mainModel: MainModel) {
        findNavController().navigate(
            MainFragmentDirections.actionMainFragmentToSecondDetailFragment(
                mainModel.image
            )
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mainList?.clear()
        binding = null
    }
}