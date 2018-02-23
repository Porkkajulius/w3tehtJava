'use strict'
var app = angular.module('elokuvaApp', []);
app.controller('ElokuvaController', function($scope, $http) {
    $http.get("/api/elokuva/elokuvat")
    .then(function(response) {
        $scope.elokuvat = response.data;
        console.log($scope.elokuvat);
    });
    
    //lisää elokuva
    $scope.luoElokuva = function($scope, $http) {
    	$http.get("/api/elokuva/uusi")
    	.then(function(response) {
            $scope.uusiElokuva = response.data;
            console.log($scope.luoElokuva);


    	});
    }
 
    
    //scope.poistaElokuva luodaan tässä eli poistaElokuva jota html sivu kutsuu ja function(elokuvaID) tulee html:stä
    $scope.poistaElokuva = function(elokuvaID) {
        console.log(elokuvaID);
        //http.get kutsuu tuon nimistä sivua joka on määritettynä ApiControlleriin +elokuvaID on elokuvan ID joka tulee html:stä
    	$http.get("/api/elokuva/poista/"+elokuvaID)
    	.then(function(response) {
    		 console.log(response);


    	});
    }
    	
});

