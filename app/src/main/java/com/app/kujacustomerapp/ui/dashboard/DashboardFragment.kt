package com.app.kujacustomerapp.ui.dashboard

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.kujacustomerapp.R
import com.app.kujacustomerapp.databinding.FragmentDashboardBinding
import com.app.kujacustomerapp.remote.entity.response.dashboard.DashboardData
import com.app.kujacustomerapp.ui.base.BaseBindingFragment
import com.app.kujacustomerapp.ui.base.event.EventObserver
import com.app.kujacustomerapp.ui.base.event.OnEventUnhandledContent
import com.app.kujacustomerapp.ui.dashboard.adapter.ImageListAdapter
import com.app.kujacustomerapp.utility.EndLessRecyclerViewScrollListener
import javax.inject.Inject

open class DashboardFragment : BaseBindingFragment<FragmentDashboardBinding>() {
    @Inject
    lateinit var factory: ViewModelProvider.Factory
    var dashboardViewModel: DashboardViewModel? = null
    private var dashboardAdapter: ImageListAdapter? = null
    private var usersList = ArrayList<DashboardData.User>()

    override val contentView: Int
        get() = R.layout.fragment_dashboard

    override fun initViews() {
        dashboardViewModel?.getAllStudent()
        binding?.viewModel = dashboardViewModel
        dashboardAdapter = ImageListAdapter(requireContext(), usersList)
        binding?.rvUser?.addOnScrollListener(
            object :
                EndLessRecyclerViewScrollListener(binding?.rvUser?.layoutManager as LinearLayoutManager) {
                override fun onLoadMore(page: Int, totalItemsCount: Int) {
                    dashboardViewModel?.loadMore = if (dashboardViewModel?.pageNumber == 0) {
                        false
                    } else {
                        dashboardViewModel?.getAllStudent()
                        true
                    }
                }
            }
        )
        binding?.rvUser?.adapter = dashboardAdapter

        binding!!.swipeLayout.setOnRefreshListener {
            binding!!.swipeLayout.isRefreshing = false
            dashboardViewModel?.pageNumber = 0
            usersList.clear()
            dashboardAdapter?.notifyDataSetChanged()
            dashboardViewModel?.getAllStudent()
        }
    }

    override fun initObservers() {
        dashboardViewModel!!.successLiveData.observe(
            this,
            EventObserver(object : OnEventUnhandledContent {
                override fun onChanged(`object`: Any?) {
                    (`object` as DashboardData).let {
                        if (!it.users.isNullOrEmpty()) {
                            usersList.addAll(it.users)
                            dashboardAdapter?.notifyDataSetChanged()
                        }
                    }
                }
            })
        )

        dashboardViewModel!!.errorLiveData.observe(
            this,
            EventObserver<Any>(object : OnEventUnhandledContent {
                override fun onChanged(status: Any?) {
                    //handle Error
                }
            })
        )
    }

    override fun viewModelSetup() {
        dashboardViewModel =
            ViewModelProviders.of(requireActivity(), factory).get(DashboardViewModel::class.java)
    }
}