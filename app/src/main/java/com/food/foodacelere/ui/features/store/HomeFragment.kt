package com.food.foodacelere.ui.features.store

import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.task.adapter.PageAdapter
import com.food.foodacelere.R
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_item_category.*
import kotlinx.android.synthetic.main.item_tab_master.*


class HomeFragment : Fragment() {

    lateinit var mViewPager: ViewPager
    lateinit var mTabLayout: TabLayout
    lateinit var productsList: ArrayList<Product>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_item_category, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).setSupportActionBar(main_toolbar)

        startInstances()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun startInstances() {
        mViewPager = pager
        mTabLayout = main_tablelayout
        setupViewPager(mViewPager)
        mTabLayout.setupWithViewPager(mViewPager)


    }

    private fun setupViewPager(viewPager: ViewPager) {
        val tab1 = ItemTabLunch()
        val bundleTab1 = Bundle()
        bundleTab1.putParcelableArrayList(PRODUCTS_KEY,getListLunch())
        tab1.arguments = bundleTab1

        val tab2 = ItemTabLunch()
        val bundleTab2 = Bundle()
        bundleTab2.putParcelableArrayList(PRODUCTS_KEY,getListPlates())
        tab2.arguments = bundleTab2

        val tab3 = ItemTabLunch()
        val bundleTab3 = Bundle()
        bundleTab3.putParcelableArrayList(PRODUCTS_KEY,getListDrinks())
        tab3.arguments = bundleTab3

        val adapter = PageAdapter(requireActivity().supportFragmentManager)
        adapter.addFragment(tab1, "Lanches")
        adapter.addFragment(tab2, "Pratos")
        adapter.addFragment(tab3, "Bebidas")
        viewPager.adapter = adapter
    }

    private fun getListLunch() =
        arrayListOf(
            Product("Bib's Chicken Crispy", "R$ 10,90", "https://www.habibs.com.br/storage/products/images/7085_interna.png"),
            Product("Beirute Tradicional", "R$ 16,30", "https://www.habibs.com.br/storage/products/images/25_interna.png"),
            Product("Mega Bib's Burger", "R$ 18,70", "https://www.habibs.com.br/storage/products/images/9708_interna.png"),
            Product("Habibão", "R$ 20,90", "https://www.habibs.com.br/storage/products/images/10127_interna.png"),
            Product("Combo Chicken Crispy (Batata Grande + Coca 300ML)", "R$ 23,40", "https://www.habibs.com.br/storage/products/images/8395_carrinho.png"),
            Product("Combo Bib's Cheese Salada (Batata Grande + Coca 300ML)", "R$ 25,80", "https://www.habibs.com.br/storage/products/images/8928_carrinho.png")
        )
    private fun getListPlates() =
        arrayListOf(
            Product("Filé de Frango Grelhado com Arroz e Fritas", "R$ 19,90", "https://www.habibs.com.br/storage/products/images/11549_carrinho.png"),
            Product("Estrofonofe de Frango com Arroz e Fritas", "R$ 19,90", "https://www.habibs.com.br/storage/products/images/11550_carrinho.png"),
            Product("Bife à Rolê com Arroz e Fritas", "R$ 23,90", "https://www.habibs.com.br/storage/products/images/11559_carrinho.png"),
            Product("Combo Bife À Rolê (Ref. Lata/suco 300ml + Sobremesa)", "R$ 25,80", "https://www.habibs.com.br/storage/products/images/12272_carrinho.png"),
            Product("Filé De Frango A Parmegiana Com Arroz", "R$ 27,90", "https://www.habibs.com.br/storage/products/images/11556_carrinho.png"),
            Product("Estrogonofe De Carne Com Arroz E Fritas", "R$ 23,90", "https://www.habibs.com.br/storage/products/images/11557_carrinho.png")
        )
    private fun getListDrinks() =
        arrayListOf(
            Product("Coca-cola Zero (Lata)","R$ 8,90","https://www.habibs.com.br/storage/products/images/499_interna.png"),
            Product("Coca-cola Zero (Lata)","R$ 8,90","https://www.habibs.com.br/storage/products/images/521_interna.png"),
            Product("Coca-cola (2 Litros)","R$ 11,90","https://www.habibs.com.br/storage/products/images/531_interna.png"),
            Product("Coca-cola Zero (2 Litros)","R$ 11,90","https://www.habibs.com.br/storage/products/images/585_interna.png"),
            Product("Guaraná Kuat (2 Litros)","R$ 11,90","https://www.habibs.com.br/storage/products/images/1563_interna.png")
        )

    companion object {
        const val PRODUCTS_KEY = "products"
    }



}