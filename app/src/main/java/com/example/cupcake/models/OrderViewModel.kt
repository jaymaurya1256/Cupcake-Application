package com.example.cupcake.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

private const val PRICE_PER_CAKE = 2.00
private const val PRICE_FOR_THE_SAME_DAY_PICKUP = 3.00
class OrderViewModel: ViewModel() {
    val dateOptions = pickUpDate()
    private val _quantity = MutableLiveData<Int>()
    val quantity: LiveData<Int> = _quantity

    private val _flavor = MutableLiveData<String>()
    val flavor: LiveData<String> = _flavor

    private val _date = MutableLiveData<String>()
    val date: LiveData<String> = _date

    private val _price = MutableLiveData<Double>()
    val price: LiveData<String> = Transformations.map(_price){
        NumberFormat.getCurrencyInstance().format(it)
    }

    init {
        resetOrder()
    }
    fun setQuantity(quantity:Int) {
        _quantity.value = quantity
        updatePrice()
    }
    fun setFlavour(flavour:String){
        _flavor.value = flavour
    }
    fun setDate(date:String){
        _date.value = date
        updatePrice()
    }
    fun resetOrder()
    {
        _quantity.value = 0
        _price.value = 0.0
        _flavor.value = ""
        _date.value = dateOptions[0]
    }
    /*Need to implement these function afterward
    fun calculatePriceWithDate(){}
    fun calculatePriceWithFlavor(flavourPrice:Int): Int {
        return quantity.value!! * flavourPrice
    }
    */
    fun updatePrice(){
        var calculatedPrice = (_quantity.value?:0).times(PRICE_PER_CAKE)
        if(dateOptions[0] == _date.value){
            calculatedPrice += PRICE_FOR_THE_SAME_DAY_PICKUP
        }
        _price.value = calculatedPrice
    }
    fun hasNoFlavourSet(): Boolean {
        return _flavor.value.isNullOrEmpty()
    }

    private fun pickUpDate():List<String>{
        val option = mutableListOf<String>()
        val formatter = SimpleDateFormat("E MMM d", Locale.getDefault())
        val calendar = Calendar.getInstance()
        repeat(4)
        {
            option.add(formatter.format(calendar.time))
            calendar.add(Calendar.DATE, 1)
        }
        return option
    }

}