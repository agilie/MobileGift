package com.agilie.agmobilegiftinterface.builder

class ShakeBuilder : Builder() {

    private val shake = Shake()

    override fun getBuilding(): Shake {
        return shake
    }


}