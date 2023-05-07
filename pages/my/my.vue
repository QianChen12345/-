<template>
	<view v-if="status">
		<!--账号-->
		<view class="inputView">
			<label class="loginLab">账号</label>
			<input class="inputText" placeholder="请输入账号" :value='this.account' @input="phoneInput" />
		</view>
		<view class="line"></view>

		<!--密码-->
		<view class="inputView">
			<label class="loginLab">密码</label>
			<input class="inputText" password="true" placeholder="请输入密码" :value='this.password' @input="passwordInput" />
		</view>

		<button @tap="login" class="login">登录</button>
		<button @tap="register">注册</button>
	</view>
	
	
	
	<view v-else>
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
		
		
	</view>
</template>

<script>
	export default {
		data() {
			return {
				account: '',
				password: '',
			status:'true',
			}
		},
		onLoad() {

			this.already()
			console.log(this.account)


		},
		onShow() {
			
			this.password = ''
			
		},

		methods: {
			phoneInput: function(e) {
				this.account = e.detail.value
				//console.log(this.account)
			},

			// 获取输入密码 
			passwordInput: function(e) {
				this.password = e.detail.value
				//console.log(this.password)
			},

			login: function() {
				var that = this
				if (that.account.length == 0 || that.password.length == 0) {
					uni.showToast({
						title: '用户名和密码不能为空',
						icon: 'none',
						duration: 2000
					})
				} else {
					uni.request({
						url: "http://localhost:8080/tomcatexample/login",
						data: {
							account: encodeURI(this.account),
							password: this.password
						},
						success(res) {
							console.log(res)
							if (res.data == '登录成功') {
								uni.setStorage({
									key: 'logininfo',
									data: [that.account, that.password],
									success() {
										console.log("登录信息已存储")
									}
								})
								that.status=false

								// uni.navigateTo({
								// 	url: "/subpkg/login/login",
								// })
							} else {
								uni.showToast({
									title: "用户名或密码错误",
									icon: 'none',
									uration: 2000
								})
							}

						}
					})

				}
			},

			already: function() {
				var that = this
				uni.getStorage({
					key: 'logininfo',
					success(res) {
						console.log(res)
						if(res.data[0]==''){
							that.account = ''
							that.password = ''
						}else{
							that.account = res.data[0]
							that.password = res.data[1]
							that.login()
							
						}
						

					},

				})
			},


			register: function() {
				uni.navigateTo({
					url: "/subpkg/register/register",
				})
			},
			
			sendgoods:function(){
				uni.navigateTo({
					url:'/subpkg/login/sendgoods/sendgoods'
				})
			},
			goback:function(){
				var that=this
				uni.showModal({
					title:'提醒',
					content:'退出后需重新登录',
					success(res) {
						if(res.confirm){
							uni.removeStorage({
								key:'logininfo',
								success() {
									console.log("已删除登录数据")
								}
							})
							that.status=true
						}
					}
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

			// setStorage() {
			// 	uni.setStorage({
			// 		key: 'information', //存储到本地的名字
			// 		data:that.itemlist,,//存储的数据
			// 		success() {
			// 			console.log('存储成功')
			// 		}
			// 	})
			// },
			// getStorage() {
			// 	uni.getStorage({
			// 		key: 'information',
			// 		success(res) {
			// 			console.log('获取成功', res)
			// 		}
			// 	})
			// }


			//login: function() {
			// uni.getUserProfile({
			// 	desc: "用于信息完善",
			// 	success:(res)=> {
			// 		console.log(res.userInfo);
			// 		// var user = res.userInfo;

			// 		this.userInfo=res.userInfo;
			// 	}
			// })

			//登录按钮触发的事件
			//let that = this
			//调用微信小程序的登录接口
			// 	wx.login({
			// 		success(e) {
			// 			that.loginInfo.code = e.code //拿到的code存储在data中
			// 			wx.showModal({
			// 				title: '温馨提示',
			// 				content: '微信授权登录后才能正常使用小程序功能',
			// 				cancelText: '拒绝',
			// 				confirmText: '同意',
			// 				success(sucessInfo) {
			// 					//调用微信小程序的获取用户信息的接口
			// 					wx.getUserProfile({
			// 						desc: '用于完善会员资料', // 声明获取用户个人信息后的用途
			// 						lang: 'zh_CN',
			// 						success(info) {
			// 							//把获取到的信息复制到data中的loginInfo中
			// 							that.loginInfo = Object.assign(that
			// 								.loginInfo, info)
			// 							//调用后台的接口，把所有整合的个人信息传过去
			// 							that.handlerLogin(that.loginInfo)
			// 						},
			// 						fail(e) {
			// 							console.log('获取用户信息失败', e)
			// 						}
			// 					})
			// 				},
			// 				fail() {
			// 					console.log("拒绝")
			// 				},
			// 				complete() {}
			// 			})
			// 		},
			// 		fail(e) {
			// 			console.log('fail', e)
			// 			wx.showToast({
			// 				title: '网络异常',
			// 				duration: 2000
			// 			})
			// 			return
			// 		}
			// 	})
			// },
			//}
		},
	}
</script>

<style>
	.inputView {
		background-color: #fff;
		line-height: 44px;
	}

	.loginLab {
		margin: 15px 15px 15px 10px;
		color: #545454;
		font-size: 14px
	}

	.inputText {
		flex: block;
		float: right;
		text-align: right;
		margin-right: 22px;
		margin-top: 11px;
		color: #cccccc;
		font-size: 14px
	}

	.line {
		width: 100%;
		height: 1px;
		background-color: #cccccc;
		margin-top: 1px;
	}
	
	.box {
		background-color: #fff;
		line-height: 44px;
	}
	
	
/* 	.login{
		width: 90%;
		border-radius: 100px;
		background-color: #c00000;
	} */
</style>
