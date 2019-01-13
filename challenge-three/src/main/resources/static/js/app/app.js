'use strict';

function errorCallback(reason) {
    var message = null;

    if (reason.data.errors) {
        message = "Errors:\n";
        reason.data.errors.forEach(function (item) {
            message += item.field + " -> " + item.defaultMessage + " | valor: " + item.rejectedValue + "\n";
        });
    }
    else  {
        message = reason.data.message;
    }
    
    alert(message);
}

var main = angular.module('challenge.three', ['ngRoute', 'ngSanitize']);

main.config(["$routeProvider", function ($routeProvider) {
    $routeProvider.when("/", {
        templateUrl: "views/default.controller.html",
        controller: "DefaultController"
    }).when("/personConfection", {
        templateUrl: "views/person.confection.controller.html",
        controller: "PersonConfectionController"
    });
}]);