
function addCart(id){
    let quanty = document.getElementById("quanty")
    let numQuanty = "";
    if(quanty !== null){
        numQuanty = parseInt(quanty.value);
    }
    $.ajax({
        url: "http://localhost:8082/api/cart/add" + id + "?quanty=" + numQuanty,
        async: false,
        method: 'post',
        data: $(this).serialize(),
        success:function (res){
            let totalQuantyCart = 0
            let totalPriceCart = 0
            let cartDrop = ""
            $('#cart').empty();
            cartDrop += '<li>';
                $.each(res, function (i, cart){
                    console.log(cart.image);
                    cartDrop += '<div class="cart-item product-summary">';
                    cartDrop +=     '<div class="row">';
                    cartDrop +=         '<div class="col-xs-4">';
                    cartDrop +=             '<div className="image"><a href="'+'http://localhost:8082/product?productID='+cart.product.id+'">'+'<img src="'+cart.image+'"'+' alt="">'+'</a></div>';
                    cartDrop +=         '</div>';
                    cartDrop +=         '<div className="col-xs-7">';
                    cartDrop +=             '<span className="name"><a href="'+'http://localhost:8082/product?productID='+'">'+cart.product.name+'</a></span>';
                    cartDrop +=             '<div className="price">$'+cart.product.price+'</div>';
                    cartDrop +=             '<div className="count"></div>';
                    cartDrop +=         '</div>';
                    cartDrop +=         '<div className="col-xs-1 action"><a href="#"><i className="fa fa-trash"></i></a></div>';
                    cartDrop +=     '</div>';
                    cartDrop += '</div>';
                })
            cartDrop += '<div class="clearfix"></div>';
            cartDrop += '<hr>';
            cartDrop += '<div class="clearfix cart-total">';
            cartDrop +=     '<div class="pull-right"> <span class="text">Sub Total :</span><span class=\'price\'>$600.00</span> </div>';
            cartDrop +=     '<div class="clearfix"></div>';
            cartDrop +=     '<a href="checkout.html" class="btn btn-upper btn-primary btn-block m-t-20">Checkout</a>';
            cartDrop += '</div>';
            cartDrop += '</li>';

            $('#cart').append(cartDrop);
        }
    });
}