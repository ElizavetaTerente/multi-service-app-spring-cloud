angular.module('app',[]).controller('productsController',function ($scope,$http) {

    const contextPath = 'http://localhost:9000';

    $scope.loadProducts = function () {

        $http.get(contextPath+'/products')   //ПОСЫЛАЮ ЗАПРОС
            .then(function (response){
                $scope.productsList = response.data;
            });
    }
/*
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

 */

    $scope.loadProducts();
});