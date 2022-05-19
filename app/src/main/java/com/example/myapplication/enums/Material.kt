package com.example.myapplication.enums

enum class Material(val elementsAmountOnScreen: Int, val pandaCanGoThrough: Boolean) {
    EMPTY(0, true),
    STONE(0,false),
    TREE(0,true),
    BAMBOO(3,true),
    PANDA(1,true),
    NULL(1,true)
}