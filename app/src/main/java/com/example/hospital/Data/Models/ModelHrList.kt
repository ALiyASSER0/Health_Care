import com.google.gson.annotations.SerializedName

data class ModelHrList(
    val count: Int,
    val data: List<Data>
)

data class Data(
    val address: String,
    val blood: String,
    val clinic: Any,
 @SerializedName("date_joined")   val dateJoined: String,
 @SerializedName("date_of_birth") val dateOfBirth: Any,
    val email: String,
    @SerializedName("first_name")  val firstName: String,
    val gender: String,
    val groups: List<Any>,
    val id: Int,
    @SerializedName("insurance_number")  val insuranceNumber: Any,
    @SerializedName("is_active")   val isActive: Boolean,
    @SerializedName("is_staff")  val isStaff: Boolean,
    @SerializedName("is_superuser")  val isSuperuser: Boolean,
    @SerializedName("last_login")  val lastLogin: Any,
    @SerializedName("last_name")  val lastName: String,
    val password: String,
    val specialization: Any,
    val ssn: Any,
    val status: String,
    val type: String,
    @SerializedName("user_permissions") val userPermissions: List<Any>,
    val username: String
)