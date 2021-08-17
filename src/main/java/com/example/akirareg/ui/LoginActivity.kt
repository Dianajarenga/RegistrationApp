
    package com.example.registration

    import android.content.Intent
    import androidx.appcompat.app.AppCompatActivity
    import android.os.Bundle
    import android.widget.Button
    import android.widget.EditText
    import android.widget.Toast
    import android.widget.Toolbar
    import androidx.activity.viewModels
    import com.example.akirareg.R
    import com.example.akirareg.api.ApiClient
    import com.example.akirareg.api.ApiInterface
    import com.example.akirareg.databinding.ActivityLoginBinding
    import com.example.akirareg.databinding.ActivityMainBinding
    import com.example.akirareg.models.LoginRequest
    import com.example.akirareg.models.LoginResponse
    import com.example.akirareg.ui.CoursesActivity
    import com.example.akirareg.ui.Details
    import com.example.akirareg.viewmodel.StudentViewModel
    import com.example.akirareg.viewmodel.UserViewModel
    import org.json.JSONObject
    import retrofit2.Call
    import retrofit2.Callback
    import retrofit2.Response
    import java.util.Arrays.toString

    class LoginActivity : AppCompatActivity() {
//       lateinit var logintoolbar: Toolbar
//        lateinit var tilusername: EditText
//        lateinit var tilpassword:EditText
//        lateinit var btnlogin: Button
          lateinit var binding: ActivityLoginBinding
          val userViewModel: UserViewModel by viewModels()
          val studentViewModel:StudentViewModel by viewModels()
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivityLoginBinding.inflate(layoutInflater)
            setContentView(binding.root)


//            setContentView(R.layout.activity_login)
           castViews()
           logInStudent()
        }
        fun castViews(){
//            tilusername=findViewById(R.id.tilusenamer)
//            tilpassword=findViewById(R.id.tilpassword)
//            btnlogin=findViewById(R.id.btnlogin)
        }
        fun logInStudent(){
            var email=binding.tilusenamer.text.toString()
            var pswd=binding.tilpassword.text.toString()
            binding.btnlogin.setOnClickListener {
                if (email.isEmpty()){
                    binding.tilusenamer.setError("required")
                    binding.tilpassword.setError("required")
                    binding.btnlogin.setError("required")
                }
                if (pswd.isEmpty()){
                   binding. tilpassword.setError("This field is compulsory")
                }
            }
            var loginRequest= LoginRequest(
                email=email,password = pswd
            )

            studentViewModel.logInStudent(loginRequest)

//            var retrofit= ApiClient.buildApiClient(ApiInterface::class.java)
//            var request=retrofit.logInStudent(loginRequest)
//            request.enqueue(object : Callback<LoginResponse> {
//                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
//                    if (response.isSuccessful) {
//                        Toast.makeText(baseContext, "your login was succesful!", Toast.LENGTH_LONG)
//                            .show()
//                    } else try {
//                        val error = JSONObject(response.errorBody()!!.string())
//                        Toast.makeText(baseContext, error.toString(), Toast.LENGTH_LONG).show()
//                    }
//                    catch (e:Exception){
//                        Toast.makeText(baseContext, e.message, Toast.LENGTH_LONG).show()
//
//                    }
//                }
//
//                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
//                    Toast.makeText(baseContext, t.message, Toast.LENGTH_LONG).show()
//
//                }
//            })
            var details = LoginRequest(email,pswd)
            val intent = Intent(baseContext, CoursesActivity::class.java)
            startActivity(intent)

            Toast.makeText(baseContext, details.toString(), Toast.LENGTH_LONG).show()
        }

        override fun onResume() {
            super.onResume()
            studentViewModel.logResponseLiveData.observe(this,{ regResponse->
                if (!regResponse.id.isNotEmpty())
                    Toast.makeText(baseContext,"LogIn successful",Toast.LENGTH_LONG).show()
            })
            studentViewModel.logErrorLiveData.observe(this,{ error->

                Toast.makeText(baseContext,error,Toast.LENGTH_LONG).show()
            })
        }
    }




