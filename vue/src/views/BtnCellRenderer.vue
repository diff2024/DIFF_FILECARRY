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

  export default {
    name: 'BtnCellRenderer',
    mounted() {
    },
    methods: {
      downClick(){
        console.log(this.$data.params.data);
      },
      deleteClick(){
        alert(this.params.userLang)
        alert(this.params.userIP)
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

            axios.post('/File/DeleteFile', null, { params: {
              file_id: file_id
            }})
            .then(response => {
              if (response.status == '200') {
                /*
                const Toast = Swal.mixin({
                  toast: true,
                  position: 'center-center',
                  showConfirmButton: false,
                  timer: 1200,
                  timerProgressBar: true,
                  didOpen: (toast) => {
                    toast.addEventListener('mouseenter', Swal.stopTimer)
                    toast.addEventListener('mouseleave', Swal.resumeTimer)
                  }
                })
                Toast.fire({
                  icon: 'success',
                  title: '정상적으로 삭제 되었습니다.'
                })
                */
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