package com.example.akirareg.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.activity.viewModels
import com.example.akirareg.R
import com.example.akirareg.api.ApiClient
import com.example.akirareg.api.ApiInterface
import com.example.akirareg.databinding.ActivityMainBinding
import com.example.akirareg.models.RegistrationRequests
import com.example.akirareg.models.RegistrationResponse
import com.example.akirareg.viewmodel.UserViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Error

class MainActivity : AppCompatActivity() {
    //    lateinit var etnames:EditText
//    lateinit var etDob:EditText
//    lateinit var spNationality:Spinner
//    lateinit var etpassword:EditText
//    lateinit var etphone:EditText
//    lateinit var etemail:EditText
//    lateinit var btnRegister:Button
    lateinit var binding: ActivityMainBinding
    val userViewModel:UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        clickRegister()
        setUpSpinner()
        //casting views in our activity//R.stands fro resourses//framework goes to R assigns it valuesmapping between id and integer activity-integer


    }

    fun setUpSpinner() {

//         etnames = findViewById(R.id.etname)//edit text is the type of etname
//         etDob = findViewById(R.id.etdob)
//         spNationality = findViewById(R.id.spnationality)
//         etpassword = findViewById(R.id.etpassword)
//         etphone = findViewById(R.id.etphonenumber)
//         etemail = findViewById(R.id.etemail)
//
//         btnRegister = findViewById(R.id.etbutton)
        var nationalities = arrayOf("Kenyan", "Rwandan", "Sudanese", "South Sudanese", "Ugandan")
        var nationalitiesAdopter =
            ArrayAdapter(baseContext, android.R.layout.simple_spinner_item, nationalities)
        nationalitiesAdopter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spnationality.adapter = nationalitiesAdopter


    }

    fun clickRegister() {
        binding.etbutton.setOnClickListener {
            var name = binding.etname.text.toString()
            var dob = binding.etdob.text.toString()
            var nationality = binding.spnationality.selectedItem.toString()
            var password = binding.etpassword.text.toString()
            var phone = binding.etphonenumber.text.toString()
            var email = binding.etemail.text.toString()
            if (email.isEmpty()) {
                binding.etemail.setError("name is required")
            }
            var regrequest = RegistrationRequests(
                name = name,
                phoneNumber = phone,
                email = email,
                dateOfBirth = dob,
                nationality = nationality,
                password = password
            )
            userViewModel.registerStudent(regrequest)
//            val retrofit = ApiClient.buildApiClient(ApiInterface::class.java)
//            var requests = retrofit.registerStudent(regrequest)
//            requests.enqueue(object : Callback<RegistrationResponse> {
//                override fun onResponse(
//                    call: Call<RegistrationResponse>,
//                    response: Response<RegistrationResponse>
//                ) {
//                    if (response.isSuccessful) {
//                        Toast.makeText(
//                            baseContext,
//                            "Your registration was succesful",
//                            Toast.LENGTH_LONG
//                        ).show()
//                    }
//                }
//
//                override fun onFailure(call: Call<RegistrationResponse>, t: Throwable) {
//                    Toast.makeText(
//                        baseContext,
//                        "Your registration was succesful",
//                        Toast.LENGTH_LONG
//                    ).show()
//
//                }
//            })
//

            var details = Details(name, dob, nationality, phone, email, password, phone)
            val intent = Intent(baseContext, CoursesActivity::class.java)
            startActivity(intent)

            Toast.makeText(baseContext, details.toString(), Toast.LENGTH_LONG).show()

        }


    }
    override fun onResume() {
        super.onResume()
        userViewModel.regResponseLiveData.observe(this,{regResponse->
            if (!regResponse.studentId.isNotEmpty())
                Toast.makeText(baseContext,"Registration successful",Toast.LENGTH_LONG).show()
        })
        userViewModel.regErrorLiveData.observe(this,{ error->

            Toast.makeText(baseContext,error,Toast.LENGTH_LONG).show()
        })
    }
}

data class Details(
    var password: String,
    var name: String, var dob: String,
    var nationality: String,
    var idNumber: String, var phone: String,
    var email: String
)

