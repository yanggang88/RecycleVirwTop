package com.xhs.recycleviewtop.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.xhs.recycleviewtop.R
import com.xhs.recycleviewtop.bean.UserInfoBean

/**
 * 时间：2021-05-11 08:49
 * 作者：yanggang
 * 邮箱：1334045135@qq.com
 */
class MyAdapter(var context: Context) : RecyclerView.Adapter<MyAdapter.MyHolder>() {

    var dataList: ArrayList<UserInfoBean>? = null

    // 包装view
    inner class MyHolder(item: View) : RecyclerView.ViewHolder(item) {
        val titleName = item.findViewById<TextView>(R.id.titleName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        // 创建view
        var mview = LayoutInflater.from(context).inflate(R.layout.mylist_item_layout, parent, false)
        return MyHolder(mview)
    }

    override fun getItemCount(): Int {
        // 数据源为空则返回0，否则返回数据大小
        return dataList?.size ?: 0
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        // 绑定视图   ?:  为空则执行后面的数据或者表达式
        holder.titleName.text = dataList?.get(position)?.userName ?: ""
    }

}