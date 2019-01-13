'use strict';

function DefaultController($scope, $queryService) {
    $queryService.setup($scope, "/personConfection", 'rest/pessoa/remove/', 'rest/pessoas');
}

main.controller("DefaultController", [ "$scope", "$queryService", DefaultController]);