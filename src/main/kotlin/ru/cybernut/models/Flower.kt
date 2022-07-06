package ru.cybernut.models

import kotlinx.serialization.Serializable

@Serializable
data class Flower(val id: Int, val name: String, val description: String)

val flowerStorage = mutableListOf<Flower>(
    Flower(1001, "Rose", "Beautiful red flower"),
    Flower(1002, "Tulip", "Beautiful yellow flower"),
    Flower(1003, "Сactus", "Huge thorny plant"),
    Flower(1004, "Golden-daisy", "Beautiful flower")
)
