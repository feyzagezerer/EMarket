package com.e_market.ui.views.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.LEFT
import androidx.recyclerview.widget.ItemTouchHelper.RIGHT
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.e_market.R
import com.e_market.databinding.FragmentFavoritesBinding
import com.e_market.ui.FavoritesAdapter
import com.e_market.ui.viewmodels.FavoritesViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FavoritesFragment @Inject constructor(
) : Fragment() {

    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FavoritesViewModel by viewModels()

    private lateinit var adapter: FavoritesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        observeChanges()

    }

    private fun setupViews() {
        adapter = FavoritesAdapter()
        binding.apply {
            listFavoritesRV.adapter = adapter
            val layoutManager = GridLayoutManager(context, 2)
            listFavoritesRV.layoutManager = layoutManager
            ItemTouchHelper(itemTouchCallback).attachToRecyclerView(listFavoritesRV)
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadFavorites()
    }

    private fun observeChanges() {
        viewModel.apply {
            favoriteList.observe(viewLifecycleOwner){
                adapter.submitList(it)
            }
        }
    }


    private val itemTouchCallback = object : ItemTouchHelper.SimpleCallback(
        0, LEFT or RIGHT
    ) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ) = true

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            // kaydırdığımız konum için recyclerView'daki ilgli konuma eşitliyoruz.
            val position = viewHolder.layoutPosition
            val item = adapter.currentList[position]
            viewModel.deleteFavoriteItem(item)
            Snackbar.make(requireView(), "Successfully deleted item", Snackbar.LENGTH_LONG).apply {
                setAction("Undo") {
                    viewModel.insertFavorite(item)
                }
                show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
