package com.abdi.prophetstoryapp.ui

import android.content.Intent
import android.nfc.NfcAdapter.EXTRA_DATA
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.abdi.prophetstoryapp.R
import com.abdi.prophetstoryapp.data.KisahResponse
import com.abdi.prophetstoryapp.data.utils.OnItemClickCallback
import com.abdi.prophetstoryapp.databinding.ActivityMainBinding
import com.abdi.prophetstoryapp.ui.detail.DetailActivity

class MainActivity : AppCompatActivity(), OnItemClickCallback {

    private var _binding : ActivityMainBinding? = null
    private val binding get() = _binding as ActivityMainBinding

    private var _viewModel : MainViewModel? = null
    private val viewModel get() = _viewModel as MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        _viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.getData()

        viewModel.kisahresponse.observe(this) { showData(it) }
        viewModel.isLoading.observe(this) { showLoading(it) }
        viewModel.isError.observe(this) { showError(it) }
    }

    private fun showData(data: List<KisahResponse>?) {
        binding.recyclerMain.apply {
            val mAdapter = KisahAdapter()
            mAdapter.setData(data)
            mAdapter.setOnItemClickCallback(this@MainActivity)
            layoutManager = GridLayoutManager(this@MainActivity, 2)
            adapter = mAdapter
        }
    }

    private fun showLoading(isLoading: Boolean?) {
        if (isLoading == true) {
            binding.progressMain.visibility = View.VISIBLE
            binding.recyclerMain.visibility = View.INVISIBLE
        } else {
            binding.progressMain.visibility = View.INVISIBLE
            binding.recyclerMain.visibility = View.VISIBLE
        }
    }

    private fun showError(error: Throwable?) {
        Log.e("MainActivity", "showError : $error")
    }

    override fun onItemClicked(item: KisahResponse) {
        startActivity(Intent(applicationContext, DetailActivity::class.java).putExtra(DetailActivity.EXTRA_DATA, item))
    }
}