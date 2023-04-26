function addreview(id){
    event.preventDefault();
    var radios = document.getElementsByName('quality');
    var content = $('#exampleInputReview').val();
    let star;

    for (var i = 0, length = radios.length; i < length; i++) {
        if (radios[i].checked) {
            star = radios[i].value;
            break;
        }
    }
    $.ajax({
        url: "http://localhost:8082/api/review/add",
        method: 'post',
        data: {
            'id': id,
            'star': star,
            'content': content
        }
    }).done(function (res){
        if (res.data){
            alert("Add review success")
            window.location.reload();
        }else {
            alert(" Add review fail, please try again")
            window.location.reload();
        }
    })
}

function addquestion(id){
    event.preventDefault();
    var content = $('#exampleInputTag').val();

    $.ajax({
        method: 'post',
        url: `http://localhost:8082/api/question/add`,
        data:{
            'productId': id,
            'content': content
        }
    }).done(function (res){
        if (res.data == 1){
            alert("Make a question succesfull")
        } else{
            alert("Error, make a question again")
        }
        window.location.reload();
    })
}