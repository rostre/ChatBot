package ro.twodoors.chatbot.fragments

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.google.android.material.tabs.TabLayout
import ro.twodoors.chatbot.R
import ro.twodoors.chatbot.data.DataItem
import ro.twodoors.chatbot.data.ItemState
import ro.twodoors.chatbot.databinding.FragmentChatBotBinding
import ro.twodoors.chatbot.utils.setIconColor
import ro.twodoors.chatbot.utils.startCircularReveal
import ro.twodoors.chatbot.viewpager.ViewPagerAdapter
import ro.twodoors.chatbot.viewpager.ViewPagerPageTransformer

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
        setupViewPager()
        setupTabs()
    }

    private fun setupViewPager(){
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
        val items = listOf(
                DataItem(
                        "Hello",
                        1,
                        ItemState.Start
                ),
                DataItem(
                        "Restaurant",
                        2,
                        ItemState.Unlock
                ),
                DataItem(
                        "Hotel",
                        3,
                        ItemState.Unlock
                ),
                DataItem(
                        "Tickets",
                        4,
                        ItemState.Unlock
                ),
                DataItem(
                        "Conversation",
                        5,
                        ItemState.Unlock
                ),
                DataItem(
                        "Shopping",
                        6,
                        ItemState.Unlock
                ),
                DataItem(
                        "Appointment",
                        7,
                        ItemState.Unlock
                ),
                DataItem(
                        "Taxi",
                        8,
                        ItemState.Unlock
                )
        )

        val adapter = ViewPagerAdapter(items)
        binding.viewPager.apply {
            this.adapter = adapter
            setPageTransformer(ViewPagerPageTransformer())
            clipToPadding = false
            clipChildren = false
            offscreenPageLimit = 1
        }

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageScrollStateChanged(state: Int) {
                Log.d("onPageScrollStateChanged", "state : $state")
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
                binding.tabLayout.getTabAt(position)?.select()

                Glide.with(requireNotNull(context))
                        .load(images[position])
                        .centerCrop()
                        .into(object : CustomTarget<Drawable>(){
                            override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                                binding.viewPager.background = resource
                            }
                            override fun onLoadCleared(placeholder: Drawable?) {}
                        })
            }
        })
    }

    private fun setupTabs() {
        binding.tabLayout.getTabAt(0)?.icon?.setIconColor(requireContext(), R.color.white)
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {}

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                tab?.icon?.setIconColor(requireContext(), R.color.semiTransparent)
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                binding.viewPager.setCurrentItem(tab?.position ?: 0, true)
                tab?.icon?.setIconColor(requireContext(), R.color.white)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}