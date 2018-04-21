package com.uninder.uninder.model

enum class Gender(val identifier:Int) {
    MALE(1), FEMALE(2), OTHER(3);

    companion object {
        private val lookUp = mutableMapOf<Int, Gender>()

        init {
            Gender.values().forEach { lookUp[it.identifier] = it }
        }

        fun get(identifier:Int):Gender? {
            return lookUp[identifier]
        }
    }
}