package com.example.akirareg.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.akirareg.R
import com.example.akirareg.databinding.ActivityCodeHiveRegistrationBinding
import com.example.akirareg.models.Course
import com.example.akirareg.viewmodel.CoursesViewModel

class CoursesActivity : AppCompatActivity() {
    lateinit var  rvCourses:RecyclerView
    lateinit var binding: ActivityCodeHiveRegistrationBinding
    val coursesViewModel: CoursesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCodeHiveRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)
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
        var coursesAdapter= CoursesAdapter(baseContext,courseList)
        binding.rvCourses.apply {
            layoutManager= LinearLayoutManager(baseContext)
            binding.rvCourses.adapter=CoursesAdapter}
    }

     override fun onResume() {
            super.onResume()
            coursesViewModel.coursesResponseLiveData.observe(this) { courseResponse ->
                if (!courseResponse.courseId.isNullOrEmpty()) {
                    Toast.makeText(baseContext, "Course added successfully", Toast.LENGTH_LONG)
                        .show()
                }
            }
         coursesViewModel.courseErrorLiveData.observe(this, { error ->
                Toast.makeText(baseContext, error, Toast.LENGTH_SHORT).show()
            })
    }
}