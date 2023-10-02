package com.example.androidmarkettest

import android.app.Dialog
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidmarkettest.databinding.ItemProductBinding
import com.google.android.material.slider.Slider

class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemProductBinding.bind(view)
    fun bind(p: Product) {
        binding.tvProductNameItem.text = p.name
        binding.tvProductDescriptionItem.text = p.description
        binding.tvProductPriceItem.text = setPrice(p.pricePerUnit)
        binding.tvProductUnitItem.text = p.unit

        setOnClickRow(p)
    }

    private fun setOnClickRow(p: Product) {

        val dialog = Dialog(binding.tvProductNameItem.context)
        dialog.setContentView(R.layout.dialog_add_product)

        val name = dialog.findViewById<TextView>(R.id.tvProductNameDialog)
        val descrption = dialog.findViewById<TextView>(R.id.tvProductDescriptionDialog)
        val price = dialog.findViewById<TextView>(R.id.tvProductPriceDialog)
        val unit = dialog.findViewById<TextView>(R.id.tvProductUnitDialog)
        val sCurrentQuantity = dialog.findViewById<Slider>(R.id.sCurrentQuantityDialog)
        val tvCurrentQuantity = dialog.findViewById<TextView>(R.id.tvCurrentQuantityDialog)
        val tvCurrentQuantityPrice = dialog.findViewById<TextView>(R.id.tvCurrentPriceDialog)

        binding.root.setOnClickListener {


            name.text = p.name
            descrption.text = p.description
            price.text = p.pricePerUnit.toString()
            unit.text = p.unit

            dialog.show()
        }

        initListenerDialog(p, sCurrentQuantity, tvCurrentQuantity, tvCurrentQuantityPrice)
    }

    private fun initListenerDialog(p: Product,
        sCurrentQuantity: Slider,
        tvCurrentQuantity: TextView,
        tvCurrentQuantityPrice: TextView
    ) {
        sCurrentQuantity.addOnChangeListener { _, value, _ ->
            tvCurrentQuantity.text = "${value}g"
            tvCurrentQuantityPrice.text = calculatePrice(p,value)
        }
    }

    private fun calculatePrice(p:Product, value: Float): CharSequence {
        var result = (value * p.pricePerUnit) / 100
        return " = $${result}"
    }


    private fun setPrice(price: Int): CharSequence {
        return "$$price"
    }


}