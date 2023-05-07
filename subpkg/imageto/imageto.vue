<template>
	<view>
		<scroll-view class="rightscroll" scroll-y="true" :style="{height: wh+'px'}">
			<view class="listrecommend">
				<view class="listcontainer" v-for="(item,i) in itemlist" :key="i" @click="goodsdetail(item.itemid)">

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
</template>

<script>
	export default {
		data() {
			return {
				active: 0,
				itemlist:[],
			};
		},
		onLoad(option) {
			this.active=option.index
			console.log(option.index)
			if(this.active==2){
				uni.setNavigationBarTitle({
					title:'手机推荐',
				})
			}else if(this.active==1){
				uni.setNavigationBarTitle({
					title:'书籍推荐',
				})
			}
			else if(this.active==8){
				uni.setNavigationBarTitle({
					title:'衣物推荐',
				})
			}else if(this.active==9){
				uni.setNavigationBarTitle({
					title:'其他推荐',
				})
			}
			this.getactive()
		},
		methods: {
			
			getactive() {
				var that = this;
				uni.request({
					url: 'http://localhost:8080/tomcatexample/goodschoose',
					header: {
						"Content-Type": "application/text",
					},
					methods: "post",
					data: {
						active: that.active
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
			},
			goodsdetail: function(i) {
			
				uni.navigateTo({
					url: '../../subpkg/goodsdetail/goodsdetail?goodsid=' + i
				})
			},
		}
	}
</script>

<style lang="scss">
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
