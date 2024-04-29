<style scoped>
.parent {
    display: flex;
    width: 100%;
	height: 100vh;
    text-align: center;
}
.child {
	float:left;
}
.input-file-button {
  padding: 9px 38px;
  font-size:20px;
  background-image: linear-gradient(to right, #FFCE4B, #23A6EE);
  border-radius: 4px;
  color: white;
  cursor: pointer;
}

.input-upload-button {
	padding: 9px 38px;
	font-size:32px;
	background-color: #FFCE4B;
	border-radius: 4px;
	color: white;
	cursor: pointer;
}
.input-download-button {
	padding: 9px 38px;
	font-size:32px;
	background-color: #23A6EE;
	border-radius: 4px;
	color: white;
	cursor: pointer;
}
</style>
<template>
	<div>
		<div class="parent">
			<div id="left_ad" class="child" style="width:250px;">
				
			</div>

			<div class="child" style="width: calc(100% - 500px);">
				<img src="../assets/Logo.jpg" style="width: 350px; padding: 10px;" />
				<div style="font-size:32px; font-weight:bold; padding-top:20px; padding-bottom:10px;">{{ userIP }} <img src="../assets/question mark.jpg" style="cursor:pointer;" v-on:click="fnc_alert" /></div>
				<div style="width:60%; display: inline-block; border: 1px solid black; height: 50vh; border-radius: 30px;">
					<div style="position: sticky; top: calc(50% - 25px);">
						<label class="input-file-button" for="input-file">
							업로드
						</label>
						<input type="file" id="input-file" style="display:none"/> 
					</div>
				</div>
				<div>
					<div>
						<label class="input-upload-button" for="input-upload">
							업로드
						</label>
						<input type="file" id="input-upload" style="display:none"/> 
					</div>
					<div>
						<label class="input-download-button" for="input-download">
							다운로드
						</label>
						<input type="file" id="input-download" style="display:none"/> 
					</div>
				</div>
			</div>

			<div id="right_ad" class="child" style="width:250px;">
				
			</div>
		</div>
		<v-dialog v-model="use_dialog" persistent max-width="290">
			<v-card>
				<v-card-title>
					TEST
				</v-card-title>
				<v-card-text style="padding-bottom:0px;">
					
				</v-card-text>
				<v-card-actions style="padding-top:0px;">
					<v-spacer></v-spacer>
				
				</v-card-actions>
			</v-card>
		</v-dialog>
  </div>
</template>
<script src="https://unpkg.com/vue"></script>
<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js"></script>
<script>
import axios from 'axios';
import Swal from 'sweetalert2'

export default {
	data() {
      return {
			userAgent: '',
			userIP: '',
			isMobile: '',
			use_dialog: false
      }
    },
	components: {
	},
	created() {
		this.userAgent = navigator.userAgent.toLowerCase();
		if((navigator.userAgent).match(/Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i)){
			this.isMobile = 'Y';
		}else{
			this.isMobile = 'N';
		}

		axios.get('https://api64.ipify.org?format=json')
		.then(response => {
			this.userIP = (response.data)['ip'];
		})
		.catch(error => {
			console.log(error)
		});
		
	},
    mounted() {
		console.log('> isMobile ['+this.isMobile+']   |   userIP  ['+this.userIP+']   |   userAgent ['+this.userAgent+']  ')
	},
	methods: {
		fnc_alert() {
			Swal.fire({
				title:this.userIP+'\nMoblie ['+this.isMobile+']\n'+this.userAgent
			});
		}
	}
}
</script>