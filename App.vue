<script>
	export default {
		onLaunch: function() {
			console.warn('当前组件仅支持 uni_modules 目录结构 ，请升级 HBuilderX 到 3.1.0 版本以上！')
			console.log('App Launch')
<<<<<<< HEAD
		},
		onShow: function() {
			console.log('App Show')
		},
		onHide: function() {
			console.log('App Hide')
		}
=======
			uni.$on('aaa', res => {
				this.globalData.value=res
				console.log(res);
			})
		},
		onShow: function() {
			//console.log('App Show')
			
			
		},
		onHide: function() {
			//console.log('App Hide')
		},
		
		//全局变量
		globalData:{
			value:''
			
		},
		//全局函数
		methods:{
			randomId: function() {
				let a = '';
				for (let i = 0; i < 9; i++) {
					let randoma = Math.floor(Math.random() * 10)
					a += randoma + '';
				}
				return a
			},
		},
		
>>>>>>> dc1d0b5 (FirstMarket)
	}
</script>

<style lang="scss">
	/*每个页面公共css */
	@import '@/uni_modules/uni-scss/index.scss';
	/* #ifndef APP-NVUE */
	@import '@/static/customicons.css';
	// 设置整个项目的背景色
	page {
		background-color: #f5f5f5;
	}

	/* #endif */
	.example-info {
		font-size: 14px;
		color: #333;
		padding: 10px;
	}
<<<<<<< HEAD
=======
	
	::-webkit-scrollbar {
			display: none;
			width: 0 !important;
			height: 0 !important;
			-webkit-appearance: none;
			background: transparent;
			color: transparent;
		}
		
	 
		
		.uni-scroll-view .uni-scroll-view::-webkit-scrollbar {
			/* 隐藏滚动条，但依旧具备可以滚动的功能 */
			display: none;
			width: 0 !important;
			height: 0 !important;
			-webkit-appearance: none;
			background: transparent;
			color: transparent;
		}

	
>>>>>>> dc1d0b5 (FirstMarket)
</style>
