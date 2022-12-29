angular.module('orderDetails',[]).controller('odc',function ($scope,$http) {

    const contextPath = 'http://localhost:8080/user';

    $scope.loadOrderDetails = function () {
        const queryString = window.location.search;
        const urlParams = new URLSearchParams(queryString);
        const id = urlParams.get('id')
        console.log(id);

        $http.get(contextPath + '/orderDetails/' + id)
            .then(function (response){
                $scope.productsInOrderList = response.data;
                console.log($scope.productsInOrderList)
            });
    }
    $scope.loadOrderDetails();
});