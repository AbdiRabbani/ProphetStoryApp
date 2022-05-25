package com.abdi.prophetstoryapp.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.abdi.prophetstoryapp.data.KisahResponse
import com.abdi.prophetstoryapp.databinding.ActivityDetailBinding
import com.abdi.prophetstoryapp.ui.MainViewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

class DetailActivity : AppCompatActivity() {

    private var _binding: ActivityDetailBinding? = null
    private val binding get() = _binding as ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var data = intent.getParcelableExtra<KisahResponse>(EXTRA_DATA)
        data?.let {
            binding.apply {
                detailNama.text = data.name
                detailTahun.text = data.thnKelahiran
                detailUsia.text = data.usia
                detailTempat.text = data.tmp
                detailDesk.text = data.description

                Glide.with(detailImage.context)
                    .load(data.imageUrl)
                    .into(detailImage)
            }
        }

    }

    companion object {
        const val EXTRA_DATA = "data"
    }
}