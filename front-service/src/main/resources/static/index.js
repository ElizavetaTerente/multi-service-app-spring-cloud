angular.module('app',['ngStorage']).controller('productsController',function ($scope,$http,$localStorage) {
    $scope.isAdmin = false;

    $scope.AuthOrSigUp = function(action) {
        if(action === "Auth") {
            $scope.tryToAuth();
            console.log("trying to auth");
        }
        if(action === "SignUp"){
            $scope.tryToSignUp();
            console.log("trying to sign up");
        }
    }

    $scope.tryToAuth = function(){
        $http.post('http://localhost:8060/auth',$scope.user)
      //  $http.post('http://localhost:5555/auth',$scope.user)
            .then(function successCallback(response){
                if(response.data.token){
                    console.log(response);
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.user = {username : $scope.user.username,token: response.data.token};
                    $scope.user.username = null;
                    $scope.user.password = null;
                    document.getElementById("login").style.visibility = "hidden";
                    document.getElementById("logout").style.visibility = "visible";
                    document.getElementById("loginInfo").innerText = "[U logged in as " + $localStorage.user.username + " ]";
                    if(JSON.parse(atob($localStorage.user.token.split('.')[1])).roles.includes("ADMIN")) {
                        document.getElementById("addButton").style.display = "block";
                       // document.getElementById("deleteButton").style.display = "none";
                        $scope.isAdmin = true;

                    }
                    $scope.loadCart();
                }
            },function errorCallback(response){
                console.log("error");
                alert("false login or password");
            });
    };

    $scope.tryToSignUp = function(){
        $http.post('http://localhost:8060/signUp',$scope.user)
            //  $http.post('http://localhost:5555/signUp',$scope.user)
            .then(function successCallback(response){
                if(response.data.token){
                    console.log(response);
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.user = {username : $scope.user.username,token: response.data.token};
                    $scope.user.username = null;
                    $scope.user.password = null;
                    document.getElementById("login").style.visibility = "hidden";
                    document.getElementById("logout").style.visibility = "visible";
                    document.getElementById("loginInfo").innerText = "[U logged in as " + $localStorage.user.username + " ]";
                    if(JSON.parse(atob($localStorage.user.token.split('.')[1])).roles.includes("ADMIN")) {
                        document.getElementById("addButton").style.display = "block";
                        document.getElementById("signUpButton").style.display = "none";
                        $scope.isAdmin = true;

                    }
                    $scope.loadCart();
                }
            },function errorCallback(response){
                    alert("this username already exists.Please try new one");
                console.log("error");
            });
    };

    $scope.tryToLogout = function(){
        console.log("in log out");
        $scope.clearUser();
        $scope.user = null;
        document.getElementById("login").style.visibility = "visible";
        document.getElementById("logout").style.visibility = "hidden";
        document.getElementById("loginInfo").innerText = "[U are not logged in]";
        document.getElementById("addButton").style.display = "none";
        $scope.isAdmin = false;
        document.getElementById("signUpButton").style.display = "block";
        $scope.loadCart();
    };

    $scope.clearUser = function(){
        delete $localStorage.user;
        $http.defaults.headers.common.Authorization = '';
    };

    $scope.isUserLoggedIn = function(){
        if($localStorage.user){
            return true;
        }else {
            return false;
        }
    };

    if ($localStorage.user) {
        console.log($localStorage.user);
            try {
                let jwt = $localStorage.user.token;
                let payload = JSON.parse(atob(jwt.split('.')[1]));
                let currentTime = parseInt(new Date().getTime() / 1000);
                if (currentTime > payload.exp) {
                    console.log("Token is exired!!!");
                    delete $localStorage.user;
                    $http.default.headers.common.Authorization = '';
                }
            } catch (e) {
            }

            $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.user.token;
        document.getElementById("login").style.visibility = "hidden";
        document.getElementById("logout").style.visibility = "visible";
        document.getElementById("loginInfo").innerText = "[U logged in as " + $localStorage.user.username + " ]";

    }else{
        document.getElementById("login").style.visibility = "visible";
        document.getElementById("logout").style.visibility = "hidden";
        document.getElementById("loginInfo").innerText = "[U are not logged in]";
            console.log("nobody is logged in");
        }

    $scope.loadProducts = function () {
        $http.get('http://localhost:8090/products')
     //   $http.get('http://localhost:5555/products')
            .then(function (response){
                $scope.productsList = response.data;
                console.log($scope.productsList);
            });
    }

    $scope.addNewProduct = function(){
        $http.post('http://localhost:8090/products/add/'+ $scope.newProduct.title +'/'+ $scope.newProduct.cost)
      //  $http.post('http://localhost:5555/products/add/'+ $scope.newProduct.title +'/'+ $scope.newProduct.cost)
            .then(function (response){
                $scope.loadProducts();
            });
    }

    $scope.deleteProduct = function(title){
        $http.delete('http://localhost:8090/products/delete/' + title)
       // $http.delete('http://localhost:5555/products/delete/' + title)
            .then(function (response){
               console.log(response.data);
                $scope.loadProducts();
            });
    }

    $scope.loadCart = function(){
        console.log($scope.isUserLoggedIn());
        if($scope.isUserLoggedIn()) {
            console.log("sending request to load cart");
            $http.get('http://localhost:9000/cart/' + $localStorage.user.username)
            //$http.get('http://localhost:5555/cart/' + $localStorage.user.username)
                .then(function (response) {
                        $scope.cartItems = response.data;
                        console.log($scope.cartItems);
                });
        }else{
            delete $scope.cartItems;
        }
    }

    $scope.addToCart = function(title){
        if(!$scope.isUserLoggedIn()){
            alert("Log in to add smth in cart!");
        }else{
            $http.post('http://localhost:9000/cart/' +$localStorage.user.username + '/' + title)
           // $http.post('http://localhost:5555/cart/' +$localStorage.user.username + '/' + title)
                .then(function (response) {
                    $scope.loadCart();
                });
        }
    }

    $scope.deleteItem = function(title){
        $http.delete('http://localhost:9000/cart/' +$localStorage.user.username + '/' + title)
        //$http.delete('http://localhost:5555/cart/' +$localStorage.user.username + '/' + title)
            .then(function (response){
                $scope.loadCart();
                });
    }
    $scope.changeQuantity = function(title,number){
        $http.post('http://localhost:9000/cart/' +$localStorage.user.username + '/' + title +'/' + number)
        //$http.post('http://localhost:5555/cart/' +$localStorage.user.username + '/' + title +'/' + number)
            .then(function (response){
                $scope.loadCart();
            });
    }

    $scope.loadProducts();
    $scope.loadCart();
    document.getElementById("addButton").style.display = "none";

});