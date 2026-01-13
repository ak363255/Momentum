/**
 * @author Amit Kumar on 13/01/26
 */

package com.example.ui.onlyForLearning.roboelectricTesting

data class Wishlist(
    val name : String,
    val wishes : List<String>,
    val id : Int
)

fun Wishlist.toEntity() = WishlistEntity(
    name = this.name,
    id = this.id,
    wishes = this.wishes.joinToString(",")
)
fun WishlistEntity.toWishList() = Wishlist(
    name = this.name,
    id = this.id,
    wishes = this.wishes.split(",")
)