package com.wlp.mykt.example

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.wlp.mykt.R
import com.wlp.mykt.databinding.ActivityVbBinding

class ViewDataBindingActivity : AppCompatActivity() {
    private lateinit var viewModel2: SecondViewModel
    private val secondViewModel:SecondViewModel by viewModels()
    //private val user by lazy { User("abc", 12) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityVbBinding>(this, R.layout.activity_vb)
        binding.user = User("wang", 11)

        var viewModel = ViewModelProvider(this)[FirstViewModel::class.java]
        binding.vm = viewModel

        viewModel.number.observe(this) {
            Toast.makeText(this, "number:$it", Toast.LENGTH_SHORT).show();
        }

        viewModel2 = ViewModelProvider(this)[SecondViewModel::class.java]
        //让ViewModel拥有View的生命周期感应
        binding.vm2 = viewModel2
        lifecycle.addObserver(viewModel2)
    }

    override fun onDestroy() {
        super.onDestroy()
        lifecycle.removeObserver(viewModel2)
    }

}