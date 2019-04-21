package com.entaku.football.service.model



class Team {

    init{

    }
    val id:Int = 0
    var area : Area = Area()
    var name:String = ""
    var shortName:String = ""
    var crestUrl:String = "http://upload.wikimedia.org/wikipedia/en/5/53/Arsenal_FC.svg"
}


class Area {

    val id:Int = 0
    var name:String = ""

}