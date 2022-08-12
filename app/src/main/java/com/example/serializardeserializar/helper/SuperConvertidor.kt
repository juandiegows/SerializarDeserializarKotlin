package com.example.serializardeserializar.helper

import android.util.Log
import org.json.JSONArray
import org.json.JSONObject
import java.lang.Exception
import kotlin.reflect.javaType
import kotlin.reflect.typeOf

@OptIn(ExperimentalStdlibApi::class)


fun JSONObject.toClass(nameClass: String): Any {

    var oClass = Class.forName(nameClass)
    var any = oClass.newInstance()
    oClass.declaredFields.forEach {
        it.isAccessible = true

        it.set(
            any, when (it.genericType) {
                typeOf<Int>().javaType -> this.getInt(it.name)
                typeOf<String>().javaType -> this.getString(it.name)
                typeOf<Boolean>().javaType -> this.getBoolean(it.name)
                typeOf<Double>().javaType -> this.getDouble(it.name)
                typeOf<Long>().javaType -> this.getLong(it.name)
                typeOf<Float>().javaType -> this.getString(it.name)
                else ->try {
                    this.getJSONObject(it.name).toClass(it.type.name).Cast()
                }catch (e:Exception){
                   this.getJSONArray(it.name).toList(it.type.name)
                }
            }
        )
    }

    return any
}

fun JSONArray.toList(nameClass: String): List<Any> {
    var list: ArrayList<Any> = ArrayList<Any>()
    for (i in 0 until this.length()) {
        list.add(this.getJSONObject(i).toClass(nameClass))
    }
    return list;
}

inline fun <reified T : Any> Any.Cast(): T {
    return this as T;
}

