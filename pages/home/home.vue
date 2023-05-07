<template >

	<view class="homenav">
		<swiper autoplay indicator-dots circular interval="3000" indicator-active-color="white">
			<swiper-item v-for="(item,i) in ima" :key="i">
				<view class="swiper-item">
					<image :src="item"></image>
				</view>
			</swiper-item>
		</swiper>

		<!-- 首页分类区域 -->
		<view class="nav-list">
			<view class="nav-item" v-for="(item,i) in classification" :key="i" >
				<image :src="item.msg" class="nav-img" @click="imageto(item.text)"></image>
				<text class="nav-text">{{item.text}}</text>
			</view>

		</view>

	</view>

	<view class="listrecommend">
		<view class="" v-for="(item,i) in itemlist" :key="i" @click="goodsdetail(item.itemid)">
			<view class="listcontainer">
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
	</view>




</template>

<script>
	export default {
		data() {
			return {
				itemlist: [],
				ima: ["/static/images/swiper/cover.jpeg", "/static/images/swiper/ed962e1b7ed92ff053b0a2e1e0136c0c.jpeg",
					"/static/images/swiper/0810b245ecfd9bafc241f34e39dda29e.png"
				],

				classificationlist: [],
				classification:
					//  [
					// 	"/static/images/navigation/手机.png",


					// 	"/static/images/navigation/书籍.png",



					// 	"/static/images/navigation/衣物.png",


					// 	"/static/images/navigation/其它.png"
					// ]


					[{
							msg: "/static/images/navigation/phone.png",
							text: "手机"
						}, {
							msg: "/static/images/navigation/book.png",
							text: "书籍"
						},
						{
							msg: "/static/images/navigation/clothes.png",
							text: "衣物"
						}, {
							msg: "/static/images/navigation/other.png",
							text: "其他"
						}
					]


			};
		},
		onLoad() {
			// this.getclassificationlist();
			this.getitemlist()

		},
		// onPullDownRefresh() {
		// 	this.getitemlist()
		// },
		onReachBottom() {

			this.getitemlist()
		},
		methods: {
			// async getclassificationlist() {
			// 	const { data: res
			// 	} = await uni.$http.get('/gallery/2qKVJBC')
			// 	if (res.meta.status !== 200) return uni.$showMsg()
			// 	this.navList = res.message;
			// }

			getitemlist() {
				uni.request({
					url: 'http://localhost:8080/tomcatexample/printdatabase.jsp ',

					success: (res) => {
						console.log(res.data)
						this.itemlist = res.data
						//	console.log(this.itemlist )
						this.itemlist.forEach(res => {
							//console.log(res.itemimage.split("-"))
							if (res.itemimage.split("-").length != 1) {
								res.itemimage = res.itemimage.split("-")[0]
							}

						})
						console.log(this.itemlist)

						//动态绑定地址
						//this.itempath = `/static/images/itemimage/${this.itemlist.itemimage}`


					}
				})
			},
			goodsdetail: function(i) {

				uni.navigateTo({
					url: '../../subpkg/goodsdetail/goodsdetail?goodsid=' + i
				})
			},
			imageto: function(i) {
				console.log(i)
				var index=0;
				
				if(i=='手机'){
					index=2
				}else if(i=='书籍'){
					index=1
				}else if(i=='衣物'){
					index=8
				}else{
					index=9
				}
				uni.navigateTo({
					url: '/subpkg/imageto/imageto?index='+index,
				})
			},



		}
	}
</script>

<style lang="scss">




	.homenav {
		position: sticky;
		top: 0px;
		z-index: 999;
	}

	swiper {
		height: 360rpx;


		.swiper-item,
		image {
			height: 100%;
			width: 100%;

		}
	}

	.nav-list {
		display: flex;
		justify-content: space-around;

		background-color: #f0f0f0;

		.nav-item {
			display: flex;
			flex-direction: column;
		}

		.nav-text {
			margin: 0px 15px;
		}

		.nav-img {
			width: 128rpx;
			height: 140rpx;
			margin-top: 10px;

		}
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
				white-space: nowrap;
				overflow: hidden;
				text-overflow: ellipsis;
				// display: -webkit-box;
				// overflow: hidden;
				// text-overflow: ellipsis;
				// word-break: break-all;
				// -webkit-box-orient: vertical;
				// -webkit-line-clamp: 3;
			}
		}
	}
</style>
