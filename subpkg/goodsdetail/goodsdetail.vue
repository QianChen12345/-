<template>
	<view class="detail" v-if="status">
		<!-- 商品详情 -->
		<view v-for="(item,i) in itemlist" :key="i">
			<view class="detailbox">
				<view class="goodsid">
					<text>商品号：{{item.itemid}}</text>
				</view>
				<view class="goodsname">
					<text>商品名：{{item.itemname}}</text>
				</view>
				<view class="goodstype">
					<text>商品类别：{{item.itemtype}}</text>
				</view>
				<view class="goodsprice">
					<text class="price">售价：￥{{item.itemprice}}</text>
				</view>
				<view class="goodsimage">
					<view class="imagetext">
						<text>图片详情</text>
					</view>
					<view class="imagebox">
						<view class="" v-for="(item1,i) in imagelist" :key="i" @tap="previewFile(i)">
							<image :src='item1' class="image"></image>
						</view>
					</view>
				</view>
				<view class="goodsdescrible">
					<text class="describle">商品描述：{{item.itemdesc}}</text>
				</view>
				<view class="itemaddress">
					<text>交易地址：{{item.itemaddress}}</text>
				</view>
			</view>
		</view>
		<!-- 联系商家和添加至购物车 -->
		<view class="shop-pay">
			<view class="left">
				<image class="contact" src="../../static/images/goodsdetail/联系.png" @click="contactclick"></image>
				<image class="shopping" src="../../static/images/goodsdetail/购物车.png" @click="setitem(this.goodsid)">
				</image>
			</view>
			<button class="pay" @click="paymoney"> 立即下单</button>
		</view>
	</view>



	<!-- 通讯窗口 -->
	<view v-else>
		<view class="wrap">
			<view class="title">
				<view>{{ userName }}</view>
			</view>
			<view class="content_box" id="box" ref="scrollBox">
				<view class="timer">2022-08-02 11:08:07</view>
				<view :class="item.position == 'left' ? 'userbox2' : 'userbox'" v-for="(item, index) in chatList"
					:key="index" :id='"item"+index'>
					<view :class="item.position == 'left' ? 'nameInfo2' : 'nameInfo'">
						<view style="font-size: 14px">{{ item.position == 'left' ?item.uname:item.to_name  }}</view>
						<view :class="item.position == 'left' ? 'contentText2' : 'contentText'">
							{{ item.msn }}
						</view>
					</view>
					<view>
						<image class="touxiang" :src="item.position == 'left' ?item.uavatar:item.to_avatar" />
					</view>
				</view>
			</view>
			<view class="bottom">
				<textarea name="输入框" id="1" cols="20" rows="5" class="areaBox" v-model="inputValue"></textarea>
				<button style="height: 30px;color:#58df4d;font-size: 14px;line-height: 30px;"
					@click="sendOut">发送</button>
			</view>
		</view>
	</view>
	

</template>

<script>
	export default {
		data() {
			return {
				itemlist: [],
				imagelist: [],
				arr: [],
				goodsid: "",
				account: '',
				price: '',
				status: true,
				useraccount: '',

			};
		},
		onLoad(option) {



			console.log(option)
			this.getitem(option.goodsid)
			this.goodsid = option.goodsid
			var that = this
			uni.getStorage({
				key: 'logininfo',
				success(res) {
					console.log(res)
					that.account = res.data[0]

				},

			})
			

			this.socket()

		},
		onShow() {
			var that = this
			uni.getStorage({
				key: 'logininfo',
				success(res) {
					that.useraccount = res.data[0]
					console.log(res.data[0])
					
					

				},


			})
		},

		methods: {
			socket() {
				uni.connectSocket({
					url: 'ws://localhost:8080/',
					success(res) {
						console.log(res)
					}
				})
			},

			getitem: function(i) {
				var that = this
				uni.request({
					url: 'http://localhost:8080/tomcatexample/searchgoodid',
					data: {
						goodsid: i
					},
					success(res) {
						that.price = res.data[0].itemprice
						//console.log("价格是："+that.price)
						that.itemlist.push(res.data[0])
						// console.log(res.data[0].itemimage)
						// console.log(res.data[0].itemimage.split("-"))
						if (res.data[0].itemimage.split("-").length == 1) {
							that.imagelist = res.data[0].itemimage.split("-")
							console.log(that.imagelist)
						} else if (res.data[0].itemimage.split("-").length == 2) {
							that.imagelist.push(res.data[0].itemimage.split("-")[0])
							that.imagelist.push(res.data[0].itemimage.split("-")[1])
						} else {
							that.imagelist.push(res.data[0].itemimage.split("-")[0])
							that.imagelist.push(res.data[0].itemimage.split("-")[1])
							that.imagelist.push(res.data[0].itemimage.split("-")[2])
						}
					}
				})
			},
			previewFile: function(i) {
				uni.previewImage({
					urls: this.imagelist,
					current: i,
					loop: true
				});

			},
			setitem: function(goodsid) {
				var that = this
				if (that.useraccount == '') {
					uni.showToast({
						title: '请先登录',
						duration: 2000,
						icon: 'none'
					})
				} else {
					uni.request({
						url: 'http://localhost:8080/tomcatexample/searchgoodid',
						data: {
							goodsid: goodsid,

						},
						success(res) {
							console.log(res.data[0])
							//uni.setStorageSync('cart',JSON.stringify(res.data[0]))
							
							if (uni.getStorageSync(that.useraccount) == []) {
								that.arr.push(res.data[0])
								uni.setStorageSync(that.useraccount, that.arr)
							} else {
								that.arr = uni.getStorageSync(that.useraccount)
								that.arr.push(res.data[0])
								uni.setStorageSync(that.useraccount, that.arr)
							}
							console.log("成功本地存储")
							uni.showToast({
								duration: 2000,
								title: "成功加入购物车",
								
							})
							console.log(uni.getStorageSync(that.useraccount))
							
						}
					})
				}

			},
			paymoney: function() {
				var that = this
				uni.request({
					url: 'http://localhost:8080/tomcatexample/pay',
					data: {
						appid: "wxbda069ef00b15893",
						goodsid: that.goodsid,
						account: that.account,
						price: that.price,

					},
					success(res) {
						console.log(res)
						console.log(res.data[0].nowtime)
						console.log(res.data[0].secret)
						uni.requestPayment({
							provider: 'wxpay',
							timeStamp: res.data[0].nowtime, // 时间戳
							nonceStr: res.data[0].randomnum, // 随机字符串
							package: res.data[0].prepay_id,
							signType: "MD5", // 签名算法
							paySign: res.data[0].secret, // 签名


							orderInfo: res.data[0].ordernum,
							success: res => {
								uni.hideLoading();
								uni.showToast({
									title: '支付成功',
									icon: 'none'
								})
							},
							fail: err => {
								console.log(err)
								uni.hideLoading();
								uni.showToast({
									title: '支付失败，请稍后再试',
									icon: 'none'
								})
							}
						})
					}

				})

				// uni.getProvider({
				// 	service: 'payment',
				// 	success: (res) => {
				// 		console.log(res)
				// 		}
				// 	}
				// })


			},
			contactclick() {
				this.status = false
			}



		}
	}
</script>

<style lang="scss">
	.detail {
		margin-top: 0px;
	}

	.detailbox {
		display: flex;
		flex-direction: column;
		justify-content: space-between;
	}

	.goodsid,
	.goodsname,
	.goodstype,
	.goodsprice,
	.goodsimage,
	.goodsdescrible {
		margin: 15px 0px;
	}

	.imagetext {
		margin-bottom: 5px;
	}

	.imagebox {
		display: flex;
		justify-content: space-between;

	}

	.image {
		width: 100px;
		height: 100px;
	}

	.shop-pay {
		display: flex;
		justify-content: space-between;




		.pay {
			width: 70%;
			height: 10%;
			margin-right: 0px;
			background-color: #ff0000;
			margin-top: 198rpx;



		}

		.contact,
		.shopping {
			width: 30px;
			height: 30px;
			margin-left: 15px;

		}

		.left {
			position: absolute;
			bottom: 3px;


		}
	}

	.itemaddress {
		margin-top: 25px;
	}


	.wrap {
		height: 100%;
		width: 100%;
		position: relative;
	}

	.touxiang {
		width: 50px;
		height: 50px;
		border-radius: 50%;
	}

	.areaBox {
		height: 40px;
	}

	.title {
		height: 40px;
		width: 100%;
		background-color: #eaeaea;
		display: flex;
		justify-content: center;
		align-items: center;
	}

	.bottom {
		min-height: 50px;
		width: 100%;
		border-top: 1px solid #eaeaea;
		background-color: #F1F1F1;
		position: fixed;
		bottom: 0;
		display: flex;
		justify-content: space-between;
		align-items: center;
		padding: 0 5px;
		border-radius: 10px;
	}

	.content_box {
		/* 
	  中间栏计算高度，110是包含了上下固定的两个元素高度90
	  这里padding：10px造成的上下够加了10，把盒子撑大了，所以一共是20要减掉
	  然后不知道是边框还是组件的原因，导致多出了一些，这里再减去5px刚好。不然会出现滚动条到顶或者底部的时候再滚动的话就会报一个错，或者出现滚动条变长一下的bug
	  */
		height: calc(100% - 115px);
		overflow: auto;
		padding: 10px 10px 50px 10px;
	}

	.timer {
		text-align: center;
		color: #c2c2c2;
	}

	/* 发送的信息样式 */
	/* 
	右边消息思路解释：首先大盒子userbox内放两个盒子，一个放头像，一个放用户名和发送的内容，我们先用flex让他横向排列。
	然后把写文字的大盒子设置flex：1。这个属性的意思就是让这个元素撑满父盒子剩余位置。然后我们再把文字盒子设置flex，并把他对齐方式设置为尾部对齐就完成了基本的结构，然后微调一下就可以了
	*/
	.userbox {
		width: 100%;
		display: flex;
		margin-bottom: 10px;
	}

	.nameInfo {
		/* 用flex：1把盒子撑开 */
		flex: 1;
		margin-right: 10px;
		/* 用align-items把元素靠右对齐 */
		display: flex;
		flex-direction: column;
		align-items: flex-end;
	}

	.contentText {
		background-color: #9eea6a;
		/* 把内容部分改为行内块元素，因为盒子flex：1把盒子撑大了，所以用行内块元素让内容宽度不根据父盒子来 */
		display: inline-block;
		/* 这四句是圆角 */
		border-top-left-radius: 10px;
		border-top-right-radius: 0px;
		border-bottom-right-radius: 10px;
		border-bottom-left-radius: 10px;
		/* 最大宽度限定内容输入到百分61换行 */
		max-width: 61%;
		padding: 5px 10px;
		/* 忽略多余的空白，只保留一个空白 */
		white-space: normal;
		/* 换行显示全部字符 */
		word-break: break-all;
		margin-top: 3px;
		font-size: 14px;
	}

	/* 接收的信息样式 */
	/* 
	左边消息思路解释：跟上面一样，就是换一下位置，首先通过把最外层大盒子的排列方式通过flex-direction: row-reverse;属性翻转，也就是头像和文字盒子换位置
	然后删除掉尾部对齐方式，因为不写这个默认是左对齐的。我们写的左边就没必要再写了。
	*/
	.userbox2 {
		width: 100%;
		display: flex;
		flex-direction: row-reverse;
		margin-bottom: 10px;
	}

	.nameInfo2 {
		/* 用flex：1把盒子撑开 */
		flex: 1;
		margin-left: 10px;
	}

	.contentText2 {
		background-color: #9eea6a;
		/* 把内容部分改为行内块元素，因为盒子flex：1把盒子撑大了，所以用行内块元素让内容宽度不根据父盒子来 */
		display: inline-block;
		/* 这四句是圆角 */
		border-top-left-radius: 0px;
		border-top-right-radius: 10px;
		border-bottom-right-radius: 10px;
		border-bottom-left-radius: 10px;
		/* 最大宽度限定内容输入到百分61换行 */
		max-width: 61%;
		padding: 5px 10px;
		/* 忽略多余的空白，只保留一个空白 */
		white-space: normal;
		/* 换行显示全部字符 */
		word-break: break-all;
		margin-top: 3px;
		font-size: 14px;
	}
</style>
