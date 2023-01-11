angular.module('orders',[]).controller('oc',function ($scope,$http) {

    const contextPath = 'http://localhost:8080/user';

    $scope.loadOrders = function () {
        $http.get(contextPath + '/orders')
            .then(function (response){
                $scope.ordersList = response.data;
                console.log($scope.ordersList)
            });
    }

    $scope.orderDetails = function(orderId) {
        window.location.href = "http://localhost:8080/user/orderDetails.html?id="+orderId;
    }



    $scope.loadOrders();

});