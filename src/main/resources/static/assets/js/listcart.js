
function addCart(id){
    event.preventDefault();
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
            cartDrop += '<li>';
                $.each(res, function (i, cart){
                    cartDrop += '<div class="cart-item product-summary">';
                    cartDrop +=     '<div class="row">';
                    cartDrop +=         '<div><span className="count" >'+cart.amount+'</span></div>';
                    cartDrop +=         '<div class="col-xs-4">';
                    cartDrop +=             '<div className="image"><a href="'+'http://localhost:8082/product?productID='+cart.product.id+'">'+'<img src="'+cart.image+'"'+' alt="">'+'</a></div>';
                    cartDrop +=         '</div>';
                    cartDrop +=         '<div className="col-xs-7">';
                    cartDrop +=             '<span className="name"><a href="'+'http://localhost:8082/product?productID='+cart.product.id+'">'+cart.product.name+'</a></span>';
                    cartDrop +=             '<div className="price">$'+cart.product.price * (100 - cart.product.discount) / 100+'</div>';
                    cartDrop +=             '<div className="count"></div>';
                    cartDrop +=         '</div>';
                    cartDrop +=         '<div className="col-xs-1 action"><a onclick="deleteCart('+i+')"><i className="fa fa-trash"></i></a></div>';
                    cartDrop +=     '</div>';
                    cartDrop += '</div>';

                    totalQuantyCart += cart.amount;
                    totalPriceCart += cart.totalPrice;
                })
            cartDrop += '<div class="clearfix"></div>';
            cartDrop += '<hr>';
            cartDrop += '<div class="clearfix cart-total">';
            cartDrop +=     '<div class="pull-right"> <span class="text">Sub Total :</span><span class=\'price\'>$'+totalPriceCart+'</span> </div>';
            cartDrop +=     '<div class="clearfix"></div>';
            cartDrop +=     '<a href="/checkout" class="btn btn-upper btn-primary btn-block m-t-20">Checkout</a>';
            cartDrop += '</div>';
            cartDrop += '</li>';


            $('#cart').empty();
            $('#cart').append(cartDrop);

            $('.basket-item-count').empty();
            $('.basket-item-count').append('<span className="count" >'+totalQuantyCart+'</span>');

            $('.total-price-basket').empty();
            $('.total-price-basket').append('<span class="lbl">cart -</span> <span class="total-price"> <span class="sign"></span><span class="value">$'+totalPriceCart+'</span> </span>');
        }
    });
};

function deleteCart(id){
    $.ajax({
        url: "http://localhost:8082/api/cart/delete" + id,
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
                cartDrop += '<div class="cart-item product-summary">';
                cartDrop +=     '<div class="row">';
                cartDrop +=         '<div><span className="count" >'+cart.amount+'</span></div>';
                cartDrop +=         '<div class="col-xs-4">';
                cartDrop +=             '<div className="image"><a href="'+'http://localhost:8082/product?productID='+cart.product.id+'">'+'<img src="'+cart.image+'"'+' alt="">'+'</a></div>';
                cartDrop +=         '</div>';
                cartDrop +=         '<div className="col-xs-7">';
                cartDrop +=             '<span className="name"><a href="'+'http://localhost:8082/product?productID='+'">'+cart.product.name+'</a></span>';
                cartDrop +=             '<div className="price">$'+cart.product.price * (100 - cart.product.discount) / 100+'</div>';
                cartDrop +=             '<div className="count"></div>';
                cartDrop +=         '</div>';
                cartDrop +=         '<div className="col-xs-1 action"><a onclick="deleteCart('+i+')"><i className="fa fa-trash"></i></a></div>';
                cartDrop +=     '</div>';
                cartDrop += '</div>';

                totalQuantyCart += cart.amount;
                totalPriceCart += cart.totalPrice;
            })
            cartDrop += '<div class="clearfix"></div>';
            cartDrop += '<hr>';
            cartDrop += '<div class="clearfix cart-total">';
            cartDrop +=     '<div class="pull-right"> <span class="text">Sub Total :</span><span class=\'price\'>$'+totalPriceCart+'</span> </div>';
            cartDrop +=     '<div class="clearfix"></div>';
            cartDrop +=     '<a href="/checkout" class="btn btn-upper btn-primary btn-block m-t-20">Checkout</a>';
            cartDrop += '</div>';
            cartDrop += '</li>';

            $('#cart').append(cartDrop);

            $('.basket-item-count').empty();
            $('.basket-item-count').append('<span className="count" >'+totalQuantyCart+'</span>');

            $('.total-price-basket').empty();
            $('.total-price-basket').append('<span class="lbl">cart -</span> <span class="total-price"> <span class="sign"></span><span class="value">$'+totalPriceCart+'</span> </span>');
        }
    });
};

function deleteCa(id){
    $.ajax({
        url: "http://localhost:8082/api/cart/delete" + id,
        async: false,
        method: 'post',
        data: $(this).serialize(),
    }).done(function(res){
        window.location.reload();
    })
}