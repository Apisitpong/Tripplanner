package com.example.tripplanner.model

class CafeModel {

    var title:String? = null
    var titleB:String? = null
    var image:String? = null
    var imageB:String? = null
    var key:String? = null
    var keyB:String? = null
    var detail:String? = null
    var detailB:String? = null

    constructor()
    constructor(title: String?,image: String?,key: String?,detail: String?){
        this.title = title
        this.titleB = title
        this.image = image
        this.imageB = image
        this.key = key.toString()
        this.keyB = key.toString()
        this.detail = detail
        this.detailB = detail
    }
    fun toMap():Map<String,Any>{
        val result = HashMap<String,Any>()
        result.put("title", title!!)
        result.put("titleB", titleB!!)
        result.put("image", image!!)
        result.put("imageB", imageB!!)
        result.put("key", key!!)
        result.put("keyB", keyB!!)
        result.put("detail", detail!!)
        result.put("detailB", detailB!!)
        return  result
    }
}