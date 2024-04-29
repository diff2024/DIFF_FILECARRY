<template>
	<v-app-bar app color="blue-grey darken-1" dark>
		<v-btn text>
			<router-link to="/" tag="div">Home</router-link>
		</v-btn>
		<v-btn text>
			<router-link to="/boardList" tag="div">게시판</router-link>
		</v-btn>
		<v-btn text>
			<router-link to="/clientList" tag="div">거래처관리</router-link>
		</v-btn>
		<v-btn text>
			<router-link to="/userList" tag="div">사용자관리</router-link>
		</v-btn>
		<v-btn text>
			<router-link to="/machineList" tag="div">설비관리</router-link>
		</v-btn>
		<v-btn text>
			<router-link to="/productList" tag="div">제품관리</router-link>
		</v-btn>
		<v-btn text>
			<router-link to="/materialList" tag="div">자재관리</router-link>
		</v-btn>
		<v-btn text>
			<router-link to="/codeList" tag="div">코드관리</router-link>
		</v-btn>
		<v-btn text>
			<router-link to="/woodList" tag="div">목재관리</router-link>
		</v-btn>
	</v-app-bar>
</template>

<script>
export default {
	name: 'AppHeader'
}
</script>
