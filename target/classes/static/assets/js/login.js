$(document).ready(function (){
    $('#btn-login').click(function (e){
        e.preventDefault();
        const email = $('#exampleInputEmail').val();
        const password = $('#exampleInputPassword1').val();

        $.ajax({
            url: 'http://localhost:8082/login',
            method: 'post',
            data:{
                'email': email,
                'password': password
            }
        }).done(function (data){
            if (data.data == 1){
                window.location.replace('http://localhost:8082/')
            }
        })
    });
    $('#btn-signup').click(function (e){
        e.preventDefault();
        const email = $('#email').val();
        const name = $('#name').val();
        const phone = $('#phone').val();
        const password = $('#password').val();
        const cpassword = $('#confirm-password').val();

        if (email=='' || name=='' || phone=='' || password=='' || cpassword==''){
            alert("You must enter all the information")
            window.location.reload()
        }else if (!email.includes('@')){
            alert("You must enter email contain @")
            window.location.reload()
        }else if (password.localeCompare(cpassword)){
            alert("You must enter password equal confirm password")
            window.location.reload()
        }else {
            $.ajax({
                url: 'http://localhost:8082/signup',
                method: 'post',
                data:{
                    'email': email,
                    'name': name,
                    'phone': phone,
                    'password': password
                }
            }).done(function (res){
                if (res.data){
                    alert("Signup success!");
                    window.location.replace('http://localhost:8082')
                }else {
                    alert("Email already exists")
                    window.location.reload()
                }
            })
        }
    })
})