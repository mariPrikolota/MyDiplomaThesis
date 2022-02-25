package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.game_fragment.*

class GameFragment: Fragment()  {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.game_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var list = ArrayList<ListItem>()
        list.add(ListItem("1 ИГРА"))
        list.add(ListItem("2 ИГРА"))
        list.add(ListItem("3 ИГРА"))

        recycleViewRazdel.hasFixedSize()
        recycleViewRazdel.layoutManager = LinearLayoutManager(context)
        recycleViewRazdel.adapter = GamrAdapter(list, requireContext())
    }

}