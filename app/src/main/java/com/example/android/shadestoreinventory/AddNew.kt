package com.example.android.shadestoreinventory

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.android.shadestoreinventory.databinding.FragmentAddNewBinding

class AddNew : Fragment() {
    private lateinit var binding: FragmentAddNewBinding
    private lateinit var viewModel: ShadeViewModel
    private lateinit var newShade: Shade

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_add_new, container, false)
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_add_new,
            container,
            false
        )

        // Get the viewmodel. Use requireActivity() instead of "this" to be able to access same data from different fragments, such as this fragment and ShadeListFragment
        viewModel = ViewModelProvider(requireActivity()).get(ShadeViewModel::class.java)

        binding.shadeViewModel = viewModel
        newShade = Shade("", "", "","")
        binding.shade = newShade

        binding.setLifecycleOwner(this.parentFragment)

        binding.createButton.setOnClickListener{view: View ->

            newShade = Shade(binding.shade!!.shade, binding.shade!!.company,
                binding.shade!!.description, binding.shade!!.color)

            binding.shade = newShade

            if (binding.shade!!.shade == "" || binding.shade!!.company == "" || binding.shade!!.description == "" || binding.shade!!.color == ""){
                Toast.makeText(requireActivity(), "Please fill all the fields", Toast.LENGTH_SHORT).show()
            }
            else {
                viewModel.addNewShade(newShade)
                Navigation.findNavController(view).navigate(R.id.action_addNew_to_shadeListFragment)
            }
        }

        binding.backButton.setOnClickListener { view: View ->
            Navigation.findNavController(view).navigate(R.id.action_addNew_to_shadeListFragment)
        }
    return binding.root
    }
}