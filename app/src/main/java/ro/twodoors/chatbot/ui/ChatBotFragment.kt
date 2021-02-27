package ro.twodoors.chatbot.ui

import android.graphics.Point
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
import ro.twodoors.chatbot.databinding.FragmentChatBotBinding
import ro.twodoors.chatbot.utils.setIconColor
import ro.twodoors.chatbot.utils.startCircularReveal

private const val POS_X = "POS_X"
private const val POS_Y = "POS_Y"

class ChatBotFragment : Fragment(R.layout.fragment_chat_bot) {

    private var _binding: FragmentChatBotBinding? = null
    private val binding get() = _binding!!

    var posX: Int = 0
    var posY: Int = 0

    companion object {
        @JvmStatic
        fun newInstance(point : Point? = null) =
                ChatBotFragment().apply {
                    if (point != null ){
                        posX = point.x
                        posY = point.y
                    }
                }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState != null){
            posX = savedInstanceState.getInt(POS_X)
            posY = savedInstanceState.getInt(POS_Y)
        }

        _binding = FragmentChatBotBinding.bind(view)
        view.startCircularReveal(posX, posY)
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

        binding.viewPagerCards.apply {
            adapter = ViewPagerAdapter()
            setPageTransformer(ViewPagerPageTransformer())
            clipToPadding = false
            clipChildren = false
            offscreenPageLimit = 1
        }

        binding.viewPagerCards.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
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
                                binding.viewPagerCards.background = resource
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
                binding.viewPagerCards.setCurrentItem(tab?.position ?: 0, true)
                tab?.icon?.setIconColor(requireContext(), R.color.white)
            }
        })
    }

    override fun onSaveInstanceState(outState: Bundle) {
        Log.d("onSaveInstanceState", "posX: $posX, " + "posY: $posY");
        outState.run {
            putInt(POS_X, posX)
            putInt(POS_Y, posY)
        }
        super.onSaveInstanceState(outState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}