package com.android.whatsapp

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class ScreenSliderAdapter(fa: MainActivity) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment = when(position) {
        0 -> InboxFragment()
        else -> PeopleFragment()
    }

}
