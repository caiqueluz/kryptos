package com.caiqueluz.kryptos.ui.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.caiqueluz.kryptos.databinding.CurrencyListItemBinding
import com.caiqueluz.kryptos.ui.vo.CurrencyDetailDialogVO
import com.caiqueluz.kryptos.ui.vo.CurrencyItemVO
import com.squareup.picasso.Picasso

class CurrencyAdapter(
    private val currencies: List<CurrencyItemVO>,
    private val onItemClicked: (CurrencyDetailDialogVO) -> Unit
) : RecyclerView.Adapter<CurrencyAdapter.CurrencyViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): CurrencyViewHolder {
        val binding = CurrencyListItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        return CurrencyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        val item = currencies[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = currencies.size

    inner class CurrencyViewHolder(
        private val binding: CurrencyListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: CurrencyItemVO) {
            item.imageUrl?.let { url ->
                Picasso.get()
                    .load(url.toUri())
                    .into(binding.currencyItemImage)
            }

            binding.currencyItemName.text = item.name
            binding.currencyItemSymbol.text = item.symbol

            val price = item.quote.priceInUsd.price
            binding.currencyItemPrice.text = price

            setupPriceInformationClickListener(item)
        }

        private fun setupPriceInformationClickListener(item: CurrencyItemVO) {
            binding.currencyItemPriceInformation.setOnClickListener {
                val detailDialogVO = CurrencyDetailDialogVO(
                    imageUrl = item.imageUrl,
                    name = item.name,
                    symbol = item.symbol,
                    lastUpdatedDate = item.quote.priceInUsd.lastUpdatedDate
                )

                onItemClicked.invoke(detailDialogVO)
            }
        }
    }
}
