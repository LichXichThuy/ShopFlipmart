$(document).ready(function (){
    $('#btn-change-pass').click(function (e){
        e.preventDefault();
        const curPass = $('#curPassword').val();
        const newPass = $('#newPassword').val();
        const renewPass = $('#renewPassword').val();

        if (newPass.localeCompare(renewPass)){
            alert("You must enter new password equal repeat new password")
            window.location.reload()
        }else {
            $.ajax({
                url: 'http://localhost:8082/api/change/password',
                method: 'post',
                data:{
                    'curPassword': curPass,
                    'newPassword': newPass
                }
            }).done(function (data){
                if (data.data){
                    alert("Change password success")
                    window.location.reload();
                }
                else {
                    alert(data.desc);
                    window.location.reload();
                }
            })
        }
    });

    $('#btn-update-information').click(function (e){
        e.preventDefault();
        var name = $('#name').val();
        var phone = $('#phone').val();
        var address = $('#address').val();
        var addressCom = $('#address-comment').val();

        $.ajax({
            url: 'http://localhost:8082/api/change/infor',
            method: 'post',
            data: {
                'name': name,
                'phone': phone,
                'address': address,
                'addressCom': addressCom
            }
        }).done(function (res){
            if (res.data){
                alert("Change success");
                window.location.reload();
            }else {
                alert("Change fail");
                window.location.reload();
            }
        })
    })
})