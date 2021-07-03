package net.simplifiedcoding.multiviewlist.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import net.simplifiedcoding.multiviewlist.data.network.Resource
import net.simplifiedcoding.multiviewlist.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<HomeViewModel>()
    private val homeRecyclerViewAdapter = HomeRecyclerViewAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = homeRecyclerViewAdapter
        }

        homeRecyclerViewAdapter.itemClickListener = { view, item, position ->
            val message = when(item){
                is HomeRecyclerViewItem.Director -> "Director ${item.name} Clicked"
                is HomeRecyclerViewItem.Movie -> "Movie ${item.title} Clicked"
                is HomeRecyclerViewItem.Title -> "View All Clicked"
            }
            snackbar(message)
        }

        viewModel.homeListItemsLiveData.observe(this){ result ->
            when(result){
                is Resource.Failure -> {
                    binding.progressBar.hide()
                    //handle failure case here
                }
                Resource.Loading -> binding.progressBar.show()
                is Resource.Success -> {
                    binding.progressBar.hide()
                    homeRecyclerViewAdapter.items = result.value
                }
            }
        }
    }
}