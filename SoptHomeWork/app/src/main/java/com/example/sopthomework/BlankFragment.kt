package com.example.sopthomework

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sopthomework.databinding.FragmentBlankBinding

class BlankFragment : Fragment() {
    private lateinit var followingListAdapter:FollowingListAdapter
    private lateinit var binding:FragmentBlankBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBlankBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        followingListAdapter = FollowingListAdapter()
        binding.userList.adapter = followingListAdapter
        binding.userList.layoutManager = LinearLayoutManager(requireContext())
        followingListAdapter.userList.addAll(
            listOf<FollowingUserInfo>(
                FollowingUserInfo(
                userImage = "지금은 빈칸 4차 때 넣어보자",
                userName = "도"
                ),
                FollowingUserInfo(
                    userImage = "지금은 빈칸 4차 때 넣어보자",
                    userName = "레"
                ),
                FollowingUserInfo(
                    userImage = "지금은 빈칸 4차 때 넣어보자",
                    userName = "미"
                ),
                FollowingUserInfo(
                    userImage = "지금은 빈칸 4차 때 넣어보자",
                    userName = "파"
                ),
                FollowingUserInfo(
                    userImage = "지금은 빈칸 4차 때 넣어보자",
                    userName = "도"
                ),
                FollowingUserInfo(
                    userImage = "지금은 빈칸 4차 때 넣어보자",
                    userName = "레"
                ),
                FollowingUserInfo(
                    userImage = "지금은 빈칸 4차 때 넣어보자",
                    userName = "미"
                ),
                FollowingUserInfo(
                    userImage = "지금은 빈칸 4차 때 넣어보자",
                    userName = "파"
                ),
                FollowingUserInfo(
                    userImage = "지금은 빈칸 4차 때 넣어보자",
                    userName = "도"
                ),
                FollowingUserInfo(
                    userImage = "지금은 빈칸 4차 때 넣어보자",
                    userName = "레"
                ),
                FollowingUserInfo(
                    userImage = "지금은 빈칸 4차 때 넣어보자",
                    userName = "미"
                ),
                FollowingUserInfo(
                    userImage = "지금은 빈칸 4차 때 넣어보자",
                    userName = "파"
                )
            )
        )
        followingListAdapter.notifyDataSetChanged()

    }
}