<template>
  <span style="text-align:center;">
    <v-btn small color="#23A6EE" style="margin-bottom:8px; color:white; font-weight:bold;" @click="downClick($event)">{{ this.text_down }}</v-btn>
    &nbsp;
    <v-btn small color="red" style="margin-bottom:8px; color:white; font-weight:bold;" @click="deleteClick($event)">{{ this.text_delete }}</v-btn>
  </span>
</template>

<script>
  import axios from 'axios';
  import Swal from 'sweetalert2';

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
    name: 'BtnCellRenderer',
    data() {
      return {
        text_down : '',
        text_delete : ''
      }
    },
    mounted() {
    },
    created() {
      this.text_down = this.params.RendererDownBTN();
      this.text_delete = this.params.RendererDelBTN();
    },  
    methods: {
      downClick(){
        var file_id = this.$data.params.data.id;
        var file_name = this.$data.params.data.file_name;
        
        axios({
					url: "/File/FileDownload",
					params: {
						userIP: this.params.userIP,					
						file_id: file_id,
            file_name: file_name
					},
					method: "POST",
					responseType: "blob"
				}).then(response => {
					console.log(response);
					const url = window.URL.createObjectURL(new Blob([response.data]));
					const link = document.createElement("a");
					link.href = url;
					link.setAttribute("download", file_name);
					document.body.appendChild(link);
					link.click();
				});
      },
      deleteClick(){
        var file_id = this.$data.params.data.id;
        var file_name = this.$data.params.data.file_name;
        var TEXT2000MSG = this.params.RendererMSG('2000');
        var TEXT2001MSG = this.params.RendererMSG('2001');
        var TEXT2003MSG = this.params.RendererMSG('2003');
        var TEXT2004MSG = this.params.RendererMSG('2004');
        var TEXT2005MSG = this.params.RendererMSG('2005');
        var TEXT2006MSG = this.params.RendererMSG('2006');

        Swal.fire({
          text: TEXT2003MSG,
          showCancelButton: true,
          allowOutsideClick: false,
          confirmButtonColor: '#3085d6',
          cancelButtonColor: '#d33',
          confirmButtonText: TEXT2000MSG,
          cancelButtonText: TEXT2001MSG
        }).then((result) => {
          if (result.isConfirmed) {
            
            axios.post('/File/FileDelete', null, { params: {
              file_id: file_id,
              userIP: this.params.userIP,
              userLang: this.params.userLang
            }})
            .then(response => {
              if (response.status == '200') {
                if(response.data == 'N'){
                  Toast.fire({
                    icon: 'error',
                    title: TEXT2004MSG
                  })
                  this.params.initGrid();
                }else{
                  Toast.fire({
                    icon: 'success',
                    title: TEXT2006MSG
                  })
                  this.params.initGrid();
                }
              }else{
                Toast.fire({
                  icon: 'error',
                  title: TEXT2005MSG
                })
                this.params.initGrid();
              }
            })
            .catch(error => {
              console.log(error)
            })
            
          }
        })
      }
    },
  }
</script>