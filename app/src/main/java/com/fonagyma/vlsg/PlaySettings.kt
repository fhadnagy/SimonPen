package com.fonagyma.vlsg

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.fonagyma.vlsg.databinding.FragmentPlaySettingsBinding


class PlaySettings: Fragment() {

    private var _binding: FragmentPlaySettingsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentPlaySettingsBinding.inflate(inflater, container, false)


        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.playSimonButton.setOnClickListener {
            if(binding.squareSizeEditText.text.isNotEmpty() &&
                binding.difficultyEditText.text.isNotEmpty() &&
                binding.timeLimitSecondsEditText.text.isNotEmpty() &&
                binding.livesEditText.text.isNotEmpty()){
                val size = binding.squareSizeEditText.text.toString().toInt()
                val difficulty = binding.difficultyEditText.text.toString().toInt()
                val timeLimitSeconds = binding.timeLimitSecondsEditText.text.toString().toInt()
                val lives = binding.livesEditText.text.toString().toInt()
                var replayable = binding.replayableSwitch.isChecked
                if(isValid(size,difficulty,timeLimitSeconds,lives))
                {
                    val action= PlaySettingsDirections.actionPlaySettingsToSimonSaysFragment(size,lives,difficulty,timeLimitSeconds,replayable)
                    findNavController().navigate(action)
                }else{
                    Toast.makeText(requireContext(),"cannot be negative",Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(requireContext(),"cannot be empty",Toast.LENGTH_LONG).show()
            }

        }

    }

    private fun isValid(size: Int, difficulty: Int, timeLimitSeconds: Int, lives: Int):Boolean{
        if(size<2){
            return false
        }
        if(difficulty<2){
            return false
        }
        if(timeLimitSeconds<1){
            return false
        }
        if(lives<1){
            return false
        }
        return true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}