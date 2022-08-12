package com.example.android.shadestoreinventory

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.android.shadestoreinventory.databinding.FragmentShadeListBinding
import com.example.android.shadestoreinventory.databinding.ItemShadeLayoutBinding

class ShadeListFragment : Fragment() {
    private lateinit var viewModel: ShadeViewModel
    private lateinit var binding: FragmentShadeListBinding
    private lateinit var llbinding: ItemShadeLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_shade_list,
            container,
            false
        )

        // Get the viewmodel. Use requireActivity() instead of "this" to be able to access same data from different fragments, such as this fragment and AddNew
        viewModel = ViewModelProvider(requireActivity()).get(ShadeViewModel::class.java)

        // Set the viewmodel for databinding - this allows the bound layout access to all of the
        // data in the VieWModel

        binding.shadeViewModel = viewModel

        // Specify the current activity as the lifecycle owner of the binding. This is used so that
        // the binding can observe LiveData updates

        binding.setLifecycleOwner(this)

        viewModel.shades.observe(viewLifecycleOwner, Observer { newShades: MutableList<Shade>->

                for (element in newShades) {
                    llbinding = DataBindingUtil.inflate(inflater, R.layout.item_shade_layout, container, false)
                    llbinding.shade = element
                    binding.apply { linearLayout.addView(llbinding.root) }
            }
        })

        binding.addButton.setOnClickListener{view: View ->

            Navigation.findNavController(view).navigate(R.id.action_shadeListFragment_to_addNew)

        }

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.overflow_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        // Navigate to login screen through nav graph action so that you can pop off th back stack. Not possibile with NavigationUI.onNavDestinationSelected
        NavHostFragment.findNavController(this).navigate(R.id.action_shadeListFragment_to_loginScreen)
        return super.onOptionsItemSelected(item)
    }


}