package com.example.akirareg.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.akirareg.R
import com.example.akirareg.models.Course

class CoursesActivity : AppCompatActivity() {
    lateinit var  rvCourses:RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_courses)
        displayCourses()
    }
    fun displayCourses(){

        var courseList= listOf(
            Course("IOT","io207","iOT FOR 21ST Century connectivity","Barre Yassin"),
            Course("Mobile Development","And101","Android Development","John Owuor"),
            Course("Python","PY101","BackEnd Development","James Mwai"),
            Course("JavaScript","JS101","Js for fun and for profit","Purity Maina"),
            Course("UX Research","UX101","Research for Humans","Joy Wambui")
        )
        rvCourses=findViewById(R.id.rvCourses)
        var coursesAdapter= CoursesAdapter(courseList)
        rvCourses.layoutManager=LinearLayoutManager(baseContext)
        rvCourses.apply {
        rvCourses.layoutManager=LinearLayoutManager(baseContext)
        adapter=coursesAdapter}
    }
}