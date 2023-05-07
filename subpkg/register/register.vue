<template>
	<view>

		<!--注册账号-->
		<view class="inputView">
			<label class="loginLab">注册账号</label>
			<input class="inputText" placeholder="请创建账号" @input="phoneInput" />
		</view>
		<view class="line"></view>

		<!--注册密码-->
		<view class="inputView">
			<label class="loginLab">注册密码</label>
			<input class="inputText" password="true" placeholder="请创建自己的密码" @input="passwordInput" />
		</view>
		<view class="line"></view>
		<!-- 确定注册密码 -->
		<view class="inputView">
			<label class="loginLab">确定密码</label>
			<input class="inputText" password="true" placeholder="请重复输入自己的密码" @input="confirmpasswordinput" />
		</view>

		<button @tap="register">注册</button>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				account: "",
				password: "",
				confirmpassword: ""
			}
		},

		methods: {
			register: function() {
				if (this.account.length == 0 || this.password.length == 0) {
					uni.showToast({
						title: '用户名和密码不能为空',
						icon: 'none',
						duration: 2000
					})
				} else if (this.password != this.confirmpassword) {
					uni.showToast({
						title: '两次密码不一致',
						icon: 'none',
						duration: 2000
					})
				} else {
					uni.request({
						url: "http://localhost:8080/tomcatexample/register",
						data: {
							account: encodeURI(this.account),
							password: this.password
						},
						success(res) {
							if (res.data == '此用户名已经注册') {
								uni.showToast({
									title: '此用户名已经注册',
									icon: 'none',
									duration: 2000
								})
							} else {
								uni.showToast({
										title: '注册成功',
										icon: 'none',
										duration: 2000
									}),
									setTimeout(() => {
										//退回前一页面，不能是显示出来的第一页面
										uni.navigateBack({
											delta: 1, // 返回的页面数
										})
									}, 2000)

							}

						},
						fail() {
							console.log("调用接口失败")
						}


					})
				}
			},
			phoneInput: function(e) {
				this.account = e.detail.value
				console.log(this.account)
			},

			passwordInput: function(e) {
				this.password = e.detail.value
				console.log(this.password)
			},
			confirmpasswordinput: function(e) {
				this.confirmpassword = e.detail.value
				console.log(this.confirmpassword)
			},




		}
	}
</script>

<style lang="scss">
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
</style>
