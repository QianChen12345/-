<template>
	<view>
		<view class="mygoods">创建自己的商品</view>
		<form action="" class="formcontrol" enctype="multipart/form-data">
			<view class="itemId">
				<text>商品号：{{this.randomnum}}</text>
			</view>
			<view class="one">
				<text>商品名:</text><input id="test" type="text" v-model="submit.goodsname" focus="true">
			</view>
			<view class="one">
				<text>商品类别:</text>
				<!-- picker是底部滚动器 -->
				<picker @change="changetype" :range="goodstype">
					<label class="">{{ defaulttype }}</label>
				</picker>

			</view>
			<view class="one">
				<text>商品售价￥:价格不能小于等于0</text><input @click="emptyprice" type="number" v-model="submit.goodsprice"
					@input="typeinput($event)">
			</view>
			<view class="one">
				<text>商品描述:请用几句话描述你的宝贝，不要超过100字</text><textarea @click="emptydesc" v-model="submit.goodsdesc" name=""
					id="" maxlength="100"></textarea>


			</view>

			<view class="add-address">
				<text>交易地点:</text>
				<picker @change="changeadress" :range="addresstype">
					<label class="chooseaddress">{{ defaultadress }}</label>
				</picker>
			</view>

			<view class="">
				<button class="addimage" @click="upload">添加图片</button>
				<view class="imagebox">

					<image class="addimagestyle" v-for="(item,i) in imagearray " :key="i" @tap="previewFile(i)"
						:src="item"></image>


				</view>

			</view>



			<button form-type="submit" @click="submitclick">提交商品</button>
			<button form-type="reset" @click="resume">重置商品</button>


		</form>

	</view>
</template>

<script>
	var app = getApp()
	export default {
		data() {
			return {
				randomnum: "",
				goodstype: ["书籍", "手机", "化妆品", "鞋子", "文具", "生活用品", "首饰", "电子产品", "其他", "衣物"],
				goodsindex: 0,
				defaulttype: "点此选择",
				submit: {
					goodsname: "",
					goodsprice: "",
					goodsdesc: "",


				},
				imagearray: [],
				goodsidlist: [],
				arr: [],
				//查看拼接路径
				one: "",
				defaultadress: '点此选择',
				addresstype: ['学校北门', '学校南门', '学校西门', '学校东门', '学校一号教学楼下', '学校一号食堂门口'],
				useraccount: '',
			}
		},


		onLoad() {

			this.randomnum = app.randomId();
			var that = this
			uni.getStorage({
				key: 'newaddress',
				success(res) {
					console.log(res)
					that.addresstype = res.data
					console.log(that.addresstype)
				},
			})


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


			submitclick: function() {
				var that = this;
				if (that.submit.goodsname == null || that.submit.goodsname.length == 0 ||
					that.submit.goodsdesc == null || that.submit.goodsdesc.length > 100 ||
					that.submit.goodsprice == null || that.submit.goodsprice <= 0 ||
					that.defaulttype == "点此选择" || that.imagearray == [] || that.defaultadress == '点此选择') {
					console.log("发布失败")


				} else if (that.useraccount == '') {
					uni.showToast({
						title: '请先登录',
						duration: 2000,
						icon: 'none'
					})
				} else {
					uni.request({
						url: "http://localhost:8080/tomcatexample/Wxappconn",
						methods: "post",
						header: {
							"Content-Type": "application/text",
						},

						data: {
							goodsId: that.randomnum,
							defaulttype: encodeURI(that.defaulttype),
							//goodsname: that.submit.goodsname,
							goodsname: encodeURI(that.submit.goodsname),
							goodsprice: that.submit.goodsprice,
							goodsdesc: encodeURI(that.submit.goodsdesc),
							goodsimage: that.one,
							address: encodeURI(that.defaultadress),

						},

						success() {
							console.log("创建商品成功");
							console.log(that.submit.goodsname)
							uni.$emit('aaa', that.randomnum)
							that.resume();
							that.defaulttype = "点此选择"
							//将自己创建的商品id发给登录后的界面，可以查看自己的商品
							// uni.navigateTo({
							// 	url: '/subpkg/login/sendgoods/sendgoods?data='+that.randomnum
							// })
							that.getitem()

						}
					})
				}
			},



			resume: function() {
				this.submit.goodsname = null
				this.submit.goodsprice = null
				this.submit.goodsdesc = null
				this.imagearray = []
				this.randomnum = app.randomId();
				this.defaulttype = "点此选择"


			},

			changetype: function(e) {
				this.goodsindex = e.target.value
				console.log(this.goodsindex)
				this.defaulttype = this.goodstype[this.goodsindex]
			},

			upload: function() {
				var that = this;
				uni.chooseImage({

					count: 3, //可上传的图片数量
					sizeType: ['original', 'compressed'], //上传图片类型：原图、压缩图
					sourceType: ['album', 'camera'], //图片来源：相册、照相机
					success(res) {
						// 成功，将图片上传到服务器
						console.log(res);
						if (that.imagearray.length != 0) {
							that.imagearray = that.imagearray.concat(res.tempFilePaths)
						} else {
							that.imagearray = res.tempFilePaths
						}

						//that.imagearray=res.tempFiles
						//	console.log(that.imagearray);


						// 拿取临时路径文件
						let filePath = res.tempFilePaths[0]
						//console.log(filePath);
						that.imagearray.forEach(item => {
							uni.uploadFile({
								/* // 拿到临时图片路径后上传到服务器，服务器将返回一个公网地址，
								届时在任意角落都将能访问到这张图片 */
								filePath: item, //临时文件路径
								url: "http://localhost:8080/tomcatexample/Imagestorage", //填写服务器地址
								name: 'image', //非常重要！！！！是后台访问二进制数据的关键 
								timeout: 5000,
								success(res2) {
									//返回服务器接受图片后发挥的参数
									//	console.log(res2);

									//实现将服务器发回的data路径存入数组,并在页面循环输出
									// if (that.one.length != 0) {
									// 	//that.one = that.one.concat('http://tmp/'+res2.data)
									// 	that.one.push('http://tmp/' + res2.data)
									// } else {
									// 	that.one.push('http://tmp/' + res2.data)
									// }
									// console.log(that.one);
									that.one = that.imagearray.join("-")
									//打印拼接后的图片地址
									//console.log(that.one)
								}
							})
						})
					}
				})
			},
			previewFile: function(i) {
				uni.previewImage({
					urls: this.imagearray,
					current: i,
					loop: true
				});

			},
			getitem: function() {
				var that = this
				console.log(app.globalData.value)
				//that.goodsid=app.globalData.value
				uni.request({
					url: 'http://localhost:8080/tomcatexample/searchgoodid',
					data: {
						goodsid: app.globalData.value
					},
					success(res) {
						console.log(res.data[0])
						//uni.setStorageSync('cart',JSON.stringify(res.data[0]))
						console.log("成功本地存储")
						if (uni.getStorageSync('addcart') == []) {
							that.arr.push(res.data[0])
							uni.setStorageSync('addcart', that.arr)
						} else {
							that.arr = uni.getStorageSync('addcart')
							that.arr.push(res.data[0])
							uni.setStorageSync('addcart', that.arr)
						}
					}
				})
			},
			changeadress: function(e) {
				this.adressindex = e.target.value
				console.log(this.adressindex)
				this.defaultadress = this.addresstype[this.adressindex]
			},
			typeinput: function(e, wal) { //输入框只可以输入数字
				const inputtype = /[^\d]/g
			}

		}
	}
</script>

<style>
	.mygoods {
		margin-top: 20px;
		text-align: center;
		font-size: 30px;
		color: aqua;
	}

	.formcontrol {
		display: flex;
		flex-direction: column;
		justify-content: space-between;



	}


	.inputthree {
		height: 200px;
	}

	.one {
		margin-top: 10px;
		margin-bottom: 30px;
	}

	.addimage {
		width: 30%;
		margin-left: 0px;
		margin-bottom: 10px;
	}

	.addimagestyle {
		width: 100px;
		height: 100px;
		margin-left: 5px;
	}

	.changeid {
		width: 120px;
		height: 25px;
		line-height: 20px;
		float: right;
		margin-right: 50px;
	}

	.imagebox {
		display: flex;
	}

	.add-address {

		display: flex;
		margin-bottom: 30px;

	}

	.chooseaddress {
		margin-left: 15px;
	}

	input,
	textarea {
		border: 1px solid;
	}
</style>
