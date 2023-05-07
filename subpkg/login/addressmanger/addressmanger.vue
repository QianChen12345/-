<template>
	<view class="addressbox">
		<input class="inputadress" placeholder="请创建新的交易地址" type="text" v-model="this.address" focus="true">
		<button type="primary" size="mini" @click="createaddress">新建交易地址</button>

		<view class="addressname" v-for="(item,i) in addresstype" :key="i" @click="deleteaddress(i)">
			{{item}}
			<image class="addressdelete" src="/static/images/address/delete.png"></image>
		</view>

	</view>

</template>

<script>
	export default {
		data() {
			return {
				address: "",
				addresstype: ['学校北门', '学校南门', '学校西门', '学校东门', '学校一号教学楼下', '学校一号食堂门口']

			};
		},
		onLoad() {
			var that = this
			uni.getStorage({
				key: 'newaddress',
				success(res) {
					if (res.data == that.addresstype) {

					} else {
						that.addresstype = res.data
					}
					console.log(res)
				}
			})
			uni.setNavigationBarTitle({
				title:'交易地址管理',
			})
		},
		methods: {
			changeadress: function(e) {
				this.adressindex = e.target.value
				console.log(this.adressindex)
				this.defaultadress = this.addresstype[this.adressindex]
			},
			addressInput: function(e) {
				this.address = e.detail.value
				//console.log(this.account)

			},
			createaddress: function() {
				var that = this
				console.log(that.address)
				if (that.address != "") {
					that.addresstype.push(this.address)
					uni.setStorage({
						key: 'newaddress',
						data: that.addresstype,
						success() {
							uni.showToast({
								title: "交易地址新建成功",
								icon: 'none',
								uration: 2000
							})
						}
					})
					that.address = ''
				} else {

					uni.showToast({
						title: "交易地址不能为空",
						icon: 'none',
						uration: 2000
					})
				}
			},
			deleteaddress(i){
				this.addresstype.splice(i,1)
				uni.setStorage({
					key: 'newaddress',
					data: this.addresstype,
					success() {
						uni.showToast({
							title: "交易地址删除成功",
							icon: 'none',
							uration: 2000
						})
					}
				})
			}
		}
	}
</script>

<style lang="scss">
	input {
		border: 1px solid;
		height: 30px;
	}

	button {
		margin-top: 30px;
		margin-left: 130px;
	}

	.addressname {
		font-size: 25px;
		display: flex;
		align-items: center;
		justify-content: space-between;
	}

	.addressdelete {
		width: 20px;
		height: 20px;
		margin-right: 25rpx;



	}
</style>
