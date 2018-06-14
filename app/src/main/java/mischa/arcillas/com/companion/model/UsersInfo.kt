package mischa.arcillas.com.companion.model

data class UserInfo(val userType: String,
                val firstname: String,
                val lastname: String,
                val email: String,
                val username: String,
                val password: String,
                val confirm: String,
                val birthday: String,
                val gender: String)