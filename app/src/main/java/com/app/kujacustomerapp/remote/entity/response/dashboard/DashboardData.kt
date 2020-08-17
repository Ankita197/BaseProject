package com.app.kujacustomerapp.remote.entity.response.dashboard

class DashboardData {
    val users: List<User>? = null
    private val has_more = false

    inner class User {
        val name: String? = null
        val image: String? = null
        val items: List<String>? = null

    }
}