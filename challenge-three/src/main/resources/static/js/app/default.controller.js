'use strict';

function DefaultController($scope, $queryService) {
    $queryService.setup($scope, "/personSave", 'rest/pessoa/save', 'rest/pessoas');
}

main.controller("DefaultController", [ "$scope", "$queryService", DefaultController]);