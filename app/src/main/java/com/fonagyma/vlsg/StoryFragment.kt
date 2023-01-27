package com.fonagyma.vlsg

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.PagerAdapter
import com.fonagyma.vlsg.databinding.FragmentStoryBinding

class StoryFragment: Fragment() {

    private var _binding: FragmentStoryBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentStoryBinding.inflate(inflater, container, false)

        val images: IntArray = intArrayOf(
            R.drawable.ic_launcher_background,
            R.drawable.ic_baseline_info_24,
            R.drawable.pen_droid)

        // Initialize our adapter
        val adapter: PagerAdapter =
            ImagePagerAdapter(requireContext(), images)
        // Binds the Adapter to the ViewPager
        binding.pager.adapter = adapter
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}