// This code is an example of security driven development in Kotlin.

// Importing the necessary libraries for cryptography
import java.security.MessageDigest
import javax.xml.bind.DatatypeConverter

// Function to hash a password using SHA-256 encryption
fun hashPassword(password: String): String {
    val messageDigest = MessageDigest.getInstance("SHA-256")
    messageDigest.update(password.toByteArray())
    val hashedPassword = messageDigest.digest()
    return DatatypeConverter.printHexBinary(hashedPassword).toLowerCase()
}

// Class to represent a user account
data class UserAccount(val username: String, var password: String) {
    // Hashing the password before storing it
    init {
        password = hashPassword(password)
    }
}

// Function to login a user
fun login(username: String, password: String, userAccounts: List<UserAccount>): Boolean {
    // Hashing the entered password to compare with the stored hash
    val hashedPassword = hashPassword(password)
    return userAccounts.any { it.username == username && it.password == hashedPassword }
}

// Example usage
val userAccounts = listOf(
    UserAccount("user1", "password1"),
    UserAccount("user2", "password2")
)

val isLoginSuccessful = login("user1", "password1", userAccounts)
println("Login Successful: $isLoginSuccessful")

