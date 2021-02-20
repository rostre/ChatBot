package ro.twodoors.chatbot.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.SeekBar
import androidx.viewpager2.widget.ViewPager2
import kotlinx.android.synthetic.main.fragment_chat_bot.*
import ro.twodoors.chatbot.*
import ro.twodoors.chatbot.databinding.FragmentChatBotBinding
import ro.twodoors.chatbot.utils.startCircularReveal

class ChatBotFragment : Fragment(R.layout.fragment_chat_bot) {

    private var _binding: FragmentChatBotBinding? = null
    private val binding get() = _binding!!

    var posX: Int? = null
    var posY: Int? = null

    companion object {
        @JvmStatic
        fun newInstance(positions : IntArray ? = null) =
                ChatBotFragment().apply {
                    if (positions != null && positions.size == 2){
                        posX = positions[0]
                        posY = positions[1]
                    }
                }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentChatBotBinding.bind(view)
        view.startCircularReveal(posX!!, posY!!)

        val items = listOf(
            DataItem(
                "Hello",
                1,
                ItemState.Start.name
            ),
            DataItem(
                "Restaurant",
                2,
                ItemState.Unlock.name
            ),
            DataItem(
                "Hotel",
                3,
                ItemState.Unlock.name
            ),
            DataItem(
                "Tickets",
                4,
                ItemState.Unlock.name
            ),
            DataItem(
                "Conversation",
                5,
                ItemState.Unlock.name
            ),
            DataItem(
                "Shopping",
                6,
                ItemState.Unlock.name
            ),
            DataItem(
                "Appointment",
                7,
                ItemState.Unlock.name
            ),
            DataItem(
                "Taxi",
                8,
                ItemState.Unlock.name
            )
        )

        val images = listOf(
            R.drawable.photo_1,
            R.drawable.photo_2,
            R.drawable.photo_3,
            R.drawable.photo_4,
            R.drawable.photo_5,
            R.drawable.photo_6,
            R.drawable.photo_7,
            R.drawable.photo_8
        )

        val itemDecoration = HorizontalMarginItemDecoration(
            context!!, R.dimen.pageMarginAndOffset)

        val adapter = ViewPagerAdapter(items)

        binding.viewPager.apply {
            this.adapter = adapter
            setPageTransformer(ViewPagerPageTransformer())
            clipToPadding = false
            clipChildren = false
            offscreenPageLimit = 1
            //addItemDecoration(itemDecoration)
        }

//        val pageMarginPx = resources.getDimensionPixelOffset(R.dimen.pageMargin)
//        val offsetPx = resources.getDimensionPixelOffset(R.dimen.offset)
//        viewPager.setPageTransformer { page, position ->
//            val offset = position * -(2 * offsetPx + pageMarginPx)
//            page.translationX = offset
//            //page.scaleY = 0.8f
//        }


        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageScrollStateChanged(state: Int) {
                Log.d("onPageScrollStateChanged", "state : $state");
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                Log.d("onPageScrolled", "position : $position, " +
                                                    "positionOffset: $positionOffset, " +
                                                    "positionOffsetPixels : $positionOffsetPixels")

            }

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.seekbar.progress = position
                //binding.fragmentChatBot.setBackgroundResource(images[position])
                binding.viewPager.setBackgroundResource(images[position])
            }
        })

        binding.seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, p2: Boolean) {
                seekBar?.thumbTintList = resources.getColorStateList(R.color.btnColorBg, null)

                if (binding.viewPager.currentItem != progress)
                    binding.viewPager.setCurrentItem(progress, true)
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}

            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}