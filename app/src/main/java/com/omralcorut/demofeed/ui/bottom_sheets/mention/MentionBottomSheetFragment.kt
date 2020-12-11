package com.omralcorut.demofeed.ui.bottom_sheets.mention

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.omralcorut.demofeed.R
import com.omralcorut.demofeed.databinding.FragmentMentionBottomSheetBinding
import com.omralcorut.demofeed.models.Mention

class MentionBottomSheetFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentMentionBottomSheetBinding? = null

    private val binding get() = _binding!!

    private val mentionAdapter = MentionAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMentionBottomSheetBinding.inflate(inflater, container, false)
        binding.recyclerViewMention.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewMention.adapter = mentionAdapter

        binding.recyclerViewMention.addItemDecoration(
            DividerItemDecoration(context, LinearLayoutManager.VERTICAL)
                .apply {
                    setDrawable(
                        ContextCompat.getDrawable(
                            requireContext(),
                            R.drawable.divider_mention
                        )!!
                    )
                }
        )
        mentionAdapter.addAll(arguments?.get("list") as Array<Mention>)

        binding.buttonClose.setOnClickListener {
            dismiss()
        }
        return binding.root
    }
}