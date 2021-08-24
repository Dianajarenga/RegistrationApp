package com.example.registration
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.activity.viewModels
import com.example.akirareg.databinding.ActivityMainBinding
import com.example.akirareg.models.Constants
import com.example.akirareg.models.RegistrationRequests
import com.example.akirareg.ui.LoginActivity.LoginActivity
import com.example.akirareg.viewmodel.UserViewModel


@ExperimentalStdlibApi
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var sharedprefs: SharedPreferences
    val userViewModel:  UserViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedprefs=getSharedPreferences(Constants.PREFS_FILE, Context.MODE_PRIVATE)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        setUpSpinner()
        binding.etbutton.setOnClickListener {

            val name = binding.etname.text.toString()
            if (name.isEmpty()) {
                binding.etname.setError("enter name")
            }
            val pswd = binding.etpassword.text.toString()
            if (pswd.isEmpty()) {
                binding.etpassword.setError("enter password")
            }
            val nationality = binding.spnationality.selectedItem.toString().toUpperCase()
            if (nationality.isEmpty()) {
            }
            val dob = binding.etdob.text.toString()
            if (dob.isEmpty()) {
                binding.etdob.setError("enter Date of birth")
            }
            val phoneNumber = binding.etphonenumber.text.toString()
            if (phoneNumber.isEmpty()) {
                binding.etphonenumber.setError("Enter phone number")
            }
            val email = binding.etemail.text.toString()
            if (email.isEmpty()) {
                binding.etemail.setError("enter email")
            }
            val regRequest = RegistrationRequests(
                name = name, password = pswd, phoneNumber = phoneNumber, email = email,
                dateOfBirth = dob, nationality = nationality
            )
            binding.progressBar2.visibility=View.VISIBLE

            userViewModel.registerStudent(regRequest)
        }
        binding.tvlogin.setOnClickListener {
            binding.progressBar2.visibility=View.VISIBLE
            val intent = Intent(baseContext, LoginActivity::class.java)
            startActivity(intent)
        }
        redirectUser()
    }

    fun redirectUser(){
        //to check whether the user is logged in or not
        var token=sharedprefs.getString(Constants.ACCESS_TOKEN,Constants.EMPTY_STRING)
        if(token!!.isNotEmpty()){
            startActivity(Intent(baseContext,CodeHiveRegistration::class.java))
        }
        else{
            startActivity(Intent(baseContext,LoginActivity::class.java))
        }
    }
    fun setUpSpinner() {
        val nationalities =
            arrayListOf(
                "Kenyan",
                "Rwandan",
                "SouthSudanese",
                "Sudanese",
                "Rwandan",
                "Ugandan"
            )
        val nationalitiesAdapter =
            ArrayAdapter(
                baseContext,
                android.R.layout.simple_spinner_dropdown_item,
                nationalities
            )
        nationalitiesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spnationality.adapter = nationalitiesAdapter
    }
    override fun onResume() {
        super.onResume()
        userViewModel.regResponseLiveData.observe(this, { regResponse ->
            binding.progressBar2.visibility= View.GONE
            if (!regResponse.studentId.isNullOrEmpty()) {
                Toast.makeText(baseContext, "Registration successful", Toast.LENGTH_LONG).show()
            }
        })
        userViewModel.regErrorLiveData.observe(this, { error ->
            Toast.makeText(baseContext, error, Toast.LENGTH_SHORT).show()
        })
    }
}