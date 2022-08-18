package com.myseconteacher.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        if (savedInstanceState == null) {
//            supportFragmentManager.beginTransaction().add(R.id.fContainer, HomeFragment()).commit()
//        }
        val recyclerView = findViewById<RecyclerView>(R.id.rvList)

        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        val items = listOf<MovieModel>(
            MovieModel(name = "Movie 1"),
            MovieModel(name = "Movie 2"),
            MovieModel(name = "Movie 3"),
            MovieModel(name = "Movie 4"),
        )
        val adapter = MovieRecyclerAdapter(items)
        recyclerView.adapter = adapter
    }
}