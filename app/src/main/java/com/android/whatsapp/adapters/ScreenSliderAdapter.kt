package com.android.whatsapp.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.android.whatsapp.fragments.InboxFragment
import com.android.whatsapp.MainActivity
import com.android.whatsapp.fragments.PeopleFragment

class ScreenSliderAdapter(fa: MainActivity) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment = when(position) {
        0 -> InboxFragment()
        else -> PeopleFragment()
    }

}
