<style scoped>
.parent {
    display: flex;
    width: 100%;
	height: calc(100vh - 100px);
    text-align: center;
}
.child {
	float:left;
}
.input-file-button {
  padding: 9px 38px;
  font-size:20px;
  background-image: linear-gradient(to right, #FFCE4B, #23A6EE);
  border-radius: 5px;
  color: white;
  cursor: pointer;
}

.input-upload-button {
	padding: 9px 38px;
	font-size:32px;
	background-color: #FFCE4B;
	border-radius: 10px;
	color: white;
	cursor: pointer;
}
.input-download-button {
	padding: 9px 38px;
	font-size:32px;
	background-color: #23A6EE;
	border-radius: 10px;
	color: white;
	cursor: pointer;
}
</style>
<template>
	<div>
		<div style="height:100px; text-align:center;">
			<img src="../assets/Logo.jpg" style="width: 350px; padding: 10px;" />
		</div>
		<div style="height:calc(100% - 100px); background-color:#f5f5fa;">
			<div class="parent">
				<div id="left_ad" class="child" style="width:250px;">
					
				</div>

				<div class="child" style="width: calc(100% - 500px);">
					<div style="font-size:32px; font-weight:bold; padding-top:15px; padding-bottom:15px;">{{ userIP }} <img src="../assets/question mark.jpg" style="cursor:pointer;" v-on:click="fnc_alert" /></div>
					<div style="width:60%; display: inline-block; height: 50vh; border-radius: 30px; margin-bottom: 15px; box-shadow: 2px 2px 5px 2px #dadce0; background-color:white;">
						<div v-if="userTab == 'U'" style="position: sticky; top: calc(50vh - 30px);">
							<label class="input-file-button" for="input-file">
								업로드
							</label>
							<input type="file" id="input-file" style="display:none"/> 
							<div style="padding-top:15px; font-size:13px;">이미지를 드래그하여 여기에 놓으세요.</div>
						</div>
						<div v-if="userTab == 'D'">
							<v-col xl="6" md="6" sm="6" cols="6" align-self="center">
								이름
							</v-col>
							<v-col xl="2" md="2" sm="2" cols="2" align-self="center">
								크기
							</v-col>
							<v-col xl="2" md="2" sm="2" cols="2" align-self="center">
								유효기간
							</v-col>
							<v-col xl="2" md="2" sm="2" cols="2" align-self="center">
								등록일시
							</v-col>
						</div>
					</div>
					<div>
						<div style="width:60%; display: inline-block;">
							<div style="float:left;">
								<label class="input-upload-button" for="input-upload" v-on:click="F_tab_update('U')">
									업로드
								</label>
								<input type="button" id="input-upload" style="display:none"/> 
							</div>
							<div style="float:right;">
								<label class="input-download-button" for="input-download" v-on:click="F_tab_update('D')">
									다운로드
								</label>
								<input type="button" id="input-download" style="display:none"/> 
							</div>
						</div>
					</div>
				</div>

				<div id="right_ad" class="child" style="width:250px;">
				
				</div>
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
			userMobileYN: '',
			userTab:'U', // U : Upload / D : Download
			use_dialog: false
      }
    },
	components: {
	},
	created() {
		this.userAgent = navigator.userAgent.toLowerCase();
		if((navigator.userAgent).match(/Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i)){
			this.userMobileYN = 'Y';
		}else{
			this.userMobileYN = 'N';
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
		console.log('> userMobileYN ['+this.userMobileYN+']   |   userIP  ['+this.userIP+']   |   userAgent ['+this.userAgent+']  ')
	},
	methods: {
		F_tab_update(type){
			this.userTab = type;
		},
		fnc_alert() {
			Swal.fire({
				title:this.userIP+'\nMoblie ['+this.userMobileYN+']\n'+this.userAgent
			});
		}
	}
}
</script>