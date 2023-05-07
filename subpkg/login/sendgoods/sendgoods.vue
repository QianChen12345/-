<template>
	<view>
		<view class="listrecommend" v-for="(item,i) in itemlist" :key="i"  >
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
			<view class="modify-delete">
				<text class="modify">修改</text>
				<text class="delete" @click="deletegood(item.itemid,i)">删除</text>
			</view>
		</view>
		
	</view>
</template>

<script>
	// import { mapMutations } from 'vuex'
	// import {mapActions} from 'vuex'
	var app = getApp()

	export default {

		data() {
			return {
				itemlist: [],
				
			}
		},


		onLoad() {
			
				uni.setNavigationBarTitle({
					title:'自己发布的商品',
				})
			

			this.itemlist = uni.getStorageSync('addcart')
			console.log(this.itemlist)
			this.itemlist.splice(0,1)


		},
		onShow() {
			
		},
		methods: {
			//...mapMutations('m_cart',['addcart']),

			deletegood(goodsid, i) {
				var that = this
				uni.request({
					url: 'http://localhost:8080/tomcatexample/deletegood',
					data: {
						goodsid: goodsid,
					},
					success(res) {
						if (res.data == '删除成功') {
							that.itemlist.splice(i, 1)
							uni.setStorageSync("addcart",that.itemlist)
							that.itemlist = uni.getStorageSync('addcart')

						}
					
					}

				})

			}
		}




	}
</script>

<style lang="scss">
	.listrecommend {
		display: flex;
		align-items: center;
		justify-content: space-between;
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

	.modify-delete {
		display: flex;
		flex-direction: column;
		margin-right: 15px;

	}

	.modify {
		margin-bottom: 15px;
	}

	.modify,
	.delete {

		background-color: greenyellow;

	}
</style>
