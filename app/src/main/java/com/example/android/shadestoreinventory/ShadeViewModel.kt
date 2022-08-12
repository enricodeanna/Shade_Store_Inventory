package com.example.android.shadestoreinventory

import android.util.Log
import android.util.Log.INFO
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.logging.Level.INFO

class ShadeViewModel: ViewModel() {
    lateinit var shades: MutableLiveData<MutableList<Shade>>
/*
    lateinit var shadeName : String
    lateinit var companyName : String
    lateinit var shadeDescription : String
    lateinit var shadeColor : String
*/


    init {
        if (!this::shades.isInitialized){
            resetList()
        }
    }

    override fun onCleared() {
        super.onCleared()
    }

    fun resetList() {

            shades = MutableLiveData<MutableList<Shade>>() //Initialised as null
            shades.value = arrayListOf(
                Shade("Predator", "RayBan", "Matrix Style sunglasses", "Black"),
                Shade("Aviator", "RayBan", "A true classic!", "Brown")
            )
            shades.value!!.add(Shade("Predator", "Zoogs", "Swimming goggles with mirror lenses", "Blue"))

    }

/*    fun getShadeData(element: Int) {
        shadeName = shades.value!![element].shade
        companyName = shades.value!![element].company
        shadeDescription = shades.value!![element].description
        shadeColor = shades.value!![element].color
    }
    fun addNewShade(name: String, company: String, description: String, color: String){
        shades.value?.add(Shade(name, company, description, color))
    }*/

    fun addNewShade(newShade: Shade){
        shades.value?.add(newShade)
    }

    /*fun getShadesNum() : Int{
        return shades.value!!.size
    }
*/
}