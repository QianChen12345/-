<template>
	<view>
		<!-- 使用自定义组件 ,在components文件夹中-->
		<!-- 这里是自定义组件，没有原生的click函数，所以必须要去组件内部去写，用this.$emit("click") -->
		<view class="searchback">
			<my-search @click.native="gotosearch"></my-search>
		</view>




		<view class="scroll-container">
			<!-- 寻宝界面左侧分类条 -->
			<scroll-view class="leftscroll" scroll-y="true" :style="{height: wh + 'px'}">
				<block v-for="(item,i) in leftscrollname" :key="i">
					<view :class="['leftscroll-item' ,i===active?'active':'']" @click="activechange(i)">{{item}}</view>
				</block>



			</scroll-view>

			<!-- 寻宝界面右侧宝贝排列 -->
			<scroll-view class="rightscroll" scroll-y="true" :style="{height: wh+'px'}">
				<view class="listrecommend">
					<view class="listcontainer" v-for="(item,i) in itemlist" :key="i">

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

			</scroll-view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				//当前设备可用高度
				wh: 0,
				leftscrollname: ["电子产品", "书籍", "手机", "化妆品", "鞋子", "文具", "生活用品", "首饰", "衣物","其他"],
				active: 0,

				itemlist: []

			};
		},


		onLoad() {
			const sysInfo = uni.getSystemInfoSync()
			// console.log(sysInfo)
			this.wh = sysInfo.windowHeight - 50
			//	console.log(this.wh)
			this.getactive()

		},


		methods: {
			gotosearch() {
				uni.navigateTo({
					url: "/subpkg/search/search"
				})
			},


			activechange(i) {
				this.active = i;
				//this.active值为0-电子产品,5-文具
				console.log(this.active)
				this.getactive()
			},


			getactive() {
				var that = this;
				uni.request({
					url: 'http://localhost:8080/tomcatexample/goodschoose',
					header: {
						"Content-Type": "application/text",
					},
					methods: "post",
					data: {
						active: this.active
					},

					success: (res) => {
						console.log(res)
						console.log(res.statusCode)
						if (res.statusCode == 500) {
							console.log("暂无此类宝贝")
						}
						that.itemlist = res.data
						//	console.log(this.itemlist )
						that.itemlist.forEach(res => {
							//console.log(res.itemimage.split("-"))
							if (res.itemimage.split("-").length != 1) {
								res.itemimage = res.itemimage.split("-")[0]
							}

						})



					},
					fail: () => {

						console.log("调用接口失败")
					}

				})
			}



		}


	}
</script>

<style lang="scss">
	.scroll-container {
		display: flex;

		.leftscroll {
			width: 150px;
			display: flex;
			flex-direction: column;
		}




	}

	::-webkit-scrollbar {
		width: 0;
		height: 0;
		background-color: transparent;
	}

	.leftscroll-item {
		background-color: #f7f7f7;
		line-height: 60px;
		text-align: center;
		font-size: 18px;



		&.active {
			background-color: #ffffff;
			position: relative;

			&::before {
				content: '';
				display: block;
				width: 3px;
				height: 30px;
				background-color: #c00000;
				position: absolute;
				top: 25%;
				left: 0;
			}
		}
	}


	//搜索框样式

	.searchcontainer {
		height: 50px;
		background-color: #ff0000;
		display: flex;
		align-items: center;
		padding: 0px 10px;

		.searchbox {
			height: 36px;
			background-color: #ffffff;
			border-radius: 18px;
			width: 100%;
			display: flex;
			justify-content: center;
			align-items: center;

		}

		.placeholder {
			font-size: 15px;
			margin-left: 5px;
		}

	}

	.searchback {
		position: sticky;
		top: 0px;
		z-index: 999;
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
