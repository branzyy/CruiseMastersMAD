package ui.models

data class Booking(
    val id: Int,
    val userId: Int,
    val carName: String,
    val startDate: String,
    val endDate: String
)