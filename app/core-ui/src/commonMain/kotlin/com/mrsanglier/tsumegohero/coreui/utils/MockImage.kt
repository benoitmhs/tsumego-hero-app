package com.mrsanglier.tsumegohero.coreui.utils

import kotlin.math.absoluteValue

object MockImage {

    fun urlFomEventId(id: String): String =
        backgrounds[id.hashCode().absoluteValue % backgrounds.count()]

    @Suppress("MaxLineLength")
    private val backgrounds: List<String> = listOf(
        "https://www.republique-grolee-carnot.com/wp-content/uploads/2022/03/visiter-presquile-lyon.jpg",
        "https://puzzlemania-154aa.kxcdn.com/products/2024/puzzle-artpuzzle-1500-pieces-cinque-terre-italy.webp",
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSJobkR0mAPUxVmMaI2rMQC_7f6jG2RumcOyA&s",
        "https://cdn.shopify.com/s/files/1/0263/9387/0398/files/birthday-party-with-delicious-homemade-cupcakes-vibrant-decorations-generated-by-artificial-intelligence_188544-150098_480x480.jpg",
        "https://assets.unileversolutions.com/v1/985812.jpg",
        "https://www.seminairesbusiness.com/wp-content/uploads/2023/08/organiser-un-afterwork.jpg",
        "https://www.valleesdegavarnie.com/wp-content/uploads/2023/12/2023-octobre-skiderando-cpmeyer-ae-medias-5-1920x1062-1727086189.jpg",
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSIuAk-uCz1hgQbHXtbnH3Q-FIyYpFzGaPc5w&s",
        "https://image.newyorkcity.fr/wp-content/uploads/2016/07/NHL-Ice-Hockey-in-New-York-700x400.jpg.webp",
        "https://www.petitfute.com/images/adresse/visuel-generique/restaurants/visuel12.jpg",
        "https://cmsphoto.ww-cdn.com/superstatic/14234/art/grande/58908929-43381174.jpg?v=1631617377",
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR57G-K7A9gB3gGzMOwr0MyqdJj5eDDmd0x9Sh6SGI8GFVtGW3Ve1Fj4_ze6uwZIk-gBHI&usqp=CAU",
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR57G-K7A9gB3gGzMOwr0MyqdJj5eDDmd0x9Sh6SGI8GFVtGW3Ve1Fj4_ze6uwZIk-gBHI&usqp=CAU",
    )
}
