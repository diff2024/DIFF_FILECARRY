<style>
@keyframes app_bell-effect {
  50% {
    opacity: 0;
  }
}
.app_bell {
  animation: app_bell-effect 1s step-end infinite;
}
.modal_message_close{
	position: relative;
	left:82%;
	font-size:180%;
}
</style>
<style scoped>
	.messagetable >>> .v-data-table-header th{
		text-align: center !important;
	}
	
	.messagetable >>> tbody tr :nth-child(3) {
		padding-left: 50px;
	}
	.messagetable >>> tbody tr :nth-child(4) {
		padding-left: 40px;
	}
</style>
<template>
	<div id="app">
		<router-view></router-view>
	</div>
</template>
<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script>
import AppHeader from './components/common/AppHeader';
import HelloWorld from './components/HelloWorld';
import axios from 'axios';
import Swal from 'sweetalert2'
var menu_json = [];
export default {
	name: 'App',
	components: {
		AppHeader,
		HelloWorld
	},
	data() {
		return {
			logoutModal:false,
			drawer: true,
			items: menu_json,
			app_bell_gubun: '',
			app_bell_items: [],
			user_id: this.$session.get('user_id'),
			MessageModal: false,
			app_bell_index: '0',
			app_msg_id: '',
			app_msg_sender_name: '',
			app_msg_sender_datetime: '',
			app_msg_title: '',
			app_msg_text: '',
			mob_yn: 'N',
			/* 메시지목록 모달 */
			schVal:'',
			m_document: [],
			m_headers: [
				{ text: '순번', align: ' d-none', value: 'num', width: 85 },
				{ text: 'ID', align: ' d-none', value: 'clm_message_id' },
				{ text: '제목', align: 'start', value: 'clm_message_title', width: 180 },
				{ text: '내용', align: 'start', value: 'clm_message_text', width: 350 },
				{ text: '발신자', align: 'center', value: 'clm_sender_user_name', width: 150 },
				{ text: '보낸 일시', align: 'center', value: 'clm_sender_datetime', width: 150 },
				{ text: '보내는사람 ID', align: ' d-none', value: 'clm_sender_user_id' },
				{ text: '받는사람 ID', align: ' d-none', value: 'clm_receive_user_id' },
				{ text: '받는사람', align: ' d-none', value: 'clm_receive_user_name', width: 150 },
				{ text: '읽은일시', align: ' d-none', value: 'clm_receive_confirm_datetime', width: 150 },
				{ text: '읽음여부', align: ' d-none', value: 'clm_receive_confirm_yn'}
			],
			MessageLogModal:false,
		}
	},
	beforeCreate: function () {
		
	},
	created() {
		if(this.$isMobile()){
			this.drawer = false;
			if(this.$route.path === '/'){
				
				this.$router.push('scr001001List');
			}
		}
		if (this.$session.exists()) {
			axios.get('/back/Common/MenuList',{
			params: {
				authority_id: this.$session.get('user_authority')
				}
			})
			.then(response => {
				var lead_end_menu = '';
				var total_menu_screen_cnt = '';
				var menu_sub_array = [];
				for(var row=0; row<response.data.length; row++){
					total_menu_screen_cnt = response.data[row].total_menu_screen_cnt;
					//lead_end_menu = response.data[row].lead_end_menu_script;
					lead_end_menu = response.data[row].end_menu_script;
					if(total_menu_screen_cnt == '1'){
						// console.log('response.data[row].clm_screen_name : ' + response.data[row].clm_screen_name)
						// console.log('response.data[row].clm_screen_id : ' + response.data[row].clm_screen_id)
						menu_json.push({ icon: '', title: response.data[row].clm_screen_name, route: response.data[row].clm_screen_id });
					}else{
						menu_sub_array.push(
						{ icon:'', title: response.data[row].clm_screen_name, route: response.data[row].title }
						);

						if(lead_end_menu != ''){
						menu_json.push({ icon: 'mdi-home', title: response.data[row].clm_menu_name, items: menu_sub_array });
						menu_sub_array = [];
						}
					}
				}
				console.log(menu_json);
			});
		}
	},
	mounted() {	
		if (!this.$session.exists()) {
			this.$router.push('/')
		}
	},
	methods: {
		fnc_Message_Confirm(){
			if(typeof this.app_bell_items[Number(this.app_bell_index)] == 'undefined'){
				if(this.MessageModal == true){
					this.MessageModal = false
				}
				this.app_bell_index = '0'
				this.app_bell_gubun = 'mdi-bell'
			}else{
				if(this.MessageModal == false){
					this.MessageModal = true
				}
				this.app_msg_id = this.app_bell_items[Number(this.app_bell_index)].clm_message_id
				this.app_msg_sender_name = this.app_bell_items[Number(this.app_bell_index)].clm_sender_user_name
				this.app_msg_sender_datetime = this.app_bell_items[Number(this.app_bell_index)].clm_sender_datetime
				this.app_msg_title = this.app_bell_items[Number(this.app_bell_index)].clm_message_title
				this.app_msg_text = this.app_bell_items[Number(this.app_bell_index)].clm_message_text
				axios.post('/back/Common/MSGConfirm', null, { params: {
					msg_id:this.app_msg_id
				}})
				.then(response => {
					console.log(response.data)
				});
				this.app_bell_index = String(Number(this.app_bell_index)+1)
			}
		},
		fnc_bell_click(){
			if(this.app_bell_items.length > 0){
				this.fnc_Message_Confirm();
			}else{
				let items = axios.get('/back/SCR003011/List',{
					params: {
						search: encodeURI(this.schVal),
						search1:'',
						search2:'',
						search3:'',
						search4:this.$session.get('user_id'),
					}
					}).then(
					response => {
						items = response.data			
						console.log(items);		
						setTimeout(() => {
							this.m_document = items
						}, 500)
					},
				)
				this.MessageLogModal=true;
				//alert('메시지가 없습니다.');
			}
		},
		getMesaageList(){
			let items = axios.get('/back/SCR003011/List',{
				params: {
					search: encodeURI(this.schVal),
					search1:'',
					search2:'',
					search3:'',
					search4:this.$session.get('user_id'),
				}
				}).then(
				response => {
					items = response.data
					setTimeout(() => {
						this.m_document = items
					}, 500)
				},
			)
		},
		onClickMessageRow(event, data){
			if(this.MessageModal == false){
				this.MessageModal = true
			}
			this.app_msg_id = data.item.clm_message_id;
			this.app_msg_sender_name = data.item.clm_sender_user_name;
			this.app_msg_sender_datetime = data.item.clm_sender_datetime;
			this.app_msg_title = data.item.clm_original_message_title;
			this.app_msg_text = data.item.clm_original_message_text;
		},
		goMainURL(){
			// this.$router.push('/');
			this.$router.push('scr001001List');
		},
		MainDraw(){
			if(this.$isMobile()){
				this.drawer = !this.drawer;
			}else{
				this.drawer = !this.drawer;
			}
		},
		fncLogin(){
			this.$router.push('/');
		},
		showModal(){
			this.logoutModal = true;
		},
		LogOut(){
			this.logoutModal = false;
			this.$session.destroy();
			if(this.$route.path !== '/'){
				// this.$router.push('/');
				
				this.$router.push('scr001001List');
			}else{
				location.href=location;
			}
		},
		CloseclientModal() {
			this.logoutModal = false;
		},
	}
};
</script>
<style>
	@import './assets/Default.css';
</style>