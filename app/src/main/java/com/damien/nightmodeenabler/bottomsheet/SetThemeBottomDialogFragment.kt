package com.damien.nightmodeenabler.bottomsheet

import android.app.Service
import android.app.UiModeManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.damien.nightmodeenabler.MainViewModel
import com.damien.nightmodeenabler.MainViewModelFactory
import com.damien.nightmodeenabler.databinding.SetThemeBottomSheetContentBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class SetThemeBottomDialogFragment : BottomSheetDialogFragment() {

    companion object {
        const val TAG = "ModalBottomSheet"
    }

    private var _binding: SetThemeBottomSheetContentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SetThemeBottomSheetContentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel: MainViewModel by viewModels {
            MainViewModelFactory(
                requireActivity().application,
                requireActivity().getSystemService(Service.UI_MODE_SERVICE) as UiModeManager
            )
        }

        binding.setDayMode.setOnClickListener {
            dismiss()
            viewModel.setNotNightMode()
        }

        binding.setNightMode.setOnClickListener {
            dismiss()
            viewModel.setNightMode()
        }

        binding.setAutoMode.setOnClickListener {
            dismiss()
            viewModel.setAutoNightMode()
        }
    }
}
