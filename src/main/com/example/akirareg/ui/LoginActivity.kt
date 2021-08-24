package com.example.akirareg.ui.LoginActivity

    import android.content.Context
    import android.content.Intent
    import android.content.SharedPreferences
    import androidx.appcompat.app.AppCompatActivity
    import android.os.Bundle
    import android.widget.Toast
    import androidx.activity.viewModels
    import com.example.akirareg.R
    import com.example.akirareg.databinding.ActivityLoginBinding
    import com.example.akirareg.models.LoginRequest
    import com.example.akirareg.ui.CoursesActivity
    import com.example.akirareg.viewmodel.StudentViewModel

    class LoginActivity : AppCompatActivity() {

          lateinit var binding: ActivityLoginBinding
          lateinit var sharedprefs:SharedPreferences
          val studentViewModel:StudentViewModel by viewModels()

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivityLoginBinding.inflate(layoutInflater)
            setContentView(binding.root)
            sharedprefs=getSharedPreferences("CODEHIVEREG_PREFS", Context.MODE_PRIVATE)

   setContentView(R.layout.activity_login)
           castViews()
           logInStudent()
        }
        fun castViews(){

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


            var details = LoginRequest(email,pswd)
            val intent = Intent(baseContext, CoursesActivity::class.java)
            startActivity(intent)

            Toast.makeText(baseContext, details.toString(), Toast.LENGTH_LONG).show()
        }

        override fun onResume() {
            //add ninding onclick listeners
            //link to courses activity
            //todays class making api calls with access token
            super.onResume()
            studentViewModel.logResponseLiveData.observe(this,{ loginResponse->
                var editor=sharedprefs.edit()
                editor.putString("ACCESS_TOKEN",loginResponse.ACCESS_TOKEN)

               editor.putString("STUDENT_ID",loginResponse.id)
                editor.apply()
                if (!loginResponse.id.isNotEmpty())
                    Toast.makeText(baseContext,loginResponse.message,Toast.LENGTH_LONG).show()
            })
            studentViewModel.logErrorLiveData.observe(this,{ error->
 //binding.tverror.visibility=view.VISIBLE
     //           binding.tverror.text=error

                Toast.makeText(baseContext,error,Toast.LENGTH_LONG).show()
            })
        }
    }
// fun validatelogin(){
//     var email=binding.etemail.text.toString()
//     var email=binding.etemail.text.toString()
//var error=false
//     if (email.isEmpty()||email.isBlank(){
//         error=true
//             binding.tilemail.error="Email is required"
//         }
//
// }
 //   if (!error){
   // binnding.progressbar.visibility=gone
    //   var loginresponse
    //   }


