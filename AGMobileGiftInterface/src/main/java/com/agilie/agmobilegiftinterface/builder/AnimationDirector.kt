package com.agilie.agmobilegiftinterface.builder

class AnimationDirector {

    companion object {
        fun getShake(): Shake {
            val shakeBuilder = ShakeBuilder()
            //shakeBuilder.setView()
            return shakeBuilder.getBuilding()
        }
    }
}


