'use strict';

function errorCallback(reason) {
    alert(reason.data.exceptionMessage);
}

var main = angular.module('challenge.three', ['ngRoute', 'ngSanitize']);

main.config(["$routeProvider", function ($routeProvider) {
    $routeProvider.when("/", {
        templateUrl: "views/default.controller.html",
        controller: "DefaultController"
    });
}]);