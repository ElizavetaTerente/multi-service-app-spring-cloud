angular.module('app1',[]).controller('uc',function ($scope,$http) {

    const contextPath = 'http://localhost:8080/admin';

    $scope.loadUsers = function () {
        $http.get(contextPath + '/users')
            .then(function (response){
                $scope.usersList = response.data;
                console.log($scope.usersList)
            });
    }

    $scope.loadUsers();
});