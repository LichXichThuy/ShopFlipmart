
$(document).ready(function (){
    $('#checkAddress').click(function (e){
        e.preventDefault();
        let address ="";
        var different = document.getElementById("different").checked;
        if (different) {
            address = $('#address').val();
            if (address == "") {
                alert("Please input shipping address!");
                window.location.reload();
            }
        }
        if ((different && address != "") || !different){
            $.ajax({
                url: "http://localhost:8082/api/cart/checkout",
                method: 'post',
                data:{
                    'address': address
                }
            }).done(function (res){
                if (res.data){
                    alert(res.desc);
                    window.location.replace("http://localhost:8082");
                }else {
                    alert(res.desc);
                    window.location.reload();
                }
            })
        }
    })
})