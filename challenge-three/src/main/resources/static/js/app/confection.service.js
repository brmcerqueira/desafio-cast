'use strict';

function ConfectionService($http, keyService) {
    this.$http = $http;
    this.keyService = keyService;
}

ConfectionService.prototype.setup = function (scope, urlLoad, urlSave, init, load, prepareToSave) {
    var _this = this;
    var key = this.keyService.key;
    this.keyService.key = null;
    init();
    if (key) {
        this.$http.get(urlLoad + key).then(function (response) {
            load(response.data);
        }, errorCallback);
    }
    scope.save = function () {
        var entity = prepareToSave();

        if (key) {
            entity.id = key;
        }

        _this.$http.put(urlSave, entity).then(function () {
            alert("salvo!");
            if (!key) {
                init();
            }
        }, errorCallback);
    };
};

main.service('$confectionService', ['$http', "$keyService", ConfectionService]);