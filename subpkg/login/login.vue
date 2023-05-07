<template>
	<view >
		<view class="box">
			<label class="shoppingcar" @click="goshoppingcar"> 我的购物车</label>
		</view>
		<view class="line"></view>
		<view class="box">
			<label class="sendgood" @click="sendgoods"> 我发布的商品</label>
		</view>
		<view class="line"></view>
		<view class="box">
			<label class="trade-address" @click="addaddress" > 我的交易地址</label>
		</view>
		<view class="line"></view>
		<view class="box">
			<label class="back" @click="goback">退出登录</label>
		</view>
		
		
		<view class="returnback">
			<button type="primary" size="mini" @click="returnback">返回至首页</button>
			
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				
			};
		},
		
		
		methods:{
			sendgoods:function(){
				uni.navigateTo({
					url:'/subpkg/login/sendgoods/sendgoods'
				})
			},
			goback:function(){
				uni.removeStorage({
					key:'logininfo',
					success() {
						console.log("已删除登录数据")
					}
				})
				uni.navigateBack({
					delta:'1',
				})
			},
			
			goshoppingcar:function(){
				uni.navigateTo({
					url:'/subpkg/login/shoppingcar/shoppingcar'
				})
			},
			addaddress:function(){
				uni.navigateTo({
					url:'/subpkg/login/addressmanger/addressmanger'
				})
			},
			returnback:function(){
				uni.navigateTo({
					url:'/pages/home/home'
				})
			}
		}
	}
</script>

<style lang="scss">
	.box {
		background-color: #fff;
		line-height: 44px;
	}

	.line {
		width: 100%;
		height: 1px;
		background-color: #cccccc;
		margin-top: 1px;
	}
	.returnback{
		margin-left:130px;
		margin-top: 50px;
	}
</style>
