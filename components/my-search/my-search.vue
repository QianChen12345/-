<template>
	<view class="searchcontainer" @click="gotosearch">
		<view class="searchbox">
			<uni-icons type="search" size="17"></uni-icons>
			<text class="placeholder">搜索</text>
		</view>
	</view>
</template>

<script>
	export default {
		name: "my-search",
		data() {
			return {

			};
		},
		onLoad() {
		
		},
		methods: {
			gotosearch() {
				this.$emit('click')
				console.log("1111")
			}
		}
	}
</script>

<style lang="scss">

</style>
