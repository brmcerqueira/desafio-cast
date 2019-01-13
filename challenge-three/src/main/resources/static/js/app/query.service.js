'use strict';

function QueryService($http, $location, $keyService) {
    this.$http = $http;
    this.$location = $location;
    this.$keyService = $keyService;
}

QueryService.prototype.setup = function (scope, editRoute, removeRoute, queryRoute) {
    var _this = this;
    scope.source = [];
    scope.edit = function (id) {
        _this.$keyService.key = id;
        _this.$location.path(editRoute);
    };
    scope.remove = function (id) {
        if (confirm("Deseja excluir esse item?")) {
            _this.$http.delete(removeRoute + id).then(function () {
                alert("excluido!");
                updateSource();
            }, errorCallback);
        }
    };
    var updateSource = function () {
        _this.$http.get(queryRoute, {}).then(function (response) {
            scope.source = response.data;
        }, errorCallback);
    };
    updateSource();
};

main.service('$queryService', ['$http', '$location', "$keyService", QueryService]);