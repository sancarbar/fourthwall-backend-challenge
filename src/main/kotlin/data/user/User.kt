package data.user

import com.fourthwall.moviesapi.controller.user.dto.UserDto
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.security.crypto.bcrypt.BCrypt
import java.util.*

@Document
data class User(
    @Id var id: String?,
    var name: String,
    var email: String,
    var roles: List<RoleEnum>,
    var passwordHash: String,
    val created: Date
) {
    constructor(userDto: UserDto) :
            this(
                null,
                userDto.name,
                userDto.email,
                listOf(RoleEnum.USER),
                BCrypt.hashpw(userDto.password, BCrypt.gensalt()),
                Date()
            )
}
