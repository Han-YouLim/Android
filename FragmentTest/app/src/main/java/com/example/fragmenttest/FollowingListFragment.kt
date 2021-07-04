package com.example.fragmenttest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fragmenttest.databinding.FragmentFollowingListBinding

class FollowingListFragment : Fragment() {
    private lateinit var followingListAdapter:FollowingListAdapter
    private lateinit var binding:FragmentFollowingListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //binding 객체를 전역선언을 해주어서는 안됨. -> 생명주기 관련성 (유)
        binding = FragmentFollowingListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        followingListAdapter = FollowingListAdapter() //우리가 만든 어뎁터 객체화
        binding.userList.adapter = followingListAdapter //userList에 우리가 만든 어뎁터 지정

        followingListAdapter.userList.addAll(
                listOf<FollowingUserInfo>(
                        FollowingUserInfo(
                                userImage = "tlqkf",
                                userName = "강감찬"
                        ),
                        FollowingUserInfo(
                                userImage = "tlqkf",
                                userName = "홍길동"
                        ),
                        FollowingUserInfo(
                            userImage = "tlqkf",
                            userName = "이순신"
                        ),
                        FollowingUserInfo(
                            userImage = "tlqkf",
                            userName = "광개토"
                        ),
                        FollowingUserInfo(
                            userImage = "tlqkf",
                            userName = "세종대왕"
                        ),
                        FollowingUserInfo(
                                userImage = "tlqkf",
                                userName = "신사임당"
                        ),
                        FollowingUserInfo(
                                userImage = "tlqkf",
                                userName = "홍범도"
                        ),
                        FollowingUserInfo(
                                userImage = "tlqkf",
                                userName = "유관순"
                        ),
                        FollowingUserInfo(
                                userImage = "tlqkf",
                                userName = "장영실"
                        ),
                        FollowingUserInfo(
                            userImage = "tlqkf",
                            userName = "김구"
                        )
                )
        )
        //Adapter에 모든 데이터가 변했으니 다시 그려달라고 알려줍니다!
        followingListAdapter.notifyDataSetChanged()
    }
}