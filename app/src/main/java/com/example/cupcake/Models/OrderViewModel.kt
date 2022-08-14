package com.example.cupcake.Models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.nio.channels.FileLock
import kotlin.properties.Delegates

class OrderViewModel: ViewModel() {
    private val _quantity = MutableLiveData<Int>(0)
    val quantity: LiveData<Int> = _quantity

    private val _flavor = MutableLiveData<String>("")
    val flavor: LiveData<String> = _flavor

    private val _date = MutableLiveData<String>("")
    val date: LiveData<String> = _date

    private val _price = MutableLiveData<Double>(0.0)
    val price: LiveData<Double> = _price

    fun setQuantity(quantity:Int) {
        _quantity.value = quantity
    }
    fun setFlavour(flavour:String){
        _flavor.value = flavour
    }
    fun setDate(date:String){
        _date.value = date
    }
    fun calculatePrice(flavourPrice:Int): Int {
        return quantity.value!! * flavourPrice
    }
}