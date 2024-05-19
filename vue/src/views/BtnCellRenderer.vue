<template>
  <span>
    <v-btn small color="#23A6EE" style="margin-bottom:8px; color:white; font-weight:bold;" @click="downClick($event)">다운로드</v-btn>
    &nbsp;
    <v-btn small color="red" style="margin-bottom:8px; color:white; font-weight:bold;" @click="deleteClick($event)">삭제</v-btn>
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
    timerProgressBar: true,
    didOpen: (toast) => {
      toast.addEventListener('mouseenter', Swal.stopTimer)
      toast.addEventListener('mouseleave', Swal.resumeTimer)
    }
  })
    
  export default {
    name: 'BtnCellRenderer',
    mounted() {
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
        
        Swal.fire({
          text: file_name + "을(를) 삭제 하시겠습니까?",
          showCancelButton: true,
          allowOutsideClick: false,
          confirmButtonColor: '#3085d6',
          cancelButtonColor: '#d33',
          confirmButtonText: '예',
          cancelButtonText: '아니요'
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
                    title: '보안상의 이유로 삭제되지 않았습니다.'
                  })
                  this.params.initGrid();
                }else{
                  Toast.fire({
                    icon: 'success',
                    title: '파일이 삭제 되었습니다.'
                  })
                  this.params.initGrid();
                }
              }else{
                Toast.fire({
                  icon: 'error',
                  title: '알 수 없는 이유로 삭제되지 않았습니다.'
                })
                this.params.initGrid();
              }
            })
            .catch(error => {
              console.log(error)
            })
            
          }
        })

        /*
        axios.post('/File/DeleteFile', null, { params: {
          file_id: file_id
        }})
        .then(response => {
          if (response.status == '200') {
            
          }
        })
        .catch(error => {
          console.log(error)
        })
        */
      }
    },
  }
</script>