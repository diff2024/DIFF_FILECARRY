<template>
	<div id="app">
		<router-view></router-view>
	</div>
</template>
<style scss>
@import '~ag-grid-community/styles/ag-grid.css';
@import '~ag-grid-community/styles/ag-theme-alpine.css';
</style>
<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script>
import axios from 'axios';
import Swal from 'sweetalert2'
var menu_json = [];
export default {
	name: 'App',
	components: {
		
	},
	data() {
		return {
			
		}
	},
	beforeCreate: function () {
		
	},
	created() {

	},
	mounted() {	

	},
	methods: {
		
	}
};
</script>
<style>
	@import './assets/Default.css';
</style>