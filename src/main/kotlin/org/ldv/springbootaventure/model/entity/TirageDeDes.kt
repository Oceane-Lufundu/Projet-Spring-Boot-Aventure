package org.ldv.springbootaventure.model.entity

class TirageDeDes(
    val nbDe:Int, val maxDe:Int) {

    fun lance():Int{

        var resultat=0
        repeat(this.nbDe){
            resultat+=(1..this.maxDe).random()
        }
        return resultat
    }
}