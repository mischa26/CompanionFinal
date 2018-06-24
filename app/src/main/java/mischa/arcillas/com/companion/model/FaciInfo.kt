package mischa.arcillas.com.companion.model

data class FaciInfo(val userTypeFaci: String,
                    val fNameFaci: String,
                    val lNameFaci: String,
                    val emailFaci: String,
                    val usernameFaci: String,
                    val passwordFaci: String,
                    val confirmFaci: String,
                    val birthdayFaci: String,
                    val genderFaci: String,
                    val prcPhoto: String,
                    val interestsFaci: List<String>)