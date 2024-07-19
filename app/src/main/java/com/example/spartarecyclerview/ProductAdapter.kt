package com.example.spartarecyclerview

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.spartarecyclerview.databinding.ItemProductRecyclerviewBinding

// "어댑터" 클래스는 파라미터로 (데이터리스트)를, :상속의 제너릭 타입으로 <뷰홀더>를 받아서 둘을 연결해줍니다
class ProductAdapter(val products: MutableList<UserProduct>) : RecyclerView.Adapter<ProductAdapter.Holder>() {
    // "어댑터" 클래스는 데이터 리스트 "products"를 파라미터로 받습니다. 데이터 클래스 <UserProduct>를 취급합니다.
    // "어댑터" 클래스는 : RecyclerView.Adapter() 클래스를 상속합니다. 이너클래스인 뷰홀더 <ProductAdapter.Holder>를 취급합니다


    inner class Holder(val binding: ItemProductRecyclerviewBinding) : RecyclerView.ViewHolder(binding.root){
        // "뷰 홀더"는 "recycler 뷰"를 받아오는 이너클래스입니다
        // 레이아웃을 받아오는 역할을 합니다
        var img = binding.ivImg
        var title = binding.tvTitle
        var region = binding.tvRegion
        var price = binding.tvPrice
        var messageCount = binding.tvMessageCount
        var likeCount = binding.tvLikeCount
    }


    // 필수 멤버 3대장을 소환합니다
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        var binding = ItemProductRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false) // inflate() 파라미터에 parent 뷰그룹이 연관되는 것을 유의합시다 !
        return Holder(binding)
        // onCreate. 뷰홀더를 생성합니다
        // 뷰바인딩에 inflater를 달아서 -> 이너클래스 "뷰홀더"의 파라미터에 집어넣습니다
    }



    // 클릭 리스너에 사용할 추상메서드 인터페이스를 설계합니다
    interface ItemClick {
        fun onClick(view : View, position : Int){
            Log.i("onclick_test", "눌림")
        }

        // onClick은 { 코드블럭 } 없이 ( 파라미터 )로 view, position 만 받아오는 추상 메서드입니다
        // 액티비티에서 메서드를 override 해서 코드를 작성해줍시다
    }
    var itemClick : ItemClick? = null

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.img.setImageResource(products[position].img)
        holder.title.text = products[position].title
        holder.region.text = products[position].region
        holder.price.text = UserProductManager.convertWon(products[position].price)
        holder.messageCount.text = products[position].messageCount.toString()
        holder.likeCount.text = products[position].likeCount.toString()
        // onBind. 뷰홀더에 값을 집어넣습니다
        // holder.레이아웃에 데이터리스트[position]의 프로퍼티를 대입하는 식입니다

        holder.itemView.setOnClickListener {
            // 모든 itemView 에 클릭리스너를 달아줍니다
            itemClick?.onClick(it, position) // ItemClick 인터페이스의 .onClick 메서드를 작동합니다
        }

    }

    override fun getItemCount(): Int {
        return products.size // 데이터 리스트.size
    }

    // ID 메서드도 추가로 override 합니다
    override fun getItemId(position: Int): Long {
        return position.toLong() // 순서를 식별자로
    }

}