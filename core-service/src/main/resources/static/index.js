angular.module('app',[]).controller('productsController',function ($scope,$http) {

    const contextPath = 'http://localhost:8080';

    //return all data

    $scope.loadProducts = function () {
        $http.get(contextPath + '/products')
            .then(function (response){
                $scope.productsList = response.data;
            });
    }

    $scope.loadCart = function () {
        var cartTable = document.getElementById("cartTable");
        cartTable.style.display = "none";
        $http.get(contextPath + '/cart')
            .then(function (response){
                $scope.cartItemsList = response.data;
                if($scope.cartItemsList.length == 0) {
                    document.getElementById("cartIsEmpty").innerHTML = "Your card is empty";
                }else{
                    document.getElementById("cartIsEmpty").innerHTML = "Your card :";
                    cartTable.style.display = "block";
                }
            });
    }

    //operations with products

    $scope.addNewProduct = function(){
        $http.post(contextPath + '/products/addNew/'+ $scope.newProduct.title +'/'+ $scope.newProduct.cost)
            .then(function (response){
                $scope.loadProducts();
            });
    }

    $scope.deleteProduct = function(title){
        $http.delete(contextPath + '/products/delete/' + title)
            .then(function (response){
                $scope.loadProducts();
            });
    }

    //operations with cart items

    $scope.addToCart = function(title){
        $http.post(contextPath + '/cart/' + title)
            .then(function (response){
                $scope.loadCart();
            });
    }

    $scope.deleteItemFromCart = function(title){
        $http.delete(contextPath + '/cart/' + title)
            .then(function (response){
                $scope.loadCart();
            });
    }

    $scope.placeOrder = function(){
        $http.post(contextPath + '/placeOrder')
            .then(function (response){
                $scope.loadCart();
            });
        document.documentElement.scrollTop = 0;
    }

    $scope.loadProducts();
    $scope.loadCart();
});