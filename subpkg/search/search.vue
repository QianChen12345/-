<template>
	<!-- 点击搜索框后进入搜索界面 -->
	<view class="search">
		<uni-search-bar focus placeholder="请输入搜索内容" @input="input" :radius="100" cancelbutton="none"></uni-search-bar>
	</view>


	<view class="listrecommend">
		<view class="listcontainer" v-for="(item,i) in searchList" :key="i">

			<view class="listimage">
				<image :src="item.itemimage" class="imagewh"></image>
			</view>

			<view class="list">
				<text class="name">商品名：{{item.itemname}}</text>
				<text class="price">售价：￥{{item.itemprice}}</text>
				<text class="describle">商品描述：{{item.itemdesc}}</text>

			</view>

		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				timer: null,
				kw: '',
				templelist: [],
				//搜索结果列表
				searchList: []

			}
		},
		onLoad() {
			this.getSearchList()
			uni.setNavigationBarTitle({
				title:'搜索宝贝'
			})	
		},

		methods: {

			input(e) {
				console.log(e)
				clearTimeout(this.timer)
				this.timer = setTimeout(() => {
					// console.log(e)
					this.kw = e;
					this.getSearchList()

					// console.log(this.templelist)

					// this.templelist.forEach(res=>{
					// 	console.log(res)

					// 	if(res.itemname==this.kw){
					// 		this.serachList.push(res)
					// 	}
					// })
				}, 500)




			},

			getSearchList() {
				if (this.kw.length === 0) {
					this.searchList = []
					return;
				}
				// const {
				// 	data: res
				// } = await uni.$http.get('/api/public/v1/goods/qsearch', {})

				uni.request({
					url: 'http://localhost:8080/tomcatexample/printdatabase.jsp ',
					success: (res) => {
						//console.log(res.data)
						//	console.log(this.searchList )
						this.searchList=[]
					res.data.forEach(res => {
							//console.log(res.itemimage.split("-"))
							if (res.itemimage.split("-").length != 1) {
								res.itemimage = res.itemimage.split("-")[0]
							}
							
							if(this.kw==res.itemname){
								this.searchList.push(res)
							}

						})
						console.log(this.searchList)
						
						
					}
				})
				//	if (res.status !== 200) return uni.$showMsg()

			}
		}

	}
</script>

<style lang="scss">
	.search {
		//设置搜索框吸顶
		position: sticky;
		top: 0px;
	}


	.listcontainer {
		display: flex;
		padding: 10px 5px;
		border-bottom: 1px solid #f0f0f0;

		.listimage {
			margin-right: 10px;

			.imagewh {
				width: 100px;
				height: 100px;
			}
		}


		.list {
			display: flex;
			flex-direction: column;
			justify-content: space-between;
			height: 100px;

			.name {
				font-size: 18px;
			}

			.price {
				color: #c00000;
				font-size: 18px;

			}

			.describle {


				display: -webkit-box;
				overflow: hidden;
				text-overflow: ellipsis;
				word-break: break-all;
				-webkit-box-orient: vertical;
				-webkit-line-clamp: 3;
			}
		}
	}
</style>
