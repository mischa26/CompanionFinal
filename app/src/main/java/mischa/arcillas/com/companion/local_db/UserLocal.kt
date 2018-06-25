package mischa.arcillas.com.companion.local_db

class UserLocal {

    var name: String = ""
    var token: String = ""

    constructor(name: String, token: String){
        this.name = name
        this.token = token
    }
}