package com.example.sopthomework

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sopthomework.databinding.FragmentRepoBinding

class Fragment_repo : Fragment() {
    private lateinit var repoListAdapter: RepoListAdapter
    private lateinit var binding:FragmentRepoBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRepoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        repoListAdapter = RepoListAdapter()
        binding.repoList.adapter = repoListAdapter

        repoListAdapter.repoList.addAll(
            listOf<Repository>(
               Repository(
                   repo_info = "이것은 이것은 이것은 이것은 이것은 이것은 이것은 이것은 이것은 이것은 이것은 이것은 이것은 이것은 이것은",
                   repo_name = "레퍼지토리레퍼지토리레퍼지토리레퍼지토리레퍼지토리레퍼지토리레퍼지토리레퍼지토리레퍼지토리레퍼지토리레퍼지토리레퍼지토리레퍼지토리레퍼지토리",
                   repo_lang = "kotlin"
               ),
                Repository(
                    repo_name = "라라",
                    repo_lang = "kotlin",
                    repo_info = "bye"
                ),
                Repository(
                    repo_name = "도도",
                    repo_lang = "kotlin",
                    repo_info = "bye"
                ),
                Repository(
                    repo_name = "레레",
                    repo_lang = "kotlin",
                    repo_info = "bye"
                ),
                Repository(
                    repo_name = "미미",
                    repo_lang = "kotlin",
                    repo_info = "bye"
                ),
                Repository(
                    repo_name = "파파",
                    repo_lang = "kotlin",
                    repo_info = "bye"
                ),
                Repository(
                    repo_name = "솔솔",
                    repo_lang = "kotlin",
                    repo_info = "bye"
                ),
                Repository(
                    repo_name = "라라",
                    repo_lang = "kotlin",
                    repo_info = "bye"
                ),
                Repository(
                    repo_name = "시시",
                    repo_lang = "kotlin",
                    repo_info = "bye"
                ),
                Repository(
                    repo_name = "도도",
                    repo_lang = "kotlin",
                    repo_info = "bye"
                ),
                Repository(
                    repo_name = "비보",
                    repo_lang = "kotlin",
                    repo_info = "bye"
                )
            )
        )
        repoListAdapter.notifyDataSetChanged()
    }

}