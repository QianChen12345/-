import Vue from 'vue'
import Vuex from 'vuex'
import moduleCart from '@/store/cart.js'
Vue.use(Vuex)
const store = new Vuex.Store({
	state: {
		cart: [],
	}
	mutations: {
		addcart(state, goods) {

			state.cart.push(goods)


			this.commit("m-cart/savestorage")
		},

		getcart(context, data) {
					context.commit("cart", data)
				}
		// savestorage(state) {
		// 	uni.setStorageSync('cart', JSON.stringify(state.cart))
		// }

	},
	actions: {

	},
	modules: {
		'm-cart': moduleCart
	}
})
export default store
