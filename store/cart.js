export default{
	//为当前模块开启命名空间
	namespaced:true,
	state:()=>({
		cart:JSON.parse( uni.getStorageSync('cart')||'[123]')
	}),
	mutations:{
		addcart(state,goods){
			
				state.cart.push(goods)
			
			
			this.commit("m-cart/savestorage")
		},
		savestorage(state){
			uni.setStorageSync('cart',JSON.stringify(state.cart))
		}
		
		getcart(context, data) {
					context.commit("cart", data)
				}
		
	},
	
	
	
	getters:{
		
	}
}