package ro.twodoors.chatbot.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import ro.twodoors.chatbot.R
import ro.twodoors.chatbot.databinding.FragmentHomeBinding
import ro.twodoors.chatbot.utils.getViewCenter
import ro.twodoors.chatbot.utils.open

class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentHomeBinding.bind(view)

        binding.btnChatBot.setOnClickListener {
            val positions = it.getViewCenter()
            fragmentManager?.open {
                add(R.id.container, ChatBotFragment.newInstance(positions)).addToBackStack(null)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}