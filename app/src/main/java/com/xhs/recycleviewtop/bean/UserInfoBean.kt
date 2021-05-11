package com.xhs.recycleviewtop.bean

import java.io.Serializable

/**
 * 时间：2021-05-11 08:52
 * 作者：yanggang
 * 邮箱：1334045135@qq.com
 */
data class UserInfoBean(
    var userName: String = "",
    var age: Int = 0,
    var className: String = ""
) : Serializable {
    companion object {
        private const val serialVersionUID = 1L
    }
}