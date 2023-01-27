package com.fonagyma.vlsg

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
import com.fonagyma.vlsg.databinding.FragmentSimonSaysBinding
import com.fonagyma.vlsg.logic.SimonSaysView


class SimonSaysFragment : Fragment() {

    private var _binding: FragmentSimonSaysBinding? = null
    private lateinit var gameView: SimonSaysView
    private val args by navArgs<SimonSaysFragmentArgs>()
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSimonSaysBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? AppCompatActivity)?.supportActionBar?.hide()

        /*val ldView = OLDLiveDrawingView(requireContext(),binding.root.display.width.toInt(),binding.root.display.height.toInt())
        binding.root.addView(ldView)
        ldView.resume()*/

        gameView = SimonSaysView(requireContext(),binding.root.display.width.toInt(),
            binding.root.display.height.toInt(), args.difficulty,args.size,args.replayable,
            args.timeLimitSeconds*1000.toLong(), args.lives)
        binding.root.addView(gameView)
        gameView.resume()

    }

    override fun onDestroyView() {
        (activity as? AppCompatActivity)?.supportActionBar?.show()
        //TODO: database for high score if time enables
        super.onDestroyView()
        _binding = null
    }

    override fun onPause() {
        gameView.pause()
        super.onPause()
    }

    override fun onResume() {
        gameView.resume()
        super.onResume()
    }

}