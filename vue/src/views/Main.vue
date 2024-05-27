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
.btn_class:hover {
	color:black;
	font-weight: bold;
}
.upload_drap {
	border: 2.5px dashed black
}

</style>
<template>
	<div>
		<v-app>
			<div style="height:100px; text-align:center;">
				<v-row>
				<v-col xl="2" md="2" sm="2" cols="2" align-self="center" style="text-align:center; color:black;">
				</v-col>
				<v-col xl="8" md="8" sm="8" cols="8" align-self="center" style="text-align:center; color:black;">
					<img src="../assets/Logo.jpg" style="width: 350px; padding: 10px;" />
				</v-col>
				<v-col xl="2" md="2" sm="2" cols="2" align-self="center" style="text-align:center; color:black;">
					<v-select :items="Langlist" v-model="userLang" style="width:140px;" >
						<template v-slot:selection="{ item }">
							<img height="24px" width="24px" :src="item.image" :value="item.value">&nbsp;&nbsp;{{ item.name }}
						</template>
						<template v-slot:item="{ item }">
							<img height="24px" width="24px" style="background-color:white !important;" :src="item.image" :value="item.value">&nbsp;&nbsp;{{ item.name }}
						</template>
					</v-select>
				</v-col>
				</v-row>
			</div>
			<div v-if="this.userIP != ''" style="height:calc(100% - 100px); background-color:#f5f5fa;">
				<div class="parent">
					<div id="left_ad" class="child" style="width:calc((100% - 1100px)/2);">
						
					</div>

					<div class="child" style="width: 1100px;">
						<div style="font-size:32px; font-weight:bold; padding-top:15px; padding-bottom:15px;">{{ userIP }} <img src="../assets/question mark.jpg" style="cursor:pointer;" v-on:click="fnc_alert" /></div>
						<div v-if="userTab == 'U'" :class="uploadClass" style="width:60%; display: inline-block; height: 50vh; border-radius: 30px; margin-bottom: 15px; box-shadow: 2px 2px 5px 2px #dadce0; background-color:white;" @dragleave.prevent="dropLeave()" @drop.prevent="dropInputFile($event)" @dragover.prevent="dropOver()">
							<div style="position: sticky; top: calc(50vh - 15px);">
								<div v-if="uploadClass == ''">
									<label class="input-file-button btn_class" for="input_file">
										{{ TEXT0003 }}
									</label>
									<input multiple type="file" @change="onInputFile()" id="input_file" ref="input_file" style="display:none"/> 
									<div style="padding-top:15px; font-size:13px;">{{ TEXT0004 }}</div>
								</div>
								<div v-else>
									<div style="padding-top:15px; font-size:22px; font-weight:bold;">{{ TEXT0005 }}</div>
								</div>
							</div>
						</div>
						<div v-if="userTab == 'D'" style="width:60%; display: inline-block; height: 50vh; border-radius: 30px; margin-bottom: 15px; box-shadow: 2px 2px 5px 2px #dadce0; background-color:white;">
							<div>
								<ag-grid-vue 
									style="width: 100%; height: 50vh;"
									class="ag-theme-alpine"
									:columnDefs="columnDefs"
									:rowData="rowData"
									@grid-ready="onGridReady"
									:overlayNoRowsTemplate=TEXT1000>
								</ag-grid-vue>
							</div>
						</div>
						<div>
							<div style="width:60%; display: inline-block;">
								<div style="float:left;">
									<label class="input-upload-button btn_class" for="input-upload" v-on:click="F_tab_update('U')">
										{{ TEXT0001 }}
									</label>
									<input type="button" id="input-upload" style="display:none"/> 
								</div>
								<div style="float:right;">
									<label class="input-download-button btn_class" for="input-download" v-on:click="F_tab_update('D')">
										{{ TEXT0002 }}
									</label>
									<input type="button" id="input-download" style="display:none"/> 
								</div>
							</div>
						</div>
					</div>

					<div id="right_ad" class="child" style="width:calc((100% - 1100px)/2);">
					
					</div>
				</div>
			</div>
			<v-dialog v-model="uploadModal" max-width="800">
				<v-card>
					<v-toolbar style="background-image: linear-gradient(to right, #FFCE4B, #23A6EE); font-family:'맑은 고딕'; font-size:24px; font-weight:800; color:white;">
						{{ TEXT0003 }}
					</v-toolbar>
					<v-card-text style="padding-top:15px; background-color:rgb(245, 245, 250);">
						<v-row>
							<v-col xl="8" md="8" sm="8" cols="8" align-self="center" style="text-align:center; font-family:'맑은 고딕'; font-size:20px; font-weight:550; color:black;">
								{{ TEXT0006 }}
							</v-col>
							<v-col xl="2" md="2" sm="2" cols="2" align-self="center" style="text-align:center; font-family:'맑은 고딕'; font-size:20px; font-weight:550; color:black;">
								{{ TEXT0007 }}
							</v-col>
							<v-col xl="2" md="2" sm="2" cols="2" align-self="center" style="text-align:center; font-family:'맑은 고딕'; font-size:20px; font-weight:550; color:black;">
								{{ TEXT0008 }}
							</v-col>
						</v-row>
						<v-row v-for="(fileItem) in fileItems" v-bind:key="fileItem.file_name || fileItem.file_size">
							<v-col xl="8" md="8" sm="8" cols="8" align-self="center" style="padding-top:0px; padding-bottom:0px;">
								<span style="font-family:'맑은 고딕'; font-size:18px; color:black;">{{ fileItem.file_name }}</span>
							</v-col>
							<v-col xl="2" md="2" sm="2" cols="2" align-self="center" style="padding-top:0px; padding-bottom:0px; text-align:right;">
								<span style="font-family:'맑은 고딕'; font-size:18px; color:black;">{{ fileItem.file_size }}</span>
							</v-col>
							<v-col xl="2" md="2" sm="2" cols="2" align-self="center" style="padding-top:0px; padding-bottom:0px;">
								<v-select 
									v-model="fileItem.file_period_type" 
									:items="period_type_items" 
									item-text="code_detail_name"
									item-value="code_detail_id"
									open-on-clear 
									persistent-hint>
								</v-select>
							</v-col>
						</v-row>
						<v-row>
							<v-col xl="12" md="12" sm="12" cols="12" align-self="center" style="padding-top:0px; padding-bottom:0px;">
								<v-btn depressed dark small class="btn_class" color="#FFCE4B" style="float:right;" @click="FileUpload">
									<v-icon small>send</v-icon>&nbsp;{{ TEXT0009 }}
								</v-btn>
							</v-col>
						</v-row>
					</v-card-text>
				</v-card>
			</v-dialog>
		</v-app>
  </div>
</template>
<script src="https://unpkg.com/vue"></script>
<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js"></script>
<script>
import axios from 'axios';
import Swal from 'sweetalert2'
import {AgGridVue} from 'ag-grid-vue'
import 'material-design-icons-iconfont/dist/material-design-icons.css'
import BtnCellRenderer from './BtnCellRenderer.vue'

const Toast = Swal.mixin({
	toast: true,
	position: 'left',
	showConfirmButton: false,
	timer: 1000,
	width: "500px",
	timerProgressBar: true,
	didOpen: (toast) => {
		toast.addEventListener('mouseenter', Swal.stopTimer)
		toast.addEventListener('mouseleave', Swal.resumeTimer)
	}
})

export default {
	name: 'Main',
	data() {
      return {
		datetime: '',
		system_datetime: '',
		userLang: '',
		userAgent: '',
		userIP: '',
		userMobileYN: '',
		userTab:'U', // U : Upload / D : Download
		gridApi: null,
		columnApi: null,
		gridOptions: null,
		columnDefs: null,
		rowData: [],
		uploadClass: '',
		uploadModal: false,
		input_file: null,
		files: null,
		fileItems:[],
		period_type_items_origin:[],
		period_type_items:[],
		TextLangList: null,
		Langlist: [
			{ name: '한국어', image: 'https://goodies.icons8.com/web/common/header/flags/ko.svg', value: 'ko' },
			{ name: 'English', image: 'https://goodies.icons8.com/web/common/header/flags/us.svg', value: 'en'},
			{ name: '简体中文', image: 'https://goodies.icons8.com/web/common/header/flags/zh.svg', value: 'ch'},
			{ name: '日本語', image: 'https://goodies.icons8.com/web/common/header/flags/ja.svg', value: 'jp'}
		],

		/* TEXT */
		TEXT0001 : '',
		TEXT0002 : '',
		TEXT0003 : '',
		TEXT0004 : '',
		TEXT0005 : '',
		TEXT0006 : '',
		TEXT0007 : '',
		TEXT0008 : '',
		TEXT0009 : '',
		TEXT1000 : '',
		TEXT1001 : '',
		TEXT1002 : '',
		TEXT1003 : '',
		TEXT1004 : '',
		TEXT1005 : '',
		TEXT1006 : '',
		TEXT1007 : '',
		TEXT2000 : '',
		TEXT2001 : '',
		TEXT2002 : '',
		TEXT2003 : '',
		TEXT2004 : '',
		TEXT2005 : '',
		TEXT2006 : ''
      }
    },
	components: {
		AgGridVue,
		BtnCellRenderer
	},
	beforeMount() {
        
    },
	created() {
		axios.get('https://api.ip.pe.kr/json/')
		.then(response => {
			this.userIP = (response.data)['ip'];
			if((response.data)['country_code'] == 'KR'){
				this.userLang = 'ko'
			}else if((response.data)['country_code'] == 'JP'){
				this.userLang = 'jp'
			}else if((response.data)['country_code'] == 'CN'){
				this.userLang = 'ch'
			}else{
				this.userLang = 'en'
			}

			this.columnDefs = [
				{headerName: '파일ID', field:"id", hide:true, sortable: false, filter: true, resizable:true},
				{headerName: 'IP', field:"ip", hide:true, sortable: false, filter: true, resizable:true},
				{headerName: '파일명', field:"file_name", width:230, cellStyle: {textAlign: "left", "padding-right": "0px"}, sortable: false, filter: true, resizable:true},
				{headerName: '파일크기', field:"file_conversation_size", width:100, cellStyle: {textAlign: "right", padding: "0px"}, sortable: true, filter: false, resizable:true},  
				{headerName: '만료일시', field:"file_expiration_datetime", width:145, cellStyle: {textAlign: "center", padding: "0px"}, sortable: true, filter: false, resizable:true}, 
				{
					field: 'download',
					headerName: '',
					cellStyle: {textAlign: "center", padding: "0px"},
					cellRenderer: "BtnCellRenderer",
					cellRendererParams: {
						initGrid: this.initDataGrid.bind(this),
						RendererDownBTN: this.RendererDownBTN.bind(this),
						RendererDelBTN: this.RendererDelBTN.bind(this),
						RendererMSG: this.RendererMSG.bind(this),
						userLang: this.userLang,
						userIP: this.userIP
					},
					width:180
				}
			];
		})
		.catch(error => {
		 	console.log(error)
		})

		axios.get('/File/TextLangList')
		.then(response => {
			this.TextLangList = response.data

			axios.get('/File/CodeList',{
				params: {
					code_id: '0001',
					code_lang_type: this.userLang
				}
			})
			.then(response => {
				this.period_type_items_origin = response.data;
				this.initDataLang()
			})
			.catch(error => {
				console.log(error)
			})
		})
		.catch(error => {
		 	console.log(error)
		})

		this.SettingDateTime();
		this.userAgent = navigator.userAgent.toLowerCase();
		if((navigator.userAgent).match(/Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i)){
			this.userMobileYN = 'Y';
		}else{
			this.userMobileYN = 'N';
		}

		axios.get('https://api64.ipify.org?format=json')
		.then(response => {
			
		})
		.catch(error => {
			console.log(error)
		});
	},
	watch: {
		userIP(newIP) {
			if(newIP != ''){
				this.initDataGrid()
			}
		},
		userLang(newLang) {
			if(newLang != ''){
				this.initDataLang()
			}
		}
	},
    mounted() {
		console.log('> userMobileYN ['+this.userMobileYN+']   |   userIP  ['+this.userIP+']   |   userAgent ['+this.userAgent+']  ')
		
	},
	methods: {
		onGridReady(params) {
			this.gridApi = params.api;
			this.columnApi = params.columnApi;
			//params.api.sizeColumnsToFit();
		},
		SettingDateTime(){
			// 일시를 초기화 한다.
			var today = new Date();
			var year = today.getFullYear();
			var month = ('0' + (today.getMonth() + 1)).slice(-2);
			var day = ('0' + today.getDate()).slice(-2);
			var hours = ('0' + today.getHours()).slice(-2); 
			var minutes = ('0' + today.getMinutes()).slice(-2);
			var seconds = ('0' + today.getSeconds()).slice(-2);
			this.datetime = year + '-' + month  + '-' + day + ' ' + hours + ':' + minutes  + ':' + seconds;
			
			var date = new Date();
			var utcNow = date.getTime() + (date.getTimezoneOffset() * 60 * 1000);
			var systemNow = new Date(utcNow + (9 * 60 * 60 * 1000));

			year = systemNow.getFullYear();
			month = ('0' + (systemNow.getMonth() + 1)).slice(-2);
			day = ('0' + systemNow.getDate()).slice(-2);
			hours = ('0' + systemNow.getHours()).slice(-2); 
			minutes = ('0' + systemNow.getMinutes()).slice(-2);
			seconds = ('0' + systemNow.getSeconds()).slice(-2);
			this.system_datetime = year + '-' + month  + '-' + day + ' ' + hours + ':' + minutes  + ':' + seconds;
		},
		Size_Conversaion(uploadsize){
			var bytesUnit = ["Byte", "KB", "MB", "GB", "TB"];
			var digit = 0;
			while (uploadsize > 1024) {
				uploadsize /= 1024;
				digit++;
			}
			
			var return_conversation_size = uploadsize.toFixed(2);
			if(return_conversation_size.slice(-1) == '.' || return_conversation_size.slice(-1) == '0'){
				return_conversation_size = return_conversation_size.slice(0, -1);
			}
			if(return_conversation_size.slice(-1) == '.' || return_conversation_size.slice(-1) == '0'){
				return_conversation_size = return_conversation_size.slice(0, -1);
			}
			if(return_conversation_size.slice(-1) == '.' || return_conversation_size.slice(-1) == '0'){
				return_conversation_size = return_conversation_size.slice(0, -1);
			}
			return_conversation_size = return_conversation_size.toLocaleString() + bytesUnit[digit];
			return return_conversation_size;
		},
		F_tab_update(type){
			this.userTab = type;
		},
		RendererDownBTN(){
			return this.TEXT1006;
		},
		RendererDelBTN(){
			return this.TEXT1007;
		},
		RendererMSG(TETX_ID){
			if(TETX_ID == '2000'){
				return this.TEXT2000;
			}else if(TETX_ID == '2001'){
				return this.TEXT2001;
			}else if(TETX_ID == '2002'){
				return this.TEXT2002;
			}else if(TETX_ID == '2003'){
				return this.TEXT2003;
			}else if(TETX_ID == '2004'){
				return this.TEXT2004;
			}else if(TETX_ID == '2005'){
				return this.TEXT2005;
			}else if(TETX_ID == '2006'){
				return this.TEXT2006;
			}
		},
		initDataGrid() {
			this.rowData = [];

			axios.get('/File/FileList',{
				params: {
					IP: this.userIP,
					code_lang_type: this.userLang
				}
			})
			.then(response => {
				for(var i=0; i<response.data.length; i++){
					this.rowData.push({
						id: response.data[i].file_id,
						file_name: response.data[i].file_name,
						file_size: response.data[i].file_size,
						file_conversation_size: response.data[i].file_conversation_size,
						file_expiration_period: response.data[i].file_expiration_period,
						file_expiration_period_hour: response.data[i].file_expiration_period_hour,
						file_create_datetime: response.data[i].file_create_datetime,
						file_expiration_datetime: response.data[i].file_expiration_datetime,
						file_system_create_datetime: response.data[i].file_system_create_datetime,
						file_system_expiration_datetime: response.data[i].file_system_expiration_datetime
					});
				}
			})
			.catch(error => {
				console.log(error)
			})
		},
		initDataLang(){
			this.period_type_items = []
			for(var i=0; i<this.period_type_items_origin.length; i++){
				if(this.period_type_items_origin[i].code_lang_type == this.userLang){
					this.period_type_items.push({
						code_detail_id : this.period_type_items_origin[i].code_detail_id,
						code_detail_name : this.period_type_items_origin[i].code_detail_name
					});
				}
			}

			if(this.TextLangList != null){
				this.TEXT0001 = this.TextLangList[0]['lang_'+this.userLang];
				this.TEXT0002 = this.TextLangList[1]['lang_'+this.userLang];
				this.TEXT0003 = this.TextLangList[2]['lang_'+this.userLang];
				this.TEXT0004 = this.TextLangList[3]['lang_'+this.userLang];
				this.TEXT0005 = this.TextLangList[4]['lang_'+this.userLang];
				this.TEXT0006 = this.TextLangList[5]['lang_'+this.userLang];
				this.TEXT0007 = this.TextLangList[6]['lang_'+this.userLang];
				this.TEXT0008 = this.TextLangList[7]['lang_'+this.userLang];
				this.TEXT0009 = this.TextLangList[8]['lang_'+this.userLang];
				this.TEXT1000 = this.TextLangList[9]['lang_'+this.userLang];
				this.TEXT1001 = this.TextLangList[10]['lang_'+this.userLang];
				this.TEXT1002 = this.TextLangList[11]['lang_'+this.userLang];
				this.TEXT1003 = this.TextLangList[12]['lang_'+this.userLang];
				this.TEXT1004 = this.TextLangList[13]['lang_'+this.userLang];
				this.TEXT1005 = this.TextLangList[14]['lang_'+this.userLang];
				this.TEXT1006 = this.TextLangList[15]['lang_'+this.userLang];
				this.TEXT1007 = this.TextLangList[16]['lang_'+this.userLang];
				this.TEXT2000 = this.TextLangList[17]['lang_'+this.userLang];
				this.TEXT2001 = this.TextLangList[18]['lang_'+this.userLang];
				this.TEXT2002 = this.TextLangList[19]['lang_'+this.userLang];
				this.TEXT2003 = this.TextLangList[20]['lang_'+this.userLang];
				this.TEXT2004 = this.TextLangList[21]['lang_'+this.userLang];
				this.TEXT2005 = this.TextLangList[22]['lang_'+this.userLang];
				this.TEXT2006 = this.TextLangList[23]['lang_'+this.userLang];
				
				this.$forceUpdate()

				this.columnDefs = [
					{headerName: this.TEXT1001, field:"id", hide:true, sortable: false, filter: true, resizable:true},
					{headerName: this.TEXT1002, field:"ip", hide:true, sortable: false, filter: true, resizable:true},
					{headerName: this.TEXT1003, field:"file_name", width:230, cellStyle: {textAlign: "left", "padding-right": "0px"}, sortable: false, filter: true, resizable:true},
					{headerName: this.TEXT1004, field:"file_conversation_size", width:100, cellStyle: {textAlign: "right", padding: "0px"}, sortable: true, filter: false, resizable:true},  
					{headerName: this.TEXT1005, field:"file_expiration_datetime", width:145, cellStyle: {textAlign: "center", padding: "0px"}, sortable: true, filter: false, resizable:true}, 
					{
						field: 'download',
						headerName: '',
						cellStyle: {textAlign: "center", padding: "0px"},
						cellRenderer: "BtnCellRenderer",
						cellRendererParams: {
							initGrid: this.initDataGrid.bind(this),
							RendererDownBTN: this.RendererDownBTN.bind(this),
							RendererDelBTN: this.RendererDelBTN.bind(this),
							RendererMSG: this.RendererMSG.bind(this),
							userLang: this.userLang,
							userIP: this.userIP
						},
						width:180
					}
				];
				if(this.gridApi != null){
					this.gridApi.setColumnDefs(this.columnDefs);
					//this.gridOptions.redrawRows();
					//this.gridApi.sizeColumnsToFit();
				}
			}
		},
		fnc_alert() {
			Swal.fire({
				title:this.userIP+'\nMoblie ['+this.userMobileYN+']\n'+this.userAgent
			});
		},
		onInputFile() {
			this.files = null;
			this.fileItems = [],
			this.files = this.$refs.input_file.files;

			for(var i=0; i<this.files.length; i++){
				var file_size = this.Size_Conversaion(this.files[i].size)
				//파일공유 추가
				this.fileItems.push({
					file_name: this.files[i].name,
					file_size: file_size,
					file_period_type: '0004'
				});
			}
			this.uploadModal = true;
		},
		dropOver(){
			this.uploadClass = 'upload_drap'
		},
		dropLeave(){
			this.uploadClass = ''
		},
		dropInputFile(event) {
			this.uploadClass = ''
			this.files = [];
			let file = Array.from(event.dataTransfer.files, v => v)[0]
     		this.uploadFile(file)
		},
		uploadFile(file) {
			this.fileItems = [];
			var file_size = this.Size_Conversaion(file.size);
			this.files.push(file);
			//파일공유 추가
			this.fileItems.push({
				file_name: file.name,
				file_size: file_size,
				file_period_type: '0004'
			});
			this.uploadModal = true;
		},
		FileUpload(){
			this.SettingDateTime();
			var file_info_list = [];
			const formData = new FormData();
			formData.append('userIP', this.userIP);
			formData.append('userLang', this.userLang)
			formData.append('datetime', this.datetime);
			formData.append('system_datetime', this.system_datetime);

			for(var i=0; i<this.files.length; i++){
				var file_size = this.Size_Conversaion(this.files[i].size);
				var file_name = this.files[i].name;
				
				file_info_list.push({
					name: String(file_name),
					size: String(this.files[i].size),
					conversation_size: String(file_size),
					period_type: String(this.fileItems[i].file_period_type)
				});
				
				formData.append('files', this.files[i]);
			}
			formData.append('file_info_list', JSON.stringify(file_info_list));
			
			axios.post('/File/FileUpload'
					, formData										
					, { headers: { 'Content-Type': 'multipart/form-data' } 
			}).then(response => {
				if (response.status == '200') {
					Toast.fire({
						icon: 'success',
						title: this.TEXT2002
					})
					this.uploadModal = false;
					this.initDataGrid();
					this.userTab = 'D';
				}
			}).catch(error => {

			})	
		}
	}
}
</script>