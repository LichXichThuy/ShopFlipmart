function deletewishlist(id){
    event.preventDefault();
    $.ajax({
        method: 'post',
        url: `http://localhost:8082/api/wishlist/delete`,
        data:{
            'productId': id
        },
        success: function (res){
            window.location.reload()
            // let body = "";
            // $.each(res, function (i, product){
            //     body +='<tr>';
            //     body +=     '<td className="col-md-2"><img src="' +product.image+'" alt="imga"></td>';
            //     body +=     '<td className="col-md-7">';
            //     body +=         '<div className="product-name"><a href="'+'http://localhost:8082/product?productID='+product.id+'">'+product.name+'</a></div>';
            //     body +=         '<div className="rating rateit-small" data-rateit-value="'+product.voteEver/1+'"></div>';
            //     body +=         '<div>';
            //     body +=             '<span className="price"></span><span className="price">$'+product.price * (100 - product.discount) / 100+'</span><span className="price-strike"></span><span className="price-strike">$'+product.price+'</span>';
            //     body +=         '</div>';
            //     body +=     '</td>';
            //     body +=     '<td className="col-md-2">';
            //     body +=         '<button onclick="addCart('+product.id+')" className="btn btn-primary cart-btn" type="button">Add to cart</button>';
            //     body +=     '</td>';
            //     body +=     '<td className="col-md-1 close-btn">';
            //     body +=         '<button onclick="deletewishlist('+product.id+')" className=""><i className="fa fa-times"></i></button>';
            //     body +=     '</td>';
            //     body +='</tr>';
            // })
            // $('#body').empty();
            // $('#body').append(body);
        }
    })
}