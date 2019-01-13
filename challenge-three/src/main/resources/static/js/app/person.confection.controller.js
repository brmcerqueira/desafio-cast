'use strict';

function PersonConfectionController($scope, $confectionService) {
    $confectionService.setup($scope, 'rest/pessoa/', 'rest/pessoa/save', function () {
        $scope.name = null;
        $scope.street = null;
        $scope.number = null;
        $scope.neighborhood = null;
        $scope.city = null;
        $scope.state = null;
        $scope.cellphone = null;
        $scope.phone = null;
    }, function (data) {
        $scope.name = data.name;
        $scope.street = data.street;
        $scope.number = data.number;
        $scope.neighborhood = data.neighborhood;
        $scope.city = data.city;
        $scope.state = data.state;
        $scope.cellphone = data.cellphone;
        $scope.phone = data.phone;
    }, function () {
        return {
            name: $scope.name,
            street: $scope.street,
            number: $scope.number,
            neighborhood: $scope.neighborhood,
            city: $scope.city,
            state: $scope.state,
            cellphone: $scope.cellphone,
            phone: $scope.phone
        };
    });
}

main.controller('PersonConfectionController', ['$scope', '$confectionService', PersonConfectionController]);