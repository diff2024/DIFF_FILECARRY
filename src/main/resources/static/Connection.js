
function fnc_barcode_read(BarcodeID, MES_GUBUN){
    document.getElementById('hdn_barcode_id').value = BarcodeID;

    /*
    axios.get(url_gubun,{
        params: {
            barcode_id: BarcodeID
        }
    })
    .then(response => {
        var items = response.data;
        //console.log(response);
        var material_order_id = items.material_order_id;
        var material_order_seq = items.material_order_seq;
        var material_order_lot = items.material_order_lot;
        var material_id = items.material_id;
        var material_name = items.material_name;
        var material_order_count = items.material_order_count;
        var material_income_date = items.material_income_date;
        var material_income_location = items.material_income_location;
        var material_income_location_name = items.material_income_location_name;
        var material_income_yn = items.material_income_yn;
        var material_income_status = items.material_income_status;
        
        //alert('> material_order_id : ' + material_order_id + ' maerial_order_seq : ' + material_order_seq + ' material_order_lot : ' + material_order_lot);
        //alert('> material_order_lot : ' + material_order_lot + ' | material_id : ' + material_id + ' | material_name : ' + material_name + ' | material_order_count : ' + material_order_count + ' | material_income_date : ' + material_income_date);
    });
    */
}