
// #ifndef VUE3
import Vue from 'vue'
import App from './App'
<<<<<<< HEAD
=======
import Axios from 'axios'
import { $http } from '@escook/request-miniprogram'
import store from '@/store/store.js'

>>>>>>> dc1d0b5 (FirstMarket)

Vue.config.productionTip = false

App.mpType = 'app'

const app = new Vue({
    ...App
<<<<<<< HEAD
=======
	store
>>>>>>> dc1d0b5 (FirstMarket)
})
app.$mount()
// #endif

// #ifdef VUE3
import { createSSRApp } from 'vue'
import App from './App.vue'
<<<<<<< HEAD
=======

>>>>>>> dc1d0b5 (FirstMarket)
export function createApp() {
  const app = createSSRApp(App)
  return {
    app
  }
}
<<<<<<< HEAD
=======

//封装数据请求失败时提示方法
uni.$showMsg=function(title="请求数据失败",duration=1500){
	uni.showToast({
		title,
		duration,
		icon:'none'
	})
}


uni.$submitgoods=function(title="提交商品失败",duration=1500){
	uni.showToast({
		title,
		duration,
		icon:'none'
	})
}


>>>>>>> dc1d0b5 (FirstMarket)
// #endif