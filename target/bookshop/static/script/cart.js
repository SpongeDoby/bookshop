// function editCart(cartItemId,buyCount){
//     window.location.href="cart.do?operateType=editCart&cartItemId="+cartItemId+"&buyCount="+buyCount;
// }

window.onload=function (){
    let vue = new Vue({
        el:"#cart_div",
        data:{
            cart:{},
            disabled:false
        },
        methods:{
            getCart:function (){
                axios({
                    method:"POST",
                    url:"cart.do",
                    params:{
                        operateType:'getCart'
                    },
                    responseType: 'json'
                }).then(function (value) {
                        let CartData = value.data;
                        vue.cart = CartData;
                }).catch(function (reason) {
                });
            },
            editCart:function (cartItemId,buyCount) {
                axios({
                    method:"POST",
                    url:"cart.do",
                    params:{
                        operateType:'editCart',
                        cartId:cartItemId,
                        buyCount:buyCount
                    }
                }).then(function (value) {

                    console.log(value);

                    if(buyCount===0){
                        vue.disabled=true;
                        buyCount=1;
                    }else {
                        vue.disabled=false;
                        vue.getCart();
                    }
                }).catch(function (reason) {

                });


            }
        }
        ,
        mounted:function (){
                this.getCart();
            }

    });
}