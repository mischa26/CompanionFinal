package mischa.arcillas.com.companion.model

data class FeelingsData(val feelings: MutableList<Feelings>)

class Feelings(val post_feeling_id: String, val post_feeling_name: String)
