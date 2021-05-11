package com.xhs.recycleviewtop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.xhs.recycleviewtop.adapter.MyAdapter
import com.xhs.recycleviewtop.bean.UserInfoBean
import com.xhs.recycleviewtop.util.MyItemDecoration
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    // 定义recycleview 适配器
    var adapter: MyAdapter? = null
    var list: ArrayList<UserInfoBean>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecycleView()
        initData()
    }

    /**
     * 生成模拟数据
     * @author yanggang 1334045135@qq.com
     * @date 2021-05-11 9:36
     **/
    private fun initData() {
        list = ArrayList()
        var round = Random(30)
        for (i in 1..20) {
            for (j in 0..5) {
                var userInfoBean = UserInfoBean()
                userInfoBean.className = "康杰中学${i}班"
                userInfoBean.age = round.nextInt()
                userInfoBean.userName = "${i}班----张三${j}号备胎"
                list?.add(userInfoBean)
            }

        }
        // 把数据填充到适配器中
        adapter?.dataList = list
        adapter?.notifyDataSetChanged()
    }

    /**
     * 初始化 recycleview
     * @author yanggang 1334045135@qq.com
     * @date 2021-05-11 9:09
     **/
    private fun initRecycleView() {
        // 创建适配器
        adapter = MyAdapter(this)
        // 创建布局管理器    最后一个参数  reverseLayout  是否倒序
        var layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        // 给recycleview设置布局管理器
        recycle_view.layoutManager = layoutManager
        // 创建 自定义 ItemDecoration
        var itemDecoration = MyItemDecoration(this)
        // 设置自定义 ItemDecoration
        recycle_view.addItemDecoration(itemDecoration)
        // 给recycleview设置适配器
        recycle_view.adapter = adapter
    }
}