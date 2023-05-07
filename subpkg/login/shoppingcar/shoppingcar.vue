<template>
	<view class="listrecommend" v-for="(item,i) in itemlist" :key="i" v-if="status">
		<view class="itembox">
			<view class="selectbox" @click="selectgood(item)">
				<label>
					<checkbox class="selected" color="#555555" :checked="checked" />
				</label>
			</view>
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

		<image src="/static/images/address/delete.png" class="gooddelete" @click="deletegood(i)"></image>
	</view>
	<view class="" v-else>
		{{this.word}}
	</view>

	<view class="end">
		<view class="end-left">
			<checkbox-group @click="checkedall">
				<label>
					<checkbox :checked="allchecked" />全选
				</label>
			</checkbox-group>
			<view>
				总计：<text style="color: #f00;font-weight: bold;">￥ {{totalPrice}}</text>
			</view>
		</view>
		<view class="end-right">
			结算({{totalnum}})
		</view>
	</view>



</template>

<script>
	export default {
		data() {
			return {
				itemlist: [],
				checked: true,
				allchecked: true,
				status: true,
				word: '购物车为空',
				useraccount: '',


			}
		},
		computed: {
			totalnum() {
				let totalnum = 0
				if(this.itemlist==[]){
					totalnum=0
				}else{
					this.itemlist.map(item => {
						item.checked ? totalnum += 1 : totalnum += 0
					})
				}
				
				return totalnum
			},
			totalPrice() {
				let totalPrice = 0
				if(this.itemlist==[]){
					totalPrice=0
				}else{
					this.itemlist.map(item => {
						item.checked ? totalPrice += parseFloat(item.itemprice) : totalPrice += 0
					})
				}
				
				return totalPrice
			}
		},
		onLoad() {
			var that = this

			uni.setNavigationBarTitle({
				title: '我的购物车',
			})
			// wx.getSystemInfo({
			//     success:function(res) {
			//     console.log(res.windowWidth, '宽')
			//     console.log(res.windowHeight, '长')   
			//     }
			// })





		},
		onShow() {

			var that = this

			uni.getStorage({
				key: 'logininfo',
				success(res) {
					that.useraccount = res.data[0]
					console.log(1)
					that.itemlist = uni.getStorageSync(that.useraccount)
					if (that.itemlist == []) {
						that.status = false
						console.log(that.status)
					} else {
						console.log(that.itemlist)
						that.itemlist.forEach(res => {
							//console.log(res.itemimage.split("-"))
							if (res.itemimage.split("-").length != 1) {
								res.itemimage = res.itemimage.split("-")[0]
							}
								
						})
					}
			
				}
			})
			
			
			//为每一个商品列表设置一个是否选中的新属性
			for (let i = 0; i < that.itemlist.length; i++) {
				that.$set(that.itemlist[i], "checked", that.checked);
			}


		},

		methods: {
			selectgood(item) {
				var that = this
				item.checked = !item.checked
				console.log(that.allchecked)
				for (let i = 0; i < that.itemlist.length; i++) {
					if (that.itemlist[i].checked != true) {
						that.allchecked = false

					}
				}
				console.log(that.allchecked)

			},
			checkedall: function() {
				this.allchecked = !this.allchecked
				console.log(this.allchecked)
				if (this.allchecked == true) {
					this.checked = true
				} else {
					this.checked = false
				}
				for (let i = 0; i < this.itemlist.length; i++) {
					this.$set(this.itemlist[i], "checked", this.checked);
				}
			},
			deletegood(i) {
				var that = this
				console.log(i)
				that.itemlist.splice(i, 1)
				uni.setStorage({
					key: that.useraccount,
					data: that.itemlist,
					success() {
						uni.showToast({
							title: "购物车商品删除成功",
							icon: 'none',
							uration: 2000
						})
						if (that.itemlist == '') {
							that.status = false
						}
					}
				})

			},
		}
	}
</script>

<style>
	@import url('/CSS/goods.css');

	.listrecommend {
		display: flex;
		align-items: center;

	}


	.itembox {
		display: flex;
		align-items: center;
		padding-left: 30rpx;

	}


	.listcontainer {
		margin-left: 15rpx;
		z-index: 1;
	}

	.end {
		width: 100%;
		height: 90rpx;
		background-color: #fff;
		position: fixed;
		bottom: 0rpx;
		left: 0;
		display: flex;
		align-items: center;
		z-index: 3;


	}

	.end-left {
		width: 70%;
		display: flex;
		justify-content: space-between;
		padding: 0 30rpx;

	}

	.end-right {
		width: 30%;
		line-height: 90rpx;
		background-color: #F44545;
		text-align: center;
		color: #fff;
	}

	.paytotal {

		background-color: #ff0000;
		width: 80%;
		margin-right: 0px;
	}

	.gooddelete {
		width: 20px;
		height: 20px;
		position: absolute;
		right: 15px;
		z-index: 2;


	}
</style>
