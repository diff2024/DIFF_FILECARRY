(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-06cb1c0a"],{"0e8f":function(t,e,a){"use strict";a("20f6");var l=a("e8f2");e["a"]=Object(l["a"])("flex")},"3e05":function(t,e,a){"use strict";a("51ff")},"51ff":function(t,e,a){},"7e1d":function(t,e,a){"use strict";a.r(e);var l=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("v-layout",{attrs:{wrap:"",row:""}},[a("v-flex",{staticStyle:{"margin-bottom":"20px"},attrs:{xs12:"",sm12:"",md12:""}},[a("v-card",{attrs:{xs12:"",sm12:"",md12:""}},[a("v-card-title",{staticClass:"fontDefault",attrs:{xs12:"",sm12:"",md12:""}},[t._v("\n\t\t\t\t자재 입고 관리\n\t\t\t")])],1)],1),a("v-flex",{attrs:{xs12:"",sm12:"",md12:""}},[a("v-card",[a("v-card-text",["N"==this.save_yn?[a("v-row",[a("v-col",{staticStyle:{"padding-bottom":"0px","margin-bottom":"0px"},attrs:{xl:"12",md:"12",sm:"12",cols:"12","align-self":"center"}},[a("v-text-field",{staticClass:"fontDefault",attrs:{label:"검색","single-line":""},on:{keypress:function(e){return!e.type.indexOf("key")&&t._k(e.keyCode,"enter",13,e.key,"Enter")?null:(e.preventDefault(),t.getList.apply(null,arguments))}},model:{value:t.schVal,callback:function(e){t.schVal=e},expression:"schVal"}})],1)],1),a("v-row",{staticClass:"justify-end"},["Y"==this.$router.app.$route.query.update_yn?[a("v-col",{staticStyle:{"padding-top":"0px","margin-top":"0px"},attrs:{xl:"1",md:"1",sm:"2",cols:"6","align-self":"center"}},[a("v-btn",{staticStyle:{color:"white"},attrs:{width:"100%",color:"green"},on:{click:t.Regopen}},[t._v("등록")])],1)]:t._e(),a("v-col",{staticStyle:{"padding-top":"0px","margin-top":"0px"},attrs:{xl:"1",md:"1",sm:"2",cols:"6","align-self":"center"}},[a("v-btn",{attrs:{width:"100%",color:"white"},on:{click:t.getList}},[t._v("검색")])],1)],2)]:[a("v-row",[a("v-col",{staticStyle:{"padding-bottom":"0px","margin-bottom":"0px"},attrs:{xl:"12",md:"12",sm:"12",cols:"12","align-self":"center"}},[a("v-text-field",{staticClass:"fontDefault",attrs:{label:"검색","single-line":"",disabled:""},model:{value:t.schVal,callback:function(e){t.schVal=e},expression:"schVal"}})],1)],1),a("v-row",{staticClass:"justify-end"},["Y"==this.$router.app.$route.query.update_yn?[a("v-col",{staticStyle:{"padding-top":"0px","margin-top":"0px"},attrs:{xl:"1",md:"1",sm:"2",cols:"6","align-self":"center"}},[a("v-btn",{staticStyle:{color:"white"},attrs:{width:"100%",color:"orange",disabled:""}},[t._v("등록")])],1)]:t._e(),a("v-col",{staticStyle:{"padding-top":"0px","margin-top":"0px"},attrs:{xl:"1",md:"1",sm:"2",cols:"6","align-self":"center"}},[a("v-btn",{attrs:{width:"100%",color:"primary",disabled:""}},[t._v("검색")])],1)],2)],a("v-row",["N"==this.save_yn?[a("v-col",[a("v-data-table",{staticClass:"elevation-1",attrs:{"mobile-breakpoint":"","footer-props":{"items-per-page-text":"페이지 당 행",pageText:"총 {2} 항목 중 {0}-{1}"},headers:t.headers,items:t.document,itemsPerPage:5,loading:t.loading},on:{"click:row":t.onClickRow,pagination:t.updatePage},scopedSlots:t._u([{key:"items",fn:function(e){return[a("td",[t._v(t._s(e.item.num))]),a("td",[t._v(t._s(e.item.clm_employee_number))]),a("td",[t._v(t._s(e.item.clm_user_id))]),a("td",[t._v(t._s(e.item.clm_user_name))]),a("td",[t._v(t._s(e.item.clm_user_department_id))]),a("td",[t._v(t._s(e.item.clm_user_authority_id))]),a("td",[t._v(t._s(e.item.clm_comment))])]}}],null,!1,439244108)})],1)]:[a("img",{staticStyle:{"padding-top":"100px","padding-bottom":"100px"},attrs:{id:"loading_img",src:"product/loading.gif"}})]],2),a("v-row",{attrs:{justify:"center"}},[a("v-dialog",{attrs:{persistent:"","max-width":"950"},on:{keydown:function(e){if(!e.type.indexOf("key")&&t._k(e.keyCode,"esc",27,e.key,["Esc","Escape"]))return null;t.regmodal=!1}},model:{value:t.regmodal,callback:function(e){t.regmodal=e},expression:"regmodal"}},[a("v-card",[a("v-toolbar",{staticClass:"fontDefault",attrs:{color:"light-blue",dark:""}},[t._v("자재 입고 등록")]),a("v-card-text",{staticStyle:{"padding-top":"15px"}},[""==this.clm_material_income_id?[a("v-row",[a("v-col",{staticStyle:{"padding-top":"20px","padding-bottom":"0px"},attrs:{xl:"12",md:"12",sm:"12",cols:"12","align-self":"center"}},[a("span",{staticStyle:{color:"red",float:"left","padding-top":"20px"}},[t._v("*")]),a("v-text-field",{staticClass:"cursortext",attrs:{label:"거래처",readonly:""},on:{click:t.getClientModal},model:{value:t.clm_material_income_client_name,callback:function(e){t.clm_material_income_client_name=e},expression:"clm_material_income_client_name"}})],1)],1),a("v-row",[a("v-col",{staticStyle:{"padding-top":"0px","padding-bottom":"0px"},attrs:{xl:"6",md:"6",sm:"6",cols:"6","align-self":"center"}},[a("span",{staticStyle:{color:"red",float:"left","padding-top":"20px"}},[t._v("*")]),a("v-text-field",{staticClass:"income_date",attrs:{type:"date",label:"입고일자"},model:{value:t.clm_material_income_date,callback:function(e){t.clm_material_income_date=e},expression:"clm_material_income_date"}})],1),a("v-col",{staticStyle:{"padding-top":"0px","padding-bottom":"0px"},attrs:{xl:"6",md:"6",sm:"6",cols:"6","align-self":"center"}},[a("v-text-field",{attrs:{label:"입고수량",type:"number"},model:{value:t.clm_material_income_count,callback:function(e){t.clm_material_income_count=e},expression:"clm_material_income_count"}})],1)],1),a("v-row",[a("v-col",{staticStyle:{"padding-top":"0px","padding-bottom":"0px"},attrs:{xl:"12",md:"12",sm:"12",cols:"12","align-self":"center"}},[a("v-select",{attrs:{items:t.clm_material_type_items,"item-text":"clm_code_sub_name","item-value":"clm_code_sub_id","open-on-clear":"",label:"자재분류"},on:{change:t.ChangeType},model:{value:t.clm_material_type,callback:function(e){t.clm_material_type=e},expression:"clm_material_type"}})],1)],1),a("v-row",[a("v-col",{staticStyle:{"padding-top":"0px","padding-bottom":"0px"},attrs:{xl:"12",md:"12",sm:"12",cols:"12","align-self":"center"}},[a("v-select",{attrs:{items:t.clm_material_name_items,"item-text":"clm_code_sub_name","item-value":"clm_code_sub_id","open-on-clear":"",label:"자재명"},on:{change:t.ChangeName},model:{value:t.clm_material_name,callback:function(e){t.clm_material_name=e},expression:"clm_material_name"}})],1)],1),a("v-row",[a("v-col",{staticStyle:{"padding-top":"0px","padding-bottom":"0px"},attrs:{xl:"12",md:"12",sm:"12",cols:"12","align-self":"center"}},[a("v-select",{attrs:{items:t.clm_material_spec_items,"item-text":"clm_code_sub_name","item-value":"clm_code_sub_id","open-on-clear":"",label:"자재규격"},model:{value:t.clm_material_spec,callback:function(e){t.clm_material_spec=e},expression:"clm_material_spec"}})],1)],1)]:[a("v-row",[a("v-col",{staticStyle:{"padding-top":"20px","padding-bottom":"0px"},attrs:{xl:"12",md:"12",sm:"12",cols:"12","align-self":"center"}},[a("v-text-field",{attrs:{label:"자재입고번호",disabled:""},model:{value:t.clm_material_income_id,callback:function(e){t.clm_material_income_id=e},expression:"clm_material_income_id"}})],1)],1),a("v-row",[a("v-col",{staticStyle:{"padding-top":"0px","padding-bottom":"0px"},attrs:{xl:"12",md:"12",sm:"12",cols:"12","align-self":"center"}},[a("v-text-field",{attrs:{label:"거래처",disabled:""},on:{click:t.getClientModal},model:{value:t.clm_material_income_client_name,callback:function(e){t.clm_material_income_client_name=e},expression:"clm_material_income_client_name"}})],1)],1),a("v-row",[a("v-col",{staticStyle:{"padding-top":"0px","padding-bottom":"0px"},attrs:{xl:"6",md:"6",sm:"6",cols:"6","align-self":"center"}},[a("v-text-field",{attrs:{label:"입고일자",disabled:""},model:{value:t.clm_material_income_date,callback:function(e){t.clm_material_income_date=e},expression:"clm_material_income_date"}})],1),a("v-col",{staticStyle:{"padding-top":"0px","padding-bottom":"0px"},attrs:{xl:"6",md:"6",sm:"6",cols:"6","align-self":"center"}},[a("v-text-field",{attrs:{label:"입고수량",type:"number",disabled:""},model:{value:t.clm_material_income_count,callback:function(e){t.clm_material_income_count=e},expression:"clm_material_income_count"}})],1)],1),a("v-row",[a("v-col",{staticStyle:{"padding-top":"0px","padding-bottom":"0px"},attrs:{xl:"12",md:"12",sm:"12",cols:"12","align-self":"center"}},[a("v-select",{attrs:{items:t.clm_material_type_items,"item-text":"clm_code_sub_name","item-value":"clm_code_sub_id",disabled:"","open-on-clear":"",label:"자재분류"},model:{value:t.clm_material_type,callback:function(e){t.clm_material_type=e},expression:"clm_material_type"}})],1)],1),a("v-row",[a("v-col",{staticStyle:{"padding-top":"0px","padding-bottom":"0px"},attrs:{xl:"12",md:"12",sm:"12",cols:"12","align-self":"center"}},[a("v-select",{attrs:{items:t.clm_material_name_items,"item-text":"clm_code_sub_name","item-value":"clm_code_sub_id",disabled:"","open-on-clear":"",label:"자재명"},model:{value:t.clm_material_name,callback:function(e){t.clm_material_name=e},expression:"clm_material_name"}})],1)],1),a("v-row",[a("v-col",{staticStyle:{"padding-top":"0px","padding-bottom":"0px"},attrs:{xl:"12",md:"12",sm:"12",cols:"12","align-self":"center"}},[a("v-select",{attrs:{items:t.clm_material_spec_items,"item-text":"clm_code_sub_name","item-value":"clm_code_sub_id","open-on-clear":"",disabled:"",label:"자재규격"},model:{value:t.clm_material_spec,callback:function(e){t.clm_material_spec=e},expression:"clm_material_spec"}})],1)],1)]],2),a("v-card-actions",{staticClass:"justify-end"},[""==this.clm_material_income_id&&"Y"==this.$router.app.$route.query.update_yn?[a("v-btn",{attrs:{text:""},on:{click:t.Save}},[t._v("등록")])]:t._e(),a("v-btn",{attrs:{text:""},on:{click:function(e){t.regmodal=!t.regmodal}}},[t._v("닫기")])],2)],1)],1)],1),a("v-row",{attrs:{justify:"center"}},[a("v-dialog",{attrs:{persistent:"","max-width":"950"},on:{keydown:function(e){if(!e.type.indexOf("key")&&t._k(e.keyCode,"esc",27,e.key,["Esc","Escape"]))return null;t.clientModal=!1}},model:{value:t.clientModal,callback:function(e){t.clientModal=e},expression:"clientModal"}},[a("v-card",[a("v-toolbar",{staticClass:"fontDefault",attrs:{color:"light-blue",dark:""}},[t._v("거래처 정보")]),a("v-card-text",{staticStyle:{"padding-top":"15px"}},[a("v-row",[a("v-col",{attrs:{xl:"9",md:"9",sm:"9",cols:"9","align-self":"center"}},[a("v-text-field",{attrs:{label:"거래처명"},on:{keypress:function(e){return!e.type.indexOf("key")&&t._k(e.keyCode,"enter",13,e.key,"Enter")?null:(e.preventDefault(),t.getclientModalList.apply(null,arguments))}},model:{value:t.clm_m_s_client_name,callback:function(e){t.clm_m_s_client_name=e},expression:"clm_m_s_client_name"}})],1),a("v-col",{attrs:{xl:"2",md:"2",sm:"2",cols:"2","align-self":"center"}},[a("v-btn",{attrs:{width:"100%",color:"primary"},on:{click:t.getclientModalList}},[t._v("검색")])],1)],1),a("v-row",{staticStyle:{"padding-left":"17px"}},[a("v-data-table",{staticClass:"elevation-1",staticStyle:{width:"95%"},attrs:{"no-data-text":"조회된 거래처가 없습니다.","footer-props":{"items-per-page-text":"페이지 당 행",pageText:"총 {2} 항목 중 {0}-{1}"},"mobile-breakpoint":"0",headers:t.client_headers,items:t.client_document,itemsPerPage:5,loading:t.loading},on:{"click:row":t.onClickClientModalRow},scopedSlots:t._u([{key:"items",fn:function(e){return[a("td",[t._v(t._s(e.item.num))]),a("td",[t._v(t._s(e.item.clm_client_name))]),a("td",[t._v(t._s(e.item.clm_client_tel))])]}}])})],1)],1),a("v-card-actions",{staticClass:"justify-end"},[a("v-btn",{attrs:{text:""},on:{click:function(e){t.clientModal=!1}}},[t._v("이전")])],1)],1)],1)],1)],2)],1)],1)],1)},i=[],c=(a("a481"),a("ac6a"),a("c5f6"),a("bc3a")),n=a.n(c),m=a("3d20"),o=a.n(m),s={data:function(){return{headers:[{text:"입고번호",align:"center",value:"clm_material_income_id",width:180},{text:"거래처명",align:"start",value:"clm_material_income_client_name",width:210},{text:"입고일자",align:"center",value:"clm_material_income_date",width:170},{text:"입고수량",align:"end",value:"clm_material_income_count",width:130},{text:"자재명",align:"start",value:"clm_material_name",width:280}],document:[],options:{multiSort:!1,sortBy:[],sortDesc:[],page:1,itemsPerPage:5},footerOptions:{"items-per-page-options":[5,10,25,50,100]},totalCount:0,loading:!1,regmodal:!1,schVal:"",save_yn:"N",list_page:10,loading_yn:"N",schVal2:"N",clm_material_income_id:"",clm_material_income_client_id:"",clm_material_income_client_name:"",clm_material_income_date:"",clm_material_income_count:"",clm_material_id:"",clm_material_name:"",clm_material_spec:"",clm_material_type:"",clm_material_type_items:[],clm_material_name_items:[],clm_material_spec_items:[],clientModal:!1,clm_m_s_client_name:"",client_document:[],client_headers:[{text:"순번",align:" d-none",value:"num",width:80},{text:"고객ID",align:" d-none",value:"clm_client_id"},{text:"거래처명",align:"start",value:"clm_client_name",width:200},{text:"사업자번호",align:"center",value:"clm_client_serial_no",width:200},{text:"대표자",align:"center",value:"clm_client_ceo",width:150}]}},mounted:function(){var t=this;n.a.get("/back/Popup/MaterialTypeList").then((function(e){t.clm_material_type_items=e.data})).catch((function(t){console.log(t)}));var e={clm_code_sub_id:"",clm_code_sub_name:"선택"};this.clm_material_type_items.unshift(e),this.getList()},watch:{options:{handler:function(){this.getList()},deep:!0}},methods:{updatePage:function(t){this.list_page=Number(t.itemsPerPage)},Regopen:function(){this.loading_yn="N";var t=new Date,e=t.getFullYear(),a=("0"+(t.getMonth()+1)).slice(-2),l=("0"+t.getDate()).slice(-2),i=e+"-"+a+"-"+l;this.clm_material_type="",this.clm_material_income_id="",this.clm_material_income_client_id="",this.clm_material_income_client_name="",this.clm_material_id="",this.clm_material_name="",this.clm_material_spec="",this.clm_material_income_date=i,this.clm_material_income_count=0,this.clm_material_name_items=[],this.clm_material_spec_items=[],this.regmodal=!0},Save:function(){var t=this;if("Y"!=this.loading_yn)return this.loading_yn="Y",""==this.clm_material_income_date?(o.a.fire({title:"입고일자를 선택해주시기 바랍니다.",icon:"error"}),void(this.loading_yn="N")):""==this.clm_material_income_client_id?(o.a.fire({title:"거래처를 선택해주시기 바랍니다.",icon:"error"}),void(this.loading_yn="N")):""==this.clm_material_type?(o.a.fire({title:"자재분류를 선택해주시기 바랍니다.",icon:"error"}),void(this.loading_yn="N")):""==this.clm_material_name?(o.a.fire({title:"자재명을 선택해주시기 바랍니다.",icon:"error"}),void(this.loading_yn="N")):""==this.clm_material_spec?(o.a.fire({title:"자재규격을 선택해주시기 바랍니다.",icon:"error"}),void(this.loading_yn="N")):""==this.clm_material_income_count||Number(this.clm_material_income_count)<=0||Number(this.clm_material_income_count)%1!=0?(o.a.fire({title:"입고수량이 정상적이지 않습니다.",icon:"error"}),void(this.loading_yn="N")):void n.a.post("/back/SCR001001/Reg",null,{params:{clm_material_income_date:this.clm_material_income_date,clm_material_income_client_id:this.clm_material_income_client_id,clm_material_id:this.clm_material_spec,clm_material_income_count:this.clm_material_income_count,clm_user:this.$session.get("user_id")}}).then((function(e){"200"==e.status?(t.loading_yn="N",o.a.fire({title:"정상적으로 등록 되었습니다.",icon:"success"})):t.loading_yn="N",t.regmodal=!1,t.getList()})).catch((function(t){console.log(t)}));o.a.fire({title:"저장중입니다.잠시만 기다려주세요.",icon:"error"})},getClientModal:function(){this.clm_m_s_client_name="",this.clientModal=!0,this.getclientModalList()},getclientModalList:function(){var t=this,e=n.a.get("/back/Popup/ClientList",{params:{search:this.clm_m_s_client_name,clm_company_key:this.$session.get("company_key")}}).then((function(a){e=a.data,setTimeout((function(){t.client_document=e}),500)}))},onClickClientModalRow:function(t,e){this.clm_material_income_client_name=e.item.clm_client_name,this.clm_material_income_client_id=e.item.clm_client_id,this.clientModal=!1},ChangeType:function(){var t=this;this.clm_material_name="",this.clm_material_spec="",n.a.get("/back/Popup/MaterialNameList",{params:{clm_material_type_id:this.clm_material_type}}).then((function(e){console.log(t.clm_material_type),t.clm_material_name_items=e.data})).catch((function(t){console.log(t)}))},ChangeName:function(){var t=this;this.clm_material_spec="",n.a.get("/back/Popup/MaterialSpecList",{params:{clm_material_type_id:this.clm_material_type,clm_material_name_id:this.clm_material_name}}).then((function(e){t.clm_material_spec_items=e.data})).catch((function(t){console.log(t)}))},getList:function(){var t=this,e=this;return this.loading=!0,this.save_yn="Y",new Promise((function(a,l){var i=t.options,c=i.sortBy,m=i.sortDesc,o=i.page,s=i.itemsPerPage,_="",r="";c.length>0?c.forEach((function(t,e){_=t,r=m[e]?"desc":"asc"})):(_="",r="");var d=n.a.get("/back/SCR001001/List",{params:{sortBy:_,sortDesc:r,search:t.schVal,page:o,PerPage:s,clm_company_key:t.$session.get("company_key")}}).then((function(a){d=a.data,setTimeout((function(){e.loading=!1,t.save_yn="N",t.document=d}),1e3)}))}))},onClickRow:function(t,e){this.clm_material_income_id=e.item.clm_material_income_id,this.clm_material_income_client_id=e.item.clm_material_income_client_id,this.clm_material_income_client_name=e.item.clm_material_income_client_name,this.clm_material_income_date=e.item.clm_material_income_date,this.clm_material_income_count=Number(String(e.item.clm_material_income_count).replace(/,/gi,"")),this.clm_material_id=e.item.clm_material_id,this.clm_material_type=e.item.material_type,this.ChangeType(),this.clm_material_name=e.item.material_name,this.ChangeName(),this.clm_material_spec=e.item.clm_material_id,this.regmodal=!0}}},_=s,r=(a("8820"),a("3e05"),a("0c7c")),d=a("6544"),p=a.n(d),u=a("8336"),g=a("b0af"),v=a("99d9"),f=a("62ad"),h=a("8fea"),x=a("169a"),y=a("0e8f"),b=a("a722"),k=a("0fd9"),w=a("b974"),C=a("8654"),S=a("71d9"),N=Object(r["a"])(_,l,i,!1,null,"5cc5ac30",null);e["default"]=N.exports;p()(N,{VBtn:u["a"],VCard:g["a"],VCardActions:v["a"],VCardText:v["b"],VCardTitle:v["c"],VCol:f["a"],VDataTable:h["a"],VDialog:x["a"],VFlex:y["a"],VLayout:b["a"],VRow:k["a"],VSelect:w["a"],VTextField:C["a"],VToolbar:S["a"]})},8820:function(t,e,a){"use strict";a("a75d")},a722:function(t,e,a){"use strict";a("20f6");var l=a("e8f2");e["a"]=Object(l["a"])("layout")},a75d:function(t,e,a){}}]);
//# sourceMappingURL=chunk-06cb1c0a.fc188a73.js.map